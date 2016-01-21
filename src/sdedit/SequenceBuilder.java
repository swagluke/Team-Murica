package sdedit;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassMethodVisitor;
import asm.SequenceMethodInsVisitor;
import jdk.internal.org.objectweb.asm.Type;
import records.GenericTreeNode;
import records.ISequenceRecord;
import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilder implements IBuilder {
	public static int created = 0;
	public int id;
	MethodSignature signature;
	private ClassReader reader;
	private ClassDeclarationVisitor declVisitor;
	// ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
	private SequenceMethodInsVisitor methodVisitor;
	private SequenceRecord record;
	private int recursionDepthLeft;
	private MethodSignature methodSignature;
	private SequenceBuilder previous;
	private GenericTreeNode<MethodSignature> node;

	public SequenceBuilder(MethodSignature m) {
		this(m, 5);
	}

	public SequenceBuilder(MethodSignature m, int recursionDepth) {
		this.id = created;
		created++;
		this.recursionDepthLeft = recursionDepth;
		this.node = new GenericTreeNode<MethodSignature>(m);
		this.record = new SequenceRecord(this.node);
//		this.record.setRoot(this.node);
		// this.addMethodSignature(m);
		for (int i=3-this.getRecursionDepth(); i > 0; i--) {
		System.out.print("\t");
		}

		System.out.println(
				System.identityHashCode(this.node)+" Created new sequence builder for " + m.getClassName() + ", depth: " + this.recursionDepthLeft);
		try {
			reader = new ClassReader(Type.getObjectType(m.getClassName()).getClassName());
		} catch (IOException e) {
			System.err.println(m.getClassName());
			e.printStackTrace();
		}
		this.methodSignature = m;

		this.declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		this.methodVisitor = new SequenceMethodInsVisitor(Opcodes.ASM5, this.declVisitor);

		this.methodVisitor.setMethodName(m.getMethodName());
		this.methodVisitor.setSignature(m.getSignature());
		this.methodVisitor.setSequenceBuilder(this);

	}

	public SequenceBuilder(GenericTreeNode<MethodSignature> node, SequenceBuilder previous) {
		MethodSignature m = node.getData();
		this.id = created;
		created++;
		this.node = node;
		this.previous = previous;
		this.recursionDepthLeft = previous.getRecursionDepth() - 1;
		// this.addMethodSignature(m);
		for (int i=3-this.getRecursionDepth(); i > 0; i--) {
		System.out.print("\t");
		}

		System.out.println(System.identityHashCode(this.node)+" Created new sequence builder for " + m.getClassName() + ": " + m.getMethodName()
				+ ", depth: " + this.recursionDepthLeft);
		for (int i=3-this.getRecursionDepth(); i > 0; i--) {
		System.out.print("\t");
		}
		System.out.println("previous: " + previous.id + ": " + System.identityHashCode(previous.getNode()));
		try {
			reader = new ClassReader(Type.getObjectType(m.getClassName()).getClassName());
		} catch (IOException e) {
			System.err.println(m.getClassName());
			e.printStackTrace();
		}
		this.methodSignature = m;

		this.declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		this.methodVisitor = new SequenceMethodInsVisitor(Opcodes.ASM5, this.declVisitor);

		this.methodVisitor.setMethodName(m.getMethodName());
		this.methodVisitor.setSignature(m.getSignature());
		this.methodVisitor.setSequenceBuilder(this);
	}

	public void addMethodSignature(GenericTreeNode<MethodSignature> methodSignatureNode) {
		// if (this.previous != null) {
		// this.previous.addMethodSignature(m);
		// } else {
		// this.record.addMethodCall(m);
		// }
		for (int i=3-this.getRecursionDepth(); i > 0; i--) {
		System.out.print("\t");
		}
		System.out.println("added " + methodSignatureNode.toString() + " to " + System.identityHashCode(this.node));
		this.node.addChild(methodSignatureNode);
	}

	@Override
	public ClassVisitor getVisitor() {
		return declVisitor;
	}

	public ClassMethodVisitor getMethodVisitor() {
		return this.methodVisitor;
	}

	public ISequenceRecord build(ClassVisitor visitor) {
		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		// record.setClassName(declVisitor.getClassName());
		// record.setMethodsList(methodVisitor.getMethods());
		// for(MethodInsVisitor m:methodInstVisitor.methodVisitors){
		// if(m.methodName.equals(methodSignature.methodName)){
		//
		// }
		// }
		return record;

	}

	@Override
	public ISequenceRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public String getSequenceUML() {
		// TODO Auto-generated method stub
		return null;
	}

	public MethodSignature getSignature() {
		return signature;
	}

	public int getRecursionDepth() {
		return recursionDepthLeft;
	}

	public GenericTreeNode<MethodSignature> getNode() {
		return this.node;
	}
}
