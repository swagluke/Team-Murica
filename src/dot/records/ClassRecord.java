package dot.records;

import java.util.ArrayList;

public class ClassRecord implements IClassRecord {
	private String className;
	private String extendsName;
	private ArrayList<String> implementsList;

	public ClassRecord() {

	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getExtendsName() {
		return extendsName;
	}

	public void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

	public ArrayList<String> getImplementsList() {
		return implementsList;
	}

	public void setImplementsList(ArrayList<String> implementsList) {
		this.implementsList = implementsList;
	}

	@Override
	public String getClassUml() {
		StringBuilder s = new StringBuilder();
		s.append(className + " [label = \"{" + className + "|");
		s.append("\n");
		
		s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
		for (String implement : this.implementsList) {
			String[] shortImplementList = implement.replace("/", ".").split("\\.");
			String shortImplement = shortImplementList[shortImplementList.length - 1];
//			if (classNames.contains(val.replace("/", ".")))
			s.append(shortClassName + " -> " + shortImplement + "\n");
//		}
		}
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
		// create extends arrows
		s.append("edge [ style = \"normal\"]\n");
		String[] shortExtendNameList = this.extendsName.replace("/", ".").split("\\.");
		String shortExtendName = shortExtendNameList[shortExtendNameList.length - 1];
		s.append(shortClassName + " -> " + shortExtendName + "\n");
		
//		for (String key2 : extendsMap.keySet()) {
//			String[] shortKeyList = key2.replace("/", ".").split("\\.");
//			String shortKey = shortKeyList[shortKeyList.length - 1];
//			String[] shortValueList = extendsMap.get(key2).replace("/", ".").split("\\.");
//			String shortValue = shortValueList[shortValueList.length - 1];
//			if (classNames.contains(extendsMap.get(key2).replace("/", ".")))
//				s.append(shortKey + " -> " + shortValue + "\n");
//		}

		return s.toString();
	}
}
