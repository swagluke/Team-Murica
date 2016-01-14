package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import dot.records.ExtendedClassRecord;
import dot.records.IClassRecord;

public class ExtensionBuilderTest {
	@Test
	public void testNoExtend() throws IOException {
		assertExtends(null, "java.lang.Object");
	}
	
	@Test
	public void testBasicExtend() throws IOException {
		assertExtends("java/lang/Object", this.getClass().getName());
	}	
	
	@Test
	public void testExtend() throws IOException {
		assertExtends("org/objectweb/asm/ClassVisitor", "asm.ClassDeclarationVisitor");
	}	
	
	public void assertExtends(String expectedResult, String className) {
		ExtensionBuilder builder = new ExtensionBuilder(className);
		ExtendedClassRecord record = (ExtendedClassRecord) builder.build();
		assertEquals(expectedResult, record.getExtendsName());
	}
}
