package dot;

import java.util.HashSet;

import records.ExtendedClassRecord;
import records.IClassRecord;
import records.ImplementsClassRecord;

public class DecoratorBuilder extends APatternBuilder {
	public DecoratorBuilder(IBuilder b) {
		super(b);
	}

	@Override
	public boolean isPattern(IClassRecord record) {
		System.out.println("\nin is pattern");
		System.out.println(this.getClassRecord().getClassName());
		HashSet<String> possible = new HashSet<String>();
		if (record.canConvertRecord(ExtendedClassRecord.class)) {
			System.out.println("can convert to the class");
			ExtendedClassRecord extendedClassRecord = (ExtendedClassRecord) record.tryConvertRecord(ExtendedClassRecord.class);
			System.out.println(extendedClassRecord.getExtendsName());
			possible.add(extendedClassRecord.getExtendsName());
		}
		if (record.canConvertRecord(ImplementsClassRecord.class)) {
			System.out.println("can convert to the class");
			ImplementsClassRecord implementsClassRecord = (ImplementsClassRecord) record.tryConvertRecord(ImplementsClassRecord.class);
			System.out.println(implementsClassRecord.getImplementsList());
			possible.addAll(implementsClassRecord.getImplementsList());
		}
		System.out.println(possible);
		
		return false;
	}

	@Override
	protected void applyPattern(IClassRecord record) {
		// TODO Auto-generated method stub
	}

	// /**
	// *
	// * @param visitor
	// * @return
	// */
	// @Override
	// public ClassRecord build(ClassVisitor visitor) {
	// this.record = this.builder.build(visitor);
	// if (this.isPattern()) {
	// this.record.setBoxColor("red1");
	// this.record.addPattern("Decorator");
	//
	// }
	// for (String s : this.decoratorEdges) {
	// this.record.addEdge(s);
	// }
	//
	// return record;
	// }
	//
	// @Override
	// public boolean isPattern() {
	// ClassRecord baseRecord = this.record.getBaseRecord();
	// boolean hasConcreteImplementation = false;
	// boolean hasOtherClass = false;
	// boolean otherClassUsesConcrete = false;
	//
	// return hasConcreteImplementation && hasOtherClass &&
	// otherClassUsesConcrete;
	// }
	//
}
