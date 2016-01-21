package sdedit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import genericTree.GenericTree;
import genericTree.GenericTreeNode;
import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilderTest {
	@Test
	public void testBasicSequqence() throws IOException {
		GenericTree<MethodSignature> tree = new GenericTree<MethodSignature>();

		GenericTreeNode<MethodSignature> root = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Foo",
				"<init>", "()V"));
		tree.setRoot(root);

		GenericTreeNode<MethodSignature> node0 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", "()V"));
		root.addChild(node0);

		GenericTreeNode<MethodSignature> node1 = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Bar",
				"<init>", "(Lsdedit/Foo;)V"));
		root.addChild(node1);

		GenericTreeNode<MethodSignature> node10 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"java/lang/Object", "<init>", "()V"));
		GenericTreeNode<MethodSignature> node11 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Bar", "methodA", "()V"));
		node1.addChild(node10);
		node1.addChild(node11);

		GenericTreeNode<MethodSignature> node110 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Bar", "methodB", "(I)I"));
		node11.addChild(node110);

		GenericTreeNode<MethodSignature> node1100 = new GenericTreeNode<MethodSignature>(new MethodSignature(
				"sdedit/Foo", "addOne", "(I)I"));
		node110.addChild(node1100);

		GenericTreeNode<MethodSignature> node2 = new GenericTreeNode<MethodSignature>(new MethodSignature("sdedit/Foo",
				"addOne", "(I)I"));
		root.addChild(node2);

		assertSequenceBuilder(new MethodSignature("sdedit/Foo", "<init>", "()V"), tree, "");
	}

	@Test
	public void testBasicSequqenceWithDepth() throws IOException {
		assertSequenceBuilder(new MethodSignature("sdedit/Foo", "<init>", "()V"), 3,
				new GenericTree<MethodSignature>(),
				// new ArrayList<MethodSignature>(Arrays.asList(new MethodSignature("sdedit/Foo", "<init>", "()V"),
				// new MethodSignature("java/lang/Object", "<init>", "()V"),
				// new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"),
				// new MethodSignature("java/lang/Object", "<init>", "()V"),
				// new MethodSignature("sdedit/Bar", "methodA", "()V"),
				// new MethodSignature("sdedit/Foo", "addOne", "(I)I"))),
				"");
	}

	@Test
	public void testShuffleSequqence() throws IOException {
		assertSequenceBuilder(new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"),
				new GenericTree<MethodSignature>(), "");
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, GenericTree<MethodSignature> expectedResult,
			String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature);
		SequenceRecord record = (SequenceRecord) builder.build();
		GenericTree<MethodSignature> methods = record.getMethodCalls();
		System.out.println(expectedResult);
		System.out.println(methods);
		assertEquals(expectedResult, record.getMethodCalls());
		assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, int recursionDepth,
			GenericTree<MethodSignature> expectedResult, String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature, recursionDepth);
		SequenceRecord record = (SequenceRecord) builder.build();
		GenericTree<MethodSignature> methods = record.getMethodCalls();
		assertEquals(expectedResult, record.getMethodCalls());
		assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}
}
