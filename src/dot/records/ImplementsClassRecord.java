package dot.records;

import java.util.ArrayList;

public class ImplementsClassRecord implements IClassRecord {
	
	private ArrayList<String> implementsList;
	public ExtendedClassRecord innerRecord;
	ArrayList<String> classList;
	public String className;
	
	public ImplementsClassRecord(IClassRecord record) {
		this.innerRecord = (ExtendedClassRecord) record;
		this.classList = ((ExtendedClassRecord)record).classList;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String className = this.innerRecord.getClassName();
		s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
		
		for (String implement : this.implementsList) {
			String[] shortImplementList = implement.replace("/", ".").split("\\.");
			String shortImplement = shortImplementList[shortImplementList.length - 1];
			if(classList.contains(implement.replace("/", ".")))
				s.append(shortClassName + " -> " + shortImplement + "\n");
		}
		return s.toString();
//		for (String key : implementsMap.keySet()) {
////			String[] shortKeyList = key.replace("/", ".").split("\\.");
////			String shortKey = shortKeyList[shortKeyList.length - 1];
//			ArrayList<String> shortValueList = implementsMap.get(key);
//			for (String val : shortValueList) {
//				String[] valList = val.replace("/", ".").split("\\.");
//				String shortValue = valList[valList.length - 1];
//				if (classNames.contains(val.replace("/", ".")))
//					s.append(shortKey + " -> " + shortValue + "\n");
//			}
//		}
	}

	public void setImplementsList(ArrayList<String> list) {
		this.implementsList = list;
	}
	public ArrayList<String> getImplementsList(){
		return implementsList;
	}

	public void setClassList(ArrayList<String> classList2) {
		this.classList =classList2;
	}

}
