import static org.junit.Assert.*;
//import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.HashSet;

import org.junit.Test;

public class TestChecker {

	@Test
	public void test() {
		LinkedList<String> testList = new LinkedList<String>();
		HashSet<String> testHash = new HashSet<String>();
		LinkedList<String> resultsList = new LinkedList<String>();
		testList.add("����������");
		testList.add("�������");
		testList.add("����");
		testList.add("����������");
		testHash.add("����������");
		testHash.add("�������");
		testHash.add("����");
		testHash.add("�����������");		
		resultsList.add("����������");	
		
		assertEquals(resultsList, Checker.finder(testList, testHash));
		
	}

}
