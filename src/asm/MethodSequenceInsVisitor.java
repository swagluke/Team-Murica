package asm;

import org.objectweb.asm.MethodVisitor;

import generictree.GenericTreeNode;
import records.MethodSignature;
import sdedit.SequenceBuilder;

public class MethodSequenceInsVisitor extends MethodVisitor {
	private SequenceBuilder sequenceBuilder;
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
		System.out.println();
		for (int i=3-this.sequenceBuilder.getRecursionDepth(); i > 0; i--) {
		System.out.print("\t");
		}
		System.out.println("\tname: " + name + " owner: " + owner + " desc: " + desc);
		GenericTreeNode<MethodSignature> node = new GenericTreeNode<MethodSignature>(methodSignature);
		this.sequenceBuilder.addMethodSignature(node);
		if (this.sequenceBuilder.getRecursionDepth() > 1) {
			SequenceBuilder sequenceBuilder = new SequenceBuilder(node, this.sequenceBuilder);
			// create new sequence builder here
			sequenceBuilder.build();
		}
//		{sdedit/Foo: <init>(()V)=0, java/lang/Object: <init>(()V)=1, sdedit/Bar: <init>((Lsdedit/Foo;)V)=1, sdedit/Foo: addOne((I)I)=1}
	}

	public SequenceBuilder getLastSequenceBuilder() {
		return sequenceBuilder;
	}

	public void setSequenceBuilder(SequenceBuilder lastSequenceBuilder) {
		this.sequenceBuilder = lastSequenceBuilder;
	}

	// public void setMethodName(String name) {
	// this.methodName = name;
	// }

}
