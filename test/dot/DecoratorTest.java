package dot;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import gui.UmlWrapper;
import org.junit.Test;

import records.ClassRecord;
import records.IClassRecord;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Created by lukezhang on 1/29/16.
 */
public class DecoratorTest
{
    @Test
    public void testNotDecorator() throws IOException, ClassNotFoundException {
        assertDecorator(new String[] { "java.util.Enumeration", "java.util.Iterator" },
                new String[0], new String[0], new String[0], "");
    }

    @Test
    public void testIsDecorator() throws IOException, ClassNotFoundException {
        String[] classnames = new String[] { "problem.client.IteratorToEnumerationAdapter", "java.util.Iterator",
                "java.util.Enumeration" };
        String expectedUml = "digraph G {fontname = \"Comic Sans MS\"  fontsize = 8  node [ fontname = \"Comic Sans MS\" fontsize = 8 shape = \"record\"] edge [ fontname = \"Comic Sans MS\" fontsize = 8 ]\n"
                + "IteratorToEnumerationAdapter [color = \"red\" label = \"{IteratorToEnumerationAdapter\\n\\<\\<Adapter\\>\\>|+itr : java.util.Iterator\\l\n"
                + "|+ nextElement : java.lang.Object\\l\n" + "+ hasMoreElements : boolean\\l\n" + "}\"]\n"
                + "IteratorToEnumerationAdapter -> Iterator[label=\"<<adapts>>\"]edge [ arrowhead = \"empty\" style = \"dotted\"]\n"
                + "IteratorToEnumerationAdapter -> Enumeration\n"
                + "Iterator [color = \"red\" label = \"{Iterator\\n\\<\\<Adaptee\\>\\>||+ forEachRemainingjava.util.function.Consumer  : void\\l\n"
                + "+ hasNext : boolean\\l\n" + "+ next : java.lang.Object\\l\n" + "+ remove : void\\l\n"
                + "}\"]"
                + "Enumeration [color = \"red\" label = \"{Enumeration\\n\\<\\<Adapter Target\\>\\>||+ hasMoreElements : boolean\\l\n"
                + "+ nextElement : java.lang.Object\\l\n" + "}\"]"
                + "}";
        assertDecorator(classnames, new String[] { "problem.client.IteratorToEnumerationAdapter" },
                new String[] { "java.util.Enumeration" }, new String[] { "java.util.Iterator" }, expectedUml);
    }

    public void assertDecorator(String[] classNames, String[] adapterClasses, String[] targetClasses,
                              String[] adapteeClasses, String expectedUml) {
        try {
            UmlWrapper umlWrapper = new UmlWrapper(classNames);
            umlWrapper.addBuilderClass(ExtensionBuilder.class);
            umlWrapper.addBuilderClass(ImplementsBuilder.class);
            umlWrapper.addBuilderClass(AdapterBuilder.class);
            String actualUml = umlWrapper.build();
            // System.out.println(actualUml);
            HashMap<String, IClassRecord> recordMap = umlWrapper.getRecords();

            for (String className : adapterClasses) {
                ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
                assertTrue(record.getPatternNames().contains("Decorator"));
                assertFalse(record.getPatternNames().contains("Decorator Target"));
                assertFalse(record.getPatternNames().contains("Decoratee"));
            }

            for (String className : targetClasses) {
                ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
                assertFalse(record.getPatternNames().contains("Decorator"));
                assertTrue(record.getPatternNames().contains("Decorator Target"));
                assertFalse(record.getPatternNames().contains("Decoratee"));
            }

            for (String className : adapteeClasses) {
                ClassRecord record = umlWrapper.getRecords().get(className).getBaseRecord();
                assertFalse(record.getPatternNames().contains("Decorator"));
                assertFalse(record.getPatternNames().contains("Decorator Target"));
                assertTrue(record.getPatternNames().contains("Decoratee"));
            }

            System.out.println(actualUml);
            if (expectedUml.equals("")) {
                UmlWrapper baseUmlWrapper = new UmlWrapper(classNames);
                umlWrapper.addBuilderClass(ExtensionBuilder.class);
                umlWrapper.addBuilderClass(ImplementsBuilder.class);
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
