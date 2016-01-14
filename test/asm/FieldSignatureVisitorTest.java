package asm;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureReader;

public class FieldSignatureVisitorTest {
	@Test
	public void testSimpleFieldSignatureVisitor() throws IOException {
		assertFieldSignature("Ljava/util/ArrayList<Ljava/lang/Integer;>;",
				new HashSet<String>(Arrays.asList("java.lang.Integer")));
	}

	@Test
	public void testComplicatedFieldSignatureVisitor() throws IOException {
		assertFieldSignature("Ljava/util/HashMap<Ljava/util/ArrayList<Ljava/lang/String;>;Ljava/lang/Integer;>;",
				new HashSet<String>(Arrays.asList("java.lang.String", "java.lang.Integer")));
	}

	public void assertFieldSignature(String signature, HashSet<String> expectedResult) throws IOException {
		SignatureReader reader = new SignatureReader(signature);
		FieldSignatureVisitor visitor = new FieldSignatureVisitor(Opcodes.ASM5);

		assertTrue(visitor.getNestedFields().isEmpty());
		reader.accept(visitor);
		assertEquals(expectedResult, visitor.getNestedFields());
	}
}
