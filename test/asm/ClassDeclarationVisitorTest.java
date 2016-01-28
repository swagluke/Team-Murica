package asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class ClassDeclarationVisitorTest {
	@Test
	public void testClassDeclarationVisitorTestGettingName() throws IOException {
		assertClassName(this.getClass().getName(), "asm/ClassDeclarationVisitorTest");
	}

	@Test
	public void testClassDeclarationVisitorTestExtendsName() throws IOException {
		assertExtendsName(this.getClass().getName(), "java/lang/Object");
	}

	@Test
	public void testClassDeclarationVisitorTestImplementsName() throws IOException {
		HashSet<String> expectedResult = new HashSet<String>(Arrays.asList(new String[] {}));
		assertImplementsName(this.getClass().getName(), expectedResult);
	}

	@Test
	public void testArrayListGettingName() throws IOException {
		assertClassName("java.util.ArrayList", "java/util/ArrayList");
	}

	@Test
	public void testArrayListExtendsName() throws IOException {
		assertExtendsName("java.util.ArrayList", "java/util/AbstractList");
	}

	@Test
	public void testArrayListImplementsName() throws IOException {
		HashSet<String> expectedResult = new HashSet<String>(Arrays.asList(new String[] { "java/util/List",
				"java/util/RandomAccess", "java/lang/Cloneable", "java/io/Serializable" }));
		assertImplementsName("java.util.ArrayList", expectedResult);
	}

	public void assertClassName(String className, String expectedResult) throws IOException {
		ClassReader reader = new ClassReader(className);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		assertNull(classNameVisitor.getClassName());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(expectedResult, classNameVisitor.getClassName());
	}

	public void assertExtendsName(String className, String expectedResult) throws IOException {
		ClassReader reader = new ClassReader(className);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		assertNull(classNameVisitor.getExtendsName());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(expectedResult, classNameVisitor.getExtendsName());
	}

	public void assertImplementsName(String className, HashSet<String> expectedResult) throws IOException {
		ClassReader reader = new ClassReader(className);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		assertNull(classNameVisitor.getImplementsList());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(expectedResult, classNameVisitor.getImplementsList());
	}
}
