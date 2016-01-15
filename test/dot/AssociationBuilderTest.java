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
		assertAssociations(new HashSet<String>(), this.getClass().getName());
	}

	@Test
	public void testBasicAssociation() throws IOException {
		assertAssociations(new HashSet<String>(Arrays.asList("java.lang.String", "int")),
				"dot.records.InstanceVarRecord");
	}

	@Test
	public void testNestedAssociation() throws IOException {
		assertAssociations(new HashSet<String>(Arrays.asList("Lab1_3.DirectoryEventHandler",
				"java.nio.file.WatchService", "java.nio.file.Path", "boolean", "java.lang.Process")),
				"Lab1_3.AppLauncher");
	}

	public void assertAssociations(HashSet<String> expectedResult, String className) {
		AssociationBuilder builder = new AssociationBuilder(className, new HashSet<String>(Arrays.asList(className)));
		AssociationClassRecord record = (AssociationClassRecord) builder.build();
		assertEquals(expectedResult, record.getAssociationNames());
	}
}
