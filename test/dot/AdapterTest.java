package dot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import gui.UmlWrapper;
import org.junit.Test;
import records.ClassRecord;

/**
 * Created by lukezhang on 1/29/16.
 */
public class AdapterTest {
	@Test
	public void testNotAdapter() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException
	{
	}

	@Test
	public void testIsAdapter() throws IOException, ClassNotFoundException {
		/*UmlWrapper umlWrapper = new UmlWrapper(new String[] {"problem.client.IteratorToEnumerationAdapter","java.util.Iterator", "java.util.Enumeration"});
		umlWrapper.addBuilderClass(AdapterBuilder.class);
		String actualUml = umlWrapper.build();
		System.out.println(actualUml);*/
		String[] classname = new String[]{"problem.client.IteratorToEnumerationAdapter","java.util.Iterator", "java.util.Enumeration"};
		String expectedUml = "digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"] edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n" +
				"IteratorToEnumerationAdapter [color = \"red\" label = \"{IteratorToEnumerationAdapter\\n\\<\\<Adapter\\>\\>|+itr : java.util.Iterator\\l\n" +
				"|+ nextElement : java.lang.Object\\l\n" +
				"+ hasMoreElements : boolean\\l\n" +
				"}\"]\n" +
				"IteratorToEnumerationAdapter -> Iterator[label=\"<<adapts>>\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
				"IteratorToEnumerationAdapter -> Enumeration\n" +
				"edge [ style = \"normal\" arrowhead = \"vee\"]\n" +
				"IteratorToEnumerationAdapter -> Iterator\n" +
				"Iterator [color = \"red\" label = \"{Iterator\\n\\<\\<Adaptee\\>\\>||+ forEachRemainingjava.util.function.Consumer  : void\\l\n" +
				"+ hasNext : boolean\\l\n" +
				"+ next : java.lang.Object\\l\n" +
				"+ remove : void\\l\n" +
				"}\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
				"Enumeration [color = \"red\" label = \"{Enumeration\\n\\<\\<Adapter Target\\>\\>||+ hasMoreElements : boolean\\l\n" +
				"+ nextElement : java.lang.Object\\l\n" +
				"}\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
				"}rrowhead = \"empty\" style = \"dotted\"]\n" +
				"UmlBuilder [label = \"{UmlBuilder|+classList : java.util.HashSet\\l\n" +
				"+record : records.ClassRecord\\l\n" +
				"+methodVisitor : asm.ClassMethodVisitor\\l\n" +
				"+declVisitor : asm.ClassDeclarationVisitor\\l\n" +
				"+fieldVisitor : asm.ClassFieldVisitor\\l\n" +
				"+reader : org.objectweb.asm.ClassReader\\l\n" +
				"|+ getClassRecord : records.ClassRecord\\l\n" +
				"+ build : records.IClassRecord\\l\n" +
				"+ buildorg.objectweb.asm.ClassVisitor  : records.ClassRecord\\l\n" +
				"+ setClassListjava.util.HashSet  : void\\l\n" +
				"+ getClassUML : java.lang.String\\l\n" +
				"+ getClassList : java.util.HashSet\\l\n" +
				"+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n" +
				"+ build : records.ClassRecord\\l\n" +
				"+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" +
				"+ getMethodVisitor : asm.ClassMethodVisitor\\l\n" +
				"+ buildorg.objectweb.asm.ClassVisitor  : records.IClassRecord\\l\n" +
				"+ calculatePatternrecords.IClassRecord java.util.HashMap  : void\\l\n" +
				"}\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
				"UmlBuilder -> IBuilder\n" +
				"AbstractBuilderDecorator [color = \"yellow\" label = \"{AbstractBuilderDecorator\\n\\<\\<Decorator\\>\\>|+builder : dot.IBuilder\\l\n" +
				"+record : records.IClassRecord\\l\n" +
				"|+ getClassRecord : records.ClassRecord\\l\n" +
				"+ build : records.IClassRecord\\l\n" +
				"+ isPatternrecords.IClassRecord java.util.HashMap  : boolean\\l\n" +
				"+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" +
				"+ getClassUML : java.lang.String\\l\n" +
				"+ applyPatternrecords.IClassRecord java.util.HashMap  : void\\l\n" +
				"+ buildorg.objectweb.asm.ClassVisitor  : records.IClassRecord\\l\n" +
				"+ getClassList : java.util.HashSet\\l\n" +
				"+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n" +
				"+ calculatePatternrecords.IClassRecord java.util.HashMap  : void\\l\n" +
				"}\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
				"AbstractBuilderDecorator -> IBuilder\n" +
				"APatternBuilder [color = \"yellow\" label = \"{APatternBuilder\\n\\<\\<Decorator\\>\\>||+ isPatternrecords.IClassRecord java.util.HashMap  : boolean\\l\n" +
				"+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" +
				"+ applyPatternrecords.IClassRecord java.util.HashMap  : void\\l\n" +
				"+ applyDecorationrecords.IClassRecord  : records.IClassRecord\\l\n" +
				"}\"]edge [ style = \"normal\"]\n" +
				"APatternBuilder -> AbstractBuilderDecorator\n" +
				"edge [ arrowhead = \"empty\" style = \"dotted\"]\n" +
				"}";
		assertAdapter(classname, true, expectedUml);

	}
													// classname, the pattern that you expect
	public void assertAdapter(String[] classNames, HashMap<String, String> classPatterns, boolean expectedIsAdapter, String expectedUml) {
		try {
			UmlWrapper umlWrapper = new UmlWrapper(classNames);
			umlWrapper.addBuilderClass(ExtensionBuilder.class);
			umlWrapper.addBuilderClass(ImplementsBuilder.class);
			umlWrapper.addBuilderClass(AdapterBuilder.class);
			String actualUml = umlWrapper.build();
			//System.out.println(actualUml);
			ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
			assertTrue(expectedIsAdapter == record.getPatternNames().contains("Adapter"));
			if (expectedIsAdapter) {
				assertEquals(expectedUml, actualUml);
			} else {
				UmlWrapper baseUmlWrapper = new UmlWrapper(className);
				assertEquals(baseUmlWrapper.build(), actualUml);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			fail();
		}
	}
}
