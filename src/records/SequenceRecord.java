package records;

import java.util.ArrayList;

public class SequenceRecord implements ISequenceRecord {
	GenericTree<MethodSignature> methodCalls = new GenericTree<MethodSignature>();
	ArrayList<String> lifeLines= new ArrayList<String>();

	public ArrayList<String> getLifeLines() {
		return lifeLines;
	}
	public void setLifeLines(ArrayList<String> lifeLines) {
		this.lifeLines = lifeLines;
	}
	public GenericTree<MethodSignature> getMethodCalls() {
		return methodCalls;
	}
	
	@Override
	public String getSequenceUml() {
		return "fail";
	}
}
