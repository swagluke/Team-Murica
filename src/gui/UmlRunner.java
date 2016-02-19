package gui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import dot.AdapterBuilder;
import dot.CompositeBuilder;
import dot.DecoratorBuilder;
import dot.ExtensionBuilder;
import dot.ImplementsBuilder;
import dot.SingletonBuilder;
import dot.UsesBuilder;
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
		UmlWrapper umlWrapper = new UmlWrapper(args);
		umlWrapper.addBuilderClass(ExtensionBuilder.class);
		umlWrapper.addBuilderClass(ImplementsBuilder.class);
		umlWrapper.addBuilderClass(AssociationBuilder.class);
		umlWrapper.addBuilderClass(DecoratorBuilder.class);
		umlWrapper.addBuilderClass(AdapterBuilder.class);
		umlWrapper.addBuilderClass(SingletonBuilder.class);
		umlWrapper.addBuilderClass(UsesBuilder.class);
		umlWrapper.addBuilderClass(CompositeBuilder.class);
		phases.add(new Load(umlWrapper));
		phases.add(new PatternDetection(umlWrapper));
		phases.add(new GenerateUML(umlWrapper));
		phases.add(new Print(umlWrapper));

		for (IPhase p : phases) {
			try {
				p.execute();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
