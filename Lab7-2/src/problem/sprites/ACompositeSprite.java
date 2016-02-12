package src.problem.sprites;

import java.awt.Dimension;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Iterator;

abstract public class ACompositeSprite extends ISprite{
	private ArrayList<ISprite> spriteComponents;
	
	public ACompositeSprite() {
		this.spriteComponents = new ArrayList<ISprite>();
	}
	
	public void add(ISprite sprite) {
		this.spriteComponents.add(sprite);
	}

	public void remove(ISprite sprite) {
		this.spriteComponents.remove(sprite);
	}
	public ISprite getChild(int i) {
		return this.spriteComponents.get(i);
	}
	
	public void move(Dimension space) {
	}
	
	public Shape getShape() {
		return null;
	}


	@Override
	public Iterator<ISprite> createIterator() {
		return new CompositeIterator(spriteComponents.iterator());
	}

	
}
