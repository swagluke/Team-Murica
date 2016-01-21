package asm;

import org.objectweb.asm.MethodVisitor;

import records.MethodSignature;
import records.SequenceRecord;
import sdedit.SequenceBuilder;

public class MethodSequenceInsVisitor extends MethodVisitor {
	private SequenceBuilder lastSequenceBuilder;
//	public String methodName;

	public MethodSequenceInsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		// creat new method signature
		MethodSignature methodSignature = new MethodSignature(owner, name, desc);
		SequenceBuilder sequenceBuilder = new SequenceBuilder(methodSignature, this.lastSequenceBuilder);
		// create new sequence builder here
		 System.out.println("HEllo name: " + name + " owner: " + owner + " desc: " + desc);
		 sequenceBuilder.build();
		 
	}

	public SequenceBuilder getLastSequenceBuilder() {
		return lastSequenceBuilder;
	}

	public void setLastSequenceBuilder(SequenceBuilder lastSequenceBuilder) {
		this.lastSequenceBuilder = lastSequenceBuilder;
	}

//	public void setMethodName(String name) {
//		this.methodName = name;
//	}

}
