package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.IClassRecord;

abstract public class IPatternBuilder implements IBuilder {
	protected IBuilder builder;
	protected IClassRecord record;

	public IPatternBuilder(IBuilder b) {
		this.builder = b;
	}

	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	public IClassRecord build(ClassVisitor visitor) {
		this.record = this.builder.build(visitor);
		if (this.isPattern()) {
			this.applyPattern();
		}
		return this.record;
	}

	public ClassVisitor getVisitor() {
		return builder.getVisitor();
	}

	public String getClassUML() {
		return this.record.getClassUml();
	}

	public HashSet<String> getClassList() {
		return builder.getClassList();
	}
	
	abstract protected boolean isPattern();
	abstract protected void applyPattern();
}
