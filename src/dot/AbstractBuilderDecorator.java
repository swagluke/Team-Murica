package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;

abstract public class AbstractBuilderDecorator implements IBuilder{
	protected IBuilder builder;
	protected IClassRecord record;
	
	public AbstractBuilderDecorator(String className, HashSet<String> classNames) {
		this(new UmlBuilder(className, classNames));
	}

	public AbstractBuilderDecorator(IBuilder b) {
		this.builder = b;
	}

	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	public IClassRecord build(ClassVisitor visitor) {
		this.record = this.builder.build(visitor);
		this.record = this.applyDecoration(this.builder.getClassRecord());
		return this.record;
	}

	abstract public ClassVisitor getVisitor();
//	public ClassVisitor getVisitor() {
//		return builder.getVisitor();
//	}

	public String getClassUML() {
		return this.record.getClassUml();
	}

	public HashSet<String> getClassList() {
		return builder.getClassList();
	}
	
	public ClassRecord getClassRecord() {
		return this.builder.getClassRecord();
	}
	
	protected abstract IClassRecord applyDecoration(IClassRecord record);

}
