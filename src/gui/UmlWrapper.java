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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dot.AdapterBuilder;
import dot.ExtensionBuilder;
import dot.IBuilder;
import dot.ImplementsBuilder;
import dot.UmlBuilder;
import records.IClassRecord;

public class UmlWrapper {
	private final static String fontName = "Comic Sans MS";
	private HashSet<String> classNames;
	private HashMap<String, IClassRecord> records; // convert to hashmap
	private ArrayList<IBuilder> decorators;


	public UmlWrapper(String[] classNames) {
		this.classNames = new HashSet<String>(Arrays.asList(classNames));
		this.records = new ArrayList<IClassRecord>();
		this.decorators = new ArrayList<IBuilder>();
	}
	
	public void build() {
		for (String className : this.classNames) {
			UmlBuilder d = new UmlBuilder(className, this.classNames);
//			SingletonBuilder sb = new SingletonBuilder(d);
			ExtensionBuilder e = new ExtensionBuilder(d);
			ImplementsBuilder i = new ImplementsBuilder(e);
//			UsesBuilder u = new UsesBuilder(i);
//			AssociationBuilder a = new AssociationBuilder(u);
//			DecoratorBuilder db = new DecoratorBuilder(i);
			AdapterBuilder ad = new AdapterBuilder(i);
			this.decorators.add(ad);
			// s.append(d.getClassUML() + "\n");
			this.records.add(ad.build());
		}
		for (int i=0;i<this.decorators.size();i++) {
//		for (IBuilder decorator : this.decorators) {
			IBuilder decorator = this.decorators.get(i);
			IClassRecord record = this.records.get(i);
			decorator.calculatePattern(record, null);
		}
		
		StringBuilder s = new StringBuilder();
		s.append("digraph G {fontname = \"" + fontName + "\"  fontsize = 8  node [ fontname = \"" + fontName
				+ "\" fontsize = 8 shape = \"record\"]" + " edge [ fontname = \"" + fontName + "\" fontsize = 8 ]\n");

		for (IClassRecord record : this.records) {
			s.append(record.getClassUml());
		}
		s.append("}");

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
		ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", "temp.dot", "-o", "out.png");
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
