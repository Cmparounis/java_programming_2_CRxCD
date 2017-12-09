package util;

import java.util.*;

public class Error1 {

	public static void finder(ArrayList<String> text, String dictionary[]) {

		boolean match = false;
		int total = 0;

		// Έλεγχος αν η λίστα είναι άδεια
		if(text != null && !text.isEmpty()) {

			// Εύρεση του μεγέθους της λίστας
			int sizeList = text.size();

			// Μετατροπή όλων των γραμμάτων σε πεζά
			text.replaceAll(String::toLowerCase);

			// Σύγκριση των αντικειμένων της λίστας με τα αντικείμενα του λεξικού
			for (int i = 0; i <= sizeList; i++) {
				String word = text.get(i);

				for (int j = 0; j <= dictionary.length; j++) {

					if (word.equals(dictionary[j])) {
						match = true;
						break;
					}
				}

				if (match == false) {
					System.out.printf("Η λέξη %s δεν υπάρχει στο λεξικό, text[i]");
				}
			}
		}
	}
}