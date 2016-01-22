package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.IClassRecord;
import records.SingletonRecord;

public class SingletonBuilder implements IBuilder {

	private UmlBuilder builder;
	private SingletonRecord record;

	public SingletonBuilder(UmlBuilder b) {
		this.builder = b;
		
	}
	@Override
	public ClassVisitor getVisitor() {
		return builder.getVisitor();
	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		this.record = new SingletonRecord(builder.build(visitor));
		return record;
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}

	@Override
	public HashSet<String> getClassList() {
		return builder.getClassList();
	}
	

}
