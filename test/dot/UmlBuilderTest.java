package dot;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dot.records.MethodRecord;

public class UmlBuilderTest {

	private static final String CLASS_NAME = UmlBuilderTest.class.getName();
	private static final String EXPECTED_RESULT = "UmlBuilderTest [label = \"{UmlBuilderTest|+ CLASS_NAME : java.lang.String \\l\n"
			+ "+ EXPECTED_RESULT : java.lang.String \\l\n"
			+ "|+ testGettingClassUML : void\\l\n"
			+ "}\"]";

	@Test
	public void testGettingClassUML() throws IOException, ClassNotFoundException {
		UmlBuilder builder = new UmlBuilder(CLASS_NAME);
		assertEquals(EXPECTED_RESULT, builder.getClassUML());
	}
}
