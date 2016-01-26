package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;
import records.SingletonRecord;

public class SingletonBuilder extends PatternDetection {

	private SingletonRecord record;

	public SingletonBuilder(String className,  HashSet<String> classNameList) {
		super(className, classNameList);
	}
	
	@Override
	public IClassRecord build() {
		this.record = new SingletonRecord(super.build());
		this.record.setIsSingleton(this.isPattern());
		return record;
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		this.record = new SingletonRecord(super.build(visitor));
		this.record.setIsSingleton(this.isPattern());
		return record;
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}

	public boolean isPattern() {
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
