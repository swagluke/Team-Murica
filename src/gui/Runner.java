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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dot.UmlBuilder;

public class Runner {
	private final static String fontName = "Comic Sans MS";
	private static HashMap<String, String> implementsMap=new HashMap<String, String>();
	private static HashMap<String, String> extendsMap = new HashMap<String, String>();
	private static HashSet<String> classNames = new HashSet<String>();
	public static void main(String[] args){
		StringBuilder s = new StringBuilder();
		//set up digraph information
		s.append("digraph G {fontname = \""+fontName+"\"  fontsize = 8  node [ fontname = \""+fontName+"\" fontsize = 8 shape = \"record\"]"
				+ " edge [ fontname = \""+fontName+"\" fontsize = 8 ]\n");
		//creates each class diagram and adds their implementations and extensions to the respective lists.
		for(String className: args){
			UmlBuilder d = new UmlBuilder(className);
			s.append(d.getClassUML()+"\n");
			for(String i: d.getImplementsList()){
				Runner.implementsMap.put(className, i);
			}
			Runner.extendsMap.put(className, d.getExtendsName());
			classNames.add(className);
		}
		//create implementation arrows
		s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		for(String key:implementsMap.keySet()){
			String[] shortKeyList =key.replace("/", ".").split("\\.");
			String shortKey = shortKeyList[shortKeyList.length-1];
			String[] shortValueList = implementsMap.get(key).replace("/", ".").split("\\.");
			String shortValue = shortValueList[shortValueList.length-1];
			if(classNames.contains(implementsMap.get(key).replace("/", ".")))
				s.append(shortKey+" -> "+shortValue+"\n");
		}
		//create extends arrows
		s.append("edge [ style = \"normal\"]\n");
		for(String key2:extendsMap.keySet()){
			String[] shortKeyList =key2.replace("/", ".").split("\\.");
			String shortKey = shortKeyList[shortKeyList.length-1];
			String[] shortValueList = extendsMap.get(key2).replace("/", ".").split("\\.");
			String shortValue = shortValueList[shortValueList.length-1];
			if(classNames.contains(extendsMap.get(key2).replace("/", ".")))
				s.append(shortKey+" -> "+shortValue+"\n");
		}
		//close the digraph
		s.append("}");
		
//		System.out.println(s.toString());
		createGraph(s.toString());
	}

	private static void createGraph(String digraph) {
		    final Path path = Paths.get("temp.dot");

		    try (
		        final BufferedWriter writer = Files.newBufferedWriter(path,
		            StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		    ) {
		        writer.write(digraph);
		        writer.flush();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ProcessBuilder pb = new ProcessBuilder("dot","-Tpng", "temp.dot", "-o out.png");
		    Map<String, String> env = pb.environment();
		   // System.out.println(env.size());
//		    pb.directory(System.getProperty("user.dir"));
		    System.out.println(System.getProperty("user.dir"));
		    try {
				//Process p = pb.start(); 
				 File log = new File("log");
				 pb.redirectErrorStream(true);
				 pb.redirectOutput(Redirect.appendTo(log));
				 Process p = pb.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	
}
