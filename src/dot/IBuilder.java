package dot;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import dot.records.IClassRecord;

public interface IBuilder {
	public ClassVisitor getVisitor();
	public IClassRecord build();
}
