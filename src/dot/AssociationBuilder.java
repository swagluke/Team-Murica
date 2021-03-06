package dot;

import java.util.HashSet;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassFieldSignatureVisitor;
import records.AssociationClassRecord;
import records.IClassRecord;

public class AssociationBuilder extends AbstractBuilderDecorator {
	private ClassFieldSignatureVisitor visitor;

	public AssociationBuilder(String className, HashSet<String> classNames, Properties properties) {
		this(new UmlBuilder(className, classNames, properties));
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
	public IClassRecord applyDecoration(IClassRecord record, Properties properties) {
		AssociationClassRecord associationRecord = new AssociationClassRecord(record);
		associationRecord.setAssociationNames(this.visitor.getAssociationNames());
		return associationRecord;
	}
}
