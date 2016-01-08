package gui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import dot.UmlBuilder;

public class Runner {

	public static void main(String[] args){
		StringBuilder s = new StringBuilder();
		s.append("digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"]"
				+ " edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n");
		for(String className: args){
			UmlBuilder d = new UmlBuilder(className);
			s.append(d.getClassUML());
		}
		s.append("}");
		System.out.println(s.toString());
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
		    
	}
}
