package sdedit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import records.MethodRecord;
import records.MethodSignature;
import records.SequenceRecord;

public class SequenceBuilderTest {
	@Test
	public void testBasicSequqence() throws IOException {
		assertSequenceBuilder(new ArrayList<MethodRecord>(),
				new MethodSignature("sdedit/Foo", "<init>", "()V" ));
	}
	
	@Test
	public void testShuffleSequqence() throws IOException {
		assertSequenceBuilder(new ArrayList<MethodRecord>(),
				new MethodSignature("java/util/Collections", "shuffle", "(Ljava/util/List;)V" ));
	}

	public void assertSequenceBuilder(ArrayList<MethodRecord> expectedResult, MethodSignature methodSignature) {
		SequenceBuilder builder = new SequenceBuilder(methodSignature);
		SequenceRecord record = (SequenceRecord) builder.build();
		System.out.println(record.getMethodCalls());
		for (MethodSignature m : record.getMethodCalls()) {
			System.out.println(m.getClassName() + ": " + m.getMethodName() + "(" + m.getSignature() + ")");
		}
		assertEquals(expectedResult, record.getMethodCalls());
	}
}
