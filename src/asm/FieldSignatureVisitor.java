package asm;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;

import org.objectweb.asm.Type;
import org.objectweb.asm.signature.SignatureVisitor;

public class FieldSignatureVisitor extends SignatureVisitor {

	private HashSet<String> nestedFields = new HashSet<String>();
	public FieldSignatureVisitor(int arg0) {
		super(arg0);
	}
	
	public void visitClassType(String name) {
		try {
			Class<?> c = Class.forName(Type.getObjectType(name).getClassName());
			if (!Collection.class.isAssignableFrom(c) && !AbstractMap.class.isAssignableFrom(c)) {
				this.nestedFields.add(Type.getObjectType(name).getClassName());
			}
		} catch (ClassNotFoundException e) {
//			assume its not a collection
			this.nestedFields.add(Type.getObjectType(name).getClassName());
		}
	}
	
	public HashSet<String> getNestedFields() {
		return this.nestedFields;
	}
}

