package problem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class EncryptionDecryptionStreamTest {
	static SubstitutionCipher sCipher;
	static String expectedPlain;
	
	
	@BeforeClass
	public static void setUpBeforeClass() {
		sCipher = new SubstitutionCipher();
		expectedPlain = "This is a the first line!\nAnd this is the 2nd line!\nAlso the third line, added!";
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		sCipher = null;
		expectedPlain = null;
	}

	@Test
	public final void testSingleByteReadWrite() throws IOException {
		// Configure and encrypt data
		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
		EncryptionOutputStream eOutStream = new EncryptionOutputStream(bOutStream, sCipher) ;
		
		// Write to the stream
		for(char c: expectedPlain.toCharArray()) {
			eOutStream.write(c);
		}
		eOutStream.close();

		// Read the encrypted data and check if the transformation worked
		String cipher = bOutStream.toString();
		assertNotEquals("Encryption did not work!", expectedPlain, cipher);

		
		// Configure and decrypt data
		ByteArrayInputStream bInStream = new ByteArrayInputStream(cipher.getBytes());
		DecryptionInputStream dInStream = new DecryptionInputStream(bInStream, sCipher);
		char[] buffer = new char[expectedPlain.length()];
		
		int input = 0;
		int i = 0;
		while((input = dInStream.read()) != -1) {
			buffer[i++] = (char)input;
		}
		dInStream.close();

		// Read the decrypted data and check if it matches the original one
		String actualPlain = new String(buffer);
		
		assertEquals("Symmetry broken!", expectedPlain, actualPlain);		
	}
}
