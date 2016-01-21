package sdedit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import records.GenericTree;
import records.GenericTreeNode;
import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilderTest {
	@Test
	public void testBasicSequqence() throws IOException {
		assertSequenceBuilder(new MethodSignature("sdedit/Foo", "<init>", "()V"),
				new ArrayList<MethodSignature>(Arrays.asList(new MethodSignature("sdedit/Foo", "<init>", "()V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "methodA", "()V"),
						new MethodSignature("sdedit/Bar", "methodB", "(I)I"),
						new MethodSignature("sdedit/Foo", "addOne", "(I)I"),
						new MethodSignature("sdedit/Foo", "addOne", "(I)I"))),
				"");
	}

	@Test
	public void testBasicSequqenceWithDepth() throws IOException {
		assertSequenceBuilder(new MethodSignature("sdedit/Foo", "<init>", "()V"), 3,
				new ArrayList<MethodSignature>(Arrays.asList(new MethodSignature("sdedit/Foo", "<init>", "()V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "methodA", "()V"),
						new MethodSignature("sdedit/Foo", "addOne", "(I)I"))),
				"");
	}

	@Test
	public void testShuffleSequqence() throws IOException {
		assertSequenceBuilder(new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"),
				new ArrayList<MethodSignature>(), "");
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, ArrayList<MethodSignature> expectedResult,
			String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature);
		SequenceRecord record = (SequenceRecord) builder.build();
		assertEquals(expectedResult, record.getMethodCalls());
		assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}

	public void assertSequenceBuilder(MethodSignature methodSignature, int recursionDepth,
			ArrayList<MethodSignature> expectedResult, String expectedSequenceUml) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature, recursionDepth);
		SequenceRecord record = (SequenceRecord) builder.build();
		GenericTree<MethodSignature> methods = record.getMethodCalls();
		System.out.println(methods.toStringWithDepth());
		System.out.println(System.identityHashCode(methods.getRoot()));
		for (GenericTreeNode<MethodSignature> child : methods.getRoot().getChildren()) {
			System.out.println(System.identityHashCode(child));
		}
		assertEquals(expectedResult, record.getMethodCalls());
		assertEquals(expectedSequenceUml, record.getSequenceDiagram());
	}
}
