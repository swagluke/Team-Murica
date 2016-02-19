package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;

public class ResultPanel extends APanel {
	private static final long serialVersionUID = -8488698412916149660L;

	public ResultPanel(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.setLayout(new BorderLayout(25, 25));
		this.setUpDirectoryPane();
		this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	private void setUpDirectoryPane() {
		this.add(new DirectoryPane(this.getGui()), BorderLayout.LINE_START);
	}

}
