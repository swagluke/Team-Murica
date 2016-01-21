package records;

public class MethodSignature {
	
	private String className;
	private String methodName;
	private String methodSignature;
	
	public MethodSignature(String className, String methodName, String signature) {
		this.className = className;
		this.methodName = methodName;
		this.methodSignature = signature;
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

	public String getSignature() {
		return methodSignature;
	}

	public void setSignature(String signature) {
		this.methodSignature = signature;
	}
}
