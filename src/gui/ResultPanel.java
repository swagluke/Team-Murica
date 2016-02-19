package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
		// Box box = Box.createHorizontalBox();
		// box.add(new DirectoryPane(this.getGui()));
		// box.add(new ImagePanel(this.getGui()));

		// this.add(box);
		DirectoryPane directoryPanel = new DirectoryPane(this.getGui());
		JScrollPane scrollPane = new JScrollPane(directoryPanel);
		scrollPane.setMinimumSize(directoryPanel.getMinimumSize());
		ImagePanel imagePanel = new ImagePanel(this.getGui());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, imagePanel);
		splitPane.setContinuousLayout(true);
		splitPane.setDividerLocation(200);
		splitPane.setDividerSize(5);
		this.add(splitPane);
		

		int width = (int) (directoryPanel.getMinimumSize().getWidth() + imagePanel.getMinimumSize().getWidth()) + 50;
		int height = (int) Math.max(directoryPanel.getMinimumSize().getHeight(),
				imagePanel.getMinimumSize().getHeight());
		Dimension minSize = new Dimension(width, height);
		this.setMinimumSize(minSize);
	}

}
