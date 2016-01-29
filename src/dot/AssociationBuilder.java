package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassFieldSignatureVisitor;
import records.AssociationClassRecord;
import records.ClassRecord;
import records.IClassRecord;

public class AssociationBuilder extends AbstractBuilderDecorator{
	private ClassFieldSignatureVisitor visitor;
	private AssociationClassRecord associationRecord;

	public AssociationBuilder(String className, HashSet<String> classNames) {
		 this(new UmlBuilder(className, classNames));
	}

	public AssociationBuilder(IBuilder b) {
		super(b);
		this.builder = b;
		this.visitor = new ClassFieldSignatureVisitor(Opcodes.ASM5, b.getVisitor());
	}
	
	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}

	@Override
	public void applyPattern(ClassRecord record) {
		this.visitor.getAssociationNames();
		}
}
