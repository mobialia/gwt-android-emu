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
	private final static String NS_ANDROID = "http://schemas.android.com/apk/res/android";
	private final static String NS_APP = "http://schemas.android.com/apk/res-auto";

	private final static String FILE_HEADER = "/** FILE GENERATED AUTOMATICALLY BY GWT_ANDROID_EMU'S GenerateResources: DO NOT EDIT MANUALLY */\n";
	private Pattern idsPattern = Pattern.compile("\\s*(?i)id\\s*=\\s*(\\\"([^\"]*\\\")|'[^']*'|([^'\">\\s]+))");
	private Pattern langPattern = Pattern.compile(".*/values-([a-zA-Z]{2})/.*");
	private Pattern layoutPattern = Pattern.compile(".*public [a-zA-Z0-9_]+ ([a-zA-Z0-9_]+)" + Pattern.quote("(") + Pattern.quote(")") + ".*");

	String packageName;

	ArrayList<String> ids = new ArrayList<>();
	HashMap<String, String> drawableIds = new HashMap<>();

	StringBuffer menuClassSB = new StringBuffer();
	ArrayList<String> menuIds = new ArrayList<>();

	HashMap<String, StringBuffer> stringPropertiesSBMap = new HashMap<>();
	ArrayList<String> stringIds = new ArrayList<>();
	StringBuffer stringClassSB = new StringBuffer();

	HashMap<String, StringBuffer> arrayPropertiesSBMap = new HashMap<>();
	ArrayList<String> arrayIds = new ArrayList<>();
	StringBuffer arrayClassSB = new StringBuffer();

	ArrayList<String> layouts = new ArrayList<>();

	HashMap<String, String> colors = new HashMap<>();

	public GenerateResources(String packageName) {
		this.packageName = packageName;

		menuClassSB.append(FILE_HEADER + "package " + packageName + ".res;\n\nimport android.view.Menu;\nimport android.view.MenuItem;\nimport " + packageName + ".R;\n\npublic class Menus {\n");
		stringClassSB.append(FILE_HEADER + "package " + packageName + ".res;\n\nimport com.google.gwt.i18n.client.Constants;\n\npublic interface Strings extends Constants {\n");
		arrayClassSB.append(FILE_HEADER + "package " + packageName + ".res;\n\nimport com.google.gwt.i18n.client.Constants;\n\npublic interface Arrays extends Constants {\n");
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
					String value = eElement.getTextContent().replace("\n", "");
					stringPropertiesSB.append(key).append(" = ").append(value).append("\n");
					// Avoids duplicated class methods in multi-language
					if (!stringIds.contains(key)) {
						stringClassSB.append("\tString ").append(key).append("();\n");
						stringIds.add(key);
					}
				}
			}

			NodeList stringArrays = doc.getElementsByTagName("string-array");

			for (int i = 0; i < stringArrays.getLength(); i++) {

				if (stringArrays.item(i).getNodeType() == Node.ELEMENT_NODE) {
					String key = ((Element) stringArrays.item(i)).getAttribute("name");
					StringBuffer valueSb = new StringBuffer();

					NodeList stringArraysItems = stringArrays.item(i).getChildNodes();

					boolean first = true;
					for (int j = 0; j < stringArraysItems.getLength(); j++) {
						if (stringArraysItems.item(j).getNodeType() == Node.ELEMENT_NODE) {
							if (!first) {
								valueSb.append(", ");
							}
							first = false;
							valueSb.append(stringArraysItems.item(j).getTextContent().replace(",", "\\\\,").replace("\n", ""));
						}
					}

					arrayPropertiesSB.append(key).append(" = ").append(valueSb.toString()).append("\n");
					// Avoids duplicated class methods in multi-language
					if (!arrayIds.contains(key)) {
						arrayClassSB.append("\tString[] ").append(key).append("();\n");
						arrayIds.add(key);
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

			NodeList itemNodes = doc.getElementsByTagName("item");
			for (int i = 0; i < itemNodes.getLength(); i++) {
				Node node = itemNodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE
						&& "id".equals(((Element) node).getAttribute("type"))) {
					Element element = (Element) node;
					ids.add(element.getAttribute("name"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processMenuFile(String fileName) {
		try {
			File xmlFile = new File(fileName);
			System.out.println("Processing menu file " + fileName + "...");

			System.out.println("File name: " + xmlFile.getName());

			String menuName = xmlFile.getName().replace(".xml", "");

			menuIds.add(menuName);

			menuClassSB.append("\tpublic static Menu " + menuName + "() {\n");
			menuClassSB.append("\t\tMenu menu = new Menu();\n");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
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

					if (eElement.hasAttributeNS(NS_ANDROID, "id")) {
						String idName = eElement.getAttributeNS(NS_ANDROID, "id").replace("@id/", "").replace("@+id/", "");
						itemId = "R.id." + idName;
						if (!ids.contains(idName)) {
							ids.add(idName);
						}
					}
					if (eElement.hasAttributeNS(NS_ANDROID, "title")) {
						title = "R.string." + eElement.getAttributeNS(NS_ANDROID, "title").replace("@string/", "");
					}
					menuClassSB.append("\t\tMenuItem item" + temp + " = menu.add(" + groupId + ", " + itemId + ", " + order + ", " + title + ");\n");

					if (eElement.hasAttributeNS(NS_ANDROID, "icon")) {
						String icon = eElement.getAttributeNS(NS_ANDROID, "icon").replace("@drawable/", "");
						menuClassSB.append("\t\titem" + temp + ".setIcon(R.drawable." + icon + ");\n");
					}

					if (eElement.hasAttributeNS(NS_APP, "showAsAction")) {
						int showAsAction = 0;
						String showAsActionString = eElement.getAttributeNS(NS_APP, "showAsAction");
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
			System.out.println("Error writing " + fileName);
			e.printStackTrace();
		}
	}

	public void writeFiles(String classesDir, String propertiesDir) {
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
		for (String str : ids) {
			idResolverSB.append("\t\t\tcase R.id." + str + ":\n\t\t\t\treturn \"" + str + "\";\n");
			idsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// DRAWABLES
		counter = 1;
		for (String str : drawableIds.keySet()) {
			drawableIdResolverSB.append("\t\t\tcase R.drawable." + str + ":\n\t\t\t\t\treturn \"" + drawableIds.get(str) + "\";\n");
			drawableIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// MENUS
		counter = 1;
		for (String str : menuIds) {
			menuIdResolverSB.append("\t\t\tcase R.menu." + str + ":\n\t\t\t\t\treturn Menus." + str + "();\n");
			menuIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		menuClassSB.append("}\n");
		writeFile(classesDir + "/res/Menus.java", menuClassSB);

		// STRING IDS
		counter = 1;
		for (String str : stringIds) {
			stringIdResolverSB.append("\t\t\tcase R.string." + str + ":\n\t\t\t\treturn strings." + str + "();\n");
			stringIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// ARRAY IDS
		counter = 1;
		for (String str : arrayIds) {
			arrayIdResolverSB.append("\t\t\tcase R.array." + str + ":\n\t\t\t\treturn arrays." + str + "();\n");
			arrayIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
		}

		// STRINGS & ARRAYS
		stringClassSB.append("}\n");
		arrayClassSB.append("}\n");

		writeFile(classesDir + "res/Strings.java", stringClassSB);
		writeFile(classesDir + "res/Arrays.java", arrayClassSB);

		for (String lang : stringPropertiesSBMap.keySet()) {
			if (lang == null) {
				writeFile(propertiesDir + "res/Strings.properties", stringPropertiesSBMap.get(lang));
				writeFile(propertiesDir + "res/Arrays.properties", arrayPropertiesSBMap.get(lang));
			} else {
				writeFile(propertiesDir + "res/Strings_" + lang + ".properties", stringPropertiesSBMap.get(lang));
				writeFile(propertiesDir + "res/Arrays_" + lang + ".properties", arrayPropertiesSBMap.get(lang));
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

		writeFile(classesDir + "R.java", RSB);

		StringBuffer contentResolverSB = new StringBuffer();
		contentResolverSB.append(FILE_HEADER);
		contentResolverSB.append("package " + packageName + ".res;\n\n");
		contentResolverSB.append("import android.view.Menu;\n\n");
		contentResolverSB.append("import com.google.gwt.core.client.GWT;\n");
		contentResolverSB.append("import com.google.gwt.user.client.ui.Widget;\n");
		contentResolverSB.append("import " + packageName + ".R;\n\n");

		contentResolverSB.append("public class Resources extends android.content.res.Resources {\n\n");
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

		writeFile(classesDir + "res/Resources.java", contentResolverSB);
	}

	public void crawlAndroidResources(String fileName) {
		File file = new File(fileName);
		if (file.isDirectory()) {
			for (String f : file.list()) {
				crawlAndroidResources(fileName + "/" + f);
			}
		} else {
			if (fileName.endsWith(".xml")) {
				if (fileName.contains("/drawable")) {
				} else if (fileName.contains("/menu/")) {
					processMenuFile(fileName);
				} else {
					processLangFile(fileName);
				}
			}
		}
	}

	/**
	 * Generates layouts for uibinder files
	 */
	public void generateIds(String dirname) {
		File dirFile = new File(dirname);
		if (dirFile.isDirectory()) {
			for (String f : dirFile.list()) {
				if (f.endsWith(".ui.xml")) {
					System.out.println("Generating ids from " + f);
					File file = new File(dirname + f);

					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						String line;
						while ((line = br.readLine()) != null) {
							Matcher matcher = idsPattern.matcher(line);

							String id;
							while (matcher.find()) {
								String aux = matcher.group(1); // maybe with leading/trailing " or '
								id = aux.substring(1, aux.length() - 1);
								if (!ids.contains(id)) {
									ids.add(id);
								}
							}
						}
						br.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void generateDrawableIds(String dirname) {
		File file = new File(dirname);
		if (file.isDirectory()) {
			for (String f : file.list()) {
				if (f.endsWith(".png") || f.endsWith(".jpg") || f.endsWith(".jpeg") || f.endsWith(".svg")) {
					String id = f.substring(0, f.lastIndexOf("."));
					if (!drawableIds.keySet().contains(id)) {
						drawableIds.put(id, f);
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		if (args.length < 2) {
			System.out.println("GWT Resource generation crawling Android resource directories.");
			System.out.println("Converts resources from and Android project to a GWT format used by the gwt-android-emu library.");
			System.out.println("Generated files:");
			System.out.println("Generated files:");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/R.java: with the resource IDs");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/res/Resources.java: mapping IDs to resources");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/res/Strings.java");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/res/Arrays.java");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/res/Strings.properties with language variants");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/res/Arrays.properties with language variants");
			System.out.println(" - <gwt_project_dir>/src/main/java/<package_folder>/res/Menus.java");
			System.out.println("It generates R.id.* for each id= in the uibinder xml layouts at <gwt_project_dir>/src/main/resources/<package_folder>/res/layout/");
			System.out.println("It generates R.drawable.* for the images in <gwt_project_dir>/src/main/webapp/img/");
			System.out.println("It generates R.layout.* for each method in <gwt_project_dir>/src/main/java/<package_folder>/res/Layouts.java");
			System.out.println("");
			System.out.println("Usage: GenerateResources package_name <gwt_project_dir> <resource_dir_1> [<resource_dir_2>] [<resource_dir_3>] ...");
			return;
		}

		String packageName = args[0];
		String packageAsFolders = packageName.replace(".", "/");
		String rootFolder = args[1];

		GenerateResources cs = new GenerateResources(packageName);
		// Get ids from uibinder xmls
		cs.generateIds(rootFolder + "/src/main/resources/" + packageAsFolders + "/res/layout/");
		// Get images from directory
		cs.generateDrawableIds(rootFolder + "/src/main/webapp/img/");
		// Get layouts from Layouts.java
		cs.getLayoutsFromJavaFile(rootFolder + "/src/main/java/" + packageAsFolders + "/res/Layouts.java");

		for (int i = 2; i < args.length; i++) {
			cs.crawlAndroidResources(args[i]);
		}

		cs.writeFiles(rootFolder + "/src/main/java/" + packageAsFolders + "/",
				rootFolder + "/src/main/resources/" + packageAsFolders + "/");
	}
}