
import java.util.Scanner;
import java.util.HashSet;
import java.util.LinkedList;

public class App {
	private static Scanner sc;
	private static final String DictionaryPath = "C:\\Documents and Settings\\t8130104\\My Documents\\Downloads\\drive-download-20171207T132530Z-001\\greek.txt";
	public static void main(String[] args) {
		System.out.println("Spellchecker by CRxCD- in development");
  		String input1 = " " ;
  		while (input1.compareToIgnoreCase("quit") != 0) {
          	System.out.println("Type 'Quit' in order to quit or type your text or the path of the file you would like to be processed:");//prompt user
  			sc = new Scanner(System.in);
  			input1 = sc.nextLine();
  			while (input1.isEmpty()) {
  				System.out.println("You must type at least one word.");
  				input1 = sc.nextLine();
  			}
  			if (input1.compareToIgnoreCase("quit") != 0) {
				LinkedList<String> uInput = new LinkedList<String>(ParseElement.reader(input1));
				HashSet<String> grDictionary = new HashSet<String>(DictionaryGenerator.readFile(DictionaryPath));
  				Checker.finder(uInput, grDictionary) ;
  			}
      }
    }
}