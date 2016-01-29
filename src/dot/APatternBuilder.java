package dot;

import org.objectweb.asm.ClassVisitor;

import records.IClassRecord;

abstract public class APatternBuilder extends AbstractBuilderDecorator {

	public APatternBuilder(IBuilder b) {
		super(b);
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.builder.getVisitor();
	}
	
	@Override
	protected IClassRecord applyDecoration(IClassRecord record) {
		if (this.isPattern(record)) {
			this.applyPattern(record);
		}
		return record;
	}
	
	abstract protected boolean isPattern(IClassRecord record);
	abstract protected void applyPattern(IClassRecord record);
}
