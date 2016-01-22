package gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class SDEditRunnerTest {
	final Path inputPath = Paths.get("test.sd");
	final Path outputPath = Paths.get("SDout.png");
	
	@Test
	public void testCreatesFile() throws IOException, InterruptedException {
		try {
			Files.delete(outputPath);
		} catch (IOException e) {
		}
		assertFalse(outputPath.toFile().exists());
		SDEditRunner.main(new String[] {"10", "java.util.Collections", "shuffle", "java.util.List"});
		Thread.sleep(750); // wait for file to write
		assertTrue(outputPath.toFile().exists());
		assertFalse(inputPath.toFile().exists());
	}
}
