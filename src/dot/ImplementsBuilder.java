package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import dot.records.IClassRecord;
import dot.records.ImplementsClassRecord;

public class ImplementsBuilder implements IBuilder {
	public HashSet<String> implementsList;
	IBuilder builder;
	private ClassDeclarationVisitor visitor;
	private ImplementsClassRecord record;
	private String className;
	HashSet<String> classList;

	public ImplementsBuilder(String className, HashSet<String> classNames) {
		this(new ExtensionBuilder(className, classNames));
		this.className = className;
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
	public ClassVisitor getVisitor() {
		return visitor;
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		// System.out.println("Building implementsList");
		record = new ImplementsClassRecord(this.builder.build(visitor));
		this.implementsList = this.visitor.getImplementsList();
		record.setImplementsList(implementsList);
//		record.className = className;
		// System.out.println(implementsList.size());
		return record;
	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}

}
