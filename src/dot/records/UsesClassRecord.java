package dot.records;

import java.util.HashSet;

public class UsesClassRecord implements IClassRecord {
	private HashSet<String> usesList = new HashSet<String>();
	public IClassRecord innerRecord;

	public UsesClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		s.append("edge [ style = \"dotted\" arrowhead = \"open\"]\n");
		for (String val : usesList) {
			String[] valList = val.replace("/", ".").split("\\.");
			String shortValue = valList[valList.length - 1];
			if (innerRecord.getClassList().contains(val.replace("/", "."))) {
				String[] n = innerRecord.getInnerRecord().getInnerRecord().getClassName().split("/"); // refactor down
				String name = n[n.length - 1];
				s.append(name + " -> " + shortValue + "\n");
			}
		}
		return s.toString();
	}

	public HashSet<MethodRecord> getMethods() {
		// return innerRecord.
		return new HashSet<MethodRecord>();
	}

	public HashSet<String> getUsesList() {
		return usesList;
	}

	/**
	 * Adds a thing to the list of uses
	 * 
	 * @param className
	 */
	public void addUses(String className) {
		usesList.add(className);
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
