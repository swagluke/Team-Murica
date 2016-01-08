package asm;


import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dot.records.MethodRecord;

public class ClassMethodVisitor extends ClassVisitor {
	private ArrayList<MethodRecord> methods = new ArrayList<MethodRecord>();

	public ClassMethodVisitor(int arg0) {
		super(arg0);
	}

	public ClassMethodVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}
	
	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		
		String returnType = Type.getReturnType(desc).getClassName();
		Type[] argTypes = Type.getArgumentTypes(desc);
		List<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
		}
		
		String symbol = "";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		}
		methods.add(new MethodRecord(
				access,
				name,
				returnType,
				argTypes,
				stypes
				));
		//System.out.println("    method " + symbol + returnType + " " + name + " " + stypes.toString());
		return toDecorate;
	}

	public ArrayList<MethodRecord> getMethods() {
		return methods;
	}

}
