package asm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class ClassFieldVisitorTest {

	private static final String CLASS_NAME = "java.util.ArrayList";
	private static final String[] EXPECTED_RESULT = { "MIN_VALUE", "MAX_VALUE",
			"TYPE", "digits", "DigitTens", "DigitOnes", "sizeTable", "value",
			"SIZE", "BYTES", "serialVersionUID", "serialVersionUID",
			"DEFAULT_CAPACITY", "EMPTY_ELEMENTDATA",
			"DEFAULTCAPACITY_EMPTY_ELEMENTDATA", "elementData", "size",
			"MAX_ARRAY_SIZE" };

	@Test
	public void testGettingName() throws IOException {
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassFieldVisitor classFieldVisitor = new ClassFieldVisitor(
				Opcodes.ASM5);

		assertNull(classFieldVisitor.getFieldsNames());
		reader.accept(classFieldVisitor, ClassReader.EXPAND_FRAMES);
		assertArrayEquals(EXPECTED_RESULT, classFieldVisitor.getFieldsNames());
	}

}
