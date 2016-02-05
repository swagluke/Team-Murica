package records;

import java.util.HashSet;

public class AssociationClassRecord implements IClassRecord {
	private HashSet<String> associationNames = new HashSet<String>();
	IClassRecord innerRecord;

	public AssociationClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		if (this.associationNames.isEmpty()) {
			return this.innerRecord.getClassUml();
		}
		StringBuilder s = new StringBuilder();
		boolean firstTime = true;
		String className = this.getClassName();
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
		s.append(this.innerRecord.getClassUml());
		for (String associationName : associationNames) {
			String[] shortAssociationNameList = associationName.replace("/", ".").split("\\.");
			String shortAssociationName = shortAssociationNameList[shortAssociationNameList.length - 1];
			if (this.getClassList().contains(associationName.replace("/", "."))) {
				// s.append("edge [ style = \"normal\"]\n");
				if (firstTime) {

					s.append("edge [ style = \"normal\" arrowhead = \"vee\"]\n");
					firstTime = false;
				}
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
		return this.innerRecord.getClassName();
	}

	@Override
	public HashSet<String> getClassList() {
		return this.innerRecord.getClassList();
	}

	@Override
	public IClassRecord getInnerRecord() {
		return this.innerRecord;
	}

	@Override
	public ClassRecord getBaseRecord() {
		return this.innerRecord.getBaseRecord();
	}
}
