package sdedit;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import asm.SequenceMethodInsVisitor;
import jdk.internal.org.objectweb.asm.Type;
import records.ISequenceRecord;
import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilder implements IBuilder {
	MethodSignature signature;
	private ClassReader reader;
	private ClassDeclarationVisitor declVisitor;
	// ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
	private SequenceMethodInsVisitor methodVisitor;
	private SequenceRecord record;
	private int recursionDepthLeft;
	private MethodSignature methodSignature;
	private SequenceBuilder previous;

	public SequenceBuilder(MethodSignature m) {
		this(m, null);
	}

	public SequenceBuilder(MethodSignature m, SequenceBuilder previous) {
		this.previous = previous;
		if (previous != null) {
			this.recursionDepthLeft = previous.getRecursionDepth() - 1;
		} else {
			this.recursionDepthLeft = 5;
			this.record = new SequenceRecord();
		}
		this.addMethodSignature(m);
		System.out.println(
				"\nCreated new sequence builder for " + m.getClassName() + ", depth: " + this.recursionDepthLeft);
		try {
			reader = new ClassReader(Type.getObjectType(m.getClassName()).getClassName());
		} catch (IOException e) {
			System.err.println(m.getClassName());
			e.printStackTrace();
		}
		this.methodSignature = m;

		this.declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		this.methodVisitor = new SequenceMethodInsVisitor(Opcodes.ASM5, this.declVisitor);
		;

		this.methodVisitor.setMethodName(m.getMethodName());
		this.methodVisitor.setSignature(m.getSignature());
		this.methodVisitor.setSequenceBuilder(this);

	}

	public void addMethodSignature(MethodSignature m) {
		if (this.previous != null) {
			this.previous.addMethodSignature(m);
		} else {
			this.record.addMethodCall(m);
		}
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
}
