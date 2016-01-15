package asm;

import java.util.HashSet;
import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {
	private String className;
	private String extendsName;
	private HashSet<String> implementsList;

	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
	}

	public ClassDeclarationVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.className = name;// + " extends " +
		this.extendsName = superName;
		this.implementsList = new HashSet<String>(Arrays.asList(interfaces));
	}

	public String getClassName() {
		return className;
	}

	public String getExtendsName() {
		return extendsName;
	}

	public HashSet<String> getImplementsList() {
		return implementsList;
	}

}
