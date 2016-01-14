package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import dot.records.IClassRecord;

public class AssociationBuilder implements IBuilder {

	public AssociationBuilder(UsesBuilder u) {
		// TODO Auto-generated constructor stub
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
		return null;
	}

	@Override
	public String getClassUML() {
		// TODO Auto-generated method stub
		return null;
	}

	public HashSet<String> getAssociationList() {
		// TODO Auto-generated method stub
		return null;
	}

}
