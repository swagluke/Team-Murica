package headfirst.decorator.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LowerCaseInputStreamTest {
	private LowerCaseInputStream in;
	private String line1;
	private String line2;
	private String path;

	@Before
	public void setUp() throws Exception {
		// Set up fields
		this.line1 = "This is the first line!";
		this.line2 = "This is the second line!";
		this.path = "./input_output/test.txt";
		
		// Let's create a file
		FileOutputStream outS = new FileOutputStream(this.path);
		outS.write(this.line1.getBytes());
		outS.write('\n');
		outS.write(this.line2.getBytes());
		outS.close();
		
		// Let's initialize the stream
		in = new LowerCaseInputStream(new FileInputStream(this.path));
	}

	@After
	public void tearDown() throws Exception {
		in.close();
		Files.deleteIfExists(Paths.get(this.path));
	}

	@Test
	public final void testSingleCharRead() throws IOException {
		// Let's read the first line using the read() method
		
		StringBuffer buffer = new StringBuffer();
		int input = 0;
		while((input = this.in.read()) != '\n') {
			buffer.append((char)input);
		}
		
		assertEquals(this.line1.toLowerCase(), buffer.toString());
	}

	@Test
	public final void testByteArrayRead() throws IOException {
		byte[] buffer = new byte[this.line2.length()];
		this.in.read(buffer, 0, buffer.length);
		assertEquals(this.line2.toLowerCase(), new String(buffer));
	}
}
