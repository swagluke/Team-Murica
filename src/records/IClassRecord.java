package records;

import java.util.HashSet;

public interface IClassRecord {
	public String getClassUml();
	public String getClassName();
	public HashSet<String> getClassList();
	public IClassRecord getInnerRecord();
	public ClassRecord getBaseRecord();
	default public boolean canConvertRecord(Class<?> classVar) {
		if (this.getClass() == classVar) {
			return true;
		}
		return this.getInnerRecord().canConvertRecord(classVar);
	}
	default public IClassRecord tryConvertRecord(Class<?> classVar) {
		if (this.getClass() == classVar) {
			return this;
		}
		return this.getInnerRecord().tryConvertRecord(classVar);
	}
}
