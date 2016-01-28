package temp;

import java.io.FileInputStream;
import java.io.InputStream;

public class UppcaseRunner {

	public static void main(String[] args) throws Exception {
		InputStream in = new UppercaseInputStream(new FileInputStream("./input_output/in.txt"));
		
		int input = 0;
		
		while((input = in.read()) != -1) {
			System.out.print((char)input);
		}
		
		in.close();
	}
}
