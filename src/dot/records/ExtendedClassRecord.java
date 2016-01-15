package dot.records;

import java.util.HashSet;

public class ExtendedClassRecord implements IClassRecord {

	public ClassRecord record;
	private String extendsName;
	public HashSet<String> classList;

	public ExtendedClassRecord(ClassRecord record) {
		this.record = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String className = ((ClassRecord) this.record).getClassName();
		// s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];

		// create extends arrows
		String[] shortExtendNameList = this.extendsName.replace("/", ".").split("\\.");
		String shortExtendName = shortExtendNameList[shortExtendNameList.length - 1];
		if (classList.contains(extendsName.replace("/", "."))) {
			s.append("edge [ style = \"normal\"]\n");
			s.append(shortClassName + " -> " + shortExtendName + "\n");
		}
		return s.toString();
	}

	public String getExtendsName() {
		return extendsName;
	}

	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

	public String getClassName() {
		return ((ClassRecord) this.record).getClassName();
	}

}
