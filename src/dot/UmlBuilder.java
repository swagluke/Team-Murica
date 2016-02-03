package dot;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import records.ClassRecord;
import records.IClassRecord;

public class UmlBuilder implements IBuilder {
	private ClassRecord record;
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
	
	@Override
	public HashSet<String> getClassList() {
		return this.classList;
	}
	
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
	public ClassRecord build(ClassVisitor visitor) {
		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		reader.accept(fieldVisitor, ClassReader.EXPAND_FRAMES);
		record = new ClassRecord();
		record.setClassName(declVisitor.getClassName());
		record.setMethodsList(methodVisitor.getMethods());
		record.setFieldsList(fieldVisitor.getFields());
		record.setClassList(classList);
		return record;

	}

	@Override
	public ClassRecord build() {
		return this.build(this.getVisitor());
	}

	@Override
	public String getClassUML() {
		return this.record.getClassUml();
	}

	@Override
	public ClassRecord getClassRecord() {
		return this.record;
	}

	@Override
	public IClassRecord applyDecoration(IClassRecord record) {
		return record;
	}

	@Override
	public void calculatePattern(IClassRecord record, HashMap<String, IClassRecord> records) {
	}
}
