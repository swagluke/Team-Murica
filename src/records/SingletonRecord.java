package records;

import java.util.HashSet;

public class SingletonRecord implements IClassRecord {

	private IClassRecord innerRecord;

	public SingletonRecord(IClassRecord innerRecord) {
		this.innerRecord = innerRecord;
	}

	@Override
	public String getClassUml() {
		String innerUML = innerRecord.getClassUml();
		if(!this.isSingleton()){
			return innerUML;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[color=blue1");
		sb.append(innerUML.substring(1));
		String className = this.getClassName();
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
		sb.append("edge [ style = \"normal\"]\n");
		sb.append(shortClassName + " -> " + shortClassName + "\n");
		return sb.toString();
	}
	//TODO: do stuff here 
	private boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getClassName() {
		return this.innerRecord.getClassName();
	}

	@Override
	public HashSet<String> getClassList() {
		return this.innerRecord.getClassList();
	}

	@Override
	public IClassRecord getInnerRecord() {
		return this.innerRecord;
	}

	@Override 
	public ClassRecord getBaseRecord() {
		return this.innerRecord.getBaseRecord();
	}
}
