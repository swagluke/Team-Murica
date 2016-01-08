package asm;


import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import dot.records.ClassRecord;

public class DesignParser {
	
	public static void main(String[] args) throws IOException {
		for (String className : args) {
			ClassReader reader = new ClassReader(className);
			
			ClassVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
			ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
			ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
			reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
//			ClassRecord record = new ClassRecord(
//					((ClassDeclarationVisitor) declVisitor).getClassName(),
//					((ClassDeclarationVisitor) declVisitor).getExtendsName(),
//					((ClassMethodVisitor) methodVisitor).getMethods(),
//					((ClassDeclarationVisitor) declVisitor).getImplementsList()
//					);
//			createDigraph(record);
		}
	}

	private static void createDigraph(ClassRecord record) {
		System.out.println("Class Name: " + record.getClassName()+"\n ExtendsName: "+ record.getExtendsName() + "\n Methods: "+record.getMethods() + "\nImplements length" + record.getImplementsList());
		
	}
}
