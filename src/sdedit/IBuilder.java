package sdedit;

import org.objectweb.asm.ClassVisitor;

import records.ISequenceRecord;

public interface IBuilder {

	public ClassVisitor getVisitor();
	ISequenceRecord build();
	ISequenceRecord build(ClassVisitor visitor);
	String getSequenceUML();
}
