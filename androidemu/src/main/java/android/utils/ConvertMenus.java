package android.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Converts strings from Android format to GWT format
 */
public class ConvertMenus {
	StringBuffer menuClassSB = new StringBuffer();

	public ConvertMenus() {
        menuClassSB.append("import android.view.Menu;\n");
        menuClassSB.append("import android.view.MenuItem;\n\n");

        menuClassSB.append("public class Menus {\n");
	}

	public void processFile(String fileName) {
        try {
			File xmlFile = new File(fileName);
            System.out.println("Processing file " + fileName + "...");

            System.out.println("File name: " + xmlFile.getName());

            menuClassSB.append("\tpublic Menu " + xmlFile.getName().replace(".xml", "") + "() {\n");
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
                    String itemId = null;
                    int order = 0;
                    String title = null;

                    if (eElement.hasAttribute("android:id")) {
                        itemId = "R.id." + eElement.getAttribute("android:id").replace("@id/", "").replace("@+id/", "");
                    }
                    if (eElement.hasAttribute("android:title")) {
                        title = "R.string." + eElement.getAttribute("android:title").replace("@string/", "") + "()";
                    }
//                    if (eElement.hasAttribute("android:title")) {
//                        menuClassSB.append("\t\titem"+ temp +".setTitle(R.string."+eElement.getAttribute("android:title").replace("@string/", "")+"());\n");
//                    }
                    menuClassSB.append("\t\tMenuItem item"+ temp +" = menu.add(" + groupId + ", " + itemId + ", "+order+", "+title+");\n");
                }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
        menuClassSB.append("\t\treturn menu;\n");
        menuClassSB.append("\t}\n\n");
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
		menuClassSB.append("}\n");

		writeFile("Menus.java", menuClassSB);
	}

	public void crawl(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			for (String f : file.list()) {
				crawl(dir + "/" + f);
			}
		} else {
			if (dir.endsWith(".xml")) {
				processFile(dir);
			}
		}
	}

	public static void main(String args[]) {
		/**
		 * Crawl the Android resource directory looking for string and array
		 * resources processing the locales
		 */
		ConvertMenus cs = new ConvertMenus();
		for (String arg : args) {
			cs.crawl(arg);
		}

		cs.output();
	}
}
