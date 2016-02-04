package dot;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

import java.util.HashMap;

public class SingletonBuilder extends APatternBuilder {

	public SingletonBuilder(IBuilder b) {
		super(b);
	}

	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap) {
		ClassRecord baseRecord = record.getBaseRecord();
		baseRecord.setBoxColor("blue1");
		baseRecord.addPattern("Singleton");
		String[] shortClassNames = baseRecord.getClassName().split("/");
		String shortClassName = shortClassNames[shortClassNames.length - 1];
		baseRecord.addEdge(shortClassName + " -> " + shortClassName + "\n");
	};

	public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap) {
		ClassRecord baseRecord = record.getBaseRecord();
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

	@Override
	public ClassVisitor getVisitor() {
		return this.builder.getVisitor();
	}


}
