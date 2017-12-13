package util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import org.junit.Test;

public class TestDictionaryGenerator {

	@Test
	public void test() {
		String fileName = "output-text.txt";
		try (PrintWriter out =  new PrintWriter(fileName, "UTF-8")) {
			out.println("Αυτό");
			out.println("είναι");
			out.println("ΕΝΑ");
			out.println("τΈΣΤ");
			out.println("ολα");
			out.println("ειναι");
			out.println("γκούτσι");
			out.println("γκανγκ");
			out.println("420");
			out.close();

		} catch(IOException e) {

		}
		HashSet<String> testDictionary = new HashSet<>();
		testDictionary.add("Αυτό");
		testDictionary.add("είναι");
		testDictionary.add("ΕΝΑ");
		testDictionary.add("τΈΣΤ");
		testDictionary.add("ολα");
		testDictionary.add("ειναι");
		testDictionary.add("γκούτσι");
		testDictionary.add("γκανγκ");
		testDictionary.add("420");

		assertEquals(testDictionary, DictionaryGenerator.readFile(fileName));
	}

}