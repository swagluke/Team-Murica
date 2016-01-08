package asm;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dot.records.MethodRecord;

public class ClassMethodVisitorTest {
	@Test
	public void testNoMethods() throws IOException, ClassNotFoundException {
		ArrayList<MethodRecord> expectedResult = new ArrayList<MethodRecord>(Arrays.asList(new MethodRecord[] {
				new MethodRecord(1, "<init>", "void", new Type[0], new ArrayList<String>()), 
		}));
		assertMethods("asm.NoMethods", expectedResult);
	}

	@Test
	public void testClassMethodVisitorTestMethods() throws IOException, ClassNotFoundException {
		ArrayList<MethodRecord> expectedResult = new ArrayList<MethodRecord>(Arrays.asList(new MethodRecord[] {
				new MethodRecord(1, "<init>", "void", new Type[0], new ArrayList<String>()), 
				new MethodRecord(1, "testNoMethods", "void", new Type[0], new ArrayList<String>()), 
				new MethodRecord(1, "testClassMethodVisitorTestMethods", "void", new Type[0], new ArrayList<String>()), 
				new MethodRecord(1, "testArrayListMethods", "void", new Type[0], new ArrayList<String>()), 
				new MethodRecord(1, "assertMethods", "void", new Type[]{Type.getType(String.class), Type.getType(ArrayList.class)}, Arrays.asList(new String[] {"java.lang.String", "java.util.ArrayList"})) ,
				new MethodRecord(2, "test", "java.lang.String", new Type[]{Type.getType(int.class), Type.getType(this.getClass())}, Arrays.asList(new String[] {"int", this.getClass().getName()})) 
		}));
		assertMethods("asm.ClassMethodVisitorTest", expectedResult);
	}

