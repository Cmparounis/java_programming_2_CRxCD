import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;

public class HashSetDemo{
	

	public static HashSet<String> readFile(String fileName) {
		File file = new File(fileName);
		HashSet<String> studentSet = new HashSet<>();
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNextLine()){
				String line = inputStream.nextLine();
				studentSet.add(line);
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return studentSet;
	}
	
	public static void main (String[] args) {
		HashSet<String> set = readFile("/Users/steliosmaroutsis/Desktop/greek.txt");
		Iterator<String> it =  set.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		