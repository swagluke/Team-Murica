package dot;

import java.util.ArrayList;

import records.ClassRecord;
import records.IClassRecord;

public class DecoratorBuilder extends APatternBuilder {

	private UmlBuilder builder;
	private ClassRecord record;
	private ArrayList<String> decoratorEdges;

	public DecoratorBuilder(IBuilder b) {
		super(b);
	}


	@Override
	public boolean isPattern(IClassRecord record) {
		if (record.get)
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
