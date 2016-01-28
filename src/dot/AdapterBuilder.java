package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import records.ClassRecord;
import records.IClassRecord;
import records.SingletonRecord;

public class AdapterBuilder extends PatternDetection {
	private UmlBuilder builder;
	private ClassRecord record;
	
	public AdapterBuilder(String className, HashSet<String> classNameList) {
		super(className, classNameList);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ClassRecord build(ClassVisitor visitor){
		this.record =this.builder.build(visitor);
		if(this.isPattern()){
			this.record.setBoxColor("red1");
			this.record.addPattern("Decorator");
			
		}
//		for(String s:){
//			this.record.addEdge(s);
//		}
		
		return record;
	}
	@Override
	public boolean isPattern() {
		// TODO Auto-generated method stub
		return false;
	}

}
