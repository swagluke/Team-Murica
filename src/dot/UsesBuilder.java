package dot;

import java.util.ArrayList;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

import dot.records.ClassRecord;
import dot.records.IClassRecord;
import dot.records.ImplementsClassRecord;
import dot.records.MethodRecord;
import dot.records.UsesClassRecord;

public class UsesBuilder implements IBuilder {

	private ImplementsBuilder implementsBuilder;
	UsesClassRecord record;
	ArrayList<String> classList;
	public UsesBuilder(ImplementsBuilder b){
		this.implementsBuilder = b;
		this.classList = b.builder.classList;
	}
	@Override
	public ClassVisitor getVisitor() {
		return this.implementsBuilder.getVisitor();
		
	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		record = new UsesClassRecord((ImplementsClassRecord)implementsBuilder.build(visitor));
		for (MethodRecord m : record.innerRecord.innerRecord.record.getMethodsList()) {
			for (Type t : m.getArgTypes()) {
				record.addUses(t.getClassName());
			}
			//if(classList.contains(m.getReturnType()))
				record.addUses(m.getReturnType());
		}
		return record;
	}
	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}
	public ArrayList<String> getUsesList() {
		// TODO Auto-generated method stub
		return null;
	}

}
