package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Map;

import net.sf.sdedit.ui.components.SystemOut;

public class SDEditRunner {
	
	public static void main(String[] args){
		System.out.println(Arrays.toString(args));
		createDiagram("");
	}
	public static void createDiagram(String diagram){
		final Path path = Paths.get("test.sd");

		try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
				StandardOpenOption.CREATE);) {
			writer.write(diagram);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessBuilder pb = new ProcessBuilder("java", "-jar sdedit-4.2-beta1.jar", "-o SDout.png", "-t png", "test.sd");
//		Map<String, String> env = pb.environment();
		// pb.directory();
		System.out.println(System.getProperty("user.dir"));
		try {
			// Process p = pb.start();
			File log = new File("log");
			pb.redirectErrorStream(true);
			pb.redirectOutput(Redirect.appendTo(log));
			Process p = pb.start();
			// Files.delete(path);//uncomment to clean up after yourself
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
