package dot;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import dot.records.IClassRecord;
import dot.records.ImplementsClassRecord;

public class ImplementsBuilder implements IBuilder {
	public ArrayList<String> implementsList;
	private ExtensionBuilder builder;
	private ClassDeclarationVisitor visitor;
	public ImplementsBuilder(String className){
		this(new ExtensionBuilder(className));
	}
	public ImplementsBuilder(ExtensionBuilder extensionBuilder) {
		this.builder = extensionBuilder;
		this.visitor = (ClassDeclarationVisitor) builder.getVisitor();
	}
	
	@Override
	public ClassVisitor getVisitor() {
		return visitor;
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		ImplementsClassRecord record = new ImplementsClassRecord(this.builder.build(visitor));
		this.implementsList = ((ClassDeclarationVisitor) visitor).getImplementsList();
		record.setImplementsList(implementsList);
		return record;
	}
	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}
	@Override
	public String getClassUML() {
		// TODO Auto-generated method stub
		return null;
	}

}
