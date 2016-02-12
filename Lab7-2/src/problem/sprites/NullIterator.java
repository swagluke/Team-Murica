package src.problem.sprites;
 
import java.util.Iterator;
  
public class NullIterator implements Iterator<ISprite> {
   
	public ISprite next() {
		return null;
	}
  
	public boolean hasNext() {
		return false;
	}
   
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
