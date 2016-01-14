package dot.records;

import java.util.ArrayList;

public class UsesClassRecord implements IClassRecord {
	private ArrayList<String> usesList = new ArrayList<String>();
	public UsesClassRecord(ClassRecord build) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getClassUml() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MethodRecord> getMethods() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Adds a thing to the list of uses
	 * @param className
	 */
	public void addUses(String className) {
		usesList.add(className);
	}

}
