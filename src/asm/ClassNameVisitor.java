package asm;

import org.objectweb.asm.ClassVisitor;

public class ClassNameVisitor extends ClassVisitor{
	private String name;
	public ClassNameVisitor(int arg0) {
		super(arg0);
		}
	public ClassNameVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.name = name;
		super.visit(version, access, name, signature, superName, interfaces);
	}
	public String getName() {
		return name;
	}


}
