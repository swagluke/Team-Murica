package dot;

import java.util.HashSet;

import records.IClassRecord;

public abstract class PatternDetection extends UmlBuilder {

	private IClassRecord record;

	public PatternDetection(String className, HashSet<String> classNameList) {
		super(className, classNameList);
		// TODO Auto-generated constructor stub
	}

	public abstract boolean isPattern();

	@Override
	public IClassRecord build(){
		return record;
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}
}
