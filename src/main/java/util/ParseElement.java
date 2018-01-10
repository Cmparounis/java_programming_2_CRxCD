package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import mgmt.FileManagement;

/**
 * Reads user input, determines if it represents a file path or an sentence and
 * then breaks into words using the @link {mgmt.FileManagement#fileParser(Path) fileParser}.
 * 
 * @author Cmparounis
 * @author manosalexiou95
 * @version 0.3-SNAPSHOT
 * @since 0.1
 */
public class ParseElement {
	/**
	 * Checks if a String is a file path, whether the specifies file exists, or a sentence.
	 * If it is a valid path or a sentence, breaks it up into words with the method 
	 * @link {mgmt.FileManagement#fileParser(Path) fileParser}.
	 * 
	 * <p>If the string is a valid file path, the file is broken into a LinkedHashMap of individual words and 
	 * their index. If the string is a sentence, that input is written temporarily in a temporary file 
	 * and then split into words.
	 * <p>If the input is an invalid file path, a @link {java.io.FileNotFoundException#FileNotFoundException(String) 
	 * FileNotFoundException} is thrown, 
	 * and the user is prompted to submit a valid path again.
	 * 
	 * @param input1 User input. Can be a file path or a sentence.
	 * @return A LinkedHashMap containing a String of the position of each word as its keys and the words themselves as values.
	 * @throws FileNotFoundException Exception thrown in case the file is not found.
	 */
	public static LinkedHashMap<String, String> reader(String input1) throws FileNotFoundException {
		if (FileManagement.isFilePath(input1)) {
			if (!FileManagement.fileExists(input1)) {
				throw new FileNotFoundException("Please submit a valid path.");
			}
			Path file = Paths.get(input1);
			return FileManagement.fileParser(file);
		} else {
			Path file = null;
			try {
				File temp = File.createTempFile("config/temp-input", ".txt");
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(temp), 
						StandardCharsets.UTF_8);

				out.write(input1);
				out.close();
				file = Paths.get(temp.getAbsolutePath());
			} catch (IOException e) {
				System.out.println("IOException: " + e.getMessage());
			}
			return FileManagement.fileParser(file);
		}
	}
}
