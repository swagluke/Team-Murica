package problem;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubstitutionCipherTest {
	private SubstitutionCipher substitutionCipher; 
	
	@Before
	public void setUp() throws Exception {
		this.substitutionCipher = new SubstitutionCipher();
	}

	@After
	public void tearDown() throws Exception {
		this.substitutionCipher = null;
	}

	@Test
	public final void testAToZ() {
		for(char plain = 'A'; plain <= 'Z'; ++plain) {
			char cipher = substitutionCipher.encrypt(plain);
			assertNotEquals("Encryption did not work!", plain, cipher);
			
			char actual = substitutionCipher.decrypt(cipher);
			assertEquals("Broken symmetry", plain, actual);
//			System.out.format("%c => %c => %c%n", plain, cipher, actual);
		}
	}

	@Test
	public final void testaToz() {
		for(char plain = 'a'; plain <= 'z'; ++plain) {
			char cipher = substitutionCipher.encrypt(plain);
			assertNotEquals("Encryption did not work!", plain, cipher);
			
			char actual = substitutionCipher.decrypt(cipher);
			assertEquals("Broken symmetry", plain, actual);
//			System.out.format("%c => %c => %c%n", plain, cipher, actual);
		}
	}
	
	@Test
	public final void testNonAlphabet() {
		for(int plain = 0; plain < 128; ++plain) {
			// Ignore if A-Z or a-z inclusive.
			if(plain >= 65 && plain <= 90 || plain >= 97 && plain <= 122)
				continue;
			// Check non-alphabets are not transformed in any way
			char cipher = substitutionCipher.encrypt((char)plain);
			assertEquals("Non-alphabet characters were also transformed!", (char)plain, cipher);
			
			char actual = substitutionCipher.decrypt(cipher);
			assertEquals("Non-alphabet characters were also transformed!", (char)plain, actual);
		}
	}
}
