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
		// Box box = Box.createHorizontalBox();
		// box.add(new DirectoryPane(this.getGui()));
		// box.add(new ImagePanel(this.getGui()));

		// this.add(box);
		DirectoryPane directoryPanel = new DirectoryPane(this.getGui());
		JScrollPane scrollPane = new JScrollPane(directoryPanel);

		scrollPane.setMinimumSize(directoryPanel.getMinimumSize());
		ImagePanel imagePanel = new ImagePanel(this.getGui());

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, imagePanel);
		this.add(splitPane);
		splitPane.setContinuousLayout(true);
		// this.getGui().updatePaint();
		splitPane.setDividerLocation(200);
		splitPane.setDividerSize(5);
		this.setMinimumSize(splitPane.getMinimumSize());
		// splitPane.setDividerLocation(0.3);
		// splitPane.setResizeWeight(0.8);
		// this.add(new ImagePanel(this.getGui()));
		// this.add(new DirectoryPane(this.getGui()));
	}

}
