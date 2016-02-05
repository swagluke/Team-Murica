package dot;

import java.util.HashMap;
import java.util.HashSet;

import records.*;

public class AdapterBuilder extends APatternBuilder {
    private HashSet<String> adapteeNames = new HashSet<>();

    public AdapterBuilder(IBuilder b) {
        super(b);
    }

    HashSet<String> targetNames = new HashSet<>();

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
                if (recordMap.get(possible.replace("/", ".")) != null) {
                    targetNames.add(possible.replace("/", "."));
                    extendsImplementsOtherClass = true;
                }
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
//				System.out.println(methodRecord.getName());
                if (methodRecord.getName().equals("<init>")) {
//					System.out.println(methodRecord.getStypes());
//					System.out.println("init");
                    for (String arg : methodRecord.getStypes()) {
                        if (fields.contains(arg)) {
                            //TODO check if the arg class does implement thingy, it's creating some false positives
                            if (recordMap.get(arg) != null) {
                                if (targetNames.contains(arg)) {
                                    targetNames.remove(arg);
                                } else {
                                    adapteeNames.add(arg);
                                    hasAdapteeFieldAndConstructor = true;
                                }
                            }
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
        record.getBaseRecord().addPattern("Adapter");
//        System.out.println(record.getClassName().substring(record.getClassName().lastIndexOf('/') + 1));

//        System.out.println("Target Name: " + targetName + " Adaptee Name" + adapteeName);
        for (String s : targetNames) {
            System.out.println("targetName: " + s);
            IClassRecord targetRecord = recordHashMap.get(s);
            if (targetRecord != null) {
                targetRecord.getBaseRecord().setBoxColor("red");
                targetRecord.getBaseRecord().addPattern("Adapter Target");
            }
        }
        for (String s : adapteeNames) {
            System.out.println("adapteeName: " + s);
            IClassRecord adapteeRecord = recordHashMap.get(s);

            if (adapteeRecord != null) {
                adapteeRecord.getBaseRecord().setBoxColor("red");
                adapteeRecord.getBaseRecord().addPattern("Adaptee");
                record.getBaseRecord().addEdge(
                        record.getClassName().substring(record.getClassName().lastIndexOf('/') + 1)
                                + " -> "
                                + adapteeRecord.getClassName().substring(adapteeRecord.getClassName().lastIndexOf('/') + 1)
                                + "[label=\"<<adapts>>\"]"
                );
            }
        }
//        System.out.println(adapteeRecord.getClassName() + " shortened: " + adapteeRecord.getClassName().substring(adapteeRecord.getClassName().lastIndexOf('/') + 1));

    }
}
