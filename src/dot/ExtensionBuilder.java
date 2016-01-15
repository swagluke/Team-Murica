package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import dot.records.ClassRecord;
import dot.records.ExtendedClassRecord;
import dot.records.IClassRecord;

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
		this.setClassList(builder.getClassList());
	}
	
	@Override
	public HashSet<String> getClassList() {
		return this.classList;
	}

	@Override
	public void setClassList(HashSet<String> classList) {
		this.classList = classList;
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record = builder.build(visitor);
		extendedRecord = new ExtendedClassRecord((ClassRecord) record);
		extendedRecord.setExtendsName(this.visitor.getExtendsName());
		extendedRecord.classList = classList;
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
		// s.append(this.builder.getClassUML());
		// s.append("\n");
		// s.append(extendedRecord.getExtendsName());
		// System.out.println("** " + s.toString() + " **");
		// System.out.println("** " + s.toString() + " **");

		return s.toString();
	}

}
