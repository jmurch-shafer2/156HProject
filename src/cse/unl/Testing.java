package cse.unl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Natalie Ruckman and Joel Murch-Shafer
 *
 */

public class Testing {
	public static void main(String[] argc) {
//		URL url = getClass().getResource("Persons.dat");
		
		File path = new File("C://Users//jmurc//Documents//GitHub//EclipseRootDir//MurckmanConsulting//Assets.dat");
		System.out.println(path.getAbsolutePath());
		Scanner scan = null;
		try {
			scan = new Scanner(path);
			while(scan.hasNextLine()) {
				System.out.println(scan.nextLine());
				//TODO Call constructor too
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numOfLines = scan.nextInt();
		

		
		
		scan.close();
	}
}