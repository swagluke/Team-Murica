package sdedit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilderTest {
	@Test
	public void testBasicSequqence() throws IOException {
		assertSequenceBuilder(
				new ArrayList<MethodSignature>(Arrays.asList(new MethodSignature("sdedit/Foo", "<init>", "()V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "methodA", "()V"),
						new MethodSignature("sdedit/Bar", "methodB", "(I)I"),
						new MethodSignature("sdedit/Foo", "addOne", "(I)I"),
						new MethodSignature("sdedit/Foo", "addOne", "(I)I")
						)),
				new MethodSignature("sdedit/Foo", "<init>", "()V"));
	}
	@Test
	public void testBasicSequqenceWithDepth() throws IOException {
		assertSequenceBuilder(
				new ArrayList<MethodSignature>(Arrays.asList(new MethodSignature("sdedit/Foo", "<init>", "()V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "<init>", "(Lsdedit/Foo;)V"),
						new MethodSignature("java/lang/Object", "<init>", "()V"),
						new MethodSignature("sdedit/Bar", "methodA", "()V"),
						new MethodSignature("sdedit/Foo", "addOne", "(I)I")
						)),
				new MethodSignature("sdedit/Foo", "<init>", "()V"), 3);
	}

	@Test
	public void testShuffleSequqence() throws IOException {
		assertSequenceBuilder(new ArrayList<MethodSignature>(),
				new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V"));
	}

	public void assertSequenceBuilder(ArrayList<MethodSignature> expectedResult, MethodSignature methodSignature) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature);
		SequenceRecord record = (SequenceRecord) builder.build();
		assertEquals(expectedResult, record.getMethodCalls());
	}

	public void assertSequenceBuilder(ArrayList<MethodSignature> expectedResult, MethodSignature methodSignature, int recursionDepth) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature, recursionDepth);
		SequenceRecord record = (SequenceRecord) builder.build();
		assertEquals(expectedResult, record.getMethodCalls());
	}
}
