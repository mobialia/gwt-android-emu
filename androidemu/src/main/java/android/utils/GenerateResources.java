package android.utils;

import android.view.MenuItem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class GenerateResources {
	private static String FILE_HEADER = "/** FILE GENERATED AUTOMATICALLY BY GWT_ANDROID_EMU'S GenerateResources: DO NOT EDIT MANUALLY */\n";
	private Pattern idsPattern = Pattern.compile(".*@" + Pattern.quote("+") + "id/([a-zA-Z0-9_]+).*");
	private Pattern langPattern = Pattern.compile(".*/values-([a-zA-Z]{2})/.*");

	private Pattern layoutPattern = Pattern.compile(".*public [a-zA-Z0-9_]+ ([a-zA-Z0-9_]+)" + Pattern.quote("(") + Pattern.quote(")") + ".*");

	String packageName;

	ArrayList<String> idsInClass = new ArrayList<String>();

	ArrayList<String> drawableIdsInClass = new ArrayList<String>();

	StringBuffer menuClassSB = new StringBuffer();
	ArrayList<String> menuIdsInClass = new ArrayList<String>();

	HashMap<String, StringBuffer> stringPropertiesSBMap = new HashMap<String, StringBuffer>();
	ArrayList<String> stringIdsInClass = new ArrayList<String>();
	StringBuffer stringClassSB = new StringBuffer();

	HashMap<String, StringBuffer> arrayPropertiesSBMap = new HashMap<String, StringBuffer>();
	ArrayList<String> arrayIdsInClass = new ArrayList<String>();
	StringBuffer arrayClassSB = new StringBuffer();

	ArrayList<String> layouts = new ArrayList<String>();

	HashMap<String, String> colors = new HashMap<String, String>();

	public GenerateResources(String packageName) {
		this.packageName = packageName;

		menuClassSB.append(FILE_HEADER + "package " + packageName + ";\n\nimport android.view.Menu;\nimport android.view.MenuItem;\n\npublic class Menus {\n");
		stringClassSB.append(FILE_HEADER + "package " + packageName + ";\n\nimport com.google.gwt.i18n.client.Constants;\n\npublic interface Strings extends Constants {\n");
		arrayClassSB.append(FILE_HEADER + "package " + packageName + ";\n\nimport com.google.gwt.i18n.client.Constants;\n\npublic interface Arrays extends Constants {\n");
	}

	public void getIdsFromFile(String fileName) {

		System.out.println("Getting Ids from file " + fileName + "...");

		try {
			File file = new File(fileName);

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				Matcher matcher = idsPattern.matcher(line);

				String id;

				while (matcher.find()) {
					id = matcher.group(1);

					boolean found = false;
					for (String s : idsInClass) {
						if (s.equals(id)) {
							found = true;
							break;
						}
					}

					if (!found) {
						System.out.println("Adding id " + id);
						idsInClass.add(id);
					}
				}
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processLangFile(String fileName) {
		Matcher matcher = langPattern.matcher(fileName);
		String lang = null;

		if (matcher.find()) {
			lang = matcher.group(1);
		}

		System.out.println("Processing file " + fileName + "(lang " + lang + ")...");

		if (!stringPropertiesSBMap.containsKey(lang)) {
			stringPropertiesSBMap.put(lang, new StringBuffer());
		}
		StringBuffer stringPropertiesSB = stringPropertiesSBMap.get(lang);

		if (!arrayPropertiesSBMap.containsKey(lang)) {
			arrayPropertiesSBMap.put(lang, new StringBuffer());
		}
		StringBuffer arrayPropertiesSB = arrayPropertiesSBMap.get(lang);

		try {
			File xmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("string");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String key = eElement.getAttribute("name");
					String value = eElement.getTextContent();
					stringPropertiesSB.append(key).append(" = ").append(value).append("\n");
					// Avoids duplicated class methods in multi-language
					if (!stringIdsInClass.contains(key)) {
						stringClassSB.append("\tString ").append(key).append("();\n");
						stringIdsInClass.add(key);
					}
				}
			}

			NodeList stringArrays = doc.getElementsByTagName("string-array");

			for (int i = 0; i < stringArrays.getLength(); i++) {

				if (stringArrays.item(i).getNodeType() == Node.ELEMENT_NODE) {
					String key = ((Element) stringArrays.item(i)).getAttribute("name");
					StringBuffer valueSb = new StringBuffer();

					NodeList stringArraysItems = stringArrays.item(i).getChildNodes();

					for (int j = 0; j < stringArraysItems.getLength(); j++) {
						if (stringArraysItems.item(j).getNodeType() == Node.ELEMENT_NODE) {
							if (valueSb.length() != 0) {
								valueSb.append(", ");
							}
							valueSb.append(stringArraysItems.item(j).getTextContent().replace(",", "\\\\,"));
						}
					}

					arrayPropertiesSB.append(key).append(" = ").append(valueSb.toString()).append("\n");
					// Avoids duplicated class methods in multi-language
					if (!arrayIdsInClass.contains(key)) {
						arrayClassSB.append("\tString[] ").append(key).append("();\n");
						arrayIdsInClass.add(key);
					}
				}
			}

			NodeList colorNodes = doc.getElementsByTagName("color");
			for (int i = 0; i < colorNodes.getLength(); i++) {
				Node node = colorNodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					colors.put(element.getAttribute("name"), element.getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processDrawable(String fileName) {
		File file = new File(fileName);
		String str = file.getName().replace(".png", "");
		if (!drawableIdsInClass.contains(str)) {
			drawableIdsInClass.add(str);
		}
	}

	public void processMenuFile(String fileName) {
		try {
			File xmlFile = new File(fileName);
			System.out.println("Processing MENU file " + fileName + "...");

			System.out.println("File name: " + xmlFile.getName());

			String menuName = xmlFile.getName().replace(".xml", "");

			menuIdsInClass.add(menuName);

			menuClassSB.append("\tpublic static Menu " + menuName + "() {\n");
			menuClassSB.append("\t\tMenu menu = new Menu();\n");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int groupId = 0;
					String itemId = "0";
					int order = 0;
					String title = "0";

					if (eElement.hasAttribute("android:id")) {
						itemId = "R.id." + eElement.getAttribute("android:id").replace("@id/", "").replace("@+id/", "");
					}
					if (eElement.hasAttribute("android:title")) {
						title = "R.string." + eElement.getAttribute("android:title").replace("@string/", "");
					}
					menuClassSB.append("\t\tMenuItem item" + temp + " = menu.add(" + groupId + ", " + itemId + ", " + order + ", " + title + ");\n");

					if (eElement.hasAttribute("android:icon")) {
						String icon = eElement.getAttribute("android:icon").replace("@drawable/", "");
						menuClassSB.append("\t\titem" + temp + ".setIcon(R.drawable." + icon + ");\n");
					}

					// TODO NS PREFIX
					if (eElement.hasAttribute("mobialia:showAsAction")) {
						int showAsAction = 0;
						String showAsActionString = eElement.getAttribute("mobialia:showAsAction");
						if ("always".equals(showAsActionString)) {
							showAsAction = MenuItem.SHOW_AS_ACTION_ALWAYS;
						} else if ("never".equals(showAsActionString)) {
							showAsAction = MenuItem.SHOW_AS_ACTION_NEVER;
						} else if ("if_room".equals(showAsActionString)) {
							showAsAction = MenuItem.SHOW_AS_ACTION_IF_ROOM;
						}

						menuClassSB.append("\t\titem" + temp + ".setShowAsAction(" + showAsAction + ");\n");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		menuClassSB.append("\t\treturn menu;\n");
		menuClassSB.append("\t}\n");
	}

	public void getLayoutsFromJavaFile(String fileName) {
		System.out.println("Getting Layouts from file " + fileName + "...");

		try {
			File javaFile = new File(fileName);

			BufferedReader br = new BufferedReader(new FileReader(javaFile));
			String line;
			while ((line = br.readLine()) != null) {
				Matcher matcher = layoutPattern.matcher(line);

				if (matcher.find()) {
					layouts.add(matcher.group(1));
				}
			}

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void writeFile(String fileName, StringBuffer sb) {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			FileWriter fos = new FileWriter(file);
			fos.append(sb.toString());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void output() {
		StringBuffer idsSB = new StringBuffer();
		StringBuffer drawableIdsSB = new StringBuffer();
		StringBuffer menuIdsSB = new StringBuffer();
		StringBuffer stringIdsSB = new StringBuffer();
		StringBuffer arrayIdsSB = new StringBuffer();
		StringBuffer layoutIdsSB = new StringBuffer();

		StringBuffer idResolverSB = new StringBuffer();
		StringBuffer drawableIdResolverSB = new StringBuffer();
		StringBuffer menuIdResolverSB = new StringBuffer();
		StringBuffer stringIdResolverSB = new StringBuffer();
		StringBuffer arrayIdResolverSB = new StringBuffer();
		StringBuffer layoutIdResolverSB = new StringBuffer();

		StringBuffer colorIdsSB = new StringBuffer();
		StringBuffer colorIdResolverSB = new StringBuffer();

		int counter;

		// IDS
		counter = 1;
		for (String str : idsInClass) {
			idResolverSB.append("\t\t\tcase R.id." + str + ":\n\t\t\t\treturn \"" + str + "\";\n");
			idsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// DRAWABLES
		counter = 1;
		for (String str : drawableIdsInClass) {
			drawableIdResolverSB.append("\t\t\tcase R.drawable." + str + ":\n\t\t\t\t\treturn \"" + str + "\";\n");
			drawableIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// MENUS
		counter = 1;
		for (String str : menuIdsInClass) {
			menuIdResolverSB.append("\t\t\tcase R.menu." + str + ":\n\t\t\t\t\treturn Menus." + str + "();\n");
			menuIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		menuClassSB.append("}\n");
		writeFile("Menus.java", menuClassSB);

		// STRING IDS
		counter = 1;
		for (String str : stringIdsInClass) {
			stringIdResolverSB.append("\t\t\tcase R.string." + str + ":\n\t\t\t\treturn strings." + str + "();\n");
			stringIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// ARRAY IDS
		counter = 1;
		for (String str : arrayIdsInClass) {
			arrayIdResolverSB.append("\t\t\tcase R.array." + str + ":\n\t\t\t\treturn arrays." + str + "();\n");
			arrayIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// STRINGS & ARRAYS
		stringClassSB.append("}\n");
		arrayClassSB.append("}\n");

		writeFile("Strings.java", stringClassSB);
		writeFile("Arrays.java", arrayClassSB);

		for (String lang : stringPropertiesSBMap.keySet()) {
			if (lang == null) {
				writeFile("Strings.properties", stringPropertiesSBMap.get(lang));
				writeFile("Arrays.properties", arrayPropertiesSBMap.get(lang));
			} else {
				writeFile("Strings_" + lang + ".properties", stringPropertiesSBMap.get(lang));
				writeFile("Arrays_" + lang + ".properties", arrayPropertiesSBMap.get(lang));
			}
		}

		// COLORS
		counter = 1;
		for (String colorName : colors.keySet()) {
			colorIdResolverSB.append("\t\t\tcase R.color." + colorName + ":\n\t\t\t\t\treturn " + colors.get(colorName).replace("#", "0x") + ";\n");
			colorIdsSB.append("\t\tpublic final static int " + colorName + " = " + counter++ + ";\n");
		}

		// LAYOUT IDS
		counter = 1;
		for (String str : layouts) {
			layoutIdResolverSB.append("\t\t\tcase R.layout." + str + ":\n\t\t\t\treturn layouts." + str + "();\n");
			layoutIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// Finally write the "R" class
		StringBuffer RSB = new StringBuffer();
		RSB.append(FILE_HEADER);
		RSB.append("package " + packageName + ";\n\n");
		RSB.append("\n");
		RSB.append("public class R {\n");

		RSB.append("\tpublic static final class id {\n");
		RSB.append(idsSB);
		RSB.append("\t}\n");

		RSB.append("\tpublic static final class color {\n");
		RSB.append(colorIdsSB);
		RSB.append("\t}\n");

		RSB.append("\tpublic static final class drawable {\n");
		RSB.append(drawableIdsSB);
		RSB.append("\t}\n");

		RSB.append("\tpublic static final class string {\n");
		RSB.append(stringIdsSB);
		RSB.append("\t}\n");

		RSB.append("\tpublic static final class array {\n");
		RSB.append(arrayIdsSB);
		RSB.append("\t}\n");

		RSB.append("\tpublic static final class menu {\n");
		RSB.append(menuIdsSB);
		RSB.append("\t}\n");

		RSB.append("\tpublic static final class layout {\n");
		RSB.append(layoutIdsSB);
		RSB.append("\t}\n");

		RSB.append("}\n");

		writeFile("R.java", RSB);

		StringBuffer contentResolverSB = new StringBuffer();
		contentResolverSB.append(FILE_HEADER);
		contentResolverSB.append("package " + packageName + ";\n\n");
		contentResolverSB.append("import android.content.res.BaseResourceResolver;\n");
		contentResolverSB.append("import android.view.Menu;\n\n");
		contentResolverSB.append("import com.google.gwt.core.client.GWT;\n");
		contentResolverSB.append("import com.google.gwt.user.client.ui.Widget;\n\n");

		contentResolverSB.append("public class ResourceResolver extends BaseResourceResolver {\n\n");
		contentResolverSB.append("\tpublic static final Strings strings = GWT.create(Strings.class);\n");
		contentResolverSB.append("\tpublic static final Arrays arrays = GWT.create(Arrays.class);\n");
		contentResolverSB.append("\tpublic static final Layouts layouts = new Layouts();\n"); // Layout and Raw must be created by user...
		contentResolverSB.append("\n");
		contentResolverSB.append("\tpublic String getIdAsString(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(idResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getIdAsString(id);\n");
		contentResolverSB.append("\t}\n");

		contentResolverSB.append("\tpublic String getString(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(stringIdResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getString(id);\n");
		contentResolverSB.append("\t}\n");

		contentResolverSB.append("\tpublic String[] getStringArray(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(arrayIdResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getStringArray(id);\n");
		contentResolverSB.append("\t}\n");

		contentResolverSB.append("\tpublic Menu getMenu(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(menuIdResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getMenu(id);\n");
		contentResolverSB.append("\t}\n");

		contentResolverSB.append("\tpublic int getColor(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(colorIdResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getColor(id);\n");
		contentResolverSB.append("\t}\n");

		contentResolverSB.append("\tpublic String getDrawable(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(drawableIdResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getDrawable(id);\n");
		contentResolverSB.append("\t}\n");

		contentResolverSB.append("\tpublic Widget getLayout(int id) {\n");
		contentResolverSB.append("\t\tswitch(id) {\n");
		contentResolverSB.append(layoutIdResolverSB);
		contentResolverSB.append("\t\t}\n");
		contentResolverSB.append("\t\treturn super.getLayout(id);\n");
		contentResolverSB.append("\t}\n");
		contentResolverSB.append("}\n");

		writeFile("ResourceResolver.java", contentResolverSB);
	}

	public void crawl(String fileName) {
		File file = new File(fileName);
		if (file.isDirectory()) {
			for (String f : file.list()) {
				crawl(fileName + "/" + f);
			}
		} else {
			if (fileName.endsWith(".png")) {
				processDrawable(fileName);
			} else if (fileName.endsWith(".xml")) {
				if (fileName.contains("/menu/")) {
					processMenuFile(fileName);
				} else {
					processLangFile(fileName);
				}
				getIdsFromFile(fileName);
			}
		}
	}

	public static void main(String args[]) {
		if (args.length < 2) {
			System.out.println("GWT Resource generation crawling Android resource directories.");
			System.out.println("Converts resources from and Android project to a GWT format used by the GWT_android_emu library.");
			System.out.println("Generates:");
			System.out.println(" - R.java: with resource IDs");
			System.out.println(" - ContentResolverImpl.java: maps IDs to resources");
			System.out.println(" - Strings.java and Strings.properties with language variants");
			System.out.println(" - Arrays.java and Arrays.properties");
			System.out.println(" - Menus.java");
			System.out.println("");
			System.out.println("Usage: GenerateResources package_name resource_dir_1 [resource_dir_2] [resource_dir_3] ...");
			return;
		}

		GenerateResources cs = new GenerateResources(args[0]);
		for (int i = 1; i < args.length; i++) {
			cs.crawl(args[i]);
		}

		// Get layouts from Layouts.java
		cs.getLayoutsFromJavaFile("Layouts.java");

		cs.output();
	}
}
