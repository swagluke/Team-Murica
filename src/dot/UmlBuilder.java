package dot;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import dot.records.ClassRecord;
import dot.records.IClassRecord;
import dot.records.InstanceVarRecord;
import dot.records.MethodRecord;

public class UmlBuilder implements IBuilder{

	private String uml = "I AM ERROR";
	private ArrayList<String> implementsList;
	private HashSet<String> usesList;
	private HashSet<String> associationList;
	ClassReader reader = null;
	ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
	ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
	ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
	public UmlBuilder(String className) {
		
		
		try {
			reader = new ClassReader(className);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		ClassDeclarationVisitor declVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
//		ClassFieldVisitor fieldVisitor = new ClassFieldVisitor(Opcodes.ASM5, declVisitor);
//		ClassMethodVisitor methodVisitor = new ClassMethodVisitor(Opcodes.ASM5, fieldVisitor);
		
	}
	public void stuff(){
		ClassRecord record = new ClassRecord(declVisitor.getClassName(), declVisitor.getExtendsName(),
				methodVisitor.getMethods(), declVisitor.getImplementsList(), fieldVisitor.getFields());
		this.uml = createDigraph(record);
		
		this.usesList = new HashSet<String>();
		
		
		this.associationList = new HashSet<String>();
		for (InstanceVarRecord fieldRecord : record.getFields()) {
			try {
				Class<?> c = Class.forName(fieldRecord.getType());
				if (!Collection.class.isAssignableFrom(c) && !AbstractMap.class.isAssignableFrom(c)) {
					// not collection
//					System.out.println(fieldRecord.getNestedFields());
					this.associationList.add(fieldRecord.getType());
				} else {
//					System.out.println(fieldRecord.getType() + " is a collection");
//					System.out.println(fieldRecord.getNestedFields());
				}
				this.associationList.addAll(fieldRecord.getNestedFields());
			} catch (ClassNotFoundException e) {
				//ignore
			}
		}
		System.out.println(this.associationList);
		// System.out.println("break");
	}

	/**
	 * @param record
	 * @return
	 */
	private String createDigraph(ClassRecord record) {
		String[] n = record.getClassName().split("/");
		String name = n[n.length - 1];
		StringBuilder s = new StringBuilder(name + " [label = \"{" + name + "|");
		for (InstanceVarRecord i : record.getFields()) {
			s.append("+ ");
			s.append(i.getName() + " : ");
			s.append(i.getType() + " \\l\n");
		}
		s.append("|");
		for (MethodRecord m : record.getMethods()) {
			if (m.getName().replaceAll("<.*?>", "").isEmpty())
				continue;
			s.append("+ ");
			s.append(m.getName().replaceAll("<.*?>", ""));
			for (Type t : m.getArgTypes()) {
				s.append(t.getClassName() + " ");
			}
			s.append(" : ");
			s.append(m.getReturnType() + "\\l\n");
		}
		s.append("}\"]");
		// System.out.println("Class Name: " + record.getClassName()+"\n
		// ExtendsName: "+ record.getExtendsName() + "\n Methods:
		// "+record.getMethods() + "\nImplements length" +
		// record.getImplementsList());
		return s.toString();
	}

	@Override
	public ClassVisitor getVisitor() {
		return declVisitor;
	}

	@Override
	public IClassRecord build(ClassVisitor visitor) {
		reader.accept(visitor, ClassReader.EXPAND_FRAMES);
		// TODO Auto-generated method stub
		return null;
		
	}
	@Override
	public IClassRecord build() {
		return this.build(this.getVisitor());
	}
	@Override
	public String getClassUML() {
		// TODO Auto-generated method stub
		return null;
	}
}
