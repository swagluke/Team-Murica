package asm;

import org.objectweb.asm.MethodVisitor;

import records.MethodSignature;
import sdedit.SequenceBuilder;

public class MethodSequenceInsVisitor extends MethodVisitor {
	private SequenceBuilder lastSequenceBuilder;
	// public String methodName;

	public MethodSequenceInsVisitor(int arg0, MethodVisitor arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
		// creat new method signature
		// TODO thread the parent method signature through
		MethodSignature methodSignature = new MethodSignature(owner, name, desc);
		System.out.println("HEllo name: " + name + " owner: " + owner + " desc: " + desc);

		if (this.lastSequenceBuilder.getRecursionDepth() > 1) {
			SequenceBuilder sequenceBuilder = new SequenceBuilder(methodSignature, this.lastSequenceBuilder);
			// create new sequence builder here
			sequenceBuilder.build();
		}

	}

	public SequenceBuilder getLastSequenceBuilder() {
		return lastSequenceBuilder;
	}

	public void setLastSequenceBuilder(SequenceBuilder lastSequenceBuilder) {
		this.lastSequenceBuilder = lastSequenceBuilder;
	}

	// public void setMethodName(String name) {
	// this.methodName = name;
	// }

}
