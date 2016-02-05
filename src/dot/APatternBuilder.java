package dot;

import org.objectweb.asm.ClassVisitor;

import records.IClassRecord;

import java.util.HashMap;

abstract public class APatternBuilder extends AbstractBuilderDecorator {

	public APatternBuilder(IBuilder b) {
		super(b);
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.builder.getVisitor();
	}
	
	@Override
	public IClassRecord applyDecoration(IClassRecord record) {
//		if (this.isPattern(record)) {
//			this.applyPattern(record);
//		}
		return record;
	}
	
	public abstract boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap);
	public abstract void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap);
}