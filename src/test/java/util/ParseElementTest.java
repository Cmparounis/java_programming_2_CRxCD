package util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import org.junit.Test;

public class ParseElementTest {

	@Test
	public void testReader() throws IOException {
		String test1 = "What a glorious day, don't you think?";
		String test2 = "Τι φανταστική ημέρα, δεν συμφωνείς;";
		LinkedHashMap<String, String> test1Map = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> test2Map = new LinkedHashMap<String, String>();
		
		test1Map.put("Line:1, Level:1", "What");
		test1Map.put("Line:1, Level:2", "a");
		test1Map.put("Line:1, Level:3", "glorious");
		test1Map.put("Line:1, Level:4", "day");
		test1Map.put("Line:1, Level:5", "");
		test1Map.put("Line:1, Level:6", "don");
		test1Map.put("Line:1, Level:7", "t");
		test1Map.put("Line:1, Level:8", "you");
		test1Map.put("Line:1, Level:9", "think");
		
		test2Map.put("Line:1, Level:1", "Τι");
		test2Map.put("Line:1, Level:2", "φανταστική");
		test2Map.put("Line:1, Level:3", "ημέρα");
		test2Map.put("Line:1, Level:4", "");
		test2Map.put("Line:1, Level:5", "δεν");
		test2Map.put("Line:1, Level:6", "συμφωνείς");
		
		assertEquals(test1Map, ParseElement.reader(test1));
		assertEquals(test2Map, ParseElement.reader(test2));
		
		File temp = File.createTempFile("config/temp-input", ".txt");
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(temp), 
				StandardCharsets.UTF_8);
		out.write(test1);
		out.close();
		
		assertEquals(test1Map, ParseElement.reader(temp.getAbsolutePath()));

				
	}

}
