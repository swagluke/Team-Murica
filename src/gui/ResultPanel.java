package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;

import net.sf.sdedit.util.UIUtilities.Grid;

public class ResultPanel extends APanel {
	private static final long serialVersionUID = -8488698412916149660L;

	public ResultPanel(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.setLayout(new GridBagLayout());
		this.setUpDirectoryPane();
		this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	private void setUpDirectoryPane() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.3;
		c.weighty = 1.0;
		c.gridwidth = 1;
		c.gridheight = GridBagConstraints.REMAINDER;
//		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.VERTICAL;
		this.add(new DirectoryPane(this.getGui()), c);
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy=0;
		c.weightx = 0.7;
		c.weighty=1.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = GridBagConstraints.REMAINDER;
		this.add(new ImagePanel(this.getGui()), c);
	}

}
