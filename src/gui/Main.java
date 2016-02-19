package gui;

import java.io.IOException;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		// TODO load confic file
		try {
			Gui gui = new Gui();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
