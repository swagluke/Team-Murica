package gui;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PROPERTIES_PATH = "appProperties";

	private UmlWrapper wrapper;
	private JPanel currentPanel;
	private int padding;
	private String configPath;

	public Gui() throws IOException {
		this.padding = 25;
		// read out properties
		String[] args = new String[] { "headfirst.composite.menu.Menu", "headfirst.composite.menu.MenuComponent",
				"headfirst.composite.menu.MenuItem" };
		this.wrapper = new UmlWrapper();
		try {
			this.loadConfig(DEFAULT_PROPERTIES_PATH);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			System.out.println("Default Properties file has invalid syntax");
			System.exit(-1);
		}

//		for (String arg : args) {
//			this.wrapper.addClass(arg);
//		}

		// this.setPreferredSize(new Dimension(600, 600));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(400, 400));
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		    	Path path = Paths.get("temp.dot");
		    	try {
					Files.delete(path);
				} catch (IOException e) {}
		    	path = Paths.get("out.png");
		    	try {
					Files.delete(path);
				} catch (IOException e) {}

		    }
		});
		this.loadInitialScreen();

		this.setVisible(true);
		this.toFront();
	}

	private void loadInitialScreen() {
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		// this.add(Box.createVerticalGlue());
		this.replacePanel(new InitialPanel(this), true);
		// this.add(new ResultPanel(this));
		// this.pack();
		// this.replacePanel(new ResultPanel(this));
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
			horizontalBox.add(Box.createHorizontalStrut(this.padding));
			horizontalBox.add(Box.createHorizontalGlue());
			horizontalBox.add(newPanel);
			horizontalBox.add(Box.createHorizontalGlue());
			horizontalBox.add(Box.createHorizontalStrut(this.padding));

			JPanel superPanel = new JPanel();
			superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.PAGE_AXIS));
			superPanel.add(Box.createVerticalStrut(this.padding));
			superPanel.add(Box.createVerticalGlue());
			superPanel.add(horizontalBox);
			superPanel.add(Box.createVerticalGlue());
			superPanel.add(Box.createVerticalStrut(this.padding));

			this.currentPanel = superPanel;
			this.setMinimumSize(new Dimension(this.padding * 4 + (int) newPanel.getMinimumSize().getWidth(),
					(int) (this.padding * 4 + newPanel.getMinimumSize().getHeight())));
		} else {
			this.currentPanel = newPanel;
			this.setMinimumSize(new Dimension(this.currentPanel.getMinimumSize().width,
					this.currentPanel.getMinimumSize().height + 50));
			this.getContentPane().setMinimumSize(this.currentPanel.getMinimumSize());
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

	public void loadConfig(String path) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.loadConfig(new File(path));
	}

	public void loadConfig(File path) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Properties p = new Properties();
		this.configPath = path.getName();
		InputStream inputStream = new FileInputStream(path);// filename may change with whatever config file you choose
		p.load(inputStream);
		inputStream.close();
		this.wrapper.setProperties(p);
		System.out.println("loaded config");

	}

	public void analyze() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		this.wrapper.execute();
	}
	
	public String getProperty(String name) {
		return this.wrapper.getProperty(name);
	}

	public void cleanWrapper() {
		this.wrapper.reset();
	}

	public void addClass(String className) {
		this.wrapper.addClass(className);
	}
	
	public String getCurrentConfigPath() {
		return this.configPath;
	}
}
