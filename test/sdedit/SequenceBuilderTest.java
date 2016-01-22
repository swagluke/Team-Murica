package sdedit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import generictree.GenericTree;
import generictree.GenericTreeNode;
import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilderTest {
	@Test
	public void testBasicSequence() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Foo", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Bar", "methodA", "()V"));
		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Bar", "methodB", "(I)I"));
		GenericTreeNode<MethodSignature> node1100 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Foo", "addOne", "(I)I"));
		GenericTreeNode<MethodSignature> node2 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Foo", "addOne", "(I)I"));

		tree.setRoot(root);
		root.addChild(node0);
		root.addChild(node1);
		node1.addChild(node10);
		node1.addChild(node11);
		node11.addChild(node110);
		node110.addChild(node1100);
		root.addChild(node2);

		assertSequenceBuilder(new MethodSignature("sdedit/Foo", "<init>", "()V"), tree, "");
	}

	@Test
	public void testBasicSequenceWithDepth() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Foo", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Bar", "methodA", "()V"));
		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Bar", "methodB", "(I)I"));
		GenericTreeNode<MethodSignature> node2 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sdedit/Foo", "addOne", "(I)I"));

		tree.setRoot(root);
		root.addChild(node0);
		root.addChild(node1);
		node1.addChild(node10);
		node1.addChild(node11);
		node11.addChild(node110);
		root.addChild(node2);

		assertSequenceBuilder(new MethodSignature("sdedit/Foo", "<init>", "()V"), 3, tree, "");
	}

	@Test
	public void testShuffleSequence() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node00 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "seedUniquifier", "()J"));
		GenericTreeNode<MethodSignature> node000 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "get", "()J"));
		GenericTreeNode<MethodSignature> node001 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "compareAndSet", "(JJ)Z"));
		GenericTreeNode<MethodSignature> node0010 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sun/misc/Unsafe", "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z"));
		GenericTreeNode<MethodSignature> node01 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/System", "nanoTime", "()J"));
		GenericTreeNode<MethodSignature> node02 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "<init>", "(J)V"));
		GenericTreeNode<MethodSignature> node020 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node021 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "getClass", "()Ljava/lang/Class;"));
		GenericTreeNode<MethodSignature> node022 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "initialScramble", "(J)J"));
		GenericTreeNode<MethodSignature> node023 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "<init>", "(J)V"));
		GenericTreeNode<MethodSignature> node0230 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Number", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node02300 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node024 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node0240 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Number", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node02400 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node025 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "setSeed", "(J)V"));
		GenericTreeNode<MethodSignature> node0250 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "initialScramble", "(J)J"));
		GenericTreeNode<MethodSignature> node0251 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "set", "(J)V"));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;Ljava/util/Random;)V"));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "size", "()I"));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "nextInt", "(I)I"));
		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V"));
		GenericTreeNode<MethodSignature> node1100 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V"));
		GenericTreeNode<MethodSignature> node11000 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Exception", "<init>", "(Ljava/lang/String;)V"));
		GenericTreeNode<MethodSignature> node111 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "next", "(I)I"));
		GenericTreeNode<MethodSignature> node1110 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "get", "()J"));
		GenericTreeNode<MethodSignature> node1111 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "compareAndSet", "(JJ)Z"));
		GenericTreeNode<MethodSignature> node11110 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sun/misc/Unsafe", "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z"));
		GenericTreeNode<MethodSignature> node112 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "next", "(I)I"));
		GenericTreeNode<MethodSignature> node1120 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "get", "()J"));
		GenericTreeNode<MethodSignature> node1121 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "compareAndSet", "(JJ)Z"));
		GenericTreeNode<MethodSignature> node11210 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sun/misc/Unsafe", "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z"));
		GenericTreeNode<MethodSignature> node12 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "swap", "(Ljava/util/List;II)V"));
		GenericTreeNode<MethodSignature> node120 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "get", "(I)Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node121 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "set", "(ILjava/lang/Object;)Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node122 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "set", "(ILjava/lang/Object;)Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node13 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "toArray", "()[Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node14 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "nextInt", "(I)I"));
		GenericTreeNode<MethodSignature> node140 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/IllegalArgumentException", "<init>", "(Ljava/lang/String;)V"));
		GenericTreeNode<MethodSignature> node1400 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V"));
		GenericTreeNode<MethodSignature> node14000 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/Exception", "<init>", "(Ljava/lang/String;)V"));
		GenericTreeNode<MethodSignature> node141 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "next", "(I)I"));
		GenericTreeNode<MethodSignature> node1410 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "get", "()J"));
		GenericTreeNode<MethodSignature> node1411 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "compareAndSet", "(JJ)Z"));
		GenericTreeNode<MethodSignature> node14110 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sun/misc/Unsafe", "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z"));
		GenericTreeNode<MethodSignature> node142 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "next", "(I)I"));
		GenericTreeNode<MethodSignature> node1420 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "get", "()J"));
		GenericTreeNode<MethodSignature> node1421 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/concurrent/atomic/AtomicLong", "compareAndSet", "(JJ)Z"));
		GenericTreeNode<MethodSignature> node14210 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("sun/misc/Unsafe", "compareAndSwapLong", "(Ljava/lang/Object;JJJ)Z"));
		GenericTreeNode<MethodSignature> node15 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "swap", "([Ljava/lang/Object;II)V"));
		GenericTreeNode<MethodSignature> node16 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "listIterator", "()Ljava/util/ListIterator;"));
		GenericTreeNode<MethodSignature> node17 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/ListIterator", "next", "()Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node18 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/ListIterator", "set", "(Ljava/lang/Object;)V"));

		tree.setRoot(root);
		root.addChild(node0);
		node0.addChild(node00);
		node00.addChild(node000);
		node00.addChild(node001);
		node001.addChild(node0010);
		node0.addChild(node01);
		node0.addChild(node02);
		node02.addChild(node020);
		node02.addChild(node021);
		node02.addChild(node022);
		node02.addChild(node023);
		node023.addChild(node0230);
		node0230.addChild(node02300);
		node02.addChild(node024);
		node024.addChild(node0240);
		node0240.addChild(node02400);
		node02.addChild(node025);
		node025.addChild(node0250);
		node025.addChild(node0251);
		root.addChild(node1);
		node1.addChild(node10);
		node1.addChild(node11);
		node11.addChild(node110);
		node110.addChild(node1100);
		node1100.addChild(node11000);
		node11.addChild(node111);
		node111.addChild(node1110);
		node111.addChild(node1111);
		node1111.addChild(node11110);
		node11.addChild(node112);
		node112.addChild(node1120);
		node112.addChild(node1121);
		node1121.addChild(node11210);
		node1.addChild(node12);
		node12.addChild(node120);
		node12.addChild(node121);
		node12.addChild(node122);
		node1.addChild(node13);
		node1.addChild(node14);
		node14.addChild(node140);
		node140.addChild(node1400);
		node1400.addChild(node14000);
		node14.addChild(node141);
		node141.addChild(node1410);
		node141.addChild(node1411);
		node1411.addChild(node14110);
		node14.addChild(node142);
		node142.addChild(node1420);
		node142.addChild(node1421);
		node1421.addChild(node14210);
		node1.addChild(node15);
		node1.addChild(node16);
		node1.addChild(node17);
		node1.addChild(node18);

		assertSequenceBuilder(new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"), tree, "");
	}

	@Test
	public void testShuffleSequenceWithDepth() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node00 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "seedUniquifier", "()J"));
		GenericTreeNode<MethodSignature> node01 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/lang/System", "nanoTime", "()J"));
		GenericTreeNode<MethodSignature> node02 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "<init>", "(J)V"));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;Ljava/util/Random;)V"));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "size", "()I"));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "nextInt", "(I)I"));
		GenericTreeNode<MethodSignature> node12 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "swap", "(Ljava/util/List;II)V"));
		GenericTreeNode<MethodSignature> node13 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "toArray", "()[Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node14 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Random", "nextInt", "(I)I"));
		GenericTreeNode<MethodSignature> node15 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/Collections", "swap", "([Ljava/lang/Object;II)V"));
		GenericTreeNode<MethodSignature> node16 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/List", "listIterator", "()Ljava/util/ListIterator;"));
		GenericTreeNode<MethodSignature> node17 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/ListIterator", "next", "()Ljava/lang/Object;"));
		GenericTreeNode<MethodSignature> node18 = new GenericTreeNode<MethodSignature>(
				new MethodSignature("java/util/ListIterator", "set", "(Ljava/lang/Object;)V"));

		tree.setRoot(root);
		root.addChild(node0);
		node0.addChild(node00);
		node0.addChild(node01);
		node0.addChild(node02);
		root.addChild(node1);
		node1.addChild(node10);
		node1.addChild(node11);
		node1.addChild(node12);
		node1.addChild(node13);
		node1.addChild(node14);
		node1.addChild(node15);
		node1.addChild(node16);
		node1.addChild(node17);
		node1.addChild(node18);

		assertSequenceBuilder(new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"), 2, tree,
				"");
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, GenericTree<MethodSignature> expectedResult,
			String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature);
		SequenceRecord record = (SequenceRecord) builder.build();

