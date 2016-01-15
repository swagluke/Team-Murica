package dot;

import java.io.IOException;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import dot.records.ClassRecord;
import dot.records.IClassRecord;

public class UmlBuilder implements IBuilder {
	private ClassRecord record;
	private HashSet<String> associationList;
	HashSet<String> classList;
	ClassReader reader = null;
	ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
	ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
	ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);

	public UmlBuilder(String className, HashSet<String> classNameList) {

		this.classList = classNameList;
		try {
			reader = new ClassReader(className);
		} catch (IOException e) {
			System.err.println(className);
			e.printStackTrace();
		}

	}
	// public void stuff(){
	//
	// this.associationList = new HashSet<String>();
	// for (InstanceVarRecord fieldRecord : record.getFields()) {
	// try {
	// Class<?> c = Class.forName(fieldRecord.getType());
	// if (!Collection.class.isAssignableFrom(c) && !AbstractMap.class.isAssignableFrom(c)) {
	// // not collection
	//// System.out.println(fieldRecord.getNestedFields());
	// this.associationList.add(fieldRecord.getType());
	// } else {
	//// System.out.println(fieldRecord.getType() + " is a collection");
	//// System.out.println(fieldRecord.getNestedFields());
	// }
	//// this.associationList.addAll(fieldRecord.getNestedFields());
	// } catch (ClassNotFoundException e) {
	// //ignore
	// }
	// }
	// System.out.println(this.associationList);
	// // System.out.println("break");
	// }
	
	@Override
	public HashSet<String> getClassList() {
		return this.classList;
	}

	@Override
	public void setClassList(HashSet<String> classList) {
		this.classList = classList;
	}

	@Override
	public ClassVisitor getVisitor() {
		return declVisitor;
	}

	public ClassMethodVisitor getMethodVisitor() {
		return this.methodVisitor;
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		reader.accept(fieldVisitor, ClassReader.EXPAND_FRAMES);
		record = new ClassRecord();
		record.setClassName(declVisitor.getClassName());
		record.setExtendsName(declVisitor.getExtendsName());
		record.setImplementsList(declVisitor.getImplementsList());
		record.setMethodsList(methodVisitor.getMethods());
		record.setFieldsList(fieldVisitor.getFields());
		record.setClassList(classList);
		return record;

	}

	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
		// return this.createDigraph(this.build());
		// TODO Auto-generated method stub
		// return null;
	}
}
