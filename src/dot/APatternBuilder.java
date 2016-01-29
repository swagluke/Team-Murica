package dot;

import org.objectweb.asm.ClassVisitor;

import records.IClassRecord;

abstract public class APatternBuilder extends AbstractBuilderDecorator {

	public APatternBuilder(IBuilder b) {
		super(b);
	}

	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	public IClassRecord build(ClassVisitor visitor) {
		this.record = this.builder.build(visitor);
		if (this.isPattern()) {
			this.record = this.applyPattern(this.record);
		}
		return this.record;
	}
	
	abstract protected boolean isPattern();
}
