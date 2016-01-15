package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Type;

import dot.records.ClassRecord;
import dot.records.IClassRecord;
import dot.records.ImplementsClassRecord;
import dot.records.MethodRecord;
import dot.records.UsesClassRecord;

public class UsesBuilder implements IBuilder {
	private IBuilder builder;
	UsesClassRecord record;

	public UsesBuilder(IBuilder b) {
		this.builder = b;
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.builder.getVisitor();

	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}
	//Get methodVisitor list from UMLBuilder somehow, and then use that HashSet to build the classname and uses list relationship
	@Override
	public IClassRecord build(ClassVisitor visitor) {
		record = new UsesClassRecord(builder.build(visitor));
		for (MethodRecord m : record.getBaseRecord().getMethodsList()) {
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

	@Override
	public HashSet<String> getClassList() {
		return this.builder.getClassList();
	}
}
