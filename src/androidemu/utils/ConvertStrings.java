package androidemu.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

	StringBuffer stringPropertiesSB = new StringBuffer();
	StringBuffer stringClassSB = new StringBuffer();

	StringBuffer arrayPropertiesSB = new StringBuffer();
	StringBuffer arrayClassSB = new StringBuffer();
	
	public ConvertStrings() {
		stringClassSB.append("import com.google.gwt.i18n.client.Constants;\npublic interface Strings extends Constants {\n");		
		arrayClassSB.append("import com.google.gwt.i18n.client.Constants;\npublic interface Arrays extends Constants {\n");
	}

	public void processFile(String fileName) {
		System.out.println("Precssing file " + fileName + "...");
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
		writeFile("Strings.properties", stringPropertiesSB);

		writeFile("Arrays.java", arrayClassSB);
		writeFile("Arrays.properties", arrayPropertiesSB);
	}
	
	public static void main(String args[]) {

		ConvertStrings cs = new ConvertStrings();
		for (int i = 0; i < args.length; i++) {
			cs.processFile(args[i]);
		}

		cs.output();
	}
}
