package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


import mgmt.FileManagement;

public class ParseElement {
	public static HashMap<String, String> reader(String input1) throws FileNotFoundException {
		if (FileManagement.isFilePath(input1)){
			if (!FileManagement.fileExists(input1)) {
				throw new FileNotFoundException("Please submit a valid path.");
			}
			Path file = Paths.get(input1);
			return FileManagement.fileParser(file);
		} else {
			Path file = null;
			try {
				File temp = File.createTempFile("config/temp-input", ".txt");
				OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(temp), StandardCharsets.UTF_8);

				out.write(input1);
				out.close();
				file = Paths.get(temp.getAbsolutePath());
			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
			return FileManagement.fileParser(file);
		}
	}
}
