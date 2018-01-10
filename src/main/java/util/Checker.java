package util;

import java.util.HashMap;
import java.util.HashSet;
/**
 * Checks a collection of words for errors from a dictionary -  a collection of most of the words of the respective language.
 * 
 * @author SpaGeorgios
 * @author dionisispap98
 * @version 0.3-SNAPSHOT
 * @since 0.1
 */
public class Checker {
	/**
	 * Checks if the String is contained within a language's or user's dictionary, for every string of the user input.
	 * 
	 * @param input1 A HashMap containing a string of the position of each word as its keys and the words to be checked themselves as values.
	 * @param dictionary A HashSet which contains every word from the dictionary.
	 * @return A HashMap containing a string of the position of each wrong word as its keys and the words themselves as values.
	 */
	public static HashMap<String, String> finder(HashMap<String, String> input1,
			HashSet<String> dictionary) {
		HashMap<String, String> errors = new HashMap<String, String>();
		for (String key : input1.keySet()) {
			String element = input1.get(key);
			String lowercaseElement = element.toLowerCase();
			if (!dictionary.contains(lowercaseElement) && !dictionary.contains(element) &&
					!element.matches("^[0-9]+$")) {
				errors.put(key, element);
			}
		}
		if (errors.isEmpty()) {
			System.out.println("No errors found.");
		} else {
			System.out.println(errors.size() + " errors found.");
		}
		return errors;
	}
}
