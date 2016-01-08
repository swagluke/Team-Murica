package dot.records;

import java.util.List;

import org.objectweb.asm.Type;

public class MethodRecord {
	private String returnType;
	private Type[] argTypes;
	private List<String> stypes;
	private String name;
	private int access;
	
	public MethodRecord(int access, String name, String returnType, Type[] argTypes, List<String> stypes){
		this.setAccess(access);
		this.setName(name);
		this.returnType = returnType;
		this.argTypes = argTypes;
		this.stypes = stypes;
	}
	public Type[] getArgTypes() {
		return argTypes;
	}
	public void setArgTypes(Type[] argTypes) {
		this.argTypes = argTypes;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public List<String> getStypes() {
		return stypes;
	}
	public void setStypes(List<String> stypes) {
		this.stypes = stypes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAccess() {
		return access;
	}
	public void setAccess(int access) {
		this.access = access;
	}
}
