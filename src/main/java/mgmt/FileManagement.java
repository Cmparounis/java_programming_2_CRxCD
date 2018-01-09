package mgmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManagement {
	private static String UTF8_BOM = "\uFEFF";
	private static Pattern UNWANTED_SYMBOLS = 
			Pattern.compile("[!@#$%^&*()-=_+|;':\",.<>?'“”’‘—]");
	private static Pattern FILE_PATH = 
			Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)");
	
	public static boolean fileExists(String str) {
		File f = null;
		boolean checker = false;
		try {
			f = new File(str);
			checker = f.exists();
		} catch (Exception e) {
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
	
	public static boolean hasBom(String str) {
		return str.startsWith(UTF8_BOM);
	}
	
	public static HashMap<String, String> fileParser(Path file) {
		boolean success = true;
		int line = 0;
		int level = 0;
		HashMap<String, String> wordMap = new LinkedHashMap<String, String>();
		String[] wordArray = new String[100];
		try {
			BufferedReader reader = Files.newBufferedReader(file , 
					StandardCharsets.UTF_8);
			String sCurrentLine = null;
			while ((sCurrentLine =reader.readLine()) != null) {
				line++;
				level = 0;
				Matcher unwantedMatcher = UNWANTED_SYMBOLS.matcher(sCurrentLine);
				sCurrentLine = unwantedMatcher.replaceAll(" ");
				wordArray = sCurrentLine.split(" ");
				for (int i = 0; i < wordArray.length; i++) {
					level++;
					String word = wordArray[i];
					if (line == 1 && hasBom(word)) {
						wordMap.put("Line:" + line +", Level:" + level, word.substring(1));
					} else {
						wordMap.put("Line:" + line +", Level:" + level, word);
					}
				}
			}
			
			System.out.println(wordMap.size() + "words");
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
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
	
	public static HashSet<String> readDictionary(String fileName) {
		Path file = Paths.get(fileName);
		HashSet<String> dictionary = new HashSet<>();
		try {
			BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
			String currentLine = null;
			while ((currentLine =reader.readLine()) != null) {
				dictionary.add(currentLine);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
		}
		return dictionary;
	}
	
	public static HashSet<String> readDictionaryWeb(String location) {
		HashSet<String> dictionary = new HashSet<>();
		try {
			URL url = new URL(location);
			URLConnection con = url.openConnection();
		
			BufferedReader reader = new BufferedReader( new InputStreamReader(con.getInputStream(), "UTF-8"));
			String sCurrentLine = null;
			while ((sCurrentLine = reader.readLine()) != null) {
				dictionary.add(sCurrentLine);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
		}
		return dictionary;
	}

}

