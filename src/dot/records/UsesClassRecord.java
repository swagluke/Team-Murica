package dot.records;

import java.util.HashSet;

public class UsesClassRecord implements IClassRecord {
	private HashSet<String> usesList = new HashSet<String>();
	public ImplementsClassRecord innerRecord;

	public UsesClassRecord(ImplementsClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		s.append("edge [ style = \"dotted\" arrowhead = \"open\"]\n");
		for (String val : usesList) {
			String[] valList = val.replace("/", ".").split("\\.");
			String shortValue = valList[valList.length - 1];
			if (innerRecord.classList.contains(val.replace("/", "."))) {
				String[] n = innerRecord.innerRecord.record.className.split("/");
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

}
