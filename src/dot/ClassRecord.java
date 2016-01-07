package dot;

import java.util.ArrayList;

public class ClassRecord {
	private String className;
	private ArrayList<String> methods;
	private String extendsName;
	private ArrayList<String> implementsList;
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
	public ArrayList<String> getMethods() {
		return methods;
	}
	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}
	public ArrayList<String> getImplementsList() {
		return implementsList;
	}
	public void setImplementsList(ArrayList<String> implementsList) {
		this.implementsList = implementsList;
	}
}
