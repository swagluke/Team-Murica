package asm;


import java.util.ArrayList;
import java.util.Arrays;

import org.objectweb.asm.ClassVisitor;

public class ClassDeclarationVisitor extends ClassVisitor {
	private String className;
	private String extendsName;
	private ArrayList<String> implementsList;
	public ClassDeclarationVisitor(int arg0) {
		super(arg0);
	}

	public ClassDeclarationVisitor(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.className =  name;// + " extends " + 
		this.extendsName = superName;
		this.implementsList = new ArrayList<String>(Arrays.asList(interfaces));
		super.visit(version, access, name, signature, superName, interfaces);
	}

	public String getClassName() {
		return className;
	}

	public String getExtendsName() {
		return extendsName;
	}

	public ArrayList<String> getImplementsList() {
		return implementsList;
	}

}
