package dot;

import java.util.HashSet;

import org.objectweb.asm.ClassVisitor;

import asm.ClassDeclarationVisitor;
import records.IClassRecord;
import records.ImplementsClassRecord;

public class ImplementsBuilder extends AbstractBuilderDecorator {
	private ClassDeclarationVisitor visitor;

	public ImplementsBuilder(String className, HashSet<String> classNames) {
		this(new UmlBuilder(className, classNames));
	}

	public ImplementsBuilder(IBuilder b) {
		super(b);
		this.visitor = (ClassDeclarationVisitor) b.getVisitor();
	}
	
	@Override
	public ClassVisitor getVisitor() {
		return visitor;
	}

	@Override
	public IClassRecord applyPattern(IClassRecord record) {
		ImplementsClassRecord implementsRecord = new ImplementsClassRecord(record);
		implementsRecord.setImplementsList(this.visitor.getImplementsList());
		return implementsRecord;
//		StringBuilder s = new StringBuilder();
//		String className = record.getClassName();
//		s.append("edge [ arrowhead = \"empty\" style = \"dotted\"]\n");
//		String[] shortClassNameList = className.replace("/", ".").split("\\.");
//		String shortClassName = shortClassNameList[shortClassNameList.length - 1];
//
//		for (String implement : record.getImplementsList()) {
//			String[] shortImplementList = implement.replace("/", ".").split("\\.");
//			String shortImplement = shortImplementList[shortImplementList.length - 1];
//			if (this.getClassList().contains(implement.replace("/", "."))) {
//				s.append(shortClassName + " -> " + shortImplement + "\n");
//			}
//		}
//		record.addEdge(s.toString());
//		
	}
}
