package dot;

import java.util.HashSet;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import records.ExtendedClassRecord;
import records.IClassRecord;

public class ExtensionBuilder extends AbstractBuilderDecorator{
	private ClassDeclarationVisitor visitor;

	public ExtensionBuilder(String className, HashSet<String> classNames, Properties properties) {
		this(new UmlBuilder(className, classNames, properties));
	}
	
	public ExtensionBuilder(IBuilder b) {
		super(b);
		this.visitor = (ClassDeclarationVisitor) b.getVisitor();
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}

	@Override
	public IClassRecord applyDecoration(IClassRecord record, Properties properties) {
		ExtendedClassRecord extendedRecord = new ExtendedClassRecord(record);
		extendedRecord.setExtendsName(this.visitor.getExtendsName());
		return extendedRecord;
	}
}
