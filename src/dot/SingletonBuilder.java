package dot;

import records.ClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

public class SingletonBuilder extends IPatternBuilder {

	public SingletonBuilder(IBuilder b) {
		super(b);
	}

	protected void applyPattern(ClassRecord record) {
		record.setBoxColor("blue1");
		record.addPattern("Singleton");
		String[] shortClassNames = this.record.getClassName().split("/");
		String shortClassName = shortClassNames[shortClassNames.length - 1];
		this.record.getBaseRecord().addEdge(shortClassName + " -> " + shortClassName + "\n");
	};

	protected boolean isPattern() {
		ClassRecord baseRecord = this.record.getBaseRecord();
		boolean hasField = false;
		for (InstanceVarRecord field : baseRecord.getFieldsList()) {
			if (field.getAccess() == 10 && field.getType().equals(baseRecord.getClassName().replace("/", "."))) {
				// System.out.println("has singleton field");
				hasField = true;
				break;
			}
			// System.out.println(field.getAccess());
			// System.out.println(field.getType());
			// System.out.println(baseRecord.getClassName());
		}
		boolean hasPublicConstructor = false;
		boolean hasGetter = false;
		for (MethodRecord method : baseRecord.getMethodsList()) {
			if (method.getAccess() == 1 && method.getName().equals("<init>")) {
				// System.out.println("has public constructor");
				hasPublicConstructor = true;
			}
			if (method.getAccess() == 9 && method.getReturnType().equals(baseRecord.getClassName().replace("/", "."))) {
				// System.out.println("has getter");
				hasGetter = true;
			}
			// System.out.println(method.getName());
			// System.out.println(method.getAccess());
			// System.out.println(method.getReturnType());
			// System.out.println();
		}
		// System.out.println("hasField: " + hasField + ", !hasPublicConstructor: " + !hasPublicConstructor +
		// ", hasGetter: " + hasGetter);
		return hasField && !hasPublicConstructor && hasGetter;
	}
}
