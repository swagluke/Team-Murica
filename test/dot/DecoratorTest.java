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

public class DecoratorTest {
	@Test
	public void testNotDecorator() throws IOException, ClassNotFoundException {
		assertDecorator(new String[] { "java.util.Enumeration", "java.util.Iterator" }, new String[0], new String[0],
				new String[] { "java.util.Enumeration", "java.util.Iterator" }, "");
	}

	@Test
	public void testNotDecorator2() throws IOException, ClassNotFoundException {
		String[] classNames = new String[] { "dot.DecoratorBuilder", "dot.APatternBuilder",
				"dot.AbstractBuilderDecorator", "dot.UmlBuilder" };
		String expectedUml = "";
		assertDecorator(classNames, new String[0], new String[0], classNames, expectedUml);
	}

	@Test
	public void testIsDecorator() throws IOException, ClassNotFoundException {
		String[] classNames = new String[] { "dot.DecoratorBuilder", "dot.APatternBuilder",
				"dot.AbstractBuilderDecorator", "dot.IBuilder", "dot.UmlBuilder" };
		String expectedUml = "digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"] edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n"
				+ "DecoratorBuilder [color = \"green\" label = \"{DecoratorBuilder\\n\\<\\<Decorator\\>\\>||+ isPatternrecords.IClassRecord java.util.HashMap  : boolean\\l\n"
				+ "+ lambda$0records.IClassRecord java.lang.String  : boolean\\l\n"
				+ "+ applyPatternrecords.IClassRecord java.util.HashMap  : void\\l\n"
				+ "}\"]edge [ style = \"normal\", arrowhead = \"normal\"]\n" + "DecoratorBuilder -> APatternBuilder\n"
				+ "IBuilder [color = \"green\" label = \"{IBuilder\\n\\<\\<Component\\>\\>||+ getClassRecord : records.ClassRecord\\l\n"
				+ "+ build : records.IClassRecord\\l\n" + "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n"
				+ "+ getClassUML : java.lang.String\\l\n"
				+ "+ buildorg.objectweb.asm.ClassVisitor  : records.IClassRecord\\l\n"
				+ "+ getClassList : java.util.HashSet\\l\n"
				+ "+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n"
				+ "+ calculatePatternrecords.IClassRecord java.util.HashMap  : void\\l\n"
				+ "}\"]UmlBuilder [label = \"{UmlBuilder|+classList : java.util.HashSet\\l\n"
				+ "+record : records.ClassRecord\\l\n" + "+methodVisitor : asm.ClassMethodVisitor\\l\n"
				+ "+declVisitor : asm.ClassDeclarationVisitor\\l\n" + "+fieldVisitor : asm.ClassFieldVisitor\\l\n"
				+ "+reader : org.objectweb.asm.ClassReader\\l\n" + "|+ getClassRecord : records.ClassRecord\\l\n"
				+ "+ build : records.IClassRecord\\l\n"
				+ "+ buildorg.objectweb.asm.ClassVisitor  : records.ClassRecord\\l\n"
				+ "+ setClassListjava.util.HashSet  : void\\l\n" + "+ getClassUML : java.lang.String\\l\n"
				+ "+ getClassList : java.util.HashSet\\l\n"
				+ "+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n"
				+ "+ build : records.ClassRecord\\l\n" + "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n"
				+ "+ getMethodVisitor : asm.ClassMethodVisitor\\l\n"
				+ "+ buildorg.objectweb.asm.ClassVisitor  : records.IClassRecord\\l\n"
				+ "+ calculatePatternrecords.IClassRecord java.util.HashMap  : void\\l\n"
				+ "}\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" + "UmlBuilder -> IBuilder\n"
				+ "AbstractBuilderDecorator [color = \"green\" label = \"{AbstractBuilderDecorator\\n\\<\\<Decorator\\>\\>|+builder : dot.IBuilder\\l\n"
				+ "+record : records.IClassRecord\\l\n" + "|+ getClassRecord : records.ClassRecord\\l\n"
				+ "+ build : records.IClassRecord\\l\n"
				+ "+ isPatternrecords.IClassRecord java.util.HashMap  : boolean\\l\n"
				+ "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" + "+ getClassUML : java.lang.String\\l\n"
				+ "+ applyPatternrecords.IClassRecord java.util.HashMap  : void\\l\n"
				+ "+ buildorg.objectweb.asm.ClassVisitor  : records.IClassRecord\\l\n"
				+ "+ getClassList : java.util.HashSet\\l\n"
				+ "+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n"
				+ "+ calculatePatternrecords.IClassRecord java.util.HashMap  : void\\l\n" + "}\"]\n"
				+ "AbstractBuilderDecorator -> IBuilder[label=\"<<decorates>>\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n"
				+ "AbstractBuilderDecorator -> IBuilder\n"
				+ "APatternBuilder [color = \"green\" label = \"{APatternBuilder\\n\\<\\<Decorator\\>\\>||+ isPatternrecords.IClassRecord java.util.HashMap  : boolean\\l\n"
				+ "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n"
				+ "+ applyPatternrecords.IClassRecord java.util.HashMap  : void\\l\n"
				+ "+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n"
				+ "}\"]edge [ style = \"normal\", arrowhead = \"normal\"]\n" + "APatternBuilder -> AbstractBuilderDecorator\n" + "}";
		assertDecorator(classNames,
				new String[] { "dot.DecoratorBuilder", "dot.APatternBuilder", "dot.AbstractBuilderDecorator" },
				new String[] { "dot.IBuilder" }, new String[] { "dot.UmlBuilder" }, expectedUml);
	}

	@Test
	public void testInputStreamReaderIsNotAnDecorator() {
		String[] classNames = new String[] { "java.io.InputStreamReader", "java.io.Closeable",
				"java.lang.AutoCloseable", "java.lang.Readable", "java.io.FileReader", "java.io.Reader",
				"java.io.InputStream" };
		assertDecorator(classNames, new String[0], new String[0], classNames, "");
	}

	@Test
	public void testOutputStreamWriterIsNotAnDecorator() {
		String[] classNames = new String[] { "java.io.OutputStreamWriter", "java.io.Closeable",
				"java.lang.AutoCloseable", "java.io.Flushable", "java.lang.Appendable", "java.io.FileWriter",
				"java.io.OutputStream" };
		assertDecorator(classNames, new String[0], new String[0], classNames, "");
	}

	@Test
	public void testMouseDecoratorIsNotAnDecorator() {
		String[] classNames = new String[] { "java.awt.event.MouseAdapter", "java.awt.event.MouseListener",
				"java.awt.event.MouseWheelListener", "java.util.EventListener" };
		assertDecorator(classNames, new String[0], new String[0], classNames, "");
	}
	
	@Test
	public void testLabDecorator() {
		String[] classNames = new String[] { "src.problem.DecryptionInputStream", "src.problem.EncryptionOutputStream",
				"src.problem.IDecryption", "src.problem.IEncryption", "src.problem.SubstitutionCipher", "src.problem.TextEditorApp" };
		assertDecorator(classNames, new String[0], new String[0], classNames, "");
	}

	public void assertDecorator(String[] classNames, String[] decoratorClasses, String[] componentClasses,
			String[] noPatternClasses, String expectedUml) {
		try {
			assertEquals(classNames.length,
					decoratorClasses.length + componentClasses.length + noPatternClasses.length);
			UmlWrapper umlWrapper = new UmlWrapper(classNames);
			umlWrapper.addBuilderClass(ExtensionBuilder.class);
			umlWrapper.addBuilderClass(ImplementsBuilder.class);
			umlWrapper.addBuilderClass(DecoratorBuilder.class);
			String actualUml = umlWrapper.build();
			HashMap<String, IClassRecord> recordMap = umlWrapper.getRecords();

			for (String className : decoratorClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertTrue(record.getPatternNames().contains("Decorator"));
				assertFalse(record.getPatternNames().contains("Component"));
			}

			for (String className : componentClasses) {
				ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
				assertFalse(record.getPatternNames().contains("Decorator"));
				assertTrue(record.getPatternNames().contains("Component"));
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
				assertEquals(expectedUml, actualUml);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
	}
}
