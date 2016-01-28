package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;

abstract public class IPatternBuilder implements IBuilder {
	protected IBuilder builder;
	protected ClassRecord record;

	public IPatternBuilder(IBuilder b) {
		this.builder = b;
	}

	public ClassRecord build() {
		return this.build(this.getVisitor());
	}

	public ClassRecord build(ClassVisitor visitor) {
		this.record = this.builder.build(visitor);
		if (this.isPattern()) {
			this.applyPattern(this.record);
		}
		return this.record;
	}

	public ClassVisitor getVisitor() {
		return builder.getVisitor();
	}

	public String getClassUML() {
		return this.getClassRecord().getClassUml();
	}

	public HashSet<String> getClassList() {
		return this.builder.getClassList();
	}
	
	public ClassRecord getClassRecord() {
		return this.builder.getClassRecord();
	}
	
	abstract protected boolean isPattern();
	abstract protected void applyPattern(ClassRecord record);
}
