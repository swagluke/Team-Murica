package gui;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import dot.*;
import singleton.Singleton;

public class UmlRunner {
	public final static String fontName = "Comic Sans MS";

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		UmlWrapper umlWrapper = new UmlWrapper(args);
		umlWrapper.addBuilderClass(ExtensionBuilder.class);
		umlWrapper.addBuilderClass(ImplementsBuilder.class);
//		umlWrapper.addBuilderClass(AssociationBuilder.class);
//		umlWrapper.addBuilderClass(DecoratorBuilder.class);
//		umlWrapper.addBuilderClass(AdapterBuilder.class);
//		umlWrapper.addBuilderClass(SingletonBuilder.class);
		try {
			umlWrapper.generateGraph();
			System.out.println("done");
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Something went wrong constructing instances of builders from the given builder classes");
			System.out.println("check to make sure that all the builder classes have a constructor that takes a IBuilder");
		}
	}
}
