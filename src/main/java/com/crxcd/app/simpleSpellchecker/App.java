package com.crxcd.app.simpleSpellchecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.LinkedList;

import mgmt.FileManagement;
import util.Checker;
import util.ParseElement;
import util.Suggestions;
import util.UserDictionary;

/**
 * Runs the program and interfaces with the user.
 *
 * @author Cmparounis,
 * @author filiamaroutsi
 * @author SpaGeorgios
 * @author katerinakontarini
 * @author manosalexiou95
 * @author SpaGeorgios
 * @author dionisispap98
 * @version 0.3-SNAPSHOT
 * @since 0.1
 *
 */
public class App {
	/**
	 * Stores the online location of the Greek Dictionary.
	 */
	private static final String grDictionaryURL = "http://ism.dmst.aueb.gr/ismgroup42/web/greek.txt";
	/**
	 * Stores the online location of the English Dictionary.
	 */
	private static final String enDictionaryURL = "http://ism.dmst.aueb.gr/ismgroup42/web/words_alpha.txt";
	/**
	 * Stores a language check; true if English words are being spell-checked.
	 */
	private static boolean isEn = false;
	/**
	 * Stores a language check; true if Greek words are being spell-checked.
	 */
	private static boolean isGr = false;

	/**
	 * This method executes a number of tasks in order to execute the spell-checking program:
	 *
	 * <p> First, the method prompts the user to decide what language he would like to use.
	 * If the user fails to enter one of the specified values, the method prompts him to enter
	 * a value again.
	 *
	 * <p>It then loads the respective language dictionaries from their online sources
	 *
	 * <p>In addition, it initializes the user dictionary: if it doesn't exist, it creates it
	 * and then appends it to the existing dictionary.
	 *
	 * <p>It prompts the user to enter his input; It is then spell-checked. If the user types
	 * quit, the program ends.
	 *
	 * <p>If there are no mistakes, the user is prompted to once again type his input.
	 *
	 * <p>If there are, each mistake is presented to the user; he is presented with correction
	 * suggestions; he is prompted to choose whether the perceived as erroneous word will be
	 * added to his personal dictionary;
	 *
	 * <p>If he chooses not to, he is prompted to enter his text again; if not, the word is
	 * written to the dictionary file the dictionary collection is reinitialized
	 *
	 * @param args Array args, per the java conventions.
	 */
	public static void main(String[] args) {
		String input1 = " " ;
		HashSet<String> dictionary = new HashSet<String>();
		HashSet<String> userDictionary = new HashSet<String>();

		System.out.println("Simple Spellchecker by CRxCD");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please choose your language (EN for English, GR for greek)");

		while (input1.compareToIgnoreCase("EN") != 0
				&& input1.compareToIgnoreCase("GR") != 0) {
			try {
				input1 = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (input1.compareToIgnoreCase("EN") == 0) {
				System.out.println("Please wait...");
				dictionary =
						new HashSet<String>(FileManagement.readDictionaryWeb(enDictionaryURL));
				isEn = true;
				System.out.println("CRxCD - Welcome to the english"
						+ " spellchecker");
			} else if (input1.compareToIgnoreCase("GR") == 0) {
				System.out.println("Please wait...");
				dictionary =
						new HashSet<String>(FileManagement.readDictionaryWeb(grDictionaryURL));
				isGr = true;
				System.out.println("CRxCD- Welcome to the greek"
						+ " spellchecker");
			} else {
				System.out.println("Please enter a valid Value");
			}
		}
		if (!UserDictionary.exists()) {
			UserDictionary.create();
		}
		userDictionary =
				new HashSet<String>(FileManagement.readDictionary(UserDictionary.getThisPath()));
		dictionary.addAll(userDictionary);

  		while (input1.compareToIgnoreCase("quit") != 0) {
          	System.out.println("Type your text or the path of the file you would like to be processed:");
          	System.out.println("Type 'Quit' in order to quit.");
          	try {
          		input1 = br.readLine();
          	} catch (Exception e ) {
          		System.out.println(e.getMessage());
          	}
  			while (input1.isEmpty()) {
  				System.out.println("You must type at least one word.");
  				try {
  	          		input1 = br.readLine();
  	          	} catch (Exception e ) {
  	          		System.out.println(e.getMessage());
  	          	}
  			}
  			if (input1.compareToIgnoreCase("quit") != 0) {
  				try {
  					final long startTime = System.nanoTime();

  					LinkedHashMap<String, String> uInput = new LinkedHashMap<String, String>(ParseElement.reader(input1));
  					LinkedHashMap<String, String> errors = Checker.finder(uInput, dictionary);

  					final long endTime = System.nanoTime();
  					System.out.println("The process took : " + ((endTime - startTime) / 1000000) + "ms to complete.");

  					for (String key : errors.keySet()) {
  						String element = errors.get(key);
  						System.out.println("Error found in word "+ element +", at "+ key);
  						if (isEn) {
  							LinkedList<String> suggestions = Suggestions.createSuggestionsEn(element, dictionary);
  							if (suggestions.isEmpty()) {
  							System.out.println("No suggestions available");
  							} else {
  								System.out.println("Did you mean:");
  								for (String suggestion : suggestions) {
  	  								System.out.println(suggestion);
  								}
  							}
  						}
  						if (isGr) {
  							LinkedList<String> suggestions = Suggestions.createSuggestionsGr(element, dictionary);
  							if (suggestions.isEmpty()) {
  							System.out.println("No suggestions available");
  							} else {
  								System.out.println("Did you mean:");
  								for (String suggestion : suggestions) {
  	  								System.out.println(suggestion);
  								}
  							}
  						}
  						input1 = " ";
	  					System.out.println("Would you want to enter this word in your personal dictionary? (Y/N)");
	  					while (input1.compareToIgnoreCase("Y") != 0 && input1.compareToIgnoreCase("N") != 0) {

	  						try {
	  							input1 = br.readLine();
	  						} catch (Exception e) {
	  							System.out.println(e.getMessage());
	  						}
	  						if (input1.compareToIgnoreCase("Y") == 0) {
	  							UserDictionary.write(element.toLowerCase());
	  							userDictionary =
	  									new HashSet<String>(FileManagement.readDictionary(UserDictionary.getThisPath()));
	  							dictionary.addAll(userDictionary);
	  						} else if (input1.compareToIgnoreCase("N") == 0) {

	  						} else {
	  							System.out.println("Please enter a valid Value");
	  						}

	  					}
  					}
  				} catch (FileNotFoundException e) {
  					System.out.println(e.getMessage());
  				}
  			}
  		}
  		try {
  			br.close();
  		} catch (Exception e) {
  			System.out.println("Reader failed to close: " + e.getMessage());
  		}
    }
}
