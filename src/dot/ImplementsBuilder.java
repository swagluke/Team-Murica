package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import records.ClassRecord;
import records.IClassRecord;
import records.ImplementsClassRecord;

public class ImplementsBuilder implements IBuilder {
	public HashSet<String> implementsList;
	IBuilder builder;
	private ClassDeclarationVisitor visitor;
	private ClassRecord record;

	public ImplementsBuilder(String className, HashSet<String> classNames) {
		this(new ExtensionBuilder(className, classNames));
	}

	public ImplementsBuilder(IBuilder extensionBuilder) {
		this.builder = extensionBuilder;
		this.visitor = (ClassDeclarationVisitor) builder.getVisitor();

	}
	
	@Override
	public HashSet<String> getClassList() {
		return this.builder.getClassList();
	}

	@Override
	public ClassRecord getClassRecord() {
		return record;
	}

	@Override
	public ClassVisitor getVisitor() {
		return visitor;
	}

	@Override
	public ClassRecord build(ClassVisitor visitor) {
		record = this.builder.build(visitor);
		this.implementsList = this.visitor.getImplementsList();
		record.setImplementsList(implementsList);
		return record;
	}

	@Override
	public ClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public String getClassUML() {
		return this.builder.getClassUML() + this.record.getClassUml();
	}

}
