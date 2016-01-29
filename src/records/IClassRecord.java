package records;

import java.util.HashSet;

public interface IClassRecord {
	public String getClassUml();
	public String getClassName();
	public HashSet<String> getClassList();
	public IClassRecord getInnerRecord();
	public ClassRecord getBaseRecord();
	default public boolean canConvertRecord(Class<?> classVar) {
		IClassRecord innerRecord = this.getInnerRecord();
		if (innerRecord.getClass().isInstance(classVar)) {
			return true;
		}
		return innerRecord.canConvertRecord(classVar);
	}
	default public IClassRecord tryConvertRecord(Class<?> classVar) {
		IClassRecord innerRecord = this.getInnerRecord();
		if (innerRecord.getClass().isInstance(classVar)) {
			return innerRecord;
		}
		return innerRecord.tryConvertRecord(classVar);
	}
}
