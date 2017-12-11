package util;

import java.util.LinkedList;
import java.util.HashSet;

public class Checker {

	public static LinkedList<String> finder(LinkedList<String> input1, HashSet<String> dictionary) {
		LinkedList<String> errors = new LinkedList<String>();
		int i =  0;
		for (String element : input1) {
			i++;
			if (dictionary.contains(element) == false) {
				errors.add(element);
				System.out.format("Error found in word %d, %s%n", i, element);
			}
		}
		return errors;
	}
}
