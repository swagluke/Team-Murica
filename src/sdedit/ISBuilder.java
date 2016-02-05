package sdedit;

import org.objectweb.asm.ClassVisitor;

import records.ISequenceRecord;

public interface ISBuilder {

	public ClassVisitor getVisitor();
	ISequenceRecord build();
	ISequenceRecord build(ClassVisitor visitor);
	String getSequenceUML();
}
