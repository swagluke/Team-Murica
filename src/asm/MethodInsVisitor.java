package asm;

import java.util.HashSet;

import org.objectweb.asm.MethodVisitor;

public class MethodInsVisitor extends MethodVisitor {

	HashSet<String> instantiations = new HashSet<String>();
	public String methodName;

	public MethodInsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		// System.out.println("name: " + name + " owner: " + owner + " desc: " + desc);
		if (name.equals("<init>")) {
			instantiations.add(owner);
		}
	}

	public HashSet<String> getInstantiations() {
		return this.instantiations;
	}

}
