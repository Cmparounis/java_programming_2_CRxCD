package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

/**
 * <h1> Initialization of Greek Dictionary </h1>
 *
 * <p> This class </p>
 *
 * @author filiamaroutsi
 * @author katerinakontarini
 * @version 0.2-INSTANCE
 * @since 0.1-INSTANCE
 */

public class DictionaryGenerator{

	/**
	 * This method creates a HashSet of Greek Dictionary, read from a local txt file.
	 *
	 * @param fileName the path where the <i>param file</i> is located.
	 * @exception FileNotFoundException is thrown for ivalid filename paths
	 * @exception IOException on input error
	 * @see FileNotFoundException
	 * @see IOException
	 * @return dictionary a hash set that contains all the greek words defined in the <i>param file</i>
	 */

	public static HashSet<String> readFile(String fileName) {
		Path file = Paths.get(fileName);
		HashSet<String> dictionary = new HashSet<>();
		try {
			BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8);
			String sCurrentLine = null;
			while ((sCurrentLine =reader.readLine()) != null) {
				dictionary.add(sCurrentLine);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.getMessage();
		}
		return dictionary;
	}
}
















