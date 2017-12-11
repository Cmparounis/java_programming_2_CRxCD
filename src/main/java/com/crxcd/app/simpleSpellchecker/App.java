package com.CRxCD.app.simpleSpellchecker;

import java.util.Scanner;

import util.ParseElement;

public class App {
	private static Scanner sc;

	public static void main(String[] args) {
		System.out.println("Spellchecker by CRxCD- in development");
		System.out.println("Contributors: @manosalexiou @ ....");
  		String input1 = " " ;
  		while (input1.compareToIgnoreCase("quit") != 0) {
          	System.out.println("Type 'Quit' in order to quit or type your text or the path of the file you would like to be processed:");//prompt user
  			sc = new Scanner(System.in);
  			input1 = sc.nextLine();
  			while (input1.isEmpty()) {
  				System.out.println("You must type at least one word.");
  				input1 = sc.nextLine();
  			}
  			if (input1.compareToIgnoreCase("quit") != 0) {
  				ParseElement.reader(input1);
  			}
      }
    }
}
