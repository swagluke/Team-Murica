package dot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.junit.Test;

import gui.UmlWrapper;
import records.ClassRecord;
import records.IClassRecord;

/**
 * Created by lukezhang on 1/29/16.
 */
public class AdapterTest {
	@Test
	public void testNotAdapter() throws IOException, ClassNotFoundException, InvocationTargetException,
			NoSuchMethodException, InstantiationException, IllegalAccessException {
		assertAdapter(new String[] { "java.util.Enumeration", "java.util.Iterator" }, new String[0], new String[0],
				new String[0], "");
	}

	@Test
	public void testIsAdapter() throws IOException, ClassNotFoundException {
		String[] classnames = new String[] { "problem.client.IteratorToEnumerationAdapter", "java.util.Iterator",
				"java.util.Enumeration" };
		String expectedUml = "digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"] edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n"
				+ "IteratorToEnumerationAdapter [color = \"red\" label = \"{IteratorToEnumerationAdapter\\n\\<\\<Adapter\\>\\>|+itr : java.util.Iterator\\l\n"
				+ "|+ nextElement : java.lang.Object\\l\n" + "+ hasMoreElements : boolean\\l\n" + "}\"]\n"
				+ "IteratorToEnumerationAdapter -> Iterator[label=\"<<adapts>>\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n"
				+ "IteratorToEnumerationAdapter -> Enumeration\n"
				+ "Iterator [color = \"red\" label = \"{Iterator\\n\\<\\<Adaptee\\>\\>||+ forEachRemainingjava.util.function.Consumer  : void\\l\n"
				+ "+ hasNext : boolean\\l\n" + "+ next : java.lang.Object\\l\n" + "+ remove : void\\l\n" + "}\"]"
				+ "Enumeration [color = \"red\" label = \"{Enumeration\\n\\<\\<Adapter Target\\>\\>||+ hasMoreElements : boolean\\l\n"
				+ "+ nextElement : java.lang.Object\\l\n" + "}\"]" + "}";
		assertAdapter(classnames, new String[] { "problem.client.IteratorToEnumerationAdapter" },
				new String[] { "java.util.Enumeration" }, new String[] { "java.util.Iterator" }, expectedUml);
	}

	@Test
	public void testInputStreamReaderIsNotAnAdapter() {
		assertAdapter(
				new String[] { "java.io.InputStreamReader", "java.io.Closeable", "java.lang.AutoCloseable",
						"java.lang.Readable", "java.io.FileReader", "java.io.Reader", "java.io.InputStream" },
				new String[0], new String[0], new String[0], "");
	}

	@Test
	public void testOutputStreamWriterIsNotAnAdapter() {
		assertAdapter(
				new String[] { "java.io.OutputStreamWriter", "java.io.Closeable", "java.lang.AutoCloseable",
						"java.io.Flushable", "java.lang.Appendable", "java.io.FileWriter", "java.io.OutputStream" },
				new String[0], new String[0], new String[0], "");
	}

	@Test
	public void testMouseAdapterIsNotAnAdapter() {
		assertAdapter(
				new String[] { "java.awt.event.MouseAdapter", "java.awt.event.MouseListener", "java.awt.event.MouseWheelListener",
						"java.util.EventListener"
					 },
				new String[0], new String[0], new String[0], "");
	}

	public void assertAdapter(String[] classNames, String[] adapterClasses, String[] targetClasses,
			String[] adapteeClasses, String expectedUml) {
		try {
			UmlWrapper umlWrapper = new UmlWrapper(classNames);
			umlWrapper.addBuilderClass(ExtensionBuilder.class);
			umlWrapper.addBuilderClass(ImplementsBuilder.class);
			umlWrapper.addBuilderClass(AdapterBuilder.class);
			String actualUml = umlWrapper.build();
			HashMap<String, IClassRecord> recordMap = umlWrapper.getRecords();

			for (String className : adapterClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertTrue(record.getPatternNames().contains("Adapter"));
				assertFalse(record.getPatternNames().contains("Adapter Target"));
				assertFalse(record.getPatternNames().contains("Adaptee"));
			}

			for (String className : targetClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Adapter"));
				assertTrue(record.getPatternNames().contains("Adapter Target"));
				assertFalse(record.getPatternNames().contains("Adaptee"));
			}

			for (String className : adapteeClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Adapter"));
				assertFalse(record.getPatternNames().contains("Adapter Target"));
				assertTrue(record.getPatternNames().contains("Adaptee"));
			}

			if (expectedUml.equals("")) {
				UmlWrapper baseUmlWrapper = new UmlWrapper(classNames);
				baseUmlWrapper.addBuilderClass(ExtensionBuilder.class);
				baseUmlWrapper.addBuilderClass(ImplementsBuilder.class);
				assertEquals(baseUmlWrapper.build(), actualUml);
			} else {
				assertEquals(expectedUml, actualUml);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
	}
}
