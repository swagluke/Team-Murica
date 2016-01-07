package dot.records;

import java.util.ArrayList;

public class ClassRecord {
	private String className;
	private ArrayList<MethodRecord> methods;
	private String extendsName;
	private ArrayList<String> implementsList;
	public ClassRecord(String className,  String extendsType, ArrayList<MethodRecord> methods, ArrayList<String> implementsList){
		this.className = className;
		this.methods = methods;
		this.extendsName = extendsType;
		this.implementsList = implementsList;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getExtendsName() {
		return extendsName;
	}
	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}
	public ArrayList<MethodRecord> getMethods() {
		return methods;
	}
	public void setMethods(ArrayList<MethodRecord> methods) {
		this.methods = methods;
	}
	public ArrayList<String> getImplementsList() {
		return implementsList;
	}
	public void setImplementsList(ArrayList<String> implementsList) {
		this.implementsList = implementsList;
	}
}
