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
		
		input.put("1" , "���������");
		input.put("2" , "�����������");
		input.put("3" , "�����");
		input.put("4" , "machine");
		input.put("5" , "computer");
		input.put("6" , "SmAlL");
		input.put("7" , "samll");
		input.put("8" , "������");
		
		dict.add("���������");
		dict.add("�����������");
		dict.add("������");
		dict.add("machine");
		dict.add("computer");
		dict.add("small");
		
		expected.put("7" , "samll");
		expected.put("8" , "������");
		
		results = Checker.finder(input, dict);
		/*for (String element : results.keySet()) {
			System.out.println(element);
		}*/
		
		assertEquals(expected, results);
	}

}
