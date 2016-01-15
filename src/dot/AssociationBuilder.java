package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassFieldSignatureVisitor;
import records.AssociationClassRecord;
import records.IClassRecord;

public class AssociationBuilder implements IBuilder {
	private IBuilder builder;
	private ClassFieldSignatureVisitor visitor;
	private AssociationClassRecord associationRecord;

	public AssociationBuilder(String className, HashSet<String> classNames) {
		 this(new UmlBuilder(className, classNames));
	}

	public AssociationBuilder(IBuilder umlBuilder) {
		this.builder = umlBuilder;
		this.visitor = new ClassFieldSignatureVisitor(Opcodes.ASM5, umlBuilder.getVisitor());
	}
	
	@Override
	public HashSet<String> getClassList() {
		return this.builder.getClassList();
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record = builder.build(visitor);
		associationRecord = new AssociationClassRecord(record);
		associationRecord.setAssociationNames(this.visitor.getAssociationNames());
		return associationRecord;
	}

	@Override
	public IClassRecord build() {
		return this.build(visitor);
	}
	
	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}

	@Override
	public String getClassUML() {
		return this.associationRecord.getClassUml();
	}
}
