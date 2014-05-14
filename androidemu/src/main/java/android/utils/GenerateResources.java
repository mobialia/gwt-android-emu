package android.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Converts strings from Android format to GWT format
 */
public class GenerateResources {
    private Pattern idsPattern = Pattern.compile(".*@" + Pattern.quote("+") + "id/([a-zA-Z0-9_]+).*");
    private Pattern langPattern = Pattern.compile(".*/values-([a-zA-Z]{2})/.*");

    String packageName;

    ArrayList<String> idsInClass = new ArrayList<String>();
    StringBuffer idsSB = new StringBuffer();
    StringBuffer idResolverSB = new StringBuffer();

    StringBuffer menuClassSB = new StringBuffer();
    ArrayList<String> menuIdsInClass = new ArrayList<String>();
    StringBuffer menuIdsSB = new StringBuffer();
    StringBuffer menuIdResolverSB = new StringBuffer();

    HashMap<String, StringBuffer> stringPropertiesSBMap = new HashMap<String, StringBuffer>();
    ArrayList<String> stringIdsInClass = new ArrayList<String>();
    StringBuffer stringClassSB = new StringBuffer();
    StringBuffer stringIdsSB = new StringBuffer();
    StringBuffer stringIdResolverSB = new StringBuffer();

    HashMap<String, StringBuffer> arrayPropertiesSBMap = new HashMap<String, StringBuffer>();
    ArrayList<String> arrayIdsInClass = new ArrayList<String>();
    StringBuffer arrayClassSB = new StringBuffer();
    StringBuffer arrayIdsSB = new StringBuffer();
    StringBuffer arrayIdResolverSB = new StringBuffer();

    public GenerateResources(String packageName) {
        this.packageName = packageName;

        menuClassSB.append("package " + packageName + ";\n\nimport android.view.Menu;\nimport android.view.MenuItem;\n\npublic class Menus {\n");
        stringClassSB.append("package " + packageName + ";\n\nimport com.google.gwt.i18n.client.Constants;\n\npublic interface Strings extends Constants {\n");
        arrayClassSB.append("package " + packageName + ";\n\nimport com.google.gwt.i18n.client.Constants;\n\npublic interface Arrays extends Constants {\n");
    }

    public void getIdsFromFile(String fileName) {

        System.out.println("Getting Ids from file " + fileName + ")...");

        try {
            File javaFile = new File(fileName);

            BufferedReader br = new BufferedReader(new FileReader(javaFile));
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
        } catch (Exception e) {
            e.printStackTrace();
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
        int counter;

        // IDS
        counter = 1;
        for (String str : idsInClass) {
            idResolverSB.append("\t\t\tcase R.id." + str + ":\n\t\t\t\t\treturn \"" + str + "\";\n");
            idsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
        }

        // MENUS
        for (String str : menuIdsInClass) {
            menuIdResolverSB.append("\t\t\tcase R.menu." + str + ":\n\t\t\t\t\treturn Menus." + str + "();\n");
            menuIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
        }

        menuClassSB.append("}\n");
        writeFile("Menus.java", menuClassSB);

        // STRING IDS
        counter = 1;
        for (String str : stringIdsInClass) {
            stringIdResolverSB.append("\t\t\tcase R.string." + str + ":\n\t\t\t\treturn stringValues." + str + "();\n");
            stringIdsSB.append("\t\tpublic final static int " + str + " = " + counter++ + ";\n");
        }

        // ARRAY IDS
        counter = 1;
        for (String str : arrayIdsInClass) {
            arrayIdResolverSB.append("\t\t\tcase R.array." + str + ":\n\t\t\t\treturn arrayValues." + str + "();\n");
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

        // Finally write the "R" class
        StringBuffer RSB = new StringBuffer();
        RSB.append("package " + packageName + ";\n");
        RSB.append("import android.content.res.ResourceResolver;\n");
        RSB.append("import android.content.res.Resources;\n");
        RSB.append("import android.view.Menu;\n");
        RSB.append("import com.google.gwt.core.client.GWT;\n");
        RSB.append("\n");
        RSB.append("public class R implements ResourceResolver {\n");
        RSB.append("\n");
        RSB.append("\tstatic {\n");
        RSB.append("\t\tResources.setResourceResolver(new R());\n");
        RSB.append("\t}\n");
        RSB.append("\n");

        RSB.append("\tpublic static final class id {\n");
        RSB.append(idsSB);
        RSB.append("\t}\n\n");

        RSB.append("\tpublic static final class string {\n");
        RSB.append(stringIdsSB);
        RSB.append("\t}\n\n");

        RSB.append("\tpublic static final class array {\n");
        RSB.append(arrayIdsSB);
        RSB.append("\t}\n\n");

        RSB.append("\tpublic static final class menu {\n");
        RSB.append(menuIdsSB);
        RSB.append("\t}\n\n");

        RSB.append("\tpublic static final Strings stringValues = GWT.create(Strings.class);\n");
        RSB.append("\tpublic static final Arrays arrayValues = GWT.create(Arrays.class);\n");
        RSB.append("\tpublic static final Layouts layout = new Layouts();\n"); // Layout and Raw must be created by user...
        RSB.append("\tpublic static final Raw raw = GWT.create(Raw.class);\n");
        RSB.append("\n");
        RSB.append("\tpublic String getIdAsString(int id) {\n");
        RSB.append("\t\tswitch(id) {\n");
        RSB.append(idResolverSB);
        RSB.append("\t\t}\n");
        RSB.append("\t\treturn null;\n");
        RSB.append("\t}\n");
        RSB.append("\n");

        RSB.append("\tpublic String getString(int id) {\n");
        RSB.append("\t\tswitch(id) {\n");
        RSB.append(stringIdResolverSB);
        RSB.append("\t\t}\n");
        RSB.append("\t\treturn null;\n");
        RSB.append("\t}\n");
        RSB.append("\n");

        RSB.append("\tpublic String[] getStringArray(int id) {\n");
        RSB.append("\t\tswitch(id) {\n");
        RSB.append(arrayIdResolverSB);
        RSB.append("\t\t}\n");
        RSB.append("\t\treturn null;\n");
        RSB.append("\t}\n");
        RSB.append("\n");

        RSB.append("\tpublic Menu getMenu(int id) {\n");
        RSB.append("\t\tswitch(id) {\n");
        RSB.append(menuIdResolverSB);
        RSB.append("\t\t}\n");
        RSB.append("\t\treturn null;\n");
        RSB.append("\t}\n");
        RSB.append("\n");

        RSB.append("}\n");

        writeFile("R.java", RSB);
    }

    public void crawl(String fileName) {
        File file = new File(fileName);
        if (file.isDirectory()) {
            for (String f : file.list()) {
                crawl(fileName + "/" + f);
            }
        } else {
            if (fileName.endsWith(".xml")) {
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
            System.out.println("Usage: GenerateResources package_name resource_dir_1 [resource_dir_2] [resource_dir_3] ...");
            return;
        }

        GenerateResources cs = new GenerateResources(args[0]);
        for (int i = 1; i < args.length; i++) {
            cs.crawl(args[i]);
        }

        cs.output();
    }
}
