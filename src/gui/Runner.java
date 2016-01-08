package gui;

import dot.DotController;

public class Runner {

	public static void main(String[] args){
		for(String className: args){
			DotController d = new DotController(className);
			System.out.println(d.getClassUML());
		}
	}
}
