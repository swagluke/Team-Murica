package asm;

import java.util.HashSet;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class ClassMethodInsVisitor extends MethodVisitor {

	HashSet<String> uses = new HashSet<String>();

	public ClassMethodInsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	// TODO may need to get the classname in here and make it a hashmap?
	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
//		if (itf) {
//			System.out.println("name: " + name + " owner: " + owner + " desc: " + desc);
//			System.out.println(Type.getMethodType(desc));
//			System.out.println(Type.getReturnType(desc));
//			System.out.println();
//			System.out.println();
			uses.add(owner);
//		}
	}

}
