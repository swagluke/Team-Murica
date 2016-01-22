package dot;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import records.SingletonRecord;

public class SingletonlBuilderTest {

	@Test
	public void testNotASingleton() throws IOException, ClassNotFoundException {
		String className = "dot.AssociationBuilder";
		assertSingleton(className, false, "");
	}

	@Test
	public void testIsSingleton() throws IOException, ClassNotFoundException {
		String className = "singleton.Singleton";
		assertSingleton(className, true, "");
	}

	@Test
	public void testRuntimeIsSingleton() throws IOException, ClassNotFoundException {
		String className = "java.lang.Runtime";
		assertSingleton(className, true, "");
	}

	@Test
	public void testCalendarIsNotASingleton() throws IOException, ClassNotFoundException {
		String className = "java.util.Calendar";
		assertSingleton(className, false, "");
	}
	
	@Test
	public void testFilterInputStreamIsNotASingleton() throws IOException, ClassNotFoundException {
		String className = "java.io.FilterInputStream";
		assertSingleton(className, false, "");
	}



	public void assertSingleton(String className, boolean expectedIsSingleton, String expectedUml) {
		SingletonBuilder builder = new SingletonBuilder(new UmlBuilder(className, new HashSet<String>(Arrays.asList(className))));
		SingletonRecord record = (SingletonRecord) builder.build();
		assertEquals(expectedIsSingleton, record.isSingleton());
		assertEquals(expectedUml, record.getClassUml());
	}
}
