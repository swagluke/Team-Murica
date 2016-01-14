package dot;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import dot.records.ExtendedClassRecord;
import dot.records.IClassRecord;

public class ExtensionBuilder implements IBuilder{
	private IBuilder builder;
	private ClassDeclarationVisitor visitor;
	
	public ExtensionBuilder(String className){
		this(new UmlBuilder(className));
		
		
	}
	public ExtensionBuilder(UmlBuilder umlBuilder){
		this.builder = umlBuilder;
		this.visitor = (ClassDeclarationVisitor) umlBuilder.getVisitor();
		//this.setExtendsName();
	}
	
	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record  = builder.build(visitor);
		ExtendedClassRecord e = new ExtendedClassRecord(record);
		e.setExtendsName(this.visitor.getExtendsName());
		return record;		
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}
	@Override
	public IClassRecord build() {
		return this.build(visitor);
	}

	


}
