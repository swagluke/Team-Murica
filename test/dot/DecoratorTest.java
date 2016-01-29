package dot;

import org.junit.Test;
import records.ClassRecord;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lukezhang on 1/29/16.
 */
public class DecoratorTest
{
    @Test
    public void testNotDecorator() throws IOException, ClassNotFoundException {
        String className="";
        String expectedUml = "";
        /*String className = "dot.AssociationBuilder";
        String expectedUml = "AssociationBuilder [label = \"{AssociationBuilder|+visitor : asm.ClassFieldSignatureVisitor\\l\n"
                + "|+ applyPatternrecords.ClassRecord  : void\\l\n"
                + "+ getVisitor : org.objectweb.asm.ClassVisitor\\l\n" + "}\"]";*/
        assertDecorator(className, false, expectedUml);
    }

    @Test
    public void testIsDecorator() throws IOException, ClassNotFoundException {
        String className="";
        String expectedUml = "";
        /*String className = "singleton.Singleton";
        String expectedUml = "Singleton [color = \"blue1\" label = \"{Singleton\\n\\<\\<Singleton\\>\\>|+instance : singleton.Singleton\\l\n"
                + "|+ getInstance : singleton.Singleton\\l\n" + "}\"]Singleton -> Singleton\n";
                */
        assertDecorator(className, true, expectedUml);
    }

    private void assertDecorator(String className, boolean expectedIsDecorator, String expectedUml)
    {
        DecoratorBuilder builder = new DecoratorBuilder(new UmlBuilder(className,new HashSet<String>(Arrays.asList(className))));
        ClassRecord record = (ClassRecord) builder.build();
        assertTrue(expectedIsDecorator == record.getBaseRecord().getPatternNames().contains("Decorator"));
        if (expectedIsDecorator) {
            assertEquals(expectedUml, record.getClassUml());
        } else {
            assertEquals(new UmlBuilder(className, new HashSet<String>(Arrays.asList(className))).build().getClassUml(),
                    record.getClassUml());
        }
    }
}
