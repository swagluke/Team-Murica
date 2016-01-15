package dot.records;

import java.util.HashSet;

import org.objectweb.asm.Type;

public class ClassRecord implements IClassRecord {
	public String className;
	private String extendsName;
	private HashSet<MethodRecord> methodsList;
	private HashSet<InstanceVarRecord> fieldsList;
	private HashSet<String> implementsList;
	private HashSet<String> classList;

	public ClassRecord() {

	}

	@Override
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

	public HashSet<String> getImplementsList() {
		return implementsList;
	}

	public void setImplementsList(HashSet<String> implementsList) {
		this.implementsList = implementsList;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String[] n = className.split("/");
		String name = n[n.length - 1];
		s.append(name + " [label = \"{" + className + "|");
		for (InstanceVarRecord f : fieldsList) {
			s.append("+" + f.getName() + " : " + f.getType() + "\\l\n");
		}
		s.append("|");
		for (MethodRecord m : methodsList) {
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

	public HashSet<MethodRecord> getMethodsList() {
		return methodsList;
	}

	public void setMethodsList(HashSet<MethodRecord> methodsList) {
		this.methodsList = methodsList;
	}

	public HashSet<InstanceVarRecord> getFieldsList() {
		return fieldsList;
	}

	public void setFieldsList(HashSet<InstanceVarRecord> fieldsList) {
		this.fieldsList = fieldsList;
	}

	@Override
	public HashSet<String> getClassList() {
		return this.classList;
	}

	public void setClassList(HashSet<String> classList) {
		this.classList = classList;
	}
}
