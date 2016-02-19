package gui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import dot.*;
import phases.GenerateUML;
import phases.IPhase;
import phases.Load;
import phases.PatternDetection;
import phases.Print;

public class UmlRunner {
	public final static String fontName = "Comic Sans MS";
	private static ArrayList<IPhase> phases = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		UmlWrapper umlWrapper = new UmlWrapper();
		for (String arg : args) {
			umlWrapper.addClass(arg);
		}
		umlWrapper.addBuilderClass(ExtensionBuilder.class);
		umlWrapper.addBuilderClass(ImplementsBuilder.class);
		umlWrapper.addBuilderClass(AssociationBuilder.class);
		umlWrapper.addBuilderClass(DecoratorBuilder.class);
		umlWrapper.addBuilderClass(AdapterBuilder.class);
		umlWrapper.addBuilderClass(SingletonBuilder.class);
		umlWrapper.addBuilderClass(UsesBuilder.class);
		umlWrapper.addBuilderClass(CompositeBuilder.class);
		umlWrapper.addPhase(new Load(umlWrapper));
		umlWrapper.addPhase(new PatternDetection(umlWrapper));
		umlWrapper.addPhase(new GenerateUML(umlWrapper));
		umlWrapper.addPhase(new Print(umlWrapper));

		try {
			umlWrapper.execute();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
