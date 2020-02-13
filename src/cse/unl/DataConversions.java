package cse.unl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class DataConversions {
	public static void main(String[] argc) {
		
		
		File assetsFilePath = new File("data//Assets.dat");
		try {
			Scanner scan = new Scanner(path);
			int numOfLines = scan.nextInt();
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
				//TODO Call constructor too
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}