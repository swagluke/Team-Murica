package asm;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

import dot.records.InstanceVarRecord;

public class ClassFieldVisitorTest {

	private static final String CLASS_NAME = "java.util.ArrayList";
	private static final ArrayList<InstanceVarRecord> EXPECTED_RESULT = new ArrayList<InstanceVarRecord>(
			Arrays.asList(new InstanceVarRecord[] { new InstanceVarRecord("serialVersionUID", "long", 26),
					new InstanceVarRecord("DEFAULT_CAPACITY", "int", 26),
					new InstanceVarRecord("EMPTY_ELEMENTDATA", "java.lang.Object[]", 26),
					new InstanceVarRecord("DEFAULTCAPACITY_EMPTY_ELEMENTDATA", "java.lang.Object[]", 26),
					new InstanceVarRecord("elementData", "java.lang.Object[]", 128),
					new InstanceVarRecord("size", "int", 2), new InstanceVarRecord("MAX_ARRAY_SIZE", "int", 26) }));

	@Test
	public void testGettingName() throws IOException {
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassFieldVisitor classFieldVisitor = new ClassFieldVisitor(Opcodes.ASM5);

		assertTrue(classFieldVisitor.getFields().isEmpty());
		reader.accept(classFieldVisitor, ClassReader.EXPAND_FRAMES);
		assertArrayEquals(EXPECTED_RESULT.toArray(), classFieldVisitor.getFields().toArray());
	}

}
