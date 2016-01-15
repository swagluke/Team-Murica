package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.IClassRecord;

public interface IBuilder {
	/**
	 * Used to get the visitor of the previous builder, used to successively build a large visitor
	 * @return
	 */
	public ClassVisitor getVisitor();
	/**
	 * called once on the outer builder, used to build all builders in the chain
	 * @return
	 */
	IClassRecord build();
	IClassRecord build(ClassVisitor visitor);
	String getClassUML();
	HashSet<String> getClassList();
}
