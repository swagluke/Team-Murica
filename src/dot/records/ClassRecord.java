package dot.records;

import java.util.ArrayList;

import org.objectweb.asm.Type;

public class ClassRecord implements IClassRecord {
	public String className;
	private String extendsName;
	private ArrayList<MethodRecord> methodsList;
	private ArrayList<InstanceVarRecord> fieldsList; 
	private ArrayList<String> implementsList;

	public ClassRecord() {

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

	public ArrayList<String> getImplementsList() {
		return implementsList;
	}

	public void setImplementsList(ArrayList<String> implementsList) {
		this.implementsList = implementsList;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String[] n = className.split("/");
		String name = n[n.length - 1];
		s.append(name + " [label = \"{" + className + "|");
		for(InstanceVarRecord f: fieldsList){
			s.append("+"+f.getName()+" : "+f.getType()+"\\l\n");
		}
		s.append("|");
		for(MethodRecord m: methodsList){
			if (m.getName().replaceAll("<.*?>", "").isEmpty())
				continue;
			s.append("+ ");
			s.append(m.getName().replaceAll("<.*?>", ""));
			for (Type t : m.getArgTypes()) {
				s.append(t.getClassName() + " ");
			}
			s.append(" : ");
			s.append(m.getReturnType() + "\\l\n");
		}
		s.append("}\"]");

		return s.toString();
	}

	public ArrayList<MethodRecord> getMethodsList() {
		return methodsList;
	}

	public void setMethodsList(ArrayList<MethodRecord> methodsList) {
		this.methodsList = methodsList;
	}

	public ArrayList<InstanceVarRecord> getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(ArrayList<InstanceVarRecord> fieldsList) {
		this.fieldsList = fieldsList;
	}
}
