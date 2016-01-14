package dot.records;

import java.util.ArrayList;

public class UsesClassRecord implements IClassRecord {
	private ArrayList<String> usesList = new ArrayList<String>();
	private IClassRecord innerRecord;

	public UsesClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		// TODO Auto-generated method stub
		return null;
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
