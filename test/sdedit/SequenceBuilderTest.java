package sdedit;

import static org.junit.Assert.assertEquals;
import generictree.GenericTree;
import generictree.GenericTreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.objectweb.asm.Type;

import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilderTest {
	@Test
	public void testBasicSequence() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Foo",
				"<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Bar",
				"<init>", Type.getArgumentTypes("(Lsdedit/Foo;)V"), Type.getReturnType("(Lsdedit/Foo;)V")));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Bar", "methodA", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Bar", "methodB", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node1100 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Foo", "addOne", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node2 = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Foo",
				"addOne", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));

		tree.setRoot(root);
		root.addChild(node0);
		root.addChild(node1);
		node1.addChild(node10);
		node1.addChild(node11);
		node11.addChild(node110);
		node110.addChild(node1100);
		root.addChild(node2);

		String sequenceUml = "/bar:bar\n/object:object\nfoo:foo\n\n"
				+ "foo:object.new(+)\nfoo:bar.new(+sdedit.Foo)\n bar:object.<init>()\n bar:bar.methodA()\n  "
				+ "bar:int=bar.methodB(int)\n   bar:int=foo.addOne(int)\nfoo:int=foo.addOne(int)\n";

		assertSequenceBuilder(
				new MethodSignature("sdedit/Foo", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")),
				tree, sequenceUml);
	}

	@Test
	public void testBasicSequenceWithDepth() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Foo",
				"<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Bar",
				"<init>", Type.getArgumentTypes("(Lsdedit/Foo;)V"), Type.getReturnType("(Lsdedit/Foo;)V")));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Bar", "methodA", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Bar", "methodB", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node2 = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Foo",
				"addOne", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));

		tree.setRoot(root);
		root.addChild(node0);
		root.addChild(node1);
		node1.addChild(node10);
		node1.addChild(node11);
		node11.addChild(node110);
		root.addChild(node2);

		String sequenceUml = "/bar:bar\n/object:object\nfoo:foo\n\n"
				+ "foo:object.new(+)\nfoo:bar.new(+sdedit.Foo)\n bar:object.<init>()\n bar:bar.methodA()\n  "
				+ "bar:int=bar.methodB(int)\nfoo:int=foo.addOne(int)\n";
		assertSequenceBuilder(
				new MethodSignature("sdedit/Foo", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")),
				3, tree, sequenceUml);
	}

	@Test
	public void testShuffleSequence() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "shuffle", Type.getArgumentTypes("(Ljava/util/List;)V"),
				Type.getReturnType("(Ljava/util/List;)V")));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node00 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "seedUniquifier", Type.getArgumentTypes("()J"), Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node000 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "get", Type.getArgumentTypes("()J"),
				Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node001 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "compareAndSet", Type.getArgumentTypes("(JJ)Z"),
				Type.getReturnType("(JJ)Z")));
		GenericTreeNode<MethodSignature> node0010 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sun/misc/Unsafe", "compareAndSwapLong", Type.getArgumentTypes("(Ljava/lang/Object;JJJ)Z"),
				Type.getReturnType("(Ljava/lang/Object;JJJ)Z")));
		GenericTreeNode<MethodSignature> node01 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/System", "nanoTime", Type.getArgumentTypes("()J"), Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node02 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "<init>", Type.getArgumentTypes("(J)V"), Type.getReturnType("(J)V")));
		GenericTreeNode<MethodSignature> node020 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node021 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "getClass", Type.getArgumentTypes("()Ljava/lang/Class;"),
				Type.getReturnType("()Ljava/lang/Class;")));
		GenericTreeNode<MethodSignature> node022 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "initialScramble", Type.getArgumentTypes("(J)J"), Type.getReturnType("(J)J")));
		GenericTreeNode<MethodSignature> node023 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "<init>", Type.getArgumentTypes("(J)V"),
				Type.getReturnType("(J)V")));
		GenericTreeNode<MethodSignature> node0230 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Number", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node02300 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node024 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "<init>", Type.getArgumentTypes("()V"),
				Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node0240 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Number", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node02400 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node025 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "setSeed", Type.getArgumentTypes("(J)V"), Type.getReturnType("(J)V")));
		GenericTreeNode<MethodSignature> node0250 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "initialScramble", Type.getArgumentTypes("(J)J"), Type.getReturnType("(J)J")));
		GenericTreeNode<MethodSignature> node0251 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "set", Type.getArgumentTypes("(J)V"),
				Type.getReturnType("(J)V")));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "shuffle", Type.getArgumentTypes("(Ljava/util/List;Ljava/util/Random;)V"),
				Type.getReturnType("(Ljava/util/List;Ljava/util/Random;)V")));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "size", Type.getArgumentTypes("()I"), Type.getReturnType("()I")));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "nextInt", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/IllegalArgumentException", "<init>", Type.getArgumentTypes("(Ljava/lang/String;)V"),
				Type.getReturnType("(Ljava/lang/String;)V")));
		GenericTreeNode<MethodSignature> node1100 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/RuntimeException", "<init>", Type.getArgumentTypes("(Ljava/lang/String;)V"),
				Type.getReturnType("(Ljava/lang/String;)V")));
		GenericTreeNode<MethodSignature> node11000 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Exception", "<init>", Type.getArgumentTypes("(Ljava/lang/String;)V"),
				Type.getReturnType("(Ljava/lang/String;)V")));
		GenericTreeNode<MethodSignature> node111 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "next", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node1110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "get", Type.getArgumentTypes("()J"),
				Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node1111 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "compareAndSet", Type.getArgumentTypes("(JJ)Z"),
				Type.getReturnType("(JJ)Z")));
		GenericTreeNode<MethodSignature> node11110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sun/misc/Unsafe", "compareAndSwapLong", Type.getArgumentTypes("(Ljava/lang/Object;JJJ)Z"),
				Type.getReturnType("(Ljava/lang/Object;JJJ)Z")));
		GenericTreeNode<MethodSignature> node112 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "next", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node1120 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "get", Type.getArgumentTypes("()J"),
				Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node1121 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "compareAndSet", Type.getArgumentTypes("(JJ)Z"),
				Type.getReturnType("(JJ)Z")));
		GenericTreeNode<MethodSignature> node11210 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sun/misc/Unsafe", "compareAndSwapLong", Type.getArgumentTypes("(Ljava/lang/Object;JJJ)Z"),
				Type.getReturnType("(Ljava/lang/Object;JJJ)Z")));
		GenericTreeNode<MethodSignature> node12 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "swap", Type.getArgumentTypes("(Ljava/util/List;II)V"),
				Type.getReturnType("(Ljava/util/List;II)V")));
		GenericTreeNode<MethodSignature> node120 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "get", Type.getArgumentTypes("(I)Ljava/lang/Object;"),
				Type.getReturnType("(I)Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node121 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "set", Type.getArgumentTypes("(ILjava/lang/Object;)Ljava/lang/Object;"),
				Type.getReturnType("(ILjava/lang/Object;)Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node122 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "set", Type.getArgumentTypes("(ILjava/lang/Object;)Ljava/lang/Object;"),
				Type.getReturnType("(ILjava/lang/Object;)Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node13 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "toArray", Type.getArgumentTypes("()[Ljava/lang/Object;"),
				Type.getReturnType("()[Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node14 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "nextInt", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node140 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/IllegalArgumentException", "<init>", Type.getArgumentTypes("(Ljava/lang/String;)V"),
				Type.getReturnType("(Ljava/lang/String;)V")));
		GenericTreeNode<MethodSignature> node1400 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/RuntimeException", "<init>", Type.getArgumentTypes("(Ljava/lang/String;)V"),
				Type.getReturnType("(Ljava/lang/String;)V")));
		GenericTreeNode<MethodSignature> node14000 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Exception", "<init>", Type.getArgumentTypes("(Ljava/lang/String;)V"),
				Type.getReturnType("(Ljava/lang/String;)V")));
		GenericTreeNode<MethodSignature> node141 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "next", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node1410 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "get", Type.getArgumentTypes("()J"),
				Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node1411 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "compareAndSet", Type.getArgumentTypes("(JJ)Z"),
				Type.getReturnType("(JJ)Z")));
		GenericTreeNode<MethodSignature> node14110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sun/misc/Unsafe", "compareAndSwapLong", Type.getArgumentTypes("(Ljava/lang/Object;JJJ)Z"),
				Type.getReturnType("(Ljava/lang/Object;JJJ)Z")));
		GenericTreeNode<MethodSignature> node142 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "next", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node1420 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "get", Type.getArgumentTypes("()J"),
				Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node1421 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/concurrent/atomic/AtomicLong", "compareAndSet", Type.getArgumentTypes("(JJ)Z"),
				Type.getReturnType("(JJ)Z")));
		GenericTreeNode<MethodSignature> node14210 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sun/misc/Unsafe", "compareAndSwapLong", Type.getArgumentTypes("(Ljava/lang/Object;JJJ)Z"),
				Type.getReturnType("(Ljava/lang/Object;JJJ)Z")));
		GenericTreeNode<MethodSignature> node15 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "swap", Type.getArgumentTypes("([Ljava/lang/Object;II)V"),
				Type.getReturnType("([Ljava/lang/Object;II)V")));
		GenericTreeNode<MethodSignature> node16 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "listIterator", Type.getArgumentTypes("()Ljava/util/ListIterator;"),
				Type.getReturnType("()Ljava/util/ListIterator;")));
		GenericTreeNode<MethodSignature> node17 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/ListIterator", "next", Type.getArgumentTypes("()Ljava/lang/Object;"),
				Type.getReturnType("()Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node18 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/ListIterator", "set", Type.getArgumentTypes("(Ljava/lang/Object;)V"),
				Type.getReturnType("(Ljava/lang/Object;)V")));

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

		String sequenceUml = "collections:collections\nsystem:system\n/runtimeexception:runtimeexception\n"
				+ "/exception:exception\nlistiterator:listiterator\nlist:list\n/number:number\natomiclong:atomiclong\n"
				+ "/random:random\nunsafe:unsafe\n/object:object\n/illegalargumentexception:illegalargumentexception\n\n"
				+ "collections:random.new(+)\n random:long=random.seedUniquifier()\n  random:long=atomiclong.get()\n  "
				+ "random:boolean=atomiclong.compareAndSet(long, long)\n   atomiclong:boolean=unsafe.compareAndSwapLong(java.lang.Object, long, long, long)\n "
				+ "random:long=system.nanoTime()\n random:random.<init>(long)\n  random:object.new(+)\n  random:java.lang.Class=object.getClass()\n  "
				+ "random:long=random.initialScramble(long)\n  random:atomiclong.<init>(long)\n   atomiclong:number.new(+)\n    "
				+ "number:object.<init>()\n  random:atomiclong.<init>()\n   atomiclong:number.<init>()\n    number:object.<init>()\n  "
				+ "random:random.setSeed(long)\n   random:long=random.initialScramble(long)\n   "
				+ "random:atomiclong.set(long)\ncollections:collections.shuffle(java.util.List, java.util.Random)\n collections:int=list.size()\n "
				+ "collections:int=random.nextInt(int)\n  random:illegalargumentexception.new(+java.lang.String)\n   "
				+ "illegalargumentexception:runtimeexception.new(+java.lang.String)\n    runtimeexception:exception.new(+java.lang.String)\n  "
				+ "random:int=random.next(int)\n   random:long=atomiclong.get()\n   random:boolean=atomiclong.compareAndSet(long, long)\n    "
				+ "atomiclong:boolean=unsafe.compareAndSwapLong(java.lang.Object, long, long, long)\n  random:int=random.next(int)\n   "
				+ "random:long=atomiclong.get()\n   random:boolean=atomiclong.compareAndSet(long, long)\n    "
				+ "atomiclong:boolean=unsafe.compareAndSwapLong(java.lang.Object, long, long, long)\n "
				+ "collections:collections.swap(java.util.List, int, int)\n  collections:java.lang.Object=list.get(int)\n  "
				+ "collections:java.lang.Object=list.set(int, java.lang.Object)\n  collections:java.lang.Object=list.set(int, java.lang.Object)\n "
				+ "collections:java.lang.Object[]=list.toArray()\n collections:int=random.nextInt(int)\n  "
				+ "random:illegalargumentexception.<init>(java.lang.String)\n   illegalargumentexception:runtimeexception.<init>(java.lang.String)\n    "
				+ "runtimeexception:exception.<init>(java.lang.String)\n  random:int=random.next(int)\n   random:long=atomiclong.get()\n   "
				+ "random:boolean=atomiclong.compareAndSet(long, long)\n    atomiclong:boolean=unsafe.compareAndSwapLong(java.lang.Object, long, long, long)\n  "
				+ "random:int=random.next(int)\n   random:long=atomiclong.get()\n   random:boolean=atomiclong.compareAndSet(long, long)\n    "
				+ "atomiclong:boolean=unsafe.compareAndSwapLong(java.lang.Object, long, long, long)\n collections:collections.swap(java.lang.Object[], int, int)\n "
				+ "collections:java.util.ListIterator=list.listIterator()\n collections:java.lang.Object=listiterator.next()\n "
				+ "collections:listiterator.set(java.lang.Object)\n";

		assertSequenceBuilder(
				new MethodSignature("java/util/Collections", "shuffle", Type.getArgumentTypes("(Ljava/util/List;)V"),
						Type.getReturnType("(Ljava/util/List;)V")), tree, sequenceUml);
	}

	@Test
	public void testShuffleSequenceWithDepth() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "shuffle", Type.getArgumentTypes("(Ljava/util/List;)V"),
				Type.getReturnType("(Ljava/util/List;)V")));
		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "<init>", Type.getArgumentTypes("()V"), Type.getReturnType("()V")));
		GenericTreeNode<MethodSignature> node00 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "seedUniquifier", Type.getArgumentTypes("()J"), Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node01 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/System", "nanoTime", Type.getArgumentTypes("()J"), Type.getReturnType("()J")));
		GenericTreeNode<MethodSignature> node02 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "<init>", Type.getArgumentTypes("(J)V"), Type.getReturnType("(J)V")));
		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "shuffle", Type.getArgumentTypes("(Ljava/util/List;Ljava/util/Random;)V"),
				Type.getReturnType("(Ljava/util/List;Ljava/util/Random;)V")));
		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "size", Type.getArgumentTypes("()I"), Type.getReturnType("()I")));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "nextInt", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node12 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "swap", Type.getArgumentTypes("(Ljava/util/List;II)V"),
				Type.getReturnType("(Ljava/util/List;II)V")));
		GenericTreeNode<MethodSignature> node13 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "toArray", Type.getArgumentTypes("()[Ljava/lang/Object;"),
				Type.getReturnType("()[Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node14 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Random", "nextInt", Type.getArgumentTypes("(I)I"), Type.getReturnType("(I)I")));
		GenericTreeNode<MethodSignature> node15 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/Collections", "swap", Type.getArgumentTypes("([Ljava/lang/Object;II)V"),
				Type.getReturnType("([Ljava/lang/Object;II)V")));
		GenericTreeNode<MethodSignature> node16 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/List", "listIterator", Type.getArgumentTypes("()Ljava/util/ListIterator;"),
				Type.getReturnType("()Ljava/util/ListIterator;")));
		GenericTreeNode<MethodSignature> node17 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/ListIterator", "next", Type.getArgumentTypes("()Ljava/lang/Object;"),
				Type.getReturnType("()Ljava/lang/Object;")));
		GenericTreeNode<MethodSignature> node18 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/util/ListIterator", "set", Type.getArgumentTypes("(Ljava/lang/Object;)V"),
				Type.getReturnType("(Ljava/lang/Object;)V")));

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
		
		String sequenceUml = "collections:collections\nsystem:system\nlistiterator:listiterator\nlist:list\n/random:random\n\n"
				+ "collections:random.new(+)\n random:long=random.seedUniquifier()\n random:long=system.nanoTime()\n random:random.<init>(long)\n"
				+ "collections:collections.shuffle(java.util.List, java.util.Random)\n collections:int=list.size()\n "
				+ "collections:int=random.nextInt(int)\n collections:collections.swap(java.util.List, int, int)\n "
				+ "collections:java.lang.Object[]=list.toArray()\n collections:int=random.nextInt(int)\n "
				+ "collections:collections.swap(java.lang.Object[], int, int)\n collections:java.util.ListIterator=list.listIterator()\n "
				+ "collections:java.lang.Object=listiterator.next()\n collections:listiterator.set(java.lang.Object)\n";
		
		assertSequenceBuilder(
				new MethodSignature("java/util/Collections", "shuffle", Type.getArgumentTypes("(Ljava/util/List;)V"),
						Type.getReturnType("(Ljava/util/List;)V")), 2, tree, sequenceUml);
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, GenericTree<MethodSignature> expectedResult,
			String expectedSequenceUml) {
		assertSequenceBuilder(methodSignature, 5, expectedResult, expectedSequenceUml);
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, int recursionDepth,
			GenericTree<MethodSignature> expectedResult, String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature, recursionDepth);
		SequenceRecord record = (SequenceRecord) builder.build();
		assertEquals(expectedResult, record.getMethodCalls());
		assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}
}
