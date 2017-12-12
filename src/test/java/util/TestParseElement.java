package util;
import static org.junit.Assert.*;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class TestParseElement {
	@Test
	public void test() {			
		
		LinkedList<String> returnWordList = new LinkedList<String>();
		returnWordList.add("Αυτό");
		returnWordList.add("είναι");
		returnWordList.add("ΕΝΑ");
		returnWordList.add("τΈΣΤ");
		returnWordList.add("ολα");
		returnWordList.add("ειναι");
		returnWordList.add("γκούτσι");
		returnWordList.add("γκανγκ");
		returnWordList.add("420");
		
		try {
			assertEquals(returnWordList, ParseElement.reader("Αυτό 'είναι' ΕΝΑ τΈΣΤ, ολα ειναι γκούτσι γκανγκ! 420"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
	}

}
