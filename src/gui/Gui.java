package gui;

import dot.*;

import javax.swing.*;
import java.awt.*;
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
import java.util.Map;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private UmlWrapper wrapper;

	public Gui() {
		String[] args = new String[] { "headfirst.composite.menu.Menu", "headfirst.composite.menu.MenuComponent",
				"headfirst.composite.menu.MenuItem" };
		this.wrapper = new UmlWrapper(args);
		this.wrapper.addBuilderClass(ExtensionBuilder.class);
		this.wrapper.addBuilderClass(ImplementsBuilder.class);
		// this.wrapper.addBuilderClass(AssociationBuilder.class);
		this.wrapper.addBuilderClass(DecoratorBuilder.class);
		this.wrapper.addBuilderClass(AdapterBuilder.class);
		this.wrapper.addBuilderClass(SingletonBuilder.class);
		this.wrapper.addBuilderClass(UsesBuilder.class);
		this.wrapper.addBuilderClass(CompositeBuilder.class);

		this.setPreferredSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.loadInitialScreen();

		this.setVisible(true);
		this.toFront();
	}

	private void loadInitialScreen() {
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(Box.createVerticalGlue());
		JPanel panel = new InitialPanel(this.wrapper);

		Box panelBox = Box.createHorizontalBox();
		panelBox.add(Box.createHorizontalGlue());
		panelBox.add(panel);
		panelBox.add(Box.createHorizontalGlue());
		this.add(panelBox);
		this.add(Box.createVerticalGlue());
		
		this.pack();
	}

	private void generate() {
		try {
			wrapper.createGraph(this.wrapper.build());
			System.out.println("done");
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out
					.println("Something went wrong constructing instances of builders from the given builder classes");
			System.out.println(
					"check to make sure that all the builder classes have a constructor that takes a IBuilder");
		}

	}



}
