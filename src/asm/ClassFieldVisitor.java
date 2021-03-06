package asm;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Type;

import records.InstanceVarRecord;

public class ClassFieldVisitor extends ClassVisitor {
	private HashSet<InstanceVarRecord> fields = new HashSet<InstanceVarRecord>();

	public ClassFieldVisitor(int arg0) {
		super(arg0);
	}

	public ClassFieldVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();
		fields.add(new InstanceVarRecord(name, type, access));
		return toDecorate;
	}

	public HashSet<InstanceVarRecord> getFields() {
		return fields;
	}
}