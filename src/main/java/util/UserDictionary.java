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

public class UserDictionary {
	private static final String userDictionaryPath = "/SimpleSpellChecker/User/udct.txt";
	private static final String userDictionaryDirectory = "/SimpleSpellChecker/User";
	private static File file = new File(userDictionaryPath);
	
	public static void write(String word) {
		try (BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),
			StandardCharsets.UTF_8));
				PrintWriter out = new PrintWriter(bwriter)) {
			    out.println(word);
			} catch (IOException e) {
			    System.out.println(e.getMessage());
			}
	}
	
	public static String getThisPath() {
		return file.getAbsolutePath();
	}
	
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
	
	public static boolean exists() {
		return file.exists();
		
	}
}
