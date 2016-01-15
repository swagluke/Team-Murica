package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import records.ClassRecord;
import records.ExtendedClassRecord;
import records.IClassRecord;

public class ExtensionBuilder implements IBuilder {
	IBuilder builder;
	private ClassDeclarationVisitor visitor;
	private ExtendedClassRecord extendedRecord;
	HashSet<String> classList;

	public ExtensionBuilder(String className, HashSet<String> classNames) {
		this(new UmlBuilder(className, classNames));

	}

	public ExtensionBuilder(IBuilder umlBuilder) {
		this.builder = umlBuilder;
		this.visitor = (ClassDeclarationVisitor) umlBuilder.getVisitor();
	}
	
	@Override
	public HashSet<String> getClassList() {
		return this.builder.getClassList();
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record = builder.build(visitor);
		extendedRecord = new ExtendedClassRecord((ClassRecord) record);
		extendedRecord.setExtendsName(this.visitor.getExtendsName());
		return extendedRecord;
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}

	@Override
	public IClassRecord build() {
		return this.build(visitor);
	}

	@Override
	public String getClassUML() {
		return this.extendedRecord.getClassUml();
	}

}
