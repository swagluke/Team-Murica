package dot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dot.records.ExtendedClassRecord;
import dot.records.MethodRecord;
import dot.records.UsesClassRecord;

public class UsesBuilderTest {
	@Test
	public void testNoUses() throws IOException {
		assertUses(new ArrayList<String>(), "asm.NoMethods");
	}
	
	@Test
	public void testBasicUses() throws IOException {
		assertUses(new ArrayList<String>(Arrays.asList("java.lang.Process", "java.lang.String")), "Lab1_3.BackReadHandler");
	}

	@Test
	public void testAdvancedUses() throws IOException {
		assertUses(new ArrayList<String>(
				Arrays.asList("java.lang.String", "dot.UmlBuilder", "ClassDeclarationVisitor", "ExtendedClassRecord")),
				"dot.ExtensionBuilder");
	}

	public void assertUses(ArrayList<String> expectedResult, String className) {
		UsesBuilder builder = new UsesBuilder(new ImplementsBuilder(className));
		UsesClassRecord record = (UsesClassRecord) builder.build();
		assertEquals(expectedResult, record.getUsesList());
	}
}
