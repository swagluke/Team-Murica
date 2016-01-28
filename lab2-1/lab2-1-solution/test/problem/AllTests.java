package problem;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	EncryptionDecryptionStreamTest.class,
	SubstitutionCipherTest.class 
})
public class AllTests {

}
