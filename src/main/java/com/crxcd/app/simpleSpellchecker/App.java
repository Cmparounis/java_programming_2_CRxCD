package com.crxcd.app.simpleSpellchecker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import util.Checker;
import util.DictionaryGeneratorWeb;
import util.ParseElement;

public class App {
	
	private static final String grDictionaryURL = "https://raw.githubusercontent.com/Cmparounis/java_programming_2_CRxCD/master/src/main/config/greek.txt";
	private static final String enDictionaryURL = "http://ism.dmst.aueb.gr/ismgroup42/web/words_alpha.txt";
	public static void main(String[] args) throws UnsupportedEncodingException {
		String input1 = " " ;
		HashSet<String> dictionary = new HashSet<String>();
		System.out.println("Simple Spellchecker by CRxCD - in development");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please choose your language (EN for English, GR for greek)");
		while (input1.compareToIgnoreCase("EN") != 0 && input1.compareToIgnoreCase("GR") != 0 )
		{
			try {
				input1 = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (input1.compareToIgnoreCase("EN") == 0) {
				System.out.println("Please wait...");
				dictionary = new HashSet<String>(DictionaryGeneratorWeb.readFile(enDictionaryURL));
				System.out.println("CRxCD - Welcome to the english spellchecker - in development");
			} else if (input1.compareToIgnoreCase("GR") == 0) {
				System.out.println("Please wait...");
				dictionary = new HashSet<String>(DictionaryGeneratorWeb.readFile(grDictionaryURL));
				System.out.println("CRxCD- Welcome to the greek spellchecker - in development");
			} else {
				System.out.println("Please enter a valid Value");
			}
		}
		
  		while (input1.compareToIgnoreCase("quit") != 0) {
          	System.out.println("Type your text or the path of the file you would like to be processed:");
          	System.out.println("Type 'Quit' in order to quit.");
          	try {
          		input1 = br.readLine();
          		System.out.println(input1);
          	} catch (Exception e ) {
          		System.out.println(e.getMessage());
          	}
  			while (input1.isEmpty()) {
  				System.out.println("You must type at least one word.");
  				try {
  	          		input1 = br.readLine();
  	          		System.out.println(input1);
  	          	} catch (Exception e ) {
  	          		System.out.println(e.getMessage());
  	          	}
  			}
  			if (input1.compareToIgnoreCase("quit") != 0) {
  				try {
  					final long startTime = System.nanoTime();
  					HashMap<String, String> uInput = new HashMap<String, String>(ParseElement.reader(input1));
  					Checker.finder(uInput, dictionary) ;
  					final long endTime = System.nanoTime();
  					System.out.println("The process took : " + ((endTime - startTime) / 1000000) + "ms to complete.");
  				} catch (FileNotFoundException e) {
  					System.out.println(e.getMessage());
  				}
  			}
  		}
    }
}
