package gui;

import dot.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private UmlWrapper wrapper;
	private JPanel currentPanel;

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
		APanel panel = new InitialPanel(this);
		this.replacePanel(panel);
//		Box panelBox = Box.createHorizontalBox();
//		panelBox.add(Box.createHorizontalGlue());
//		panelBox.add(panel);
//		panelBox.add(Box.createHorizontalGlue());
//		this.add(panelBox);
//		this.add(Box.createVerticalGlue());
//		
//		this.pack();
	}

	private void generate() {
//		try {
//			wrapper.createGraph();
//			System.out.println("done");
//		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
//				| IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//			System.out
//					.println("Something went wrong constructing instances of builders from the given builder classes");
//			System.out.println(
//					"check to make sure that all the builder classes have a constructor that takes a IBuilder");
//		}

	}
	
	public void replacePanel(APanel newPanel) {
		if (this.currentPanel != null) {
			this.remove(this.currentPanel);
		}
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(newPanel);
		horizontalBox.add(Box.createHorizontalGlue());
		
		JPanel superPanel = new JPanel();
		superPanel.add(Box.createVerticalGlue());
		superPanel.add(horizontalBox);
		superPanel.add(Box.createVerticalGlue());

		this.currentPanel = superPanel;
		this.add(this.currentPanel);
		this.pack();
		this.repaint();
		
	}



}
