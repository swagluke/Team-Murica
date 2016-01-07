package asm;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class ClassMethodVisitorTest {

	private static final String CLASS_NAME = "java.util.ArrayList";
	private ClassDeclarationVisitor classNameVisitor;
	private ClassReader reader;

	public ClassMethodVisitorTest() throws IOException {
		this.reader = new ClassReader(CLASS_NAME);
		this.classNameVisitor = new ClassDeclarationVisitor(Opcodes.ASM5);
	}
	
	@Test
	public void testGettingName() {
		assertNull(this.classNameVisitor.getClassName());
		reader.accept(this.classNameVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(this.classNameVisitor.getClassName(), "java/util/ArrayList");
	}

}
