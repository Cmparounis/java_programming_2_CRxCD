package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Provides a variety of methods for the handling of a user dictionary (or, even better user glossary).
 * 
 * <p> The following methods manage the usage of a user dictionary -  a collection of words
 * that the user has specified to no longer show up as incorrect in his input. These wirds are
 * stored in a file, stored in a predetermined path. This path is compatible with both windows 
 * and POSIX file systems. Through the method @link {java.io#getAbsolutePath() getAbsolutePath()} true 
 * portability is ensured.
 * 
 * @author Cmparounis
 * @version 0.3-SNAPSHOT
 * @since 0.2
 */
public class UserDictionary {
	/**
	 * The specified path of the created user dictionary.
	 */
	private static final String userDictionaryPath = "/SimpleSpellChecker/User/udct.txt";
	/**
	 * The specified directory of the created user dictionary.
	 */
	private static final String userDictionaryDirectory = "/SimpleSpellChecker/User";
	/**
	 * Stores the file object.
	 */
	private static File file = new File(userDictionaryPath);
	
	/**
	 * Writes a specified word in the dictionary path.
	 * 
	 * <p> This method uses a @link {Java.io.FileOutputStream#FileOutputStream(File, boolean)} with 
	 * the constructor that specifies that the input will be appended on the existing 
	 * file. This is wrapped in a @link {java.io#OutputStreamWriter.OutputStreamWriter(OutputStream, Charset) OutputStreamWriter}
	 * in a @link {java.io#BufferedWriter.BufferedWriter(Writer out) BufferedWriter}. The BufferedWriter uses the 
	 * constructor which allows it to specify a character set as the second argument.
	 * 
	 * @param word The String that will be written in the file
	 */
	public static void write(String word) {
		try (BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),
			StandardCharsets.UTF_8));
				PrintWriter out = new PrintWriter(bwriter)) {
			    out.println(word);
			} catch (IOException e) {
			    System.out.println(e.getMessage());
			}
	}
	/**
	 * Returns the absolute path of the user dictionary.
	 * @return String The path of the user dictionary.
	 */
	public static String getThisPath() {
		return file.getAbsolutePath();
	}
	/**
	 * Creates the directory of the user dictionary and the file itself. 
	 * Also, prints a blank line.
	 */
	public static void create() {
		if (!file.exists()) {
			try {
				Files.createDirectories(Paths.get(userDictionaryDirectory));
				file.createNewFile();
				FileWriter writer = new FileWriter(userDictionaryPath, true);
			    BufferedWriter bwriter = new BufferedWriter(writer);
			    PrintWriter out = new PrintWriter(bwriter);
			    out.println(" ");
			    out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	/**
	 * Checks if the user dictionary file exists
	 * @return A boolean variable that is set to true if the file exists, and false if not. 
	 */
	public static boolean exists() {
		return file.exists();
		
	}
}
