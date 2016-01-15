package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import records.ExtendedClassRecord;

public class ExtensionBuilderTest {
	@Test
	public void testNoExtend() throws IOException {
		assertExtends("java.lang.Object", new HashSet<String>(Arrays.asList(new String[] { "java.lang.Object" })), new HashSet<String>(), "java.lang.Object");
	}

	@Test
	public void testBasicExtend() throws IOException {
		//assertExtends("java/lang/Object", this.getClass().getName());
	}

	@Test
	public void testExtend() throws IOException {
		//assertExtends("org/objectweb/asm/ClassVisitor", "asm.ClassDeclarationVisitor");
	}
	//public void assertExtends(String expectedResult, String className) {
	public void assertExtends(String className, HashSet<String> includedClasses, HashSet<String> expectedResult,
							  String expectedUml){
		ExtensionBuilder builder = new ExtensionBuilder(className, new HashSet<String>(Arrays.asList(className)));
		ExtendedClassRecord record = (ExtendedClassRecord) builder.build();
//		System.out.println(record.getClassUml());
//		assertEquals(expectedResult, record.getExtendsName());
//		assertEquals(expectedUml, record.getClassUml());
	}

}
