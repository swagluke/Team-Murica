package dot.records;

import java.util.HashSet;

public interface IClassRecord {
	String getClassUml();
	String getClassName();
	HashSet<String> getClassList();
	IClassRecord getInnerRecord();
	ClassRecord getBaseRecord();
}
