package src.problem.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

import src.problem.sprites.AllSprites;
import src.problem.sprites.ISprite;
import src.problem.sprites.SpriteFactory;

public class AnimationPanel extends JPanel {
	private static final long serialVersionUID = -9141525646098105526L;
	
	private ISprite allSprites;
	private volatile boolean animating;
	private long sleepTime;

	public AnimationPanel(long sleepTime) {
		super(true);
		animating = false;
//		allSprites = Collections.synchronizedList(new ArrayList<ISprite>());
		allSprites = new AllSprites();
		this.sleepTime = sleepTime;
	}
	
	public void add(ISprite s) {
		allSprites.add(s);
		this.repaint();
	}	
	
	@Override
	public Dimension getSize() {
		Dimension d = super.getSize();
		d.width -= SpriteFactory.WIDTH;
		d.height -= SpriteFactory.HEIGHT;
		return d;
	}
	
	public void animate() {
		if(animating)
			return;
		
		animating = true;
		
		Thread animator = new Thread() {
			@Override
			public void run() {
				while(animating) {
					long start = System.currentTimeMillis();

					synchronized(allSprites) {
						Iterator<ISprite> itr = allSprites.createIterator();
						while(itr.hasNext()) {
							ISprite sprite = itr.next();
							sprite.move(getSize());
						}
					}
					repaint();

					long end = System.currentTimeMillis();
					long delta = sleepTime - (end - start);
					delta = (delta > 0)? delta : 0;
					
					try {
						Thread.sleep(delta);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		animator.start();
	}
	
	public void reset() {
		animating = false;
		allSprites = new AllSprites();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics = (Graphics2D)g;
		synchronized(allSprites) {
			Iterator<ISprite> itr = allSprites.createIterator();
			while(itr.hasNext()) {
				ISprite sprite = itr.next();
				if (sprite.getShape() != null) {
					graphics.draw(sprite.getShape());
				}
			}
		}
	}
}
