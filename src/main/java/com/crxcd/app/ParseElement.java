//Program that reads and imports user input
import java.util.Scanner; //Imports class Scanner
import java.io.BufferedReader; //Imports class BufferedReader
import java.io.FileReader; //Imports class FileReader
import java.io.IOException; //Imports class IOException

package com.crxcd.app;

public class ParseElement {
  public static Array<String> reader() {
    String input;
    System.out.println("Type your text or the path of the file you would like
     to be processed:");//prompt user
    Scanner sc = new Scanner(System.in);
    String input = scanner.nextLine();
    if (isFilePath(input) = true){
      try (BufferedReader reader = new Files.newBufferedReader(input, UTF-8 )) {
        String sCurrentLine = null;
        String[] fileArray;
        int i = 0;
        while ((sCurrentLine =reader.readLine()) != null) {
          fileArray[i] = sCurrentLine;
          i++;
        }



			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

      System.out.println("Your file was initialized.");
    } else {
      System.out.println("Your input was initialized.");

    }

  }
  public static boolean isFilePath(String input) {

  }

}
