package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Task6 {

	public static void main(String[] args) {
		new Task6();
	}

	public Task6() {
		ArrayList<String> input = getInput("Task6Input.txt");
		int[][] counts = getLetterCounts(input);
		String partA = getLetters(counts, 'a');
		String partB = getLetters(counts, 'b');
		System.out.println("Part A: " + partA);
		System.out.println("Part B: " + partB);
	}

	private String getLetters(int[][] counts, char part) {
		String letters = "";
		for (int i = 0; i < 8; i++) {
			int max = counts[i][0];
			int min = max;
			char maxChar = 0;
			char minChar = 0;

			for (int j = 0; j < 26; j++) {
				if (counts[i][j] > max) {
					max = counts[i][j];
					maxChar = (char) (j + 97);
				}
				if (counts[i][j] < min) {
					min = counts[i][j];
					minChar = (char) (j + 97);
				}
			}
			if (part == 'a') {
				letters += maxChar;
			} else {
				letters += minChar;
			}
		}
		return letters;
	}

	private int[][] getLetterCounts(ArrayList<String> input) {
		int[][] letters = new int[8][26];
		for (String line : input) {
			for (int i = 0; i < line.length(); i++) {
				int c = line.charAt(i) - 97;
				letters[i][c]++;
			}
		}
		return letters;
	}

	private ArrayList<String> getInput(String fileName) {
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

		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}

		return lines;
	}

}
