package androidemu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Converts strings from Android format to GWT format
 * 
 */
public class ConvertStrings {
	private Pattern langPattern = Pattern.compile(".*/values-([a-zA-Z]{2})/.*");

	HashMap<String, StringBuffer> stringPropertiesSBMap = new HashMap<String, StringBuffer>();
	StringBuffer stringClassSB = new StringBuffer();

	HashMap<String, StringBuffer> arrayPropertiesSBMap = new HashMap<String, StringBuffer>();
	StringBuffer arrayClassSB = new StringBuffer();

	public ConvertStrings() {
		stringClassSB.append("import com.google.gwt.i18n.client.Constants;\npublic interface Strings extends Constants {\n");
		arrayClassSB.append("import com.google.gwt.i18n.client.Constants;\npublic interface Arrays extends Constants {\n");
	}

	public void processFile(String fileName) {
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

					stringPropertiesSB.append(eElement.getAttribute("name") + " = " + eElement.getTextContent() + "\n");
					stringClassSB.append("\tString " + eElement.getAttribute("name") + "();\n");
				}
			}

			NodeList stringArrays = doc.getElementsByTagName("string-array");

			for (int i = 0; i < stringArrays.getLength(); i++) {

				if (stringArrays.item(i).getNodeType() == Node.ELEMENT_NODE) {
					StringBuffer sb = new StringBuffer();

					NodeList stringArraysItems = stringArrays.item(i).getChildNodes();

					for (int j = 0; j < stringArraysItems.getLength(); j++) {
						if (stringArraysItems.item(j).getNodeType() == Node.ELEMENT_NODE) {
							if (sb.length() != 0) {
								sb.append(", ");
							}
							sb.append(stringArraysItems.item(j).getTextContent().replace(",", "\\\\,"));
						}
					}

					arrayPropertiesSB.append(((Element) stringArrays.item(i)).getAttribute("name") + " = " + sb.toString() + "\n");
					arrayClassSB.append("\tString[] " + ((Element) stringArrays.item(i)).getAttribute("name") + "();\n");
				}
			}
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
		 * resources and processing the locales
		 */
		ConvertStrings cs = new ConvertStrings();
		for (int i = 0; i < args.length; i++) {
			cs.crawl(args[i]);
		}

		cs.output();
	}
}
