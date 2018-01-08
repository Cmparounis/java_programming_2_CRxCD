package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseElement {
	private static final Pattern UNWANTED_SYMBOLS = Pattern.compile("\\p{Punct}");
	private static final Pattern FILE_PATH = Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)");
	public static HashMap<String, String> reader(String input1) throws FileNotFoundException {
		if (isFilePath(input1)){
			if (!fileExists(input1)) {
				throw new FileNotFoundException("Please submit a valid path.");
			}
			Path file = Paths.get(input1);
			return fileParser(file);
		} else {
			Path file = null;
			try {
				File temp = File.createTempFile("config/temp-input", ".txt");
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8);

				out.write(input1);
				out.close();
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
	public static HashMap<String, String> fileParser(Path file) {
		boolean success = true;
		int line= 0;
		int level= 0;
		HashMap<String, String> wordMap = new LinkedHashMap<String, String>();
		String[] wordArray = new String[100];
		try {
			BufferedReader reader = Files.newBufferedReader(file , StandardCharsets.UTF_8);
			String sCurrentLine = null;
			while ((sCurrentLine =reader.readLine()) != null) {
				line++;
				level = 0;
				Matcher unwantedMatcher = UNWANTED_SYMBOLS.matcher(sCurrentLine);
				sCurrentLine = unwantedMatcher.replaceAll(" ");
				wordArray = sCurrentLine.split(" ");
				for (int i = 0; i < wordArray.length; i++) {
					level++;
					wordMap.put("Line:" + line +", Level:" + level, wordArray[i]);
				}
			}
			for (String element : wordMap.keySet()) {
				System.out.println(element + " " + wordMap.get(element));
				}
			System.out.println(wordMap.size());
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
		return wordMap;
	}
}
