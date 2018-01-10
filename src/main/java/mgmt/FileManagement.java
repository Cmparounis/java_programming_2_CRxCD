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

/**
 * Provides a variety of methods for file handling, file reading, and file writing
 * @author Cmparounis, 
 * @author filiamaroutsi
 * @author SpaGeorgios
 * @author katerinakontarini
 * @author manosalexiou95
 * @author SpaGeorgios
 * @author dionisispap98
 * @version 0.3-SNAPSHOT
 * @since 0.2
 */
public class FileManagement {
	
	/**
	 * Stores Byte Order Mark to filter out later.
	 */
	private static String UTF8_BOM = "\uFEFF";
	/**
	 * Regular Expression Pattern of all punctuation compiled to be filtered out later.
	 */
	private static Pattern UNWANTED_SYMBOLS = 
			Pattern.compile("[!@#$%^&*()-=_+|;':\",.<>?'“”’‘—]");
	/**
	 * Regular Expression to determine if a String is a filepath.
	 */
	private static Pattern FILE_PATH = 
			Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)");
	
	/**
	 * Checks if a file exists in a specific path.
	 * @param str A string containing a path.
	 * @return A boolean variable that is set to true if the file exists, and false if not.
	 */
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
	
	/**
	 * Checks if a string is a representative of a file path.
	 * @param str A string that representative of a file path or not.
	 * @return A boolean variable that is set to true if the string is a representative of a file path, and false if not.
	 */
	public static boolean isFilePath(String str) {
		Matcher pathMatcher = FILE_PATH.matcher(str);
		if (pathMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if a string begins with Byte Order Mark.
	 * @param str A string that begins with Byte Order Mark or not.
	 * @return A boolean variable that is set to true if the string begins with Byte Order Mark., and false if not.
	 */
	public static boolean hasBom(String str) {
		return str.startsWith(UTF8_BOM);
	}
	
	/**
	 * Breaks down a text file into lower case words with no punctuation and stores them in a HashMap.
	 * 
	 * <p>This method is used to read with a BufferedReader from - temporary or not - files that contain 
	 * the user's input. The test is broken into words; these are procedurally stored in a HashMap with 
	 * a String "Line: x Level: x" as their key. The method also prints a message to inform the user 
	 * whether his file was processed or not. For, now, the program supports only UTF-8 encoding 
	 * @param file A UTF-8 text file.
	 * @return A HashMap containing a string of the position of each word as its keys and the words themselves as values.
	 */
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
			if(wordMap.size() == 1) {
				System.out.println(wordMap.size() + " word.");
			} else {
				System.out.println(wordMap.size() + " words.");
			}
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
	
	/**
	 * Breaks down a text file into words and stores them in a HashSet, which represents 
	 * the dictionary -  a collection of most of the words of the respective language.
	 * 
	 * <p>This method is used for the storage of dictionaries, which are later searched
	 * and compared with Strings to determine if they are correct or not. This method is 
	 * extremely dependent on the format chosen; the text file contains one String per line.
	 * @param fileName The path of the file containing the dictionary.
	 * @return A HashSet which contains every word from the dictionary.
	 */
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
	
	/**
	 * Breaks down a text file from the web into words and stores them in a HashSet, which represents 
	 * the dictionary -  a collection of most of the words of the respective language.
	 * 
	 * <p>This method is used for the storage of dictionaries, which are later searched
	 * and compared with Strings to determine if they are correct or not. This method is 
	 * extremely dependent on the format chosen; the text file contains one String per line.
	 * This method is used for bigger dictionaries (language dictionaries) stored in the web.
	 * @param location The URL of the file containing the dictionary.
	 * @return A HashSet which contains every word from the dictionary.
	 */
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

