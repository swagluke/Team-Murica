package records;

import java.util.ArrayList;

public class SequenceRecord implements ISequenceRecord {
	ArrayList<MethodSignature> methodCalls = new ArrayList<MethodSignature>();
	ArrayList<ClassRecord> lifeLines= new ArrayList<ClassRecord>();

	public ArrayList<ClassRecord> getLifeLines() {
		return lifeLines;
	}
	public void setLifeLines(ArrayList<ClassRecord> lifeLines) {
		this.lifeLines = lifeLines;
	}
	public ArrayList<MethodSignature> getMethodCalls() {
		return methodCalls;
	}
	public void setMethodCalls(ArrayList<MethodSignature> methodCalls) {
		this.methodCalls = methodCalls;
	}
	
	public void addMethodCall(MethodSignature m) {
		this.methodCalls.add(m);
	}
}
