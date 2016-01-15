package dot.records;

import java.util.HashSet;

public class AssociationClassRecord implements IClassRecord {
	IClassRecord record;
	private HashSet<String> associationNames = new HashSet<String>();
	private HashSet<String> classList;

	public AssociationClassRecord(IClassRecord record) {
		this.record = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		s.append("edge [ style = \"normal\" arrowhead = \"vee\"]\n");
		String className = ((ClassRecord) this.record).getClassName();
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
		for (String associationName : associationNames) {
			String[] shortAssociationNameList = associationName.replace("/", ".").split("\\.");
			String shortAssociationName = shortAssociationNameList[shortAssociationNameList.length - 1];
			if (classList.contains(associationName.replace("/", "."))) {
				s.append("edge [ style = \"normal\"]\n");
				s.append(shortClassName + " -> " + shortAssociationName + "\n");
			}

		}
		return s.toString();

	}

	public HashSet<String> getAssociationNames() {
		return associationNames;
	}

	public void setAssociationNames(HashSet<String> associationName) {
		this.associationNames = associationName;
	}
	
	public String getClassName() {
		return ((ClassRecord) this.record).getClassName();
	}
	
	public void setClassList(HashSet<String> classList) {
		this.classList = classList;
	}

}
