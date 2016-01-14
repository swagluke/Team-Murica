package dot;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

import dot.records.ClassRecord;
import dot.records.IClassRecord;
import dot.records.MethodRecord;
import dot.records.UsesClassRecord;

public class UsesBuilder implements IBuilder {

	private ImplementsBuilder implementsBuilder;

	public UsesBuilder(ImplementsBuilder b){
		this.implementsBuilder = b;
	}
	@Override
	public ClassVisitor getVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		// TODO Auto-generated method stub
		UsesClassRecord record = new UsesClassRecord((ClassRecord) implementsBuilder.build(visitor));
//		for (MethodRecord m : record.getMethods()) {
//			for (Type t : m.getArgTypes()) {
//				usesList.add(t.getClassName());
//			}
//			usesList.add(m.getReturnType());
//		}
		return record;
	}

}
