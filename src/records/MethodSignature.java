package records;

import java.util.Arrays;

import org.objectweb.asm.Type;

public class MethodSignature {

	private String className;
	private String methodName;
	private Type[] methodArgs;
	private Type returnType;

	public MethodSignature(String className, String methodName, Type[] methodArgs, Type returnType) {
		this.className = className;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
		this.returnType = returnType;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Type[] getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(Type[] methodArgs) {
		this.methodArgs = methodArgs;
	}

	@Override
	public boolean equals(Object other) {
		MethodSignature o = (MethodSignature) other;
		return o.getClassName().equals(this.className) && o.getMethodName().equals(this.getMethodName())
				&& Arrays.equals(o.getMethodArgs(), this.methodArgs);
	}
	
	@Override
	public String toString() {
		return this.getClassName() + ": " + this.getMethodName() + "(" + Arrays.toString(this.getMethodArgs()) + ")";
	}
}
