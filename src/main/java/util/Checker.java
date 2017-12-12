package util;

import java.util.HashSet;
import java.util.LinkedList;

public class Checker {

	public static LinkedList<String> finder(LinkedList<String> input1, HashSet<String> dictionary) {
		LinkedList<String> errors = new LinkedList<String>();
		int i =  0;
		for (String element : input1) {
			i++;
			String lowercaseElement = element.toLowerCase();
			if (!dictionary.contains(lowercaseElement) && !dictionary.contains(element)) {
				errors.add(element);
				System.out.format("Error found in word %d, %s%n", i, element);
			}
		}
		if (errors.isEmpty()) {
			System.out.println("No errors found.");
		}
		return errors;
	}
}
