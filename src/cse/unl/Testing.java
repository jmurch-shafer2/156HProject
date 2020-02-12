package cse.unl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Testing {
	public static void main(String[] argc) {
		URL url = getClass().getResource("Persons.dat");
		File path = new File(url.toURI());
		System.out.println(path.exists());
		Scanner scan = null;
		try {
			scan = new Scanner(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numOfLines = scan.nextInt();
		
		for(int i = 0; i<numOfLines;i++) {
			System.out.println(scan.hasNextLine());
		}
		
		
		scan.close();
	}
}