package util;

import java.util.HashMap;
import java.util.HashSet;

public class Checker {

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
