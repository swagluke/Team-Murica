package records;

import java.util.HashSet;

public class ImplementsClassRecord implements IClassRecord {
	private HashSet<String> implementsList;
	public IClassRecord innerRecord;

	public ImplementsClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		s.append(this.innerRecord.getClassUml());
		String className = this.getClassName();
		s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];

		for (String implement : this.implementsList) {
			String[] shortImplementList = implement.replace("/", ".").split("\\.");
			String shortImplement = shortImplementList[shortImplementList.length - 1];
			if (this.getClassList().contains(implement.replace("/", "."))) {
				s.append(shortClassName + " -> " + shortImplement + "\n");
			}
		}
		return s.toString();
	}

	public void setImplementsList(HashSet<String> list) {
		this.implementsList = list;
	}

	public HashSet<String> getImplementsList() {
		return implementsList;
	}

	@Override
	public String getClassName() {
		return this.innerRecord.getClassName();
	}

	@Override
	public HashSet<String> getClassList() {
		return innerRecord.getClassList();
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
