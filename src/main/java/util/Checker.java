package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Checker {

	public static LinkedList<String> finder(HashMap<String, String> input1, HashSet<String> dictionary) {
		LinkedList<String> errors = new LinkedList<String>();
		for (String key : input1.keySet()) {
			String element = input1.get(key);
			String lowercaseElement = element.toLowerCase();
			if (!dictionary.contains(lowercaseElement) && !dictionary.contains(element) && !element.matches("^[0-9]+$")) {
				errors.add(element);
				System.out.println("Error found in word "+ element +", at "+ key);
			}
		}
		if (errors.isEmpty()) {
			System.out.println("No errors found.");
		} else {
			System.out.println( errors.size() + " errors found.");
		}
		return errors;
	}
}
