package gui;

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
	}
}
