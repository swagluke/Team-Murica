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

public class CompositeTest {
	@Test
	public void testNotComposite() throws IOException, ClassNotFoundException {
		assertComposite(new String[] { "headfirst.composite.menu.Menu", "headfirst.composite.menu.MenuComponent"}, new String[0], new String[0],
				new String[0], new String[] { "headfirst.composite.menu.Menu", "headfirst.composite.menu.MenuComponent" }, "");
	}

	@Test
	public void testComposite() throws IOException, ClassNotFoundException {
		assertComposite(new String[] { "headfirst.composite.menu.Menu", "headfirst.composite.menu.MenuComponent", "headfirst.composite.menu.MenuItem"}, new String[]{"headfirst.composite.menu.MenuComponent"}, new String[]{"headfirst.composite.menu.Menu"},
				new String[]{"headfirst.composite.menu.MenuItem"}, new String[0], "");
	}
	
	@Test
	public void testSwingIsComposite() throws IOException, ClassNotFoundException {
		assertComposite(new String[] { "java.awt.Container", "java.awt.Component", "java.awt.Button"}, new String[]{"java.awt.Component"}, new String[]{"java.awt.Container"},
				new String[]{"java.awt.Button"}, new String[0], "");
	}	

	public void assertComposite(String[] classNames, String[] componentClasses, String[] compositeClasses,
			String[] leafClasses, String[] noPatternClasses, String expectedUml) {
		try {
			assertEquals(classNames.length,
					compositeClasses.length + componentClasses.length + noPatternClasses.length + leafClasses.length);
			UmlWrapper umlWrapper = new UmlWrapper(classNames);
			umlWrapper.addBuilderClass(ExtensionBuilder.class);
			umlWrapper.addBuilderClass(ImplementsBuilder.class);
			umlWrapper.addBuilderClass(CompositeBuilder.class);
			String actualUml = umlWrapper.build();
			HashMap<String, IClassRecord> recordMap = umlWrapper.getRecords();

			for (String className : compositeClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				System.out.println(record.getPatternNames());
				assertTrue(record.getPatternNames().contains("Composite"));
				assertFalse(record.getPatternNames().contains("Component"));
				assertFalse(record.getPatternNames().contains("Leaf"));
			}

			for (String className : componentClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Composite"));
				assertTrue(record.getPatternNames().contains("Component"));
				assertFalse(record.getPatternNames().contains("Leaf"));
			}

			for (String className : leafClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Composite"));
				assertFalse(record.getPatternNames().contains("Component"));
				assertTrue(record.getPatternNames().contains("Leaf"));
			}

			for (String className : noPatternClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertEquals(0, record.getPatternNames().size());
			}
			if (expectedUml.equals("")) {
				UmlWrapper baseUmlWrapper = new UmlWrapper(classNames);
				baseUmlWrapper.addBuilderClass(ExtensionBuilder.class);
				baseUmlWrapper.addBuilderClass(ImplementsBuilder.class);
				assertEquals(baseUmlWrapper.build(), actualUml);
			} else {
				System.out.println(expectedUml);
				assertEquals(expectedUml, actualUml);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
	}
}
