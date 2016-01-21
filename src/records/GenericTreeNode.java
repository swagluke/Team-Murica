package records;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// from http://vivin.net/2010/01/30/generic-n-ary-tree-in-java/
public class GenericTreeNode<T> implements Iterable<T>{

    public T data;
    public List<GenericTreeNode<T>> children;

    public GenericTreeNode() {
        super();
        children = new ArrayList<GenericTreeNode<T>>();
    }

    public GenericTreeNode(T data) {
        this();
        setData(data);
    }

    public List<GenericTreeNode<T>> getChildren() {
        return this.children;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }

    public void setChildren(List<GenericTreeNode<T>> children) {
        this.children = children;
    }

    public void addChild(GenericTreeNode<T> child) {
        children.add(child);
    }

    public void addChildAt(int index, GenericTreeNode<T> child) throws IndexOutOfBoundsException {
        children.add(index, child);
    }

    public void removeChildren() {
        this.children = new ArrayList<GenericTreeNode<T>>();
    }

    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        children.remove(index);
    }

    public GenericTreeNode<T> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return getData().toString();
    }

    public boolean equals(GenericTreeNode<T> node) {
        return node.getData().equals(getData());
    }

    public int hashCode() {
        return getData().hashCode();
    }

    public String toStringVerbose() {
        String stringRepresentation = getData().toString() + ":[";

        for (GenericTreeNode<T> node : getChildren()) {
            stringRepresentation += node.getData().toString() + ", ";
        }

        //Pattern.DOTALL causes ^ and $ to match. Otherwise it won't. It's retarded.
        Pattern pattern = Pattern.compile(", $", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stringRepresentation);

        stringRepresentation = matcher.replaceFirst("");
        stringRepresentation += "]";

        return stringRepresentation;
    }

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
    
	public class GenericTreeIterator implements Iterator<T>{
		Stack<GenericTreeNode<T>> toVisit = new Stack<GenericTreeNode<T>>();
		GenericTreeNode<T> current;
		public GenericTreeIterator(GenericTreeNode<T> node){
			current = node;
			for(GenericTreeNode<T> n:node.children)
				toVisit.push(n);
		}
		@Override
		public boolean hasNext() {
			return !toVisit.isEmpty();
		}

		@Override
		public T next() {
			GenericTreeNode<T> ret = current;
			current = toVisit.pop();
			for(GenericTreeNode<T> n:current.children)
				toVisit.push(n);
			return ret.data;
		}
		
	}
}