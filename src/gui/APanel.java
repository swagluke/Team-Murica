package gui;

import javax.swing.JPanel;

abstract public class APanel extends JPanel {
	private static final long serialVersionUID = 7005402849745054392L;
	private Gui gui;

	public APanel(Gui gui) {
		this.gui = gui;
		this.setUp();
	}
	
	public Gui getGui() {
		return this.gui;
	}
	
	public void replacePanel(APanel newPanel) {
		this.gui.replacePanel(newPanel);
	}

	abstract protected void setUp();
}
