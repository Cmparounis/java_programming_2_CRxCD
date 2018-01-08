package util;

import java.util.HashSet;
import java.util.LinkedList;

public class Suggestions {
	final static char[] alphabetEn = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	final static char[] alphabetGr = "áâãäåæçèéêëìíîïðñóôõö÷øùÜÝÞßüýþúûàÀ".toCharArray();
	
	public static LinkedList<String> createSuggestionsEn(String word, HashSet<String> dictionary){
		LinkedList<String> suggestions = new LinkedList<String>();
		suggestions.addAll(addedLetterEn(word, dictionary));
		suggestions.addAll(replaceLetterEn(word, dictionary));
		suggestions.addAll(swapLetter(word, dictionary));
		suggestions.addAll(removedLetter(word, dictionary));
		return suggestions;
	}
	
	public static LinkedList<String> createSuggestionsGr(String word, HashSet<String> dictionary){
		LinkedList<String> suggestions = new LinkedList<String>();
		suggestions.addAll(addedLetterGr(word, dictionary));
		suggestions.addAll(replaceLetterGr(word, dictionary));
		suggestions.addAll(swapLetter(word, dictionary));
		suggestions.addAll(removedLetter(word, dictionary));
		return suggestions;
	}
	
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
	
	private static LinkedList<String> removedLetter(String input, HashSet<String> dictionary) { 
        LinkedList<String> suggest = new LinkedList<>();
        int length = input.length();
        String firstremove = input.substring(1, length);
        String lastremove = input.substring(0, length-1);
        if( dictionary.contains(firstremove)){
    	   suggest.add(firstremove);
        }
        if( dictionary.contains(lastremove)){
     	   suggest.add(lastremove);
        }
        for (int i = 1; i < length; i++) {
        	String charRemove = input.substring(0, i).concat(input.substring(i+1, length));
        	if( dictionary.contains(charRemove)){
          	   suggest.add(charRemove);
             }
        }	
        return suggest;
    }
	
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
