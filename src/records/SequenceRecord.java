package records;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import generictree.GenericTree;
import generictree.GenericTreeNode;

public class SequenceRecord implements ISequenceRecord {
	GenericTree<MethodSignature> methodCalls = new GenericTree<MethodSignature>();
	ArrayList<String> lifeLines= new ArrayList<String>();
	public SequenceRecord(GenericTreeNode<MethodSignature> node) {
		this.methodCalls.setRoot(node);
	}

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
	
	private String buildMethodCalls(GenericTreeNode<MethodSignature> node){
		StringBuilder sb = new StringBuilder();
		
		return null;
	}
	
	public void setRoot(GenericTreeNode<MethodSignature> root) {
		this.methodCalls.setRoot(root);
	}
}
