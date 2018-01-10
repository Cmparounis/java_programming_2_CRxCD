package util;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.HashSet;

import org.junit.Test;
/**
 * Unit test for class UserDictionary
 * 
 * @author MakisFnt
 *
 */
public class CheckerTest {

	@Test
	public void testFinder() {
		LinkedHashMap<String, String> input = new LinkedHashMap<String, String>();
		HashSet<String> dict = new HashSet<String>();
		LinkedHashMap<String, String> expected = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
		
		input.put("1" , "πλυντήριο");
		input.put("2" , "υπολογιστής");
		input.put("3" , "ΜιΚρΌς");
		input.put("4" , "machine");
		input.put("5" , "computer");
		input.put("6" , "SmAlL");
		input.put("7" , "samll");
		input.put("8" , "Μιρκός");
		
		dict.add("πλυντήριο");
		dict.add("υπολογιστής");
		dict.add("μικρός");
		dict.add("machine");
		dict.add("computer");
		dict.add("small");
		
		expected.put("7" , "samll");
		expected.put("8" , "Μιρκός");
		
		results = Checker.finder(input, dict);
		/*for (String element : results.keySet()) {
			System.out.println(element);
		}*/
		
		assertEquals(expected, results);
	}

}
