package asm;

import java.util.Arrays;
import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import records.MethodRecord;
import records.MethodSignature;
import sdedit.SequenceBuilder;

public class SequenceMethodInsVisitor extends ClassMethodVisitor {
	private HashSet<MethodRecord> methods = new HashSet<MethodRecord>();
	public HashSet<MethodSequenceInsVisitor> methodVisitors = new HashSet<MethodSequenceInsVisitor>();
	private String methodName;
	private Type[] signature;
	private HashSet<String> returnParams = new HashSet<String>();
	private SequenceBuilder sequenceBuilder;
	private MethodSignature originalMethod;

	public SequenceMethodInsVisitor(int arg0) {
		super(arg0);
	}

	public SequenceMethodInsVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		// System.out.println("sequence method ins visitor, name: " + name + ": " + this.methodName + ", " + desc
		// + ": " + this.signature);
		// System.out.println(desc);
		if (this.originalMethod != null && name.equals(this.originalMethod.getClassName()) && Arrays.equals(Type.getArgumentTypes(desc), this.originalMethod.getMethodArgs())) {
			this.originalMethod.setReturnType(Type.getReturnType(desc));
		}
		if (name.equals(this.methodName) && Arrays.equals(this.signature, Type.getArgumentTypes(desc))){
//				desc.equals(this.signature)) {

//			for (int i = 3 - this.sequenceBuilder.getRecursionDepth(); i > 0; i--) {
//				System.out.print("\t");
//			}
//			System.out.println("found method: " + name + ", " + desc);
			MethodSequenceInsVisitor methodVisitor = new MethodSequenceInsVisitor(Opcodes.ASM5, toDecorate);
			methodVisitor.setSequenceBuilder(this.sequenceBuilder);
			methodVisitors.add(methodVisitor);

			return methodVisitor;
		}
		return toDecorate;
	}

	public HashSet<MethodRecord> getMethods() {
		return methods;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Type[] getSignature() {
		return signature;
	}

	public void setSignature(Type[] signature) {
		this.signature = signature;
	}

	private Class<?> isSubClassOfReturnOrParam(String instantiation) {
		try {
			Class<?> instantiationClass = Class.forName(Type.getObjectType(instantiation).getClassName());
			for (String returnParam : returnParams) {
				if (!returnParam.equals("java.lang.Object")) {
					try {
						Class<?> returnParamClass = Class.forName(returnParam);
						if (returnParamClass.isAssignableFrom(instantiationClass)) {
							return returnParamClass;
						}
					} catch (ClassNotFoundException e) {
						// silently ignore and try the next one
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setSequenceBuilder(SequenceBuilder sequenceBuilder) {
		this.sequenceBuilder = sequenceBuilder;
	}

	public SequenceBuilder getSequenceBuilder() {
		return this.sequenceBuilder;
	}

	public void setOriginalMethod(MethodSignature m) {
		this.originalMethod = m;
	}
}
