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

import records.ISequenceRecord;
import records.MethodSignature;
import records.SequenceRecord;
import sdedit.SequenceBuilder;

public class SDEditRunner {
	/**
	 * args fields: int depth, classname, method name, method parameter types...
	 * @param args
	 */
	public static void main(String[] args){
//		System.out.println(Arrays.toString(args));
		System.out.println(Type.getType(args.getClass()).getClassName());
		//construct the "methodField" argument
		String[] paramTypes = Arrays.copyOfRange(args, 3, args.length);
		Type[] typeDesc = new Type[paramTypes.length];
		for(int i=0;i<paramTypes.length;i++){
			try {
				typeDesc[i]=Type.getType(Class.forName(paramTypes[i]));
//				System.out.println("Type:"+typeDesc[i].getInternalName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MethodSignature m = new MethodSignature(args[1].replace(".", "/"), args[2], typeDesc, null);
		SequenceBuilder s = new SequenceBuilder(m, Integer.parseInt(args[0]));
		SequenceRecord temp = (SequenceRecord) s.build();
//		System.out.println(temp.getMethodCalls());
		createDiagram(s.getSequenceUML());
	}
	public static void createDiagram(String diagram){
		final Path path = Paths.get("temp.sd");

		try (final BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
				StandardOpenOption.CREATE);) {
			writer.write(diagram);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessBuilder pb = new ProcessBuilder("java", "-jar", "sdedit-4.2-beta1.jar", "-o", "SDout.png", "-t", "png", "temp.sd");
//		Map<String, String> env = pb.environment();
		// pb.directory();
		System.out.println("Written to " +System.getProperty("user.dir")+"\\"+path.toString());
		try {
			// Process p = pb.start();
			File log = new File("log");
			pb.redirectErrorStream(true);
			pb.redirectOutput(Redirect.appendTo(log));
			Process p = pb.start();
//			Files.delete(path);//uncomment to clean up after yourself
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}