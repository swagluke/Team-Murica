package dot;

import java.util.HashSet;
import java.util.Properties;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassMethodInsVisitor;
import records.IClassRecord;
import records.UsesClassRecord;

public class UsesBuilder extends AbstractBuilderDecorator{
	private ClassMethodInsVisitor visitor;

	public UsesBuilder(String className, HashSet<String> classNames, Properties properties) {
		 this(new UmlBuilder(className, classNames, properties));
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
	public IClassRecord applyDecoration(IClassRecord record, Properties properties) {
		UsesClassRecord usesClassRecord = new UsesClassRecord(record);
		usesClassRecord.setUsesNamesList(this.visitor.getUsesNames());
		return usesClassRecord;
	}
}
