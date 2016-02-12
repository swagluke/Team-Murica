package src.problem.sprites;

import java.awt.Dimension;
import java.awt.Shape;
import java.util.Iterator;

public abstract class ISprite {
	public void add(ISprite sprite) {
		throw new UnsupportedOperationException();
	}
	public void remove(ISprite sprite) {
		throw new UnsupportedOperationException();
	}
	public ISprite getChild(int i) {
		throw new UnsupportedOperationException();
	}
	public void move(Dimension space) {
		throw new UnsupportedOperationException();
	}
	public Shape getShape() {
		throw new UnsupportedOperationException();
	}
	
	abstract public Iterator<ISprite> createIterator();
}
