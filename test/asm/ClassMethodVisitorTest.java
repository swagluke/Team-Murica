package asm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dot.records.MethodRecord;

public class ClassMethodVisitorTest {

	private static final String CLASS_NAME = "java.utilArrayList<E>t";

	@Test
	public void testGettingName() throws IOException, ClassNotFoundException {
		Object[] EXPECTED_RESULT = new MethodRecord[] {
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("void", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.Collection")) },
						Arrays.asList(new String[] { "java.util.Collection" })),
				new MethodRecord("void", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("int", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("int", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("boolean", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "java.lang.Object" })),
				new MethodRecord("int",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "java.lang.Object" })),
				new MethodRecord("int",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "java.lang.Object" })),
				new MethodRecord("java.lang.Object", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("java.lang.Object[]", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("java.lang.Object[]",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.lang.Object[]")) },
						Arrays.asList(new String[] { "java.lang.Object[]" })),
				new MethodRecord("java.lang.Object",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("java.lang.Object",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("java.lang.Object",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "int", "java.lang.Object" })),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "java.lang.Object" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "int", "java.lang.Object" })),
				new MethodRecord("java.lang.Object",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.lang.Object")) },
						Arrays.asList(new String[] { "java.lang.Object" })),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("void", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.Collection")) },
						Arrays.asList(new String[] { "java.util.Collection" })),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("java.util.Collection")) },
						Arrays.asList(new String[] { "int", "java.util.Collection" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int", "int" })),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("void", new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("java.lang.String",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.Collection")) },
						Arrays.asList(new String[] { "java.util.Collection" })),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.Collection")) },
						Arrays.asList(new String[] { "java.util.Collection" })),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.Collection")),
								org.objectweb.asm.Type.getType(Class.forName("boolean")) },
						Arrays.asList(new String[] { "java.util.Collection", "boolean" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.io.ObjectOutputStream")) },
						Arrays.asList(new String[] { "java.io.ObjectOutputStream" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.io.ObjectInputStream")) },
						Arrays.asList(new String[] { "java.io.ObjectInputStream" })),
				new MethodRecord("java.util.ListIterator",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int" })),
				new MethodRecord("java.util.ListIterator", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("java.util.Iterator", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("java.util.List",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int", "int" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("int")),
								org.objectweb.asm.Type.getType(Class.forName("int")) },
						Arrays.asList(new String[] { "int", "int", "int" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.function.Consumer")) },
						Arrays.asList(new String[] { "java.util.function.Consumer" })),
				new MethodRecord("java.util.Spliterator", new Type[] {}, Arrays.asList(new String[] {})),
				new MethodRecord("boolean",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.function.Predicate")) },
						Arrays.asList(new String[] { "java.util.function.Predicate" })),
				new MethodRecord("void",
						new Type[] {
								org.objectweb.asm.Type.getType(Class.forName("java.util.function.UnaryOperator")) },
						Arrays.asList(new String[] { "java.util.function.UnaryOperator" })),
				new MethodRecord("void",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.Comparator")) },
						Arrays.asList(new String[] { "java.util.Comparator" })),
				new MethodRecord("int",
						new Type[] { org.objectweb.asm.Type.getType(Class.forName("java.util.ArrayList")) },
						Arrays.asList(new String[] { "java.util.ArrayList" })),
				new MethodRecord("void", new Type[] {}, Arrays.asList(new String[] {})) };

		ClassReader reader = new ClassReader(CLASS_NAME);
		ClassMethodVisitor classMethodVisitor = new ClassMethodVisitor(Opcodes.ASM5);

		assertNull(classMethodVisitor.getMethods());
		reader.accept(classMethodVisitor, ClassReader.EXPAND_FRAMES);
		System.out.println("*" + classMethodVisitor.getMethods() + "*");
		assertArrayEquals(EXPECTED_RESULT, classMethodVisitor.getMethods().toArray());
	}
}
