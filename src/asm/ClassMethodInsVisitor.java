package asm;

import java.util.HashSet;

import org.objectweb.asm.MethodVisitor;

public class ClassMethodInsVisitor extends MethodVisitor{

	HashSet<String> uses =new HashSet<String>();
	
	public ClassMethodInsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	//TODO may need to get the classname in here and make it a hashmap?
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf){
//		System.out.println("owner: "+ owner +" desc: "+desc);
		uses.add(owner);
	}

}
