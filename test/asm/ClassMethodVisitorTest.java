package asm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dot.records.MethodRecord;

public class ClassMethodVisitorTest {

	private static final String CLASS_NAME = "java.utilArrayList<E>t";

	@Test
	public void testGettingName() throws IOException, ClassNotFoundException {
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassMethodVisitor classMethodVisitor = new ClassMethodVisitor(Opcodes.ASM5);

		assertNull(classMethodVisitor.getMethods());
		reader.accept(classMethodVisitor, ClassReader.EXPAND_FRAMES);
		assertArrayEquals(EXPECTED_RESULT, classMethodVisitor.getMethods().toArray());
	}
}
