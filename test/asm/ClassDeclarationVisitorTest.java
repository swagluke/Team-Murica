package asm;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class ClassDeclarationVisitorTest {

	private static final String CLASS_NAME = "java.util.ArrayList";

	@Test
	public void testGettingName() throws IOException {
		String EXPECTED_RESULT = "java/util/ArrayList";
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		assertNull(classNameVisitor.getClassName());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(EXPECTED_RESULT, classNameVisitor.getClassName());
	}
	
	@Test
	public void testExtendsName() throws IOException {
		String EXPECTED_RESULT = "java/util/AbstractList";
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		assertNull(classNameVisitor.getExtendsName());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(EXPECTED_RESULT, classNameVisitor.getExtendsName());
	}

	@Test
	public void testImplementsName() throws IOException {
		ArrayList<String> EXPECTED_RESULT = new ArrayList<String>(Arrays.asList(new String[] {"java/util/List", "java/util/RandomAccess", "java/lang/Cloneable", "java/io/Serializable"}));
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		System.out.println(classNameVisitor.getImplementsList());
		assertNull(classNameVisitor.getImplementsList());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(EXPECTED_RESULT, classNameVisitor.getImplementsList());
	}
}
