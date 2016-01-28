package dot;

import java.util.HashSet;

import records.IClassRecord;

public abstract class PatternDetection extends UmlBuilder {

	protected IClassRecord record;

	public PatternDetection(String className, HashSet<String> classNameList) {
		super(className, classNameList);
	}

	public abstract boolean isPattern();

	@Override
	public IClassRecord build(){
		return super.build();
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}
}
