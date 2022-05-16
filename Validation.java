/*
 * Helper class to do authentication and encryption. 
 */



import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Validation {

	/*
	 * reads 2 line file and stores int values in array
	 */
	public ArrayList<Integer> decryptFile(File f) {	
		ArrayList<Integer> creds = new ArrayList<Integer>(2);
		try {
			Scanner scnr = new Scanner(f);
			
			while(scnr.hasNextLine()) {
				int x = scnr.nextInt();
				creds.add(x);	
			} 
			scnr.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		System.out.println("Valid int values: " + creds);
		return creds;
	}
	/*
	 * Converts username and password strings to character arrays, 
	 * 	then converts those arrays to integers to be written to 
	 * 	a file that will store them for later use
	 */
	public boolean encryptFile(File f, String u, String p) {
		int uInt = 0; int pInt = 0;
		
		char[] user = toCharArray(u);
		char[] pass = toCharArray(p);
		uInt = charToInt(user);
		pInt = charToInt(pass); 
		
		try {
			FileWriter out = new FileWriter(f);
			out.write(uInt + "\n");
			out.write(pInt + "");
			out.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,  e.getMessage(), "Error: " + e.getStackTrace(), JOptionPane.ERROR_MESSAGE);
			return false;
		}
		JOptionPane.showMessageDialog(null,  "Encrypted!", "Encrypted!", JOptionPane.ERROR_MESSAGE);
		return true;
	}
	/*
	 * converts string to char array
	 */
	public char[] toCharArray(String str) {
		char[] ch = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			ch[i] = str.charAt(i);
		}
		return ch;
	}
	/*
	 * Converts char array to an integer sum for encryptFile()
	 * 	then returns that sum.
	 */
	public int charToInt(char[] ch) {
		int threes = 0;
		int x = 0;
		
		/*
		 * Algorithm for encryption. Increases sum value based on 
		 * 	index of char array
		 */
		for (int i = 1; i < ch.length + 1; i++) {
			int tmp = ch[i-1];
			if (i%2 == 0) {								//evens
				x += (ch[i-1] + 7);
			} else if (i%3 == 0) { 						//multiples of 3
				++threes;
				x += (ch[i-1] - 5)*threes;
			} else { 									//rest of the odds
				x += (ch[i-1] / 2);
			}
		}
		return x;
	}
}
