package asm;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class ClassDeclarationVisitorTest {

	private static final String CLASS_NAME = "java.util.ArrayList";
	private static final String EXPECTED_RESULT = "java.util.ArrayList";

	@Test
	public void testGettingName() throws IOException {
		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassDeclarationVisitor classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);

		assertNull(classNameVisitor.getClassName());
		reader.accept(classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(EXPECTED_RESULT, classNameVisitor.getClassName());
	}
}
