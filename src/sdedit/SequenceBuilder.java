package sdedit;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodInsVisitor;
import asm.ClassMethodVisitor;
import asm.MethodInsVisitor;
import records.ClassRecord;
import records.IClassRecord;
import records.MethodSignature;

public class SequenceBuilder implements IBuilder{
	MethodSignature signature;
	private ClassReader reader;
	ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
	ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
	ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
	private ClassRecord record;
	private ClassMethodInsVisitor methodInstVisitor;
	private MethodSignature methodSignature;
	
	
	public SequenceBuilder(MethodSignature m){
		try {
			reader = new ClassReader(m.className);
		} catch (IOException e) {
			System.err.println(m.className);
			e.printStackTrace();
		}
		this.methodSignature = m;
		this.methodInstVisitor = new ClassMethodInsVisitor(Opcodes.ASM5, methodVisitor);

	}
	
	public IClassRecord build() {
//		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		reader.accept(methodInstVisitor, ClassReader.EXPAND_FRAMES);
//		record = new SequenceRecord();
		record.setClassName(declVisitor.getClassName());
		record.setExtendsName(declVisitor.getExtendsName());
		record.setImplementsList(declVisitor.getImplementsList());
		record.setMethodsList(methodVisitor.getMethods());
		for(MethodInsVisitor m:methodInstVisitor.methodVisitors){
			if(m.methodName.equals(methodSignature.methodName)){
				
			}
		}
		return record;

	}
}
