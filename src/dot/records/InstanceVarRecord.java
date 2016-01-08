package dot.records;


import org.objectweb.asm.Type;

public class InstanceVarRecord {
	private String name;
	private String type;
	private int access;
	
	public InstanceVarRecord(String name, String type, int access){
		this.name = name;
		this.type = type;
		this.access = access;
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
	
}