package asm;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureReader;

public class ClassFieldSignatureVisitor extends ClassFieldVisitor {
	
	private HashSet<String> associationNames = new HashSet<String>();

	public ClassFieldSignatureVisitor(int arg0) {
		super(arg0);
	}

	public ClassFieldSignatureVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}
	
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		FieldVisitor toDecorate = super.visitField(access, name, desc, signature, value);
		String type = Type.getType(desc).getClassName();
		if (signature != null) {
			SignatureReader signatureReader = new SignatureReader(signature);
			FieldSignatureVisitor signatureVisitor = new FieldSignatureVisitor(Opcodes.ASM5);
			signatureReader.accept(signatureVisitor);
			associationNames.addAll(signatureVisitor.getNestedFields());
		} else {
			associationNames.add(type);
		}
		return toDecorate;
	}

	public HashSet<String> getAssociationNames() {
		return associationNames;
	}
}
