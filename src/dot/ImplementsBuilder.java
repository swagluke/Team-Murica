package dot;

import java.util.HashSet;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import records.IClassRecord;
import records.ImplementsClassRecord;

public class ImplementsBuilder extends AbstractBuilderDecorator {
	private ClassDeclarationVisitor visitor;

	public ImplementsBuilder(String className, HashSet<String> classNames, Properties properties) {
		this(new UmlBuilder(className, classNames, properties));
	}

	public ImplementsBuilder(IBuilder b) {
		super(b);
		this.visitor = (ClassDeclarationVisitor) b.getVisitor();
	}
	
	@Override
	public ClassVisitor getVisitor() {
		return visitor;
	}

	@Override
	public IClassRecord applyDecoration(IClassRecord record, Properties properties) {
		ImplementsClassRecord implementsRecord = new ImplementsClassRecord(record);
		implementsRecord.setImplementsList(this.visitor.getImplementsList());
		return implementsRecord;
	}
}
