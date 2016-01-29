package dot;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
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
		if (this.isPattern()) {
			this.applyPattern(record.getBaseRecord());
		}
		return record;
	}
	
	abstract protected boolean isPattern();
	abstract protected void applyPattern(ClassRecord record);
}
