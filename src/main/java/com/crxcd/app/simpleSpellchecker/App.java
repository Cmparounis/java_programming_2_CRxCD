package com.crxcd.app.simpleSpellchecker;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import util.Checker;
import util.DictionaryGenerator;
import util.ParseElement;

/**
 * <h1> Type or quit </h1>
 * <p> The program asks from the user to quit or to type text he would like to processed </p>
 *
 * @author Cmparounis
 * @version 0.2-INSTANCE
 * @since 0.1-INSTANCE
 */

public class App {
	private static Scanner sc;
	private static final String DictionaryPath =  new File("src/main/java/config/greek.txt").getAbsolutePath();

	/**
		 * <h2> Main Method </h2>
		 * <p> This is the main method of App, which prompts the user to type something and then reacts acoordingly.</p>
		 * <p> Specifically: </p>
		 * <p>
		 * <li><b>Case 1:</b> if the user types "quit", the program stops running. </li>
		 * <li><b>Case 2:</b> if the user types nothing, the program shows the relevant message
	  	 *  	and asks him to type at least one word.</li>
	  	 *  <li><b>Case 3:</b>if the user neither types quit nor inserts nothing, his input is then processed by the classes of the util package and checked for spelling errors </li>
		 * </p>
		 *
		 * @param args Unused
		 * @return nothing
		 *
	 */

	public static void main(String[] args) {
		System.out.println("Greek Spellchecker by CRxCD- in development");
  		String input1 = " " ;
  		while (input1.compareToIgnoreCase("quit") != 0) {
          	System.out.println("Type your text or the path of the file you would like to be processed:");
          	System.out.println("Type 'Quit' in order to quit.");
  			sc = new Scanner(System.in);
  			input1 = sc.nextLine();
  			while (input1.isEmpty()) {
  				System.out.println("You must type at least one word.");
  				input1 = sc.nextLine();
  			}
  			if (input1.compareToIgnoreCase("quit") != 0) {
  				try {
  					final long startTime = System.nanoTime();
  					LinkedList<String> uInput = new LinkedList<String>(ParseElement.reader(input1));
  					HashSet<String> grDictionary = new HashSet<String>(DictionaryGenerator.readFile(DictionaryPath));
  					Checker.finder(uInput, grDictionary) ;
  					final long endTime = System.nanoTime();
  					System.out.println("The process took : " + ((endTime - startTime) / 1000000) + "ms to complete.");
  				} catch (FileNotFoundException e) {
  					System.out.println(e.getMessage());
  				}
  			}
  		}
    }
}
