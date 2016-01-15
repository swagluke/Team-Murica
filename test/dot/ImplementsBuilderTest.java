package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import dot.records.ImplementsClassRecord;

public class ImplementsBuilderTest {
	@Test
	public void testNoImplements() throws IOException {
		assertImplements(new HashSet<String>(), "java.lang.Object");
	}

	@Test
	public void testBasicImplements() throws IOException {
		assertImplements(new HashSet<String>(Arrays.asList("dot/IBuilder")), "dot.ImplementsBuilder");
	}

	@Test
	public void testMultipleImplements() throws IOException {
		assertImplements(new HashSet<String>(Arrays.asList("java/util/List", "java/util/RandomAccess",
				"java/lang/Cloneable", "java/io/Serializable")), "java.util.ArrayList");
	}

	public void assertImplements(HashSet<String> expectedResult, String className) {
		ImplementsBuilder builder = new ImplementsBuilder(className, new HashSet<String>(Arrays.asList(className)));
		ImplementsClassRecord record = (ImplementsClassRecord) builder.build();
		assertEquals(expectedResult, record.getImplementsList());
	}
}
