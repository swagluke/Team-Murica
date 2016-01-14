package dot.records;

import java.util.Arrays;

public class ExtendedClassRecord implements IClassRecord {

	IClassRecord record;
	private String extendsName;
	
	public ExtendedClassRecord(IClassRecord record) {
		this.record = record;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		String className = ((ClassRecord) this.record).getClassName();
		s.append("edge [ style = \"normal\"]\n");
		String[] shortClassName = className.replace("/", ".").split("\\.");
		System.out.println(Arrays.toString(shortClassName));
		
//		for (String key2 : extendsMap.keySet()) {
//			String[] shortKeyList = key2.replace("/", ".").split("\\.");
//			String shortKey = shortKeyList[shortKeyList.length - 1];
//			String[] shortValueList = extendsMap.get(key2).replace("/", ".").split("\\.");
//			String shortValue = shortValueList[shortValueList.length - 1];
//			if (classNames.contains(extendsMap.get(key2).replace("/", ".")))
//				s.append(shortKey + " -> " + shortValue + "\n");
//		}
		return null;
	}

	public String getExtendsName() {
		return extendsName;
	}

	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

}
