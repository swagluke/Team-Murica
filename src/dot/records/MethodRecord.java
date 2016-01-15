package dot.records;

import java.util.Arrays;
import java.util.HashSet;

import org.objectweb.asm.Type;

public class MethodRecord {
	private String returnType;
	private Type[] argTypes;
	private HashSet<String> stypes;
	private String name;
	private int access;

	public MethodRecord(int access, String name, String returnType, Type[] argTypes, HashSet<String> stypes) {
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

	public HashSet<String> getStypes() {
		return stypes;
	}

	public void setStypes(HashSet<String> stypes) {
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

	// int access, String name, String returnType, Type[] argTypes, HashSet<String>
	// stypes
	@Override
	public boolean equals(Object other) {
		MethodRecord o = (MethodRecord) other;
		return o.getAccess() == this.access && o.getName().equals(this.name)
				&& o.getReturnType().equals(this.returnType) && Arrays.equals(o.getArgTypes(), this.argTypes)
				&& o.getStypes().equals(this.stypes);
	}
	
	@Override
	public int hashCode() {
		int collectionHashCode = 0;
		for (String stype : this.stypes) {
			collectionHashCode += stype.hashCode();
		}
		for (Type t : this.argTypes) {
			collectionHashCode += t.hashCode();
		}
		return this.access + this.name.hashCode() + this.returnType.hashCode() + collectionHashCode;
	}
}
