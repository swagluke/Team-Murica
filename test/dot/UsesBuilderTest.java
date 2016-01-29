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
		assertUses("asm.NoMethods", new HashSet<String>(Arrays.asList("asm.NoMethods")), new HashSet<String>(), "");
	}

	@Test
	public void testBasicUses() throws IOException {
		assertUses("dot.ImplementsBuilder",
				new HashSet<String>(Arrays.asList("java.util.HashSet", "org.objectweb.asm.ClassVisitor",
						"java.lang.String", "records.ImplementsClassRecord", "dot.UmlBuilder", "dot.ImplementsBuilder",
						"dot.AbstractBuilderDecorator")),
				new HashSet<String>(Arrays.asList("java/util/HashSet", "org/objectweb/asm/ClassVisitor",
						"java/lang/String", "records/ImplementsClassRecord", "dot/UmlBuilder", "dot/ImplementsBuilder",
						"dot/AbstractBuilderDecorator")),
				"edge [ style = \"dotted\" arrowhead = \"open\"]\n" + "ImplementsBuilder -> ClassVisitor\n"
						+ "ImplementsBuilder -> ImplementsClassRecord\n" + "ImplementsBuilder -> UmlBuilder\n"
						+ "ImplementsBuilder -> String\n" + "ImplementsBuilder -> AbstractBuilderDecorator\n"
						+ "ImplementsBuilder -> HashSet\n" + "" + "ImplementsBuilder -> ImplementsBuilder\n");
	}

	@Test
	public void testAdvancedUses() throws IOException {
		assertUses("headfirst.factory.pizzafm.ChicagoPizzaStore",
				new HashSet<String>(Arrays.asList("headfirst.factory.pizzafm.ChicagoPizzaStore",
						"headfirst.factory.pizzafm.ChicagoStyleCheesePizza",
						"headfirst.factory.pizzafm.ChicagoStyleVeggiePizza",
						"headfirst.factory.pizzafm.ChicagoStyleClamPizza")),
				new HashSet<String>(Arrays.asList("headfirst/factory/pizzafm/ChicagoStyleCheesePizza",
						"headfirst/factory/pizzafm/ChicagoStyleVeggiePizza",
						"headfirst/factory/pizzafm/ChicagoStyleClamPizza",
						"headfirst/factory/pizzafm/ChicagoStylePepperoniPizza", "java/lang/String")),
				"edge [ style = \"dotted\" arrowhead = \"open\"]\n" + "ChicagoPizzaStore -> ChicagoStyleClamPizza\n"
						+ "ChicagoPizzaStore -> ChicagoStyleCheesePizza\n"
						+ "ChicagoPizzaStore -> ChicagoStyleVeggiePizza\n");
	}

	public void assertUses(String className, HashSet<String> includedClasses, HashSet<String> expectedResult,
			String expectedUml) {
		UsesBuilder builder = new UsesBuilder(className, includedClasses);
		UsesClassRecord record = (UsesClassRecord) builder.build();
		System.out.println(expectedResult);
		System.out.println(record.getUsesNamesList());
		System.out.println();
		assertEquals(expectedResult, record.getUsesNamesList());
		assertEquals(new UmlBuilder(className, includedClasses).build().getClassUml() + expectedUml,
				record.getClassUml());
	}
}
