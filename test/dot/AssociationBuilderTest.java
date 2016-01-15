package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import dot.records.AssociationClassRecord;

public class AssociationBuilderTest {
	@Test
	public void testNoAssociation() throws IOException {
		assertAssociations(this.getClass().getName(),
				new HashSet<String>(Arrays.asList(new String[] { this.getClass().getName() })), new HashSet<String>(),
				"edge [ style = \"normal\" arrowhead = \"vee\"]\n");
	}

	@Test
	public void testBasicAssociation() throws IOException {
		assertAssociations("dot.records.InstanceVarRecord",
				new HashSet<String>(
						Arrays.asList(new String[] { "dot.records.InstanceVarRecord", "java.lang.String", "int" })),
				new HashSet<String>(Arrays.asList("java.lang.String", "int")),
				"edge [ style = \"normal\" arrowhead = \"vee\"]\nInstanceVarRecord -> String\nInstanceVarRecord -> int\n");
	}

	@Test
	public void testNestedAssociation() throws IOException {
		assertAssociations("Lab1_3.AppLauncher",
				new HashSet<String>(
						Arrays.asList(new String[] { this.getClass().getName(), "Lab1_3.DirectoryEventHandler",
								"java.nio.file.WatchService", "java.nio.file.Path", "boolean", "java.lang.Process" })),
				new HashSet<String>(Arrays.asList("Lab1_3.DirectoryEventHandler", "java.nio.file.WatchService",
						"java.nio.file.Path", "boolean", "java.lang.Process")),
				"edge [ style = \"normal\" arrowhead = \"vee\"]\nAppLauncher -> WatchService\nAppLauncher -> boolean\nAppLauncher -> Process\nAppLauncher -> DirectoryEventHandler\nAppLauncher -> Path\n");
	}

	public void assertAssociations(String className, HashSet<String> includedClasses, HashSet<String> expectedResult,
			String expectedUml) {
		AssociationBuilder builder = new AssociationBuilder(className, includedClasses);
		AssociationClassRecord record = (AssociationClassRecord) builder.build();
		assertEquals(expectedResult, record.getAssociationNames());
		assertEquals(expectedUml, record.getClassUml());
	}
}
