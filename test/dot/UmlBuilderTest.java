package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import records.ClassRecord;
import records.InstanceVarRecord;
import records.MethodRecord;

public class UmlBuilderTest {

	@Test
	public void testGettingClassUML() throws IOException, ClassNotFoundException {
		String className = "headfirst.factory.pizzas.Pizza";
		String expectedClassName = "headfirst/factory/pizzas/Pizza";
		HashSet<MethodRecord> expectedMethods = new HashSet<MethodRecord>(Arrays.asList(
				new MethodRecord[] { new MethodRecord(1, "<init>", "void", new Type[0], new ArrayList<String>()),
						new MethodRecord(1, "getName", "java.lang.String", new Type[0], new ArrayList<String>()),
						new MethodRecord(1, "prepare", "void", new Type[0], new ArrayList<String>()),
						new MethodRecord(1, "bake", "void", new Type[0], new ArrayList<String>()),
						new MethodRecord(1, "cut", "void", new Type[0], new ArrayList<String>()),
						new MethodRecord(1, "box", "void", new Type[0], new ArrayList<String>()),
						new MethodRecord(1, "toString", "java.lang.String", new Type[0], new ArrayList<String>()), }));
		HashSet<InstanceVarRecord> expectedFieldsList = new HashSet<InstanceVarRecord>(
				Arrays.asList(new InstanceVarRecord[] { new InstanceVarRecord("name", "java.lang.String", 0),
						new InstanceVarRecord("dough", "java.lang.String", 0),
						new InstanceVarRecord("sauce", "java.lang.String", 0),
						new InstanceVarRecord("toppings", "java.util.ArrayList", 0) }));

		assertBuild(className, expectedClassName, expectedMethods, expectedFieldsList);
	}

	public void assertBuild(String className, String expectedClassName, HashSet<MethodRecord> expectedMethods,
			HashSet<InstanceVarRecord> expectedFieldsList) {
		UmlBuilder builder = new UmlBuilder(className, new HashSet<String>(Arrays.asList(className)));
		ClassRecord record = (ClassRecord) builder.build();
		assertEquals(expectedClassName, record.getClassName());
		assertEquals(expectedMethods, record.getMethodsList());
		assertEquals(expectedFieldsList, record.getFieldsList());
	}
}
