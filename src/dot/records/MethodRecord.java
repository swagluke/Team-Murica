package dot.records;

import java.util.List;

import org.objectweb.asm.Type;

public class MethodRecord {
	private String returnType;
	private Type[] argTypes;
	private List<String> stypes;
	public MethodRecord(String returnType, Type[] argTypes, List<String> stypes){
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
}
