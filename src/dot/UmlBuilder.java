package dot;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import dot.records.ClassRecord;
import dot.records.InstanceVarRecord;
import dot.records.MethodRecord;

public class UmlBuilder {
	
	
	private String uml="I AM ERROR";
	private ArrayList<String> implementsList;
	private String extendsName;
	
	public UmlBuilder(String className) {
		ClassReader reader = null;
		try {
			reader = new ClassReader(className);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
		ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
		ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
		reader.accept(methodVisitor, ClassReader.EXPAND_FRAMES);
		ClassRecord record = new ClassRecord(
				declVisitor.getClassName(),
				declVisitor.getExtendsName(),
				methodVisitor.getMethods(),
				declVisitor.getImplementsList(),
				fieldVisitor.getFields()
				);
		this.uml = createDigraph(record);
		this.setImplementsList(record.getImplementsList());
		this.setExtendsName(record.getExtendsName());
	}
	/**
	 * @param record
	 * @return
	 */
	private String createDigraph(ClassRecord record) {
		String[] n = record.getClassName().split("/");
		String name = n[n.length-1];
		StringBuilder s = new StringBuilder(name+" [label = \"{"+name+"|");
		for(InstanceVarRecord i:record.getFields()){
			s.append("+ ");
			s.append(i.getName()+" : ");
			s.append(i.getType()+" \\l\n");
		}
		s.append("|");
		for(MethodRecord m:record.getMethods()){
			if(m.getName().replaceAll("<.*?>", "").isEmpty())
				continue;
			s.append("+ ");
			s.append(m.getName().replaceAll("<.*?>", ""));
			for(Type t:m.getArgTypes()){
				s.append(t.getClassName()+ " ");
			}
			s.append(" : ");
			s.append(m.getReturnType() + "\\l\n");
		}
		s.append("}\"]");
//		System.out.println("Class Name: " + record.getClassName()+"\n ExtendsName: "+ record.getExtendsName() + "\n Methods: "+record.getMethods() + "\nImplements length" + record.getImplementsList());
		return s.toString();
	}
	public String getClassUML(){
		return uml;
	}
	public ArrayList<String> getImplementsList() {
		return implementsList;
	}
	private void setImplementsList(ArrayList<String> implementsList) {
		this.implementsList = implementsList;
	}
	public String getExtendsName() {
		return extendsName;
	}
	private void setExtendsName(String extendsName) {
		this.extendsName = extendsName;
	}
}
