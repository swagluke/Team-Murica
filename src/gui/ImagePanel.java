package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ImagePanel extends APanel {
	private static final long serialVersionUID = -4211101320957937220L;
	private Image img;

	public ImagePanel(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		String impPath = "out.png";
		this.img = new ImageIcon(impPath).getImage();
//		this.img = img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
//		this.add(new JLabel(img));
		this.setPreferredSize(new Dimension(1000, 1000));
		this.setMinimumSize(new Dimension(500, 500));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(this.img,  0,  0,this.getWidth(), this.getHeight(), null);
	}

}
