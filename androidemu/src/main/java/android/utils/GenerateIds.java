package android.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Crawl the Android source directory looking for java files
 *
 * Gets all the R.id.xxxxx occurrences in all the java classes and writes it to a Ids class file
 */
public class GenerateIds {
	private Pattern idsPattern = Pattern.compile(".*R"+Pattern.quote(".")+"id"+Pattern.quote(".")+"([a-zA-Z0-9_]+).*");

	ArrayList<String> idsInClass = new ArrayList<String>();
	StringBuffer idsClassSB = new StringBuffer();

	public GenerateIds() {
		idsClassSB.append("public class Ids {\n");
	}

	public void processFile(String fileName) {

		System.out.println("Processing file " + fileName + ")...");

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
		for (String id : idsInClass) {
			idsClassSB.append("\tpublic final static String " + id + " = \"" + id + "\";\n");
		}
		idsClassSB.append("}\n");

		writeFile("Ids.java", idsClassSB);
	}

	public void crawl(String dir) {
		File file = new File(dir);
		if (file.isDirectory()) {
			for (String f : file.list()) {
				crawl(dir + "/" + f);
			}
		} else {
			if (dir.endsWith(".java")) {
				processFile(dir);
			}
		}
	}

	public static void main(String args[]) {

		GenerateIds cs = new GenerateIds();
		for (String arg : args) {
			cs.crawl(arg);
		}

		cs.output();
	}
}
