package dot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import asm.ClassDeclarationVisitor;
import dot.records.ClassRecord;
import dot.records.ExtendedClassRecord;
import dot.records.IClassRecord;
import dot.records.MethodRecord;
import sun.nio.cs.ext.ExtendedCharsets;

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
		assertExtends("	org/objectweb/asm/ClassVisitor", "asm.ClassDeclarationVisitor");
	}	
	
	public void assertExtends(String expectedResult, String className) {
		ExtensionBuilder builder = new ExtensionBuilder(className);
		builder.build();
		assertEquals(expectedResult, builder.getExtendsName());
	}
}
