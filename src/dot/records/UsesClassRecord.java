package dot.records;

import java.util.ArrayList;

public class UsesClassRecord implements IClassRecord {
	private ArrayList<String> usesList = new ArrayList<String>();
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
			if (innerRecord.classList.contains(val.replace("/", "."))){
				String[] n = innerRecord.innerRecord.record.className.split("/");
				String name = n[n.length - 1];
				s.append( name+ " -> " + shortValue + "\n");
			}
		}
		return s.toString();
	}

	public ArrayList<MethodRecord> getMethods() {
//		return innerRecord.
		return new ArrayList<MethodRecord>();
	}
	
	public ArrayList<String> getUsesList() {
		return usesList;
	}
	/**
	 * Adds a thing to the list of uses
	 * @param className
	 */
	public void addUses(String className) {
		usesList.add(className);
	}

}
