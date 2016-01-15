package asm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import records.InstanceVarRecord;

public class ClassFieldVisitorTest {
	public String testField;
	private int privateField;
	protected HashSet<Integer> listField;

	@Test
	public void testNoFields() throws IOException {
		HashSet<InstanceVarRecord> expectedResult = new HashSet<InstanceVarRecord>();
		assertFields("asm.ClassDeclarationVisitorTest", expectedResult);
	}

	@Test
	public void testClassFieldVisitorTestFields() throws IOException {
		HashSet<InstanceVarRecord> expectedResult = new HashSet<InstanceVarRecord>(
				Arrays.asList(new InstanceVarRecord[] { new InstanceVarRecord("testField", "java.lang.String", 1),
						new InstanceVarRecord("privateField", "int", 2),
						new InstanceVarRecord("listField", "java.util.HashSet", 4) }));
		assertFields(this.getClass().getName(), expectedResult);
	}

	@Test
	public void testArrayListFields() throws IOException {
		HashSet<InstanceVarRecord> expectedResult = new HashSet<InstanceVarRecord>(
				Arrays.asList(new InstanceVarRecord[] { new InstanceVarRecord("serialVersionUID", "long", 26),
						new InstanceVarRecord("DEFAULT_CAPACITY", "int", 26),
						new InstanceVarRecord("EMPTY_ELEMENTDATA", "java.lang.Object[]", 26),
						new InstanceVarRecord("DEFAULTCAPACITY_EMPTY_ELEMENTDATA", "java.lang.Object[]", 26),
						new InstanceVarRecord("elementData", "java.lang.Object[]", 128),
						new InstanceVarRecord("size", "int", 2), new InstanceVarRecord("MAX_ARRAY_SIZE", "int", 26) }));
		assertFields("java.util.ArrayList", expectedResult);
	}

	public void assertFields(String className, HashSet<InstanceVarRecord> expectedResult) throws IOException {
		ClassReader reader = new ClassReader(className);
		ClassFieldVisitor classFieldVisitor = new ClassFieldVisitor(Opcodes.ASM5);

		assertTrue(classFieldVisitor.getFields().isEmpty());
		reader.accept(classFieldVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(expectedResult, classFieldVisitor.getFields());

	}

}
