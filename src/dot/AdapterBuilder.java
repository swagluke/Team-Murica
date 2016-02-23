package dot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;

import records.ExtendedClassRecord;
import records.IClassRecord;
import records.ImplementsClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

public class AdapterBuilder extends APatternBuilder {
    private HashSet<String> adapteeNames;
    private HashSet<String> targetNames;

    public AdapterBuilder(IBuilder b) {
        super(b);
        adapteeNames = new HashSet<String>();
        targetNames = new HashSet<String>();
    }


    @Override
    public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap, Properties properties) {
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
//                	System.out.println(possible.replace("/", "."));
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
//                        	System.out.println(arg);
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
    public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap, Properties properties) {
//    	System.out.println("apply adapter");
//    	System.out.println(targetNames);
//    	System.out.println(adapteeNames);
        record.getBaseRecord().setBoxColor("red");
        record.getBaseRecord().addPattern("Adapter");
//        System.out.println(record.getClassName().substring(record.getClassName().lastIndexOf('/') + 1));

//        System.out.println("Target Name: " + targetName + " Adaptee Name" + adapteeName);
        for (String s : targetNames) {
//            System.out.println("targetName: " + s);
            IClassRecord targetRecord = recordHashMap.get(s);
            if (targetRecord != null) {
                targetRecord.getBaseRecord().setBoxColor("red");
                targetRecord.getBaseRecord().addPattern("Adapter Target");
            }
        }
        for (String s : adapteeNames) {
//            System.out.println("adapteeName: " + s);
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
