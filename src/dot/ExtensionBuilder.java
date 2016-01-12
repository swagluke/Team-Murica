package dot;

import org.objectweb.asm.ClassVisitor;

import dot.records.ExtendedClassRecord;
import dot.records.IClassRecord;

public class ExtensionBuilder implements IBuilder{
	private IBuilder builder;
	private String extendsName;
	
	public ExtensionBuilder(String className){
		this.builder = new UmlBuilder(className);
		
		//this.setExtendsName();
	}
	
	public String getExtendsName() {
		return extendsName;
	}

	private void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}

	@Override
	public IClassRecord build() {
		IClassRecord record  = builder.build();
		ExtendedClassRecord e = new ExtendedClassRecord(record);
		return record;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClassVisitor getVisitor() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
