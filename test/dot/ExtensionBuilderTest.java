package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import dot.records.ExtendedClassRecord;

public class ExtensionBuilderTest {
	@Test
	public void testNoExtend() throws IOException {
		assertExtends("java.lang.Object", new HashSet<String>(Arrays.asList(new String[] { "java.lang.Object" })), null,
				"");
	}

	@Test
	public void testNotIncludedExtend() throws IOException {
		assertExtends(this.getClass().getName(), new HashSet<String>(Arrays.asList(this.getClass().getName())),
				"java/lang/Object", "");
	}

	@Test
	public void testBasicExtend() throws IOException {
		assertExtends(this.getClass().getName(),
				new HashSet<String>(Arrays.asList(this.getClass().getName(), "java.lang.Object")), "java/lang/Object",
				"edge [ style = \"normal\"]\nExtensionBuilderTest -> Object\n");
	}

	@Test
	public void testExtend() throws IOException {
		assertExtends("asm.ClassDeclarationVisitor",
				new HashSet<String>(Arrays.asList("asm.ClassDeclarationVisitor", "org.objectweb.asm.ClassVisitor")),
				"org/objectweb/asm/ClassVisitor",
				"edge [ style = \"normal\"]\nClassDeclarationVisitor -> ClassVisitor\n");
	}

	public void assertExtends(String className, HashSet<String> includedClasses, String expectedResult,
			String expectedUml) {
		ExtensionBuilder builder = new ExtensionBuilder(className, includedClasses);
		ExtendedClassRecord record = (ExtendedClassRecord) builder.build();

		assertEquals(expectedResult, record.getExtendsName());
		assertEquals(expectedUml, record.getClassUml());
	}

}
