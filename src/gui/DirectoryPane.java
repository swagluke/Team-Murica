package gui;

import java.awt.Color;

import javax.swing.BorderFactory;

public class DirectoryPane extends APanel {
	private static final long serialVersionUID = -1869406409371334949L;

	public DirectoryPane(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.setBorder(BorderFactory.createLineBorder(Color.red));

	}

}
