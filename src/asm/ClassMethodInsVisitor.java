package asm;

import org.objectweb.asm.MethodVisitor;

public class ClassMethodInsVisitor extends MethodVisitor{

	public ClassMethodInsVisitor(int arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		//Do stuff
	}

}
