package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

public class DictionaryGeneratorWeb {
	public static HashSet<String> readFile(String location) {
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
