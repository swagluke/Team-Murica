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
import dot.records.ImplementsClassRecord;
import dot.records.MethodRecord;

public class ImplementsBuilderTest {
	@Test
	public void testNoImplements() throws IOException {
		assertImplements(new ArrayList<String>(), "java.lang.Object");
	}

	@Test
	public void testBasicImplements() throws IOException {
		assertImplements(new ArrayList<String>(Arrays.asList("dot/IBuilder")), "dot.ImplementsBuilder");
	}

	@Test
	public void testMultipleImplements() throws IOException {
		assertImplements(new ArrayList<String>(Arrays.asList("java/util/List", "java/util/RandomAccess",
				"java/lang/Cloneable", "java/io/Serializable")), "java.util.ArrayList");
	}

	public void assertImplements(ArrayList<String> expectedResult, String className) {
		ImplementsBuilder builder = new ImplementsBuilder(className);
		ImplementsClassRecord record = (ImplementsClassRecord) builder.build();
		assertEquals(expectedResult, record.getImplementsList());
	}
}