//		GenericTree<MethodSignature> methods = record.getMethodCalls();
//		GenericTreeNode<MethodSignature> root = methods.getRoot();
//		StringBuilder decl = new StringBuilder();
//		StringBuilder add = new StringBuilder();
//		for (int i = 0; i < root.getNumberOfChildren(); i++) {
//			printNode(root.getChildAt(i), new ArrayList<Integer>(Arrays.asList(new Integer[] { i })), decl, add);
//		}
//		System.out.println("GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();\n");
//		System.out.println(
//				"GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature(\""
//						+ methodSignature.getClassName() + "\", \"" + methodSignature.getMethodName() + "\", \""
//						+ methodSignature.getSignature() + "\"));");
//		System.out.println(decl.toString());
//		System.out.println("tree.setRoot(root);");
//		System.out.println(add.toString());

		assertEquals(expectedResult, record.getMethodCalls());
		// assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, int recursionDepth,
			GenericTree<MethodSignature> expectedResult, String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature, recursionDepth);
		SequenceRecord record = (SequenceRecord) builder.build();
		for(MethodSignature m:record.getMethodCalls()){
			System.out.println("className: "+m.getClassName() + " MethodName: "+ m.getMethodName());
		}
//		GenericTree<MethodSignature> methods = record.getMethodCalls();
//		GenericTreeNode<MethodSignature> root = methods.getRoot();
//		StringBuilder decl = new StringBuilder();
//		StringBuilder add = new StringBuilder();
//		for (int i = 0; i < root.getNumberOfChildren(); i++) {
//			printNode(root.getChildAt(i), new ArrayList<Integer>(Arrays.asList(new Integer[] { i })), decl, add);
//		}
//		System.out.println("GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();\n");
//		System.out.println(
//				"GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature(\""
//						+ methodSignature.getClassName() + "\", \"" + methodSignature.getMethodName() + "\", \""
//						+ methodSignature.getSignature() + "\"));");
//		System.out.println(decl.toString());
//		System.out.println("tree.setRoot(root);");
//		System.out.println(add.toString());

		assertEquals(expectedResult, record.getMethodCalls());
		// assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}
	
	public void printNode(GenericTreeNode<MethodSignature> node, ArrayList<Integer> level, StringBuilder decl,
			StringBuilder add) {
		MethodSignature data = node.data;
		decl.append("GenericTreeNode<MethodSignature> node");
		for (Integer i : level) {
			decl.append(i);
		}
		decl.append(" = new GenericTreeNode<MethodSignature>(new MethodSignature(\"" + data.getClassName() + "\", \""
				+ data.getMethodName() + "\", \"" + data.getSignature() + "\"));\n");
		if (level.size() == 1) {
			add.append("root.addChild(node" + level.get(0) + ");\n");
		} else {
			add.append("node");
			for (int i = 0; i < level.size() - 1; i++) {
				add.append(level.get(i));
			}
			add.append(".addChild(node");
			for (int i = 0; i < level.size(); i++) {
				add.append(level.get(i));
			}
			add.append(");\n");
		}
		for (int i = 0; i < node.getNumberOfChildren(); i++) {
			ArrayList<Integer> newLevel = new ArrayList<Integer>(level);
			newLevel.add(i);
			printNode(node.getChildAt(i), newLevel, decl, add);
		}
	}
}
