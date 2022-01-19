package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
	
	public static List<String> fileReader(String filename) {
		
		File file = new File(filename);
	 
	        // Note:  Double backquote is to avoid compiler
	        // interpret words
	        // like \test as \t (ie. as a escape sequence)
	 
	        // Creating an object of BufferedReader class
	        BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(file));
				
				 String st;
				 List<String> thelist = new ArrayList<>();
			        // Condition holds true till
			        // there is character in a string
			        try {
						while ((st = br.readLine()) != null) {
 
							thelist.add(st);
						   // System.out.println(st);
						}
						br.close();
						return thelist;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	 
	        // Declaring a string variable
	       
	    }
		


}
