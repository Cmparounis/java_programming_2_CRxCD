package util;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * <h1> Check the text</h1>
 * <p> this class checks if a word in the text is inside the dictionary </p>
 *
 * @author SpaGeorgios
 * @author dionisispap98
 * @version 0.2-INSTANCE
 * @since 0.1-INSTANCE
 */

public class Checker {

	/**
	 * <p> This method processes the user input, contained in a LinkedList, and then sellchecks it with a greek dictionary, contained in a HashSet.</p>
	 * <p> It then returns a LinkedList with all the errors.
	 *
	 * @param input1 the parameter passed by <i>ParseElement.reader()</i>.
	 * @param dictionary the parameter passed by <i>DictionaryGenerator.readFile()</i>
	 * @return errors LinkedList containing errors
	 */

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
		} else {
			System.out.println( errors.size() + " errors found.");
		}
		return errors;
	}
}
