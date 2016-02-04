package dot;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

/**
 * Created by lukezhang on 1/29/16.
 */
public class AdapterTest {
	@Test
	public void testNotAdapter() throws IOException, ClassNotFoundException {
		String className = "";
		String expectedUml = "";
		/*
		 * String className = "dot.AssociationBuilder"; String expectedUml =
		 * "AssociationBuilder [label = \"{AssociationBuilder|+visitor : asm.ClassFieldSignatureVisitor\\l\n"
		 * + "|+ applyPatternrecords.ClassRecord  : void\\l\n" +
		 * "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" + "}\"]";
		 */
		assertAdapter(className, false, expectedUml);
	}

	@Test
	public void testIsAdapter() throws IOException, ClassNotFoundException {
		String className = "";
		String expectedUml = "";
		/*
		 * String className = "singleton.Singleton"; String expectedUml =
		 * "Singleton [color = \"blue1\" label = \"{Singleton\\n\\<\\<Singleton\\>\\>|+instance : singleton.Singleton\\l\n"
		 * + "|+ getInstance : singleton.Singleton\\l\n" +
		 * "}\"]Singleton -> Singleton\n";
		 */
		assertAdapter(className, true, expectedUml);
	}

	private void assertAdapter(String className, boolean expectedIsAdapter, String expectedUml) {
		fail();
		// AdapterBuilder builder = new AdapterBuilder(className,new
		// HashSet<String>(Arrays.asList(className)));
		// ClassRecord record = (ClassRecord) builder.build();
		// assertTrue(expectedIsAdapter ==
		// record.getBaseRecord().getPatternNames().contains("Adapter"));
		// if (expectedIsAdapter) {
		// assertEquals(expectedUml, record.getClassUml());
		// } else {
		// assertEquals(new UmlBuilder(className, new
		// HashSet<String>(Arrays.asList(className))).build().getClassUml(),
		// record.getClassUml());
		// }
	}
}
