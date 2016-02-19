package gui;

import java.awt.Color;
import java.awt.Component;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class ResultPanel extends APanel {
	private static final long serialVersionUID = -8488698412916149660L;

	public ResultPanel(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.setUpDirectoryPane();
		this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	private void setUpDirectoryPane() {
//		Box box = Box.createHorizontalBox();
//		box.add(new DirectoryPane(this.getGui()));
//		box.add(new ImagePanel(this.getGui()));

//		this.add(box);
		DirectoryPane directoryPanel = new DirectoryPane(this.getGui());
		ImagePanel imagePanel = new ImagePanel(this.getGui());
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(directoryPanel), new JScrollPane(imagePanel));
		this.add(splitPane);
//		this.add(new ImagePanel(this.getGui()));
//		this.add(new DirectoryPane(this.getGui()));
		for (Component c : this.getComponents()) {
			System.out.println(c);
		}
		System.out.println(this.getLocation());
	}

}
