package records;

import java.util.ArrayList;

public class SequenceRecord implements ISequenceRecord {
	ArrayList<ClassRecord> lifeLines= new ArrayList<ClassRecord>();
	ArrayList<MethodSignature> methodCalls = new ArrayList<MethodSignature>();
}
