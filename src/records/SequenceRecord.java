package records;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import generictree.GenericTree;
import generictree.GenericTreeNode;

public class SequenceRecord implements ISequenceRecord {
	GenericTree<MethodSignature> methodCalls = new GenericTree<MethodSignature>();
	HashSet<ClassNameStringWrapper> seenClasses= new HashSet<ClassNameStringWrapper>();
//	HashSet<String> createdLines = new HashSet<String>();
	StringBuilder methodCallsStrings = new StringBuilder();
	public SequenceRecord(GenericTreeNode<MethodSignature> node) {
		this.methodCalls.setRoot(node);
	}

	public HashSet<ClassNameStringWrapper> getLifeLines() {
		return seenClasses;
	}
	public GenericTree<MethodSignature> getMethodCalls() {
		return methodCalls;
	}

	@Override
	public String getSequenceDiagram() {
		StringBuilder sb = new StringBuilder();
		//createLifelines();//populate the lifelines data
		methodCallsStrings.append(buildMethodCalls(methodCalls.getRoot(),0));
		for(ClassNameStringWrapper s:seenClasses)
			sb.append(s.string+"\n");
		sb.append("\n");
		sb.append(methodCallsStrings.toString());
		return sb.toString();
	}

	private String buildMethodCalls(GenericTreeNode<MethodSignature> node, int depth){
		StringBuilder sb = new StringBuilder();
		for(GenericTreeNode<MethodSignature> n:node.children){
			String[] temparr = n.data.getClassName().split("/");
			String nClassName = temparr[temparr.length - 1].toLowerCase();
			temparr = node.data.getClassName().split("/");
			String nodeClassName = temparr[temparr.length - 1].toLowerCase();
			for(int i=0;i<depth;i++){
				sb.append(" ");
			}
			if(!seenClasses.contains(new ClassNameStringWrapper(nClassName+":"+nClassName))&&n.getData().getMethodName().equals("<init>")){
				sb.append(nodeClassName+ ":"+nClassName+"."+"new"+"()\n");
				seenClasses.add(new ClassNameStringWrapper("/"+nClassName.toLowerCase()+":"+nClassName));
			}
			else if(!seenClasses.contains(new ClassNameStringWrapper(nClassName+":"+nClassName))){
				sb.append(nodeClassName+ ":"+nClassName+"."+n.getData().getMethodName()+"()\n");
				seenClasses.add(new ClassNameStringWrapper(nClassName.toLowerCase()+":"+nClassName));
			}
			else{
				sb.append(nodeClassName+ ":"+nClassName+"."+n.data.getMethodName()+"()\n");
			}
			sb.append(buildMethodCalls(n, depth+1));
		}
		return sb.toString();
	}


	public void setRoot(GenericTreeNode<MethodSignature> root) {
		this.methodCalls.setRoot(root);
	}
}
