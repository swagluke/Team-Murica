package dot;

import java.util.HashMap;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

public class SingletonBuilder extends APatternBuilder {

	public SingletonBuilder(IBuilder b) {
		super(b);
	}

	public void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap, Properties properties) {
		ClassRecord baseRecord = record.getBaseRecord();
		baseRecord.setBoxColor("blue1");
		baseRecord.addPattern("Singleton");
		String[] shortClassNames = baseRecord.getClassName().split("/");
		String shortClassName = shortClassNames[shortClassNames.length - 1];
		baseRecord.addEdge(shortClassName + " -> " + shortClassName + "\n");
	};

	public boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap, Properties properties) {
		ClassRecord baseRecord = record.getBaseRecord();
		boolean hasField = false;
		for (InstanceVarRecord field : baseRecord.getFieldsList()) {
			if (field.getAccess() == 10 && field.getType().equals(baseRecord.getClassName().replace("/", "."))) {
				hasField = true;
				break;
			}
		}
		boolean hasPublicConstructor = false;
		boolean hasGetter = false;
		for (MethodRecord method : baseRecord.getMethodsList()) {
			if (method.getAccess() == 1 && method.getName().equals("<init>")) {
				hasPublicConstructor = true;
			}
			if (method.getAccess() == 9 && method.getReturnType().equals(baseRecord.getClassName().replace("/", "."))) {
				hasGetter = true;
			}
		}
		return hasField && !hasPublicConstructor && hasGetter;
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.builder.getVisitor();
	}


}
