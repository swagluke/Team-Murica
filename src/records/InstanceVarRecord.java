package records;

public class InstanceVarRecord {
	private String name;
	private String type;
	private int access;
	// private HashSet<String> nestedFields;

	public InstanceVarRecord(String name, String type, int access) {
		this.name = name;
		this.type = type;
		this.access = access;
		// this.setNestedFields(nestedFields);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	@Override
	public boolean equals(Object o) {
		InstanceVarRecord other = (InstanceVarRecord) o;
		return other.getName().equals(this.name) && other.getType().equals(this.type)
				&& other.getAccess() == this.access;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode() + this.type.hashCode() + this.access;
	}
	
	@Override
	public String toString() {
		return this.name + ", " + this.type + ", " + this.access;
	}
}
