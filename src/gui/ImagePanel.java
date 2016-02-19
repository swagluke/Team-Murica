package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class ImagePanel extends APanel {
	private static final long serialVersionUID = -4211101320957937220L;

	public ImagePanel(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		this.add(new JLabel("Hello"));
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setMinimumSize(new Dimension(500, 500));
	}

}
