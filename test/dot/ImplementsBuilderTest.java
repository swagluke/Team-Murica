package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import records.ImplementsClassRecord;

public class ImplementsBuilderTest {
	@Test
	public void testNoImplements() throws IOException {
		assertImplements("java.lang.Object", new HashSet<String>(Arrays.asList(new String[] { "java.lang.Object" })),
				new HashSet<String>(), "edge [ arrowhead = \"empty\" style = \"dotted\"]\n");

	}

	@Test
	public void testBasicImplements() throws IOException {
		assertImplements("dot.UmlBuilder",
				new HashSet<String>(Arrays.asList(new String[] { "dot.ImplementsBuilder", "dot.IBuilder" })),
				new HashSet<String>(Arrays.asList(new String[] { "dot/IBuilder" })),
				"edge [ arrowhead = \"empty\" style = \"dotted\"]\nUmlBuilder -> IBuilder\n");
	}

	@Test
	public void testMultipleImplements() throws IOException {
		assertImplements("java.util.ArrayList",
				new HashSet<String>(Arrays.asList(new String[] { "java.util.ArrayList", "java.util.List",
						"java.util.RandomAccess", "java.lang.Cloneable", "java.io.Serializable" })),
				new HashSet<String>(Arrays.asList("java/util/List", "java/util/RandomAccess", "java/lang/Cloneable",
						"java/io/Serializable")),
				"edge [ arrowhead = \"empty\" style = \"dotted\"]\nArrayList -> Serializable\nArrayList -> Cloneable\nArrayList -> RandomAccess\nArrayList -> List\n");
	}

	public void assertImplements(String className, HashSet<String> includedClasses, HashSet<String> expectedResult,
			String expectedUml) {
		ImplementsBuilder builder = new ImplementsBuilder(className, includedClasses);
		ImplementsClassRecord record = (ImplementsClassRecord) builder.build();
		
		assertEquals(expectedResult, record.getImplementsList());
		assertEquals(new UmlBuilder(className, includedClasses).build().getClassUml() + expectedUml, record.getClassUml());
	}
}
