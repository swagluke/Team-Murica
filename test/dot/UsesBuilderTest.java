package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import dot.records.UsesClassRecord;

public class UsesBuilderTest {
	@Test
	public void testNoUses() throws IOException {
		assertUses(new HashSet<String>(Arrays.asList("void")), "asm.NoMethods");
	}

	@Test
	public void testBasicUses() throws IOException {
		assertUses(new HashSet<String>(Arrays.asList("java.lang.Process", "java.lang.String", "void")),
				"Lab1_3.BackReadHandler");
	}

	@Test
	public void testAdvancedUses() throws IOException {
		assertUses(new HashSet<String>(Arrays.asList("java.lang.String", "dot.UmlBuilder",
				"asm.ClassDeclarationVisitor", "dot.records.ExtendedClassRecord", "java.util.HashSet", "dot.records.IClassRecord", "void")),
				"dot.ExtensionBuilder");
	}

	public void assertUses(HashSet<String> expectedResult, String className) {
		UsesBuilder builder = new UsesBuilder(
				new ImplementsBuilder(className, new HashSet<String>(Arrays.asList(className))));
		UsesClassRecord record = (UsesClassRecord) builder.build();
		assertEquals(expectedResult, record.getUsesList());
	}
}
