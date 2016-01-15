package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

import dot.records.IClassRecord;
import dot.records.ImplementsClassRecord;
import dot.records.MethodRecord;
import dot.records.UsesClassRecord;

public class UsesBuilder implements IBuilder {

	private ImplementsBuilder implementsBuilder;
	UsesClassRecord record;
	HashSet<String> classList;

	public UsesBuilder(ImplementsBuilder b) {
		this.implementsBuilder = b;
		this.classList = b.builder.getClassList();
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.implementsBuilder.getVisitor();

	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}
	//Get methodVisitor list from UMLBuilder somehow, and then use that HashSet to build the classname and uses list relationship
	@Override
	public IClassRecord build(ClassVisitor visitor) {
		record = new UsesClassRecord((ImplementsClassRecord) implementsBuilder.build(visitor));
		for (MethodRecord m : record.innerRecord.innerRecord.record.getMethodsList()) {
			for (Type t : m.getArgTypes()) {
				record.addUses(t.getClassName());
			}
			// if(classList.contains(m.getReturnType()))
			record.addUses(m.getReturnType());
		}
		return record;
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}

	public HashSet<String> getUsesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashSet<String> getClassList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClassList(HashSet<String> classList) {
		// TODO Auto-generated method stub
		
	}

}
