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
		// TODO Auto-generated method stub
		return null;
	}

	public HashSet<String> getAssociationNames() {
		return associationNames;
	}

	public void setAssociationNames(HashSet<String> associationName) {
		this.associationNames = associationName;
	}
}
