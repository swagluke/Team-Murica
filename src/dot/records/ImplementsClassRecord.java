package dot.records;

import java.util.HashSet;

public class ImplementsClassRecord implements IClassRecord {

	private HashSet<String> implementsList;
	public IClassRecord innerRecord;

	public ImplementsClassRecord(IClassRecord record) {
		this.innerRecord = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String className = this.getClassName();
		s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];

		for (String implement : this.implementsList) {
			String[] shortImplementList = implement.replace("/", ".").split("\\.");
			String shortImplement = shortImplementList[shortImplementList.length - 1];
			if (this.getClassList().contains(implement.replace("/", ".")))
				s.append(shortClassName + " -> " + shortImplement + "\n");
		}
		return s.toString();
		// for (String key : implementsMap.keySet()) {
		//// String[] shortKeyList = key.replace("/", ".").split("\\.");
		//// String shortKey = shortKeyList[shortKeyList.length - 1];
		// HashSet<String> shortValueList = implementsMap.get(key);
		// for (String val : shortValueList) {
		// String[] valList = val.replace("/", ".").split("\\.");
		// String shortValue = valList[valList.length - 1];
		// if (classNames.contains(val.replace("/", ".")))
		// s.append(shortKey + " -> " + shortValue + "\n");
		// }
		// }
	}

	public void setImplementsList(HashSet<String> list) {
		this.implementsList = list;
	}

	public HashSet<String> getImplementsList() {
		return implementsList;
	}

	@Override
	public String getClassName() {
		return this.innerRecord.getClassName();
	}

	@Override
	public HashSet<String> getClassList() {
		return innerRecord.getClassList();
	}

}
