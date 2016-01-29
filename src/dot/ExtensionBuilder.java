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
	public ClassVisitor getVisitor() {
		return this.visitor;
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
}
