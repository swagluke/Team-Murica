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

import org.objectweb.asm.Type;

import records.MethodSignature;
import sdedit.SequenceBuilder;

public class SDEditRunner {
	/**
	 * args fields: int depth, classname, method name, method parameter types...
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(Arrays.toString(args));
		//construct the "methodField" argument
		String[] paramTypes = Arrays.copyOfRange(args, 4, args.length);
		Type[] typeDesc = new Type[paramTypes.length];
		for(int i=0;i<paramTypes.length;i++){
			typeDesc[i]=Type.getType(paramTypes[i]);
		}
		String methodDesc = Type.getMethodDescriptor(Type.getType(args[3]), typeDesc);
			
		
		MethodSignature m = new MethodSignature(args[1], args[2], methodDesc);
		SequenceBuilder s = new SequenceBuilder(m, Integer.parseInt(args[0]));
		createDiagram(s.getSequenceUML());
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
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "sdedit-4.2-beta1.jar", "-o", "SDout.png", "-t", "png", "test.sd");
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