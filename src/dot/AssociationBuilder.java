package dot;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassFieldSignatureVisitor;
import dot.records.AssociationClassRecord;
import dot.records.IClassRecord;

public class AssociationBuilder implements IBuilder {
	private IBuilder builder;
	private ClassFieldSignatureVisitor visitor;
	
	public AssociationBuilder(String className){
		this(new UmlBuilder(className));
		
		
	}
	public AssociationBuilder(UmlBuilder umlBuilder){
		this.builder = umlBuilder;
		this.visitor = new ClassFieldSignatureVisitor(Opcodes.ASM5, umlBuilder.getVisitor());
		//this.setExtendsName();
	}
	
	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record  = builder.build(visitor);
		AssociationClassRecord e = new AssociationClassRecord(record);
		e.setAssociationNames(this.visitor.getAssociationNames());
		return e;		
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
		// TODO Auto-generated method stub
		return null;
	}
}
