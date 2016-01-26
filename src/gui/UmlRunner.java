package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dot.AssociationBuilder;
import dot.ExtensionBuilder;
import dot.ImplementsBuilder;
import dot.SingletonBuilder;
import dot.UmlBuilder;
import dot.UsesBuilder;

public class UmlRunner {
	private final static String fontName = "Comic Sans MS";
	private static HashMap<String, HashSet<String>> implementsMap = new HashMap<String, HashSet<String>>();
	private static HashMap<String, String> extendsMap = new HashMap<String, String>();
	private static HashMap<String, HashSet<String>> usesMap = new HashMap<String, HashSet<String>>();
	private static HashSet<String> classNames = new HashSet<String>();
	private static HashMap<String, HashSet<String>> associatesMap = new HashMap<String, HashSet<String>>();

	public static void main(String[] args) {
		StringBuilder s = new StringBuilder();
		// set up digraph information
		s.append("digraph G {fontname = \"" + fontName + "\"  fontsize = 8  node [ fontname = \"" + fontName
				+ "\" fontsize = 8 shape = \"record\"]" + " edge [ fontname = \"" + fontName + "\" fontsize = 8 ]\n");
		// creates each class diagram and adds their implementations and
		// extensions to the respective lists.
		for (String className : args) {
//			UmlBuilder d = new UmlBuilder(className, new HashSet<String>(Arrays.asList(args)));
			SingletonBuilder sb = new SingletonBuilder(className, new HashSet<String>(Arrays.asList(args)));
			ExtensionBuilder e = new ExtensionBuilder(sb);
			ImplementsBuilder i = new ImplementsBuilder(e);
			UsesBuilder u = new UsesBuilder(i);
			AssociationBuilder a = new AssociationBuilder(u);
			// s.append(d.getClassUML() + "\n");
			a.build();
//			s.append(d.getClassUML());
//			s.append(sb.getClassUML());
//			s.append(e.getClassUML());
//			s.append(i.getClassUML());
//			s.append(u.getClassUML());
			s.append(a.getClassUML());
//			 for (String imp : i.implementsList) {
			// HashSet<String> list = implementsMap.get(className);
			// if (list == null)
			// list = new HashSet<String>();
			// list.add(imp);
			// Runner.implementsMap.put(className, list);
			// }
			// for (String uses : u.getUsesList().toArray(new String[u.getUsesList().size()])) {
			// HashSet<String> list = usesMap.get(className);
			// if (list == null)
			// list = new HashSet<String>();
			// list.add(uses);
			// Runner.usesMap.put(className, list);
			// }
			// associatesMap.put(className, ((AssociationClassRecord) a.build()).getAssociationNames());
			// Runner.extendsMap.put(className, d.getExtendsName());
			// classNames.add(className);
		}
		// create implementation arrows
		// s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		// for (String key : implementsMap.keySet()) {
		// String[] shortKeyList = key.replace("/", ".").split("\\.");
		// String shortKey = shortKeyList[shortKeyList.length - 1];
		// HashSet<String> shortValueList = implementsMap.get(key);
		// for (String val : shortValueList) {
		// String[] valList = val.replace("/", ".").split("\\.");
		// String shortValue = valList[valList.length - 1];
		// if (classNames.contains(val.replace("/", ".")))
		// s.append(shortKey + " -> " + shortValue + "\n");
		// }
		// }
		// create extends arrows
		// s.append("edge [ style = \"normal\"]\n");
		// for (String key2 : extendsMap.keySet()) {
		// String[] shortKeyList = key2.replace("/", ".").split("\\.");
		// String shortKey = shortKeyList[shortKeyList.length - 1];
		// String[] shortValueList = extendsMap.get(key2).replace("/", ".").split("\\.");
		// String shortValue = shortValueList[shortValueList.length - 1];
		// if (classNames.contains(extendsMap.get(key2).replace("/", ".")))
		// s.append(shortKey + " -> " + shortValue + "\n");
		// }
		// create uses arrows
		// s.append("edge [ style = \"dotted\" arrowhead = \"open\"]\n");
		// for (String key : usesMap.keySet()) {
		// String[] shortKeyList = key.replace("/", ".").split("\\.");
		// String shortKey = shortKeyList[shortKeyList.length - 1];
		// HashSet<String> shortValueList = usesMap.get(key);
		// for (String val : shortValueList) {
		// String[] valList = val.replace("/", ".").split("\\.");
		// String shortValue = valList[valList.length - 1];
		// if (classNames.contains(val.replace("/", ".")))
		// s.append(shortKey + " -> " + shortValue + "\n");
		// }
		// }
		// System.out.println(shortKey);
		// s.append(key)

		// }

		// close the digraph
		s.append("}");

		// System.out.println(s.toString());
		createGraph(s.toString());
	}

	private static void createGraph(String digraph) {
		final Path path = Paths.get("temp.dot");

		try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
				StandardOpenOption.CREATE);) {
			writer.write(digraph);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "temp.dot", "-o out.png");
		Map<String, String> env = pb.environment();
		// pb.directory();
		System.out.println(System.getProperty("user.dir"));
		try {
			// Process p = pb.start();
			File log = new File("log");
			pb.redirectErrorStream(true);
			pb.redirectOutput(Redirect.appendTo(log));
			Process p = pb.start();
			// Files.delete(path);//uncomment to clean up after yourself
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
