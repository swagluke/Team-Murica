package gui;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import asm.ClassDeclarationVisitor;
import asm.ClassFieldVisitor;
import asm.ClassMethodVisitor;
import dot.DotController;
import dot.records.ClassRecord;

public class Runner {

	public static void main(String[] args){
		for(String className: args){
			DotController d = new DotController();
		}
	}
}
