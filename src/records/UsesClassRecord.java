package records;

import java.util.HashSet;

public class UsesClassRecord implements IClassRecord {
	private HashSet<String> usesNamesList = new HashSet<String>();
	public IClassRecord innerRecord;

	public UsesClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		if (this.usesNamesList.isEmpty()) {
			return this.innerRecord.getClassUml();
		}
		boolean firstTime = true;
		StringBuilder s = new StringBuilder();
		s.append(this.innerRecord.getClassUml());
		for (String val : usesNamesList) {
			String[] valList = val.replace("/", ".").split("\\.");
			String shortValue = valList[valList.length - 1];
			if (innerRecord.getClassList().contains(val.replace("/", "."))) {
				String[] n = innerRecord.getClassName().split("/");
				String name = n[n.length - 1];
				if (firstTime) {
					s.append("edge [ style = \"dotted\" arrowhead = \"open\"]\n");
					firstTime = false;
				}
				s.append(name + " -> " + shortValue + "\n");
			}
		}
		return s.toString();
	}

	public HashSet<String> getUsesNamesList() {
		return usesNamesList;
	}

	public void setUsesNamesList(HashSet<String> usesNamesList) {
		this.usesNamesList = usesNamesList;
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
