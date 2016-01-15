package records;

public class MethodSignature {
	
	public String className;
	public String methodName;
	public String[] methodArgTypes;
	
	public MethodSignature(String className, String methodName, String[] methodArgTypes) {
		this.className = className;
		this.methodName = methodName;
		this.methodArgTypes = methodArgTypes;
	}

}
