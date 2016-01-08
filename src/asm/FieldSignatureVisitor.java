package asm;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;

import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;

public class FieldSignatureVisitor extends SignatureVisitor {

	private HashSet<String> fields = new HashSet<String>();
	public FieldSignatureVisitor(int arg0) {
		super(arg0);
	}
	
	public void visitClassType(String name) {
		try {
			Class<?> c = Class.forName(Type.getObjectType(name).getClassName());
			if (!Collection.class.isAssignableFrom(c) && !AbstractMap.class.isAssignableFrom(c)) {
				this.fields.add(Type.getObjectType(name).getClassName());
//				System.out.println(name + " *** ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public HashSet<String> getFields() {
		return this.fields;
	}
}

