package records;

import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.HashSet;

import org.objectweb.asm.Type;

import javax.management.RuntimeErrorException;

public class ClassRecord implements IClassRecord {
	public String className;
	private HashSet<MethodRecord> methodsList;
	private HashSet<InstanceVarRecord> fieldsList;
	private HashSet<String> classList;
	private ArrayList<String> patternNames;
	private String boxColor;
	private HashSet<String> extraEdges;

	public ClassRecord() {
		this.patternNames = new ArrayList<String>();
		this.extraEdges = new HashSet<String>();
	}

	@Override
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String[] n = className.split("/");
		String name = n[n.length - 1];
		s.append(name + " [");
		if (this.hasBoxColor()) {
			s.append("color = \"" + this.boxColor + "\" ");
		}
		s.append("label = \"{" + name);
		for (String patternName : this.patternNames) {
			s.append("\\n\\<\\<" + patternName + "\\>\\>");
		}
		s.append("|");
		// here
		// s.append(name + " [label = \"{" + name + "|");
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
		for (String str : this.extraEdges) {
			s.append("\n" + str);
		}

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

	@Override
	public IClassRecord getInnerRecord() {
		throw new RuntimeException("This is the base record, there is no more inner records.");
	}

	@Override
	public ClassRecord getBaseRecord() {
		return this;
	}

	public ArrayList<String> getPatternNames() {
		return this.patternNames;
	}

	public void addPattern(String patternName) {
		this.patternNames.add(patternName);
	}

	private boolean hasBoxColor() {
		return this.boxColor != null;
	}

	public void setBoxColor(String boxColor) {
		this.boxColor = boxColor;
	}

	public void addEdge(String string) {
		this.extraEdges.add(string);
	}

	public HashSet<String> getExtraEdges() {
		return this.extraEdges;
	}

	@Override
	public IClassRecord tryConvertRecord(Class<?> classVar) {
		if (this.getClass() == classVar) {
			return this;
		}
		throw new RuntimeException("Could not convert the record to type " + classVar);
	}

	@Override
	public boolean canConvertRecord(Class<?> classVar) {
		return this.getClass() == classVar;
	}
}
