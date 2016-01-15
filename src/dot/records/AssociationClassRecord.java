package dot.records;

import java.util.HashSet;

public class AssociationClassRecord implements IClassRecord {
	IClassRecord record;
	private HashSet<String> associationNames = new HashSet<String>();

	public AssociationClassRecord(IClassRecord record) {
		this.record = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		s.append("edge [ style = \"normal\" arrowhead = \"vee\"]\n");
		String className = this.getClassName();
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
		for (String associationName : associationNames) {
			String[] shortAssociationNameList = associationName.replace("/", ".").split("\\.");
			String shortAssociationName = shortAssociationNameList[shortAssociationNameList.length - 1];
			if (this.getClassList().contains(associationName.replace("/", "."))) {
//				s.append("edge [ style = \"normal\"]\n");
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

	@Override
	public String getClassName() {
		return this.record.getClassName();
	}

	@Override
	public HashSet<String> getClassList() {
		return this.record.getClassList();
	}

}
