package asm;

import java.util.ArrayList;
import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import records.MethodRecord;

public class ClassMethodInsVisitor extends ClassVisitor {
	private HashSet<MethodRecord> methods = new HashSet<MethodRecord>();
	public HashSet<MethodInsVisitor> methodVisitors = new HashSet<MethodInsVisitor>();
	public String methodName;
	private HashSet<String> returnParams = new HashSet<String>();

	public ClassMethodInsVisitor(int arg0) {
		super(arg0);
	}

	public ClassMethodInsVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor toDecorate = super.visitMethod(access, name, desc, signature, exceptions);
		MethodInsVisitor methodVisitor = new MethodInsVisitor(Opcodes.ASM5, toDecorate); // move this out?
		String returnType = Type.getReturnType(desc).getClassName();
		this.returnParams.add(returnType);
		Type[] argTypes = Type.getArgumentTypes(desc);
		ArrayList<String> stypes = new ArrayList<String>();
		for (Type t : argTypes) {
			stypes.add(t.getClassName());
			returnParams.add(t.getClassName());
		}

		String symbol = "-";
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			symbol = "+";
		}
		methods.add(new MethodRecord(access, name, returnType, argTypes, stypes));
		// System.out.println(" method " + symbol + returnType + " " + name + " " + stypes.toString());
		methodVisitor.methodName = name;
		methodVisitors.add(methodVisitor);

		return methodVisitor;
	}

	public HashSet<MethodRecord> getMethods() {
		return methods;
	}

	public HashSet<String> getUsesNames() {
		HashSet<String> usesNames = new HashSet<String>();
		for (String returnParam: returnParams) {
			usesNames.add(returnParam.replace(".", "/"));
		}
		for (MethodInsVisitor methodVisitor : methodVisitors) {
			for (String instantiation : methodVisitor.getInstantiations()) {
				Class<?> superClass;
				if ((superClass = isSubClassOfReturnOrParam(instantiation)) != null) {
					usesNames.remove(Type.getInternalName(superClass));
					usesNames.add(instantiation);
				}
			}
		}
		usesNames.remove("void");
		return usesNames;
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
}
