package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import asm.ClassMethodInsVisitor;
import asm.MethodInsVisitor;
import dot.records.ClassRecord;
import dot.records.IClassRecord;
import dot.records.ImplementsClassRecord;
import dot.records.MethodRecord;
import dot.records.UsesClassRecord;

public class UsesBuilder implements IBuilder {
	private IBuilder builder;
	UsesClassRecord record;
	private ClassMethodInsVisitor visitor;

	public UsesBuilder(String className, HashSet<String> classNames) {
		 this(new UmlBuilder(className, classNames));
	}
	
	public UsesBuilder(IBuilder b) {
		this.builder = b;
		this.visitor = new ClassMethodInsVisitor(Opcodes.ASM5, b.getVisitor());
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;

	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		record = new UsesClassRecord(builder.build(visitor));
		record.setUsesNamesList(this.visitor.getUsesNames());
		return record;
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}

	@Override
	public HashSet<String> getClassList() {
		return this.builder.getClassList();
	}
}
