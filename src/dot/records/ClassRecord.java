package dot.records;

import java.util.ArrayList;

public class ClassRecord implements IClassRecord {
	private String className;
	private ArrayList<MethodRecord> methods;
	private String extendsName;
	private ArrayList<String> implementsList;
	private ArrayList<InstanceVarRecord> fields;
	public ClassRecord(String className,  String extendsType, ArrayList<MethodRecord> methods, ArrayList<String> implementsList, ArrayList<InstanceVarRecord> fields){
		this.className = className;
		this.methods = methods;
		this.extendsName = extendsType;
		this.implementsList = implementsList;
		this.fields = fields;
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
	public ArrayList<InstanceVarRecord> getFields() {
		return fields;
	}
	public void setFields(ArrayList<InstanceVarRecord> fields) {
		this.fields = fields;
	}
	@Override
	public String getClassUml() {
		// TODO Auto-generated method stub
		return null;
	}
}