	@Test
	public void testArrayListMethods() throws IOException, ClassNotFoundException {
		ArrayList<MethodRecord> expectedResult = new ArrayList<MethodRecord>(Arrays.asList(new MethodRecord[] {
				new MethodRecord(1, "<init>", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "<init>", "void", Type.getArgumentTypes("()V"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "<init>", "void", Type.getArgumentTypes("(Ljava/util/Collection;)V"), Arrays.asList(new String[] {"java.util.Collection"})), 
				new MethodRecord(1, "trimToSize", "void", Type.getArgumentTypes("()V"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "ensureCapacity", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(2, "ensureCapacityInternal", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(2, "ensureExplicitCapacity", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(2, "grow", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(10, "hugeCapacity", "int", Type.getArgumentTypes("(I)I"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "size", "int", Type.getArgumentTypes("()I"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "isEmpty", "boolean", Type.getArgumentTypes("()Z"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "contains", "boolean", Type.getArgumentTypes("(Ljava/lang/Object;)Z"), Arrays.asList(new String[] {"java.lang.Object"})), 
				new MethodRecord(1, "indexOf", "int", Type.getArgumentTypes("(Ljava/lang/Object;)I"), Arrays.asList(new String[] {"java.lang.Object"})), 
				new MethodRecord(1, "lastIndexOf", "int", Type.getArgumentTypes("(Ljava/lang/Object;)I"), Arrays.asList(new String[] {"java.lang.Object"})), 
				new MethodRecord(1, "clone", "java.lang.Object", Type.getArgumentTypes("()Ljava/lang/Object;"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "toArray", "java.lang.Object[]", Type.getArgumentTypes("()[Ljava/lang/Object;"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "toArray", "java.lang.Object[]", Type.getArgumentTypes("([Ljava/lang/Object;)[Ljava/lang/Object;"), Arrays.asList(new String[] {"java.lang.Object[]"})), 
				new MethodRecord(0, "elementData", "java.lang.Object", Type.getArgumentTypes("(I)Ljava/lang/Object;"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "get", "java.lang.Object", Type.getArgumentTypes("(I)Ljava/lang/Object;"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "set", "java.lang.Object", Type.getArgumentTypes("(ILjava/lang/Object;)Ljava/lang/Object;"), Arrays.asList(new String[] {"int", "java.lang.Object"})), 
				new MethodRecord(1, "add", "boolean", Type.getArgumentTypes("(Ljava/lang/Object;)Z"), Arrays.asList(new String[] {"java.lang.Object"})), 
				new MethodRecord(1, "add", "void", Type.getArgumentTypes("(ILjava/lang/Object;)V"), Arrays.asList(new String[] {"int", "java.lang.Object"})), 
				new MethodRecord(1, "remove", "java.lang.Object", Type.getArgumentTypes("(I)Ljava/lang/Object;"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "remove", "boolean", Type.getArgumentTypes("(Ljava/lang/Object;)Z"), Arrays.asList(new String[] {"java.lang.Object"})), 
				new MethodRecord(2, "fastRemove", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "clear", "void", Type.getArgumentTypes("()V"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "addAll", "boolean", Type.getArgumentTypes("(Ljava/util/Collection;)Z"), Arrays.asList(new String[] {"java.util.Collection"})), 
				new MethodRecord(1, "addAll", "boolean", Type.getArgumentTypes("(ILjava/util/Collection;)Z"), Arrays.asList(new String[] {"int", "java.util.Collection"})), 
				new MethodRecord(4, "removeRange", "void", Type.getArgumentTypes("(II)V"), Arrays.asList(new String[] {"int", "int"})), 
				new MethodRecord(2, "rangeCheck", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(2, "rangeCheckForAdd", "void", Type.getArgumentTypes("(I)V"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(2, "outOfBoundsMsg", "java.lang.String", Type.getArgumentTypes("(I)Ljava/lang/String;"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "removeAll", "boolean", Type.getArgumentTypes("(Ljava/util/Collection;)Z"), Arrays.asList(new String[] {"java.util.Collection"})), 
				new MethodRecord(1, "retainAll", "boolean", Type.getArgumentTypes("(Ljava/util/Collection;)Z"), Arrays.asList(new String[] {"java.util.Collection"})), 
				new MethodRecord(2, "batchRemove", "boolean", Type.getArgumentTypes("(Ljava/util/Collection;Z)Z"), Arrays.asList(new String[] {"java.util.Collection", "boolean"})), 
				new MethodRecord(2, "writeObject", "void", Type.getArgumentTypes("(Ljava/io/ObjectOutputStream;)V"), Arrays.asList(new String[] {"java.io.ObjectOutputStream"})), 
				new MethodRecord(2, "readObject", "void", Type.getArgumentTypes("(Ljava/io/ObjectInputStream;)V"), Arrays.asList(new String[] {"java.io.ObjectInputStream"})), 
				new MethodRecord(1, "listIterator", "java.util.ListIterator", Type.getArgumentTypes("(I)Ljava/util/ListIterator;"), Arrays.asList(new String[] {"int"})), 
				new MethodRecord(1, "listIterator", "java.util.ListIterator", Type.getArgumentTypes("()Ljava/util/ListIterator;"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "iterator", "java.util.Iterator", Type.getArgumentTypes("()Ljava/util/Iterator;"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "subList", "java.util.List", Type.getArgumentTypes("(II)Ljava/util/List;"), Arrays.asList(new String[] {"int", "int"})), 
				new MethodRecord(8, "subListRangeCheck", "void", Type.getArgumentTypes("(III)V"), Arrays.asList(new String[] {"int", "int", "int"})), 
				new MethodRecord(1, "forEach", "void", Type.getArgumentTypes("(Ljava/util/function/Consumer;)V"), Arrays.asList(new String[] {"java.util.function.Consumer"})), 
				new MethodRecord(1, "spliterator", "java.util.Spliterator", Type.getArgumentTypes("()Ljava/util/Spliterator;"), Arrays.asList(new String[] {})), 
				new MethodRecord(1, "removeIf", "boolean", Type.getArgumentTypes("(Ljava/util/function/Predicate;)Z"), Arrays.asList(new String[] {"java.util.function.Predicate"})), 
				new MethodRecord(1, "replaceAll", "void", Type.getArgumentTypes("(Ljava/util/function/UnaryOperator;)V"), Arrays.asList(new String[] {"java.util.function.UnaryOperator"})), 
				new MethodRecord(1, "sort", "void", Type.getArgumentTypes("(Ljava/util/Comparator;)V"), Arrays.asList(new String[] {"java.util.Comparator"})), 
				new MethodRecord(4104, "access$100", "int", Type.getArgumentTypes("(Ljava/util/ArrayList;)I"), Arrays.asList(new String[] {"java.util.ArrayList"})), 
				new MethodRecord(8, "<clinit>", "void", Type.getArgumentTypes("()V"), Arrays.asList(new String[] {}))
		}));
		assertMethods("java.util.ArrayList", expectedResult);
	}
	
	public void assertMethods(String className, ArrayList<MethodRecord> expectedResult) throws IOException {
		ClassReader reader = new ClassReader(className);
		ClassMethodVisitor classMethodVisitor = new ClassMethodVisitor(Opcodes.ASM5);

		assertTrue(classMethodVisitor.getMethods().isEmpty());
		reader.accept(classMethodVisitor, ClassReader.EXPAND_FRAMES);
		assertEquals(expectedResult, classMethodVisitor.getMethods());
	}
	
	private String test(int arg1, ClassMethodVisitorTest arg2) {
		return "";
	}
}
