package asm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import records.MethodRecord;
import sdedit.SequenceBuilder;

public class SequenceMethodInsVisitor extends ClassMethodVisitor {
	private HashSet<MethodRecord> methods = new HashSet<MethodRecord>();
	public HashSet<MethodSequenceInsVisitor> methodVisitors = new HashSet<MethodSequenceInsVisitor>();
	private String methodName;
	private String signature;
	private HashSet<String> returnParams = new HashSet<String>();
	private SequenceBuilder sequenceBuilder;

	public SequenceMethodInsVisitor(int arg0) {
		super(arg0);
	}

	public SequenceMethodInsVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
//		System.out.println("sequence method ins visitor, name: " + name + ": " + this.methodName + ", " + desc
//				+ ": " + this.signature);
		// System.out.println(desc);
		if (name.equals(this.methodName) && desc.equals(this.signature)) {
			// && (desc.equals(this.signature) || (signature != null && signature.equals(this.signature)))) {
			System.out.println("found method: " + name + ", " + desc);
			MethodSequenceInsVisitor methodVisitor = new MethodSequenceInsVisitor(Opcodes.ASM5, toDecorate); // move
			// methodVisitor.setMethodName(name);
			methodVisitor.setLastSequenceBuilder(this.sequenceBuilder);
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	// public HashSet<String> getUsesNames() {
	// HashSet<String> usesNames = new HashSet<String>();
	// for (String returnParam: returnParams) {
	// usesNames.add(returnParam.replace(".", "/"));
	// }
	// for (MethodSequenceInsVisitor methodVisitor : methodVisitors) {
	// for (String instantiation : methodVisitor.getInstantiations()) {
	// Class<?> superClass;
	// if ((superClass = isSubClassOfReturnOrParam(instantiation)) != null) {
	// usesNames.remove(Type.getInternalName(superClass));
	// usesNames.add(instantiation);
	// }
	// }
	// }
	// usesNames.remove("void");
	// return usesNames;
	// }

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
}
