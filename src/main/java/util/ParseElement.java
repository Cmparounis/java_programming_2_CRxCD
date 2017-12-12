package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseElement {
	private static final Pattern UNWANTED_SYMBOLS = Pattern.compile("\\p{Punct}");
	private static final Pattern FILE_PATH = Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)");
	public static LinkedList<String> reader(String input1) throws FileNotFoundException {
		if (isFilePath(input1)){
			if (!fileExists(input1)) {
				throw new FileNotFoundException("Please submit a valid path.");
			}
			Path file = Paths.get(input1);
			return fileParser(file);
		} else {
			Path file = null;
			try {
				File temp = File.createTempFile("temp-input", ".tmp");
				BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
				writer.write(input1);
				writer.close();
				file = Paths.get(temp.getAbsolutePath());
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
			return fileParser(file);
		}
	}
	public static boolean fileExists(String str) {
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
	public static boolean isFilePath(String str) {
		Matcher pathMatcher = FILE_PATH.matcher(str);
		if (pathMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	public static LinkedList<String> fileParser(Path file) {
		boolean success = true;
		String[] wordArray = new String[100];
		LinkedList<String> wordList = new LinkedList<String>();
		try {
			BufferedReader reader = Files.newBufferedReader(file , StandardCharsets.UTF_8);
			String sCurrentLine = null;
			while ((sCurrentLine =reader.readLine()) != null) {
				Matcher unwantedMatcher = UNWANTED_SYMBOLS.matcher(sCurrentLine);
				sCurrentLine = unwantedMatcher.replaceAll("");
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
			success = false;
		} finally {
			if (!success) {
				System.out.println("Your input was not initialized.");
			} else {
				System.out.println("Your input was initialized.");
			}
		}
		return wordList;
	}
}
