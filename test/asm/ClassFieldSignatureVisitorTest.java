package asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import dot.records.InstanceVarRecord;

public class ClassFieldSignatureVisitorTest {
	public String testField;
	private int privateField;
	protected ArrayList<Integer> listField;
	public HashMap<HashMap<ArrayList<String>, HashSet<Boolean>>, Integer> nested;

	@Test
	public void testNoFields() throws IOException {
		HashSet<String> expectedResult = new HashSet<String>();
		assertFields("asm.ClassDeclarationVisitorTest", expectedResult);
	}

	@Test
	public void testNoNestedFields() throws IOException {
		HashSet<String> expectedResult = new HashSet<String>(
				Arrays.asList("dot.IBuilder", "asm.ClassFieldSignatureVisitor"));

		assertFields("dot.AssociationBuilder", expectedResult);
	}

	@Test
	public void testNestedFields() throws IOException {
		HashSet<String> expectedResult = new HashSet<String>(
				Arrays.asList("java.lang.Integer", "java.lang.String", "java.lang.Boolean", "int"));
		assertFields(this.getClass().getName(), expectedResult);
	}

	public void assertFields(String className, HashSet<String> expectedResult) throws IOException {
		ClassReader reader = new ClassReader(className);
		ClassFieldSignatureVisitor classFieldVisitor = new ClassFieldSignatureVisitor(Opcodes.ASM5,
				new ClassFieldVisitor(Opcodes.ASM5));

		assertTrue(classFieldVisitor.getAssociationNames().isEmpty());
		reader.accept(classFieldVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(expectedResult, classFieldVisitor.getAssociationNames());

	}

}
