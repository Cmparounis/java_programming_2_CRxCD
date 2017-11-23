//Program that reads and imports user input
import java.util.LinkedList;
import java.io.BufferedReader; //Imports class BufferedReader
import java.io.FileReader; //Imports class FileReader
import java.io.IOException; //Imports class IOException
import java.io.File;
import java.nio.file.Files;//Imports NIO class Files
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class ParseElement {
	public static LinkedList<String> reader(String input1) {
		String[] wordArray;
		LinkedList<String> wordList = new LinkedList<String>();
		if (isFilePath(input1)){
			Path file = Paths.get(input1);
			wordArray = new String[10];
			try {
				BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
				String sCurrentLine = null;
				while ((sCurrentLine =reader.readLine()) != null) {
					wordArray = sCurrentLine.split(" ");
					for (int i = 0; i < wordArray.length; i++) {
						wordList.add(wordArray[i]);
					}
				}
				for (int z = 0; z < wordList.size(); z++) {
					System.out.println(wordList.get(z));
					}
				System.out.println(wordList.size());
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
			System.out.println("Your file was initialized.");
			return wordList;
		} else {
			wordArray = new String[10];
			wordArray = input1.split(" ");
			for (int i = 0; i < wordArray.length; i++) {
				wordList.add(wordArray[i]);
			}
			for (int z = 0; z < wordList.size(); z++) {
				System.out.println(wordList.get(z));
			}
			System.out.println("Your input was initialized.");
			return wordList;
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
