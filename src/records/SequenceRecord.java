package records;

import java.util.ArrayList;

public class SequenceRecord implements ISequenceRecord {
	ArrayList<MethodSignature> methodCalls = new ArrayList<MethodSignature>();
	ArrayList<String> lifeLines= new ArrayList<String>();

	public ArrayList<String> getLifeLines() {
		return lifeLines;
	}
	public void setLifeLines(ArrayList<String> lifeLines) {
		this.lifeLines = lifeLines;
	}
	public ArrayList<MethodSignature> getMethodCalls() {
		return methodCalls;
	}

	public void addMethodCall(MethodSignature m) {
		this.methodCalls.add(m);
	}
	
	@Override
	public String getSequenceUml() {
		return "fail";
	}
}
