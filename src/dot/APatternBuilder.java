package dot;

import java.util.HashMap;
import java.util.Properties;

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
	public IClassRecord applyDecoration(IClassRecord record, Properties properties) {
		return record;
	}
	
	public abstract boolean isPattern(IClassRecord record, HashMap<String, IClassRecord> recordMap, Properties properties);
	public abstract void applyPattern(IClassRecord record, HashMap<String, IClassRecord> recordHashMap, Properties properties);
}
