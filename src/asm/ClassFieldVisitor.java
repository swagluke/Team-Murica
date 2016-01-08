package asm;

import java.util.ArrayList;
import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureReader;

import dot.records.InstanceVarRecord;

public class ClassFieldVisitor extends ClassVisitor {
	private ArrayList<InstanceVarRecord> fields = new ArrayList<InstanceVarRecord>();

	public ClassFieldVisitor(int arg0) {
		super(arg0);
	}

	public ClassFieldVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		
		HashSet<String> nestedFields = new HashSet<String>();
		String type = Type.getType(desc).getClassName();
//		System.out.println(" " + type + " " + name + ": " + signature);
		if (signature != null) {
			SignatureReader foo = new SignatureReader(signature);
			FieldSignatureVisitor blah = new FieldSignatureVisitor(Opcodes.ASM5);
			foo.accept(blah);
			nestedFields = blah.getFields();
//			System.out.println(nestedFields.toString());
		}

		fields.add(new InstanceVarRecord(name, type, access, nestedFields));
		return toDecorate;
	}

	public ArrayList<InstanceVarRecord> getFields() {
		return fields;
	}
}