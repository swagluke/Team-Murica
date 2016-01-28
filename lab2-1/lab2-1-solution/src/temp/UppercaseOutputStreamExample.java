package temp;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UppercaseOutputStreamExample {
	
	public static void main(String[] args) throws IOException {
		OutputStream bytesOut = new ByteArrayOutputStream();
		OutputStream upperOut = new UpperCaseOutputStream(bytesOut);
		
		String text = "This is a test";
		
		upperOut.write(text.getBytes());

//		Or use the following code to write
//		byte[] textBytes = text.getBytes();
//		for(int i = 0; i < textBytes.length; ++i) {
//			uOut.write(textBytes[i]);
//		}
		
		String written = bytesOut.toString();
		
		System.out.println("Intial String: " + text);
		System.out.println("Output String: " + written);

		upperOut.close();
	}
}

class UpperCaseOutputStream extends FilterOutputStream {
	public UpperCaseOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(int b) throws IOException {
		int upperB = Character.toUpperCase(b);
		super.write(upperB);
	}	

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		for(int i = off; i < off + len; ++i) {
			b[i] = (byte)Character.toUpperCase(b[i]);
		}
		super.write(b, off, len);
	}
}
