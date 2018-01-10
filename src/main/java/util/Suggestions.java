package util;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * Creates multiple suggestions for the correction of a word
 * 
 * <p> This class contains a multiplicity of methods in order to create all
 * Levenshtein distance 1 Strings of a word. The goal is, whenever a word is 
 * found incorrect, to create a number of suggestions for corrections.
 * 
 * <p>The process of producing Levenhstein Distance 1 Strings essentially is 
 * as follows: 
 * - Add a letter to the front or the back of the word and compare it with the available 
 * dictionary.
 * - Remove a letter from the word and compare it with the available dictionary.
 * - Add a letter to the word and compare it with the available  dictionary.
 * - Replace a letter from the word and compare it with the available dictionary.
 * - Swap two adjacent letters from the word and compare it with the available dictionary.
 * 
 * @author Cmparounis
 * @version 0.3-SNAPSHOT
 * @since 0.2
 */
public class Suggestions {
	
	/**
	 * Saves all the characters of the English alphabet.
	 */
	private static char[] alphabetEn = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	/**
	 * Saves all the characters of the Greek alphabet.
	 */
	private static char[] alphabetGr = "áâãäåæçèéêëìíîïðñóòôõö÷øùÜÝÞßüýþúûàÀ".toCharArray();
	
	/**
	 * Creates all distance-1 suggestions for a String in the English language.
	 * 
	 * <p> Adds in a common collection all collections produced by @link {Suggestions#addedLetterEn(String, HashSet) 
	 * addedLetterEn}, @link {util.Suggestions#removedLetter(String, HashSet) 
	 * removedLetter}, @link {Suggestions#replaceLetterEn(String, HashSet) 
	 * replaceLetterEn}, @link {util.Suggestions#swapLetter(String, HashSet) swapLetter}.
	 *  
	 * <p> Due to the usage of different Dictionaries and Alphabets, it was determined that two 
	 * separate methods should be created for each language respectively. Although quality of code 
	 * is severely impacted - it's not exactly an elegant solution - this is used for both readability 
	 * and ease of use. 
	 * 
	 * @param word The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all English words.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	public static LinkedList<String> createSuggestionsEn(String word, 
			HashSet<String> dictionary) {
		LinkedList<String> suggestions = new LinkedList<String>();
		suggestions.addAll(addedLetterEn(word, dictionary));
		suggestions.addAll(replaceLetterEn(word, dictionary));
		suggestions.addAll(swapLetter(word, dictionary));
		suggestions.addAll(removedLetter(word, dictionary));
		return suggestions;
	}
	
	/**
	 * Creates all distance-1 suggestions for a String in the Greek language.
	 * 
	 * <p> Adds in a common collection all collections produced by @link {Suggestions#addedLetterGr(String, HashSet<String>) 
	 * addedLetterGr}, @link {util.Suggestions#removedLetter(String, HashSet)
	 * removedLetter}, @link {Suggestions#replaceLetterGr(String, HashSet) 
	 * replaceLetterGr}, @link {util.Suggestions#swapLetter(String, HashSet) swapLetter}.
	 *  
	 * <p> Due to the usage of different Dictionaries and Alphabets, it was determined that two 
	 * separate methods should be created for each language respectively. Although quality of code 
	 * is severely impacted - it's not exactly an elegant solution - this is used for both readability 
	 * and ease of use. 
	 * 
	 * @param word The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all Greek words.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	public static LinkedList<String> createSuggestionsGr(String word, 
			HashSet<String> dictionary) {
		LinkedList<String> suggestions = new LinkedList<String>();
		suggestions.addAll(addedLetterGr(word, dictionary));
		suggestions.addAll(replaceLetterGr(word, dictionary));
		suggestions.addAll(swapLetter(word, dictionary));
		suggestions.addAll(removedLetter(word, dictionary));
		return suggestions;
	}
	
	/**
	 * Creates correction suggestions by adding a letter to the start of an English word 
	 * or to its end.
	 * 
	 * @param input The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all English words.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	private static LinkedList<String> addedLetterEn(String input, HashSet<String> dictionary) { 
        LinkedList<String> suggest = new LinkedList<>();
        for (char c : alphabetEn) {
            String firstAdd = c + input;
            String lastAdd = input + c;
            if (dictionary.contains(firstAdd)) {
            	suggest.add(firstAdd);
            }
            if (dictionary.contains(lastAdd)) {
            	suggest.add(lastAdd);
            }
        }
        return suggest;
    }
	
	/**
	 * Creates correction suggestions by adding a letter to the start of an Greek word 
	 * or to its end.
	 * 
	 * @param input The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all Greek words.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	private static LinkedList<String> addedLetterGr(String input, HashSet<String> dictionary) { 
        LinkedList<String> suggest = new LinkedList<>();
        for (char c : alphabetGr) {
            String firstAdd = c + input;
            String lastAdd = input + c;
            if (dictionary.contains(firstAdd)) {
            	suggest.add(firstAdd);
            }
            if (dictionary.contains(lastAdd)) {
            	suggest.add(lastAdd);
            }
        }
        return suggest;
    }
	
	/**
	 * Creates correction suggestions by removing a letter from a word 
	 * 
	 * @param input The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all words of a language.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	
	private static LinkedList<String> removedLetter(String input, HashSet<String> dictionary) { 
        LinkedList<String> suggest = new LinkedList<>();
        int length = input.length();
        String firstremove = input.substring(1, length);
        String lastremove = input.substring(0, length - 1);
        if (dictionary.contains(firstremove)) {
    	   suggest.add(firstremove);
        }
        if (dictionary.contains(lastremove)) {
     	   suggest.add(lastremove);
        }
        for (int i = 1; i < length; i++) {
        	String charRemove = input.substring(0, i).concat(input.substring(i + 1, length));
        	if (dictionary.contains(charRemove)) {
          	   suggest.add(charRemove);
             }
        }	
        return suggest;
    }
	
	/**
	 * Creates correction suggestions by replacing a letter from a Greek word with another one.
	 * 
	 * @param input The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all Greek words.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	private static LinkedList<String> replaceLetterGr(String input, HashSet<String> dictionary) { 
        LinkedList<String> suggest = new LinkedList<>();
        int length = input.length();
        for (char c : alphabetGr) {
            String firstReplace = c + input.substring(1, length);
            String lastReplace = input.substring(0, length-1) + c;
            if (dictionary.contains(firstReplace)) {
            	suggest.add(firstReplace);
            }
            if (dictionary.contains(lastReplace)) {
            	suggest.add(lastReplace);
            }
            for (int i = 1; i < length-1; i++) {
            	String charReplace = input.substring(0, i) + c + input.substring(i+1, length);
            	if( dictionary.contains(charReplace)){
              	   suggest.add(charReplace);
                 }
            }	
            
        }
        return suggest;
    }
	
	/**
	 * Creates correction suggestions by replacing a letter from an English word with another one.
	 * 
	 * @param input The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all English words.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	private static LinkedList<String> replaceLetterEn(String input, HashSet<String> dictionary) { 
        LinkedList<String> suggest = new LinkedList<>();
        int length = input.length();
        for (char c : alphabetEn) {
            String firstReplace = c + input.substring(1, length);
            String lastReplace = input.substring(0, length-1) + c;
            if (dictionary.contains(firstReplace)) {
            	suggest.add(firstReplace);
            }
            if (dictionary.contains(lastReplace)) {
            	suggest.add(lastReplace);
            }
            for (int i = 1; i < length-1; i++) {
            	String charReplace = input.substring(0, i) + c + input.substring(i+1, length);
            	if( dictionary.contains(charReplace)){
              	   suggest.add(charReplace);
                 }
            }	
            
        }
        return suggest;
    }
	/**
	 * Creates correction suggestions by swapping a letter of a word with an adjacent one.
	 * 
	 * @param input The String to be corrected.
	 * @param dictionary The HashSet containing the dictionary - a collection of all words of a language.
	 * @return LinkedList A collection of Strings. Available suggestions for correction.
	 */
	private static LinkedList<String> swapLetter(String input, HashSet<String> dictionary) { 
		LinkedList<String> suggest = new LinkedList<>();

        for (int i = 0; i < input.length() - 1; i++) {
            String working = input.substring(0, i);
            working += input.charAt(i + 1);  
            working += input.charAt(i); 
            working += input.substring((i + 2));
            if (dictionary.contains(working)) {
                suggest.add(working);
            }
        }
        return suggest;
	}
}
