package gui;

import java.lang.reflect.InvocationTargetException;

import dot.AdapterBuilder;
import dot.AssociationBuilder;
import dot.ExtensionBuilder;
import dot.ImplementsBuilder;

public class UmlRunner {
	public final static String fontName = "Comic Sans MS";

	public static void main(String[] args) {
		UmlWrapper umlWrapper = new UmlWrapper(args);
		umlWrapper.addBuilderClass(ExtensionBuilder.class);
		umlWrapper.addBuilderClass(ImplementsBuilder.class);
		umlWrapper.addBuilderClass(AssociationBuilder.class);
//		umlWrapper.addBuilderClass(DecoratorBuilder.class);
		umlWrapper.addBuilderClass(AdapterBuilder.class);
		try {
			umlWrapper.generateGraph();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Something went wrong constructing instances of builders from the given builder classes");
			System.out.println("check to make sure that all the builder classes have a constructor that takes a IBuilder");
		}
	}
}
