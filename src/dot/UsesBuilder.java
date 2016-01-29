package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import asm.ClassMethodInsVisitor;
import asm.MethodInsVisitor;
import records.ClassRecord;
import records.IClassRecord;
import records.ImplementsClassRecord;
import records.MethodRecord;
import records.UsesClassRecord;

public class UsesBuilder extends AbstractBuilderDecorator{
	private ClassMethodInsVisitor visitor;

	public UsesBuilder(String className, HashSet<String> classNames) {
		 this(new UmlBuilder(className, classNames));
	}
	
	public UsesBuilder(IBuilder b) {
		super(b);
		this.visitor = new ClassMethodInsVisitor(Opcodes.ASM5, b.getVisitor());
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}

	@Override
	public IClassRecord applyDecoration(IClassRecord record) {
		UsesClassRecord usesClassRecord = new UsesClassRecord(record);
		usesClassRecord.setUsesNamesList(this.visitor.getUsesNames());
		return usesClassRecord;
	}
}
