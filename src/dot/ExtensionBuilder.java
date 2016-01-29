package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import records.ClassRecord;

public class ExtensionBuilder extends AbstractBuilderDecorator{
	private ClassDeclarationVisitor visitor;

	public ExtensionBuilder(String className, HashSet<String> classNames) {
		this(new UmlBuilder(className, classNames));
	}
	
	public ExtensionBuilder(IBuilder b) {
		super(b);
		this.visitor = (ClassDeclarationVisitor) b.getVisitor();
	}

	@Override
	public void applyPattern(ClassRecord record) {
		String extendsName = record.getExtendsName();
		if (extendsName == null) {
			return;
		}
		StringBuilder s = new StringBuilder();
		String className = record.getClassName();
		String[] shortClassNameList = className.replace("/", ".").split("\\.");
		String shortClassName = shortClassNameList[shortClassNameList.length - 1];

		String[] shortExtendNameList = extendsName.replace("/", ".").split("\\.");
		String shortExtendName = shortExtendNameList[shortExtendNameList.length - 1];
		if (this.getClassList().contains(extendsName.replace("/", "."))) {
			s.append("edge [ style = \"normal\"]\n");
			s.append(shortClassName + " -> " + shortExtendName + "\n");
		}
		record.addEdge(s.toString());
	}

	@Override
	public ClassVisitor getVisitor() {
		return this.visitor;
	}
	
//	@Override
//	public HashSet<String> getClassList() {
//		return this.builder.getClassList();
//	}
//
//	@Override
//	public ClassRecord build(ClassVisitor visitor) {
//		ClassRecord record = builder.build(visitor);
//		extendedRecord = new ExtendedClassRecord(record.getBaseRecord());
//		extendedRecord.setExtendsName(this.visitor.getExtendsName());
//		return extendedRecord;
//	}
//
//	@Override
//	public ClassVisitor getVisitor() {
//		return this.visitor;
//	}
//
//	@Override
//	public ClassRecord build() {
//		return this.build(visitor);
//	}
//
//	@Override
//	public String getClassUML() {
//		return this.builder.getClassUML() + this.extendedRecord.getClassUml();
//	}

}
