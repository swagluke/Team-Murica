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

//		this.setPreferredSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.loadInitialScreen();

		this.setVisible(true);
		this.toFront();
	}

	private void loadInitialScreen() {
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
//		this.add(Box.createVerticalGlue());
//		 this.replacePanel(new InitialPanel(this), true);
		this.add(new ResultPanel(this));
		this.pack();
//		this.replacePanel(new ResultPanel(this));
	}

	private void generate() {
		// try {
		// wrapper.createGraph();
		// System.out.println("done");
		// } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
		// | IllegalArgumentException | InvocationTargetException e) {
		// e.printStackTrace();
		// System.out
		// .println("Something went wrong constructing instances of builders from the given builder classes");
		// System.out.println(
		// "check to make sure that all the builder classes have a constructor that takes a IBuilder");
		// }

	}

	public void replacePanel(APanel newPanel) {
		this.replacePanel(newPanel, false);
	}

	public void replacePanel(APanel newPanel, boolean padding) {
		this.removeCurrentPanel();
		if (padding) {
			Box horizontalBox = Box.createHorizontalBox();
			horizontalBox.add(Box.createHorizontalGlue());
			horizontalBox.add(newPanel);
			horizontalBox.add(Box.createHorizontalGlue());

			JPanel superPanel = new JPanel();
			superPanel.add(Box.createVerticalGlue());
			superPanel.add(horizontalBox);
			superPanel.add(Box.createVerticalGlue());

			this.currentPanel = superPanel;
		} else {
			this.currentPanel = newPanel;
		}
		this.add(this.currentPanel);
		this.pack();
		this.repaint();
	}

	public void removeCurrentPanel() {
		if (this.currentPanel != null) {
			this.remove(this.currentPanel);
		}
	}
	
	public void updatePaint() {
		if (this.currentPanel != null) {
			this.currentPanel.revalidate();
		}
		this.pack();
	}

}
