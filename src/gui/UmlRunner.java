package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import dot.AdapterBuilder;
import dot.AssociationBuilder;
import dot.DecoratorBuilder;
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
