package records;

import java.util.ArrayList;
import java.util.LinkedHashSet;

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
	public String getSequenceDiagram() {
		createLifelines();//populate the lifelines data
		
		return "";
	}
	/**
	 * goes through the list of method calls and finds the ones that exist at the start
	 */
	private void createLifelines() {
		LinkedHashSet<String> lines = new LinkedHashSet<String>();
		for(MethodSignature m:methodCalls){
			if(m.getMethodName().equals("<init>")){
				lines.add("/"+m.getClassName().toLowerCase()+":"+m.getClassName());
			}else{
				lines.add(m.getClassName().toLowerCase()+":"+m.getClassName());
			}
		}
		for(String s:lines){
			lifeLines.add(s);
			System.out.println(s);
		}
	}
}
