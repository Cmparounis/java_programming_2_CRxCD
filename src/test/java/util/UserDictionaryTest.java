package util;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.Test;

import util.UserDictionary;
/**
 * Unit test for class UserDictionary
 * 
 * @author MakisFnt
 *
 */
public class UserDictionaryTest {

	@Test
	public void testWrite() {
		String str1 = "thot";
		String str2 = "θοτ";
		UserDictionary.write(str1);
		UserDictionary.write(str2);
		Path file = Paths.get("/SimpleSpellChecker/User/udct.txt");
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
		if (!dictionary.contains(str1) && !dictionary.contains(str2) ) {
			fail("");
		}
	}

	@Test
	public void testCreate() {
		UserDictionary.create();
		boolean exists;
		
		File file = new File("/SimpleSpellChecker/User/udct.txt");
		exists= file.exists();
		assertEquals(exists, UserDictionary.exists());
		
	}

	@Test
	public void testExists() throws IOException {
		boolean exists;
		
		File temp = File.createTempFile("config/temp-input", ".txt");
		exists= temp.exists();
		assertEquals(exists, UserDictionary.exists());
	}

}
