package dot;

import java.util.ArrayList;
import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;

public class DecoratorBuilder extends PatternDetection {

	private UmlBuilder builder;
	private ClassRecord record;
	private ArrayList<String> decoratorEdges;
	
	public DecoratorBuilder(String className, HashSet<String> classNameList) {
		super(className, classNameList);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param visitor
	 * @return
	 */
	@Override
	public ClassRecord build(ClassVisitor visitor){
		this.record =this.builder.build(visitor);
		if(this.isPattern()){
			this.record.setBoxColor("red1");
			this.record.addPattern("Decorator");
			
		}
		for(String s:this.decoratorEdges){
			this.record.addEdge(s);
		}
		
		return record;
	}
	@Override
	public boolean isPattern() {
		ClassRecord baseRecord = this.record.getBaseRecord();
		boolean hasConcreteImplementation=false;
		boolean hasOtherClass=false;
		boolean otherClassUsesConcrete = false;
		
		
		
		return hasConcreteImplementation&&hasOtherClass&&otherClassUsesConcrete;
	}

}
