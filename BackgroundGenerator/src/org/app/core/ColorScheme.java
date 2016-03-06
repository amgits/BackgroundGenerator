package org.app.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class ColorScheme {
	Map<String, String> colorScheme;
	
	public void loadScheme(String filePath) {
		try {
			FileReader fr = new FileReader(new File(filePath));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
