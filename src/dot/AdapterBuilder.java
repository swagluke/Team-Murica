package dot;

import java.util.HashMap;
import java.util.HashSet;

import records.ExtendedClassRecord;
import records.IClassRecord;
import records.ImplementsClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

public class AdapterBuilder extends APatternBuilder {
    private String adapteeName;

    public AdapterBuilder(IBuilder b) {
		super(b);
	}
    String targetName = "";

	@Override
	public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap) {
		boolean hasAdapteeFieldAndConstructor = false;
		boolean extendsImplementsOtherClass = false;
		HashSet<String> possibles = new HashSet<>();
		if (record.canConvertRecord(ExtendedClassRecord.class)) {
			ExtendedClassRecord extendedClassRecord = (ExtendedClassRecord) record
					.tryConvertRecord(ExtendedClassRecord.class);
			possibles.add(extendedClassRecord.getExtendsName());
		}
		if (record.canConvertRecord(ImplementsClassRecord.class)) {
			ImplementsClassRecord implementsClassRecord = (ImplementsClassRecord) record
					.tryConvertRecord(ImplementsClassRecord.class);
			possibles.addAll(implementsClassRecord.getImplementsList());
		}
		for (String possible : possibles) {
			if (record.getClassList().contains(possible.replace("/", "."))) {
                adapteeName = possible.replace("/", ".");
				extendsImplementsOtherClass = true;
			}
		}
//		System.out.println("\nin is pattern");
//		System.out.println(this.getClassRecord().getClassName());
//		System.out.println(record.getClassList());
		HashSet<String> fields = new HashSet<>();
		if (extendsImplementsOtherClass) {
			for (InstanceVarRecord field : record.getBaseRecord().getFieldsList()) {
				fields.add(field.getType());
			}

			for (MethodRecord methodRecord : record.getBaseRecord().getMethodsList()) {
				System.out.println(methodRecord.getName());
				if (methodRecord.getName().equals("<init>")) {
//					System.out.println(methodRecord.getStypes());
//					System.out.println("init");
					for (String arg : methodRecord.getStypes()) {
						if (fields.contains(arg)) {
                            //TODO check if the arg class does implement thingy, it's creating some false positives
                            this.targetName = arg;
							hasAdapteeFieldAndConstructor = true;
						}
					}
				}
			}
		}
//		System.out.println("hasAdapteeFieldAndConstructor: " + hasAdapteeFieldAndConstructor
//				+ " && extendsImplementsOtherClass: " + extendsImplementsOtherClass);
		return hasAdapteeFieldAndConstructor && extendsImplementsOtherClass;
	}

	@Override
	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap) {
        record.getBaseRecord().setBoxColor("red");
        record.getBaseRecord().addPattern("Adapter");//right method?
        System.out.println("Target Name: " + targetName + " Adaptee Name" + adapteeName);
        IClassRecord targetRecord = recordHashMap.get(targetName);
        IClassRecord adapteeRecord = recordHashMap.get(adapteeName);

        targetRecord.getBaseRecord().setBoxColor("red");
        targetRecord.getBaseRecord().addPattern("Adapter Target");

        adapteeRecord.getBaseRecord().setBoxColor("red");
        adapteeRecord.getBaseRecord().addPattern("Adaptee");
	}
}
