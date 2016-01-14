package dot;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import dot.records.ExtendedClassRecord;
import dot.records.IClassRecord;

public class ExtensionBuilder implements IBuilder {
	private IBuilder builder;
	private ClassDeclarationVisitor visitor;
	private ExtendedClassRecord extendedRecord;

	public ExtensionBuilder(String className) {
		this(new UmlBuilder(className));

	}

	public ExtensionBuilder(UmlBuilder umlBuilder) {
		this.builder = umlBuilder;
		this.visitor = (ClassDeclarationVisitor) umlBuilder.getVisitor();
		// this.setExtendsName();
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record = builder.build(visitor);
		extendedRecord = new ExtendedClassRecord(record);
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
		StringBuilder s = new StringBuilder();
		s.append(this.extendedRecord.getClassUml());
//		s.append(this.builder.getClassUML());
//		s.append("\n");
//		s.append(extendedRecord.getExtendsName());
//		System.out.println("** " + s.toString() + " **");
		System.out.println("** " + s.toString() + " **");

		
		return s.toString();
	}

}
