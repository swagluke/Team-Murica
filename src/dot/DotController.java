package dot;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import dot.records.ClassRecord;
import dot.records.InstanceVarRecord;

public class DotController {
	
	
	private String uml="";
	private ArrayList<String> implementsList;
	private String extendsName;
	
	public DotController(String className) {
		ClassReader reader = null;
		try {
			reader = new ClassReader(className);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ClassVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		ClassVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
		ClassVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		ClassRecord record = new ClassRecord(
				((ClassDeclarationVisitor) declVisitor).getClassName(),
				((ClassDeclarationVisitor) declVisitor).getExtendsName(),
				((ClassMethodVisitor) methodVisitor).getMethods(),
				((ClassDeclarationVisitor) declVisitor).getImplementsList(),
				((ClassFieldVisitor) fieldVisitor).getFields()
				);
		this.uml = createDigraph(record);
		this.implementsList =record.getImplementsList();
		this.extendsName = record.getExtendsName();
	}
	private String createDigraph(ClassRecord record) {
		System.out.println("Class Name: " + record.getClassName()+"\n ExtendsName: "+ record.getExtendsName() + "\n Methods: "+record.getMethods() + "\nImplements length" + record.getImplementsList());
		return "Stuff comes from here";
	}
	public String getUML(){
		return uml;
	}
}
