//Program that reads and imports user input

import java.util.Scanner; //Imports class Scanner
import java.io.BufferedReader; //Imports class BufferedReader
import java.io.FileReader; //Imports class FileReader
import java.io.IOException; //Imports class IOException
import java.io.File;
import java.nio.file.Files;//Imports NIO class Files
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class ParseElement {
	public static String[] reader() {
		//method reader() parses the input, decides whether the input is a path
		//for a file or raw text and then appends every line to the Array
		//fileArray, which it returns
		System.out.println("Type your text or the path of the file you would like to be processed:");//prompt user
		Scanner sc = new Scanner(System.in);
		String input1 = sc.nextLine();
		String[] wordArray;

		while (input1.isEmpty()){
			System.out.println("You must type at least one word.");
			input1 = sc.nextLine();
		}
		if (isFilePath(input1)){
			Path file = Paths.get(input1);
			wordArray = new String[10];
			try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
				String sCurrentLine = null;
				while ((sCurrentLine =reader.readLine()) != null) {
					wordArray = sCurrentLine.split(" ");
					for (String word : wordArray) {
						System.out.println(word);
					}
				}
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
			System.out.println("Your file was initialized.");
			return wordArray;
		} else {
			wordArray = new String[10];
			wordArray = input1.split(" ");
			for (String word : wordArray) {
				System.out.println(word);
			}
			System.out.println("Your input was initialized.");
			return wordArray;
		}

	}
	public static boolean isFilePath(String str) {
		File f = null;
		boolean checker = false;
		try{
			f = new File(str);
			checker = f.exists();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return checker;
	}
}
