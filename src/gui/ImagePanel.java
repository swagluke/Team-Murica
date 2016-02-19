package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class ImagePanel extends APanel implements ImageObserver {
	private static final long serialVersionUID = -4211101320957937220L;
	private ImageIcon img;
	private String loadingIconPath = "loading.gif";
	private boolean loading;

	public ImagePanel(Gui gui) {
		super(gui);
	}

	@Override
	protected void setUp() {
		this.loading = false;
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		String imgPath = "out.png";
		this.changeImage(imgPath);
		this.setPreferredSize(new Dimension(1700, 800));
		this.setMinimumSize(new Dimension(500, 500));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.drawImage(this.img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void loading() {
		this.img.getImage().flush();
		this.changeImage(this.loadingIconPath);
		this.loading = true;
		this.img.setImageObserver(this);
	}

	public void changeImage(String path) {
		this.loading = false;
		if (this.img != null) {
			this.img.getImage().flush();
		}
		this.img = new ImageIcon(path);
		this.repaint();
	}
	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		if (this.loading) {
			repaint();
		}
		return true;
	}

}
