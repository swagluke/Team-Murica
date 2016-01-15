package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import records.UsesClassRecord;

public class UsesBuilderTest {
	@Test
	public void testNoUses() throws IOException {
		assertUses(new HashSet<String>(), "asm.NoMethods");
	}

	@Test
	public void testBasicUses() throws IOException {
		assertUses(
				new HashSet<String>(
						Arrays.asList("java/lang/String", "java/util/HashSet", "org/objectweb/asm/ClassVisitor",
								"dot/records/ImplementsClassRecord", "dot/ExtensionBuilder", "dot/ImplementsBuilder")),
				"dot.implementsBuilder");
	}

	@Test
	public void testAdvancedUses() throws IOException {
		assertUses(
				new HashSet<String>(Arrays.asList("headfirst/factory/pizzafm/ChicagoStyleCheesePizza",
						"headfirst/factory/pizzafm/ChicagoStyleVeggiePizza",
						"headfirst/factory/pizzafm/ChicagoStyleClamPizza",
						"headfirst/factory/pizzafm/ChicagoStylePepperoniPizza", "java/lang/String")),
				"headfirst.factory.pizzafm.ChicagoPizzaStore");
	}

	public void assertUses(HashSet<String> expectedResult, String className) {
		UsesBuilder builder = new UsesBuilder(
				new ImplementsBuilder(className, new HashSet<String>(Arrays.asList(className))));
		UsesClassRecord record = (UsesClassRecord) builder.build();
		assertEquals(expectedResult, record.getUsesNamesList());
	}
}
