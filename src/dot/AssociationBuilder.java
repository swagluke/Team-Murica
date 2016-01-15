package dot;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassFieldSignatureVisitor;
import dot.records.AssociationClassRecord;
import dot.records.IClassRecord;

public class AssociationBuilder implements IBuilder {
	private IBuilder builder;
	private ClassFieldSignatureVisitor visitor;

	public AssociationBuilder(String className) {
		//this(new UmlBuilder(className));

	}

	public AssociationBuilder(IBuilder umlBuilder) {
		this.builder = umlBuilder;
		this.visitor = new ClassFieldSignatureVisitor(Opcodes.ASM5, umlBuilder.getVisitor());
		// this.setExtendsName();
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		IClassRecord record = builder.build(visitor);
		AssociationClassRecord e = new AssociationClassRecord(record);
		e.setAssociationNames(this.visitor.getAssociationNames());
		return e;
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}

	@Override
	public IClassRecord build() {
		return this.build(visitor);
	}

	@Override
	public String getClassUML() {
		System.out.println(this.build());
		StringBuilder s = new StringBuilder();
		s.append(this.builder.getClassUML());
		s.append("\n");
		AssociationClassRecord record = (AssociationClassRecord) this.build();
		record.getAssociationNames();
		// this
		// associatesMap.put(className, ((AssociationClassRecord) a.build()).getAssociationNames());
		// AssociationClassRecord record = (AssociationClassRecord) this.build();
		// associatesMap = record.ge
		// s.append("edge [ style = \"normal\" arrowhead = \"vee\"]\n");
		// for (String key : associatesMap.keySet()) {
		// System.out.println(key);
		// System.out.println(associatesMap.get(key));
		// String[] shortKeyList = key.split("\\.");
		// String shortKey = shortKeyList[shortKeyList.length - 1];
		// HashSet<String> shortValueList = associatesMap.get(key);
		// for (String val : shortValueList) {
		// String[] shortValList = val.replace("/", ".").split("\\.");
		// String shortValue = shortValList[shortValList.length - 1];
		// s.append(shortKey + " -> " + shortValue + "\n");
		// // System.out.println(shortValue);
		//
		// }
		// }
		return s.toString();
	}
}
