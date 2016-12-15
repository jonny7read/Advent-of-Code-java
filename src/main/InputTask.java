package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class InputTask {

	protected ArrayList<String> getInput(String fileName) {
		return getInput(fileName, 0);
	}

	protected ArrayList<String> getInput(String fileName, int sampleSize) {
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(fileName);
		File file = new File(url.getFile());
		Scanner scanner = null;
		ArrayList<String> lines = new ArrayList<String>();

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

		while ((sampleSize == 0 || lines.size() < sampleSize) && scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}

		return lines;

	}
}
