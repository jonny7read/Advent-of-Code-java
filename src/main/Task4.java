package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task4 {

	public static void main(String[] args) {
		new Task4();
	}

	public Task4() {
		// test();
		run();
	}

	private void test() {
		char[] charArray = "aaaaaabbbbzyx".toCharArray();
		Arrays.sort(charArray);
		System.out.println(charArray);

		ArrayList<String> lines = getExamples();

		for (String line : lines) {
			System.out.println(line + " - " + (isRoom(line) ? "valid" : "invalid"));
		}
	}

	private ArrayList<String> getExamples() {
		ArrayList<String> examples = new ArrayList<String>();

		examples.add("aaaaa-bbb-z-y-x-123[abxyz]");
		examples.add("a-b-c-d-e-f-g-h-987[abcde]");
		examples.add("not-a-real-room-404[oarel]");
		examples.add("totally-real-room-200[decoy]");

		return examples;
	}

	private void run() {
		ArrayList<String> lines = getInput("Task4Input.txt");
		ArrayList<String> rooms = processLines(lines);

		int total = getTotalFromIds(rooms);

		System.out.println(rooms.size());
		System.out.println("Total: " + total);
	}

	private int getTotalFromIds(ArrayList<String> rooms) {
		int total = 0;
		for (String room : rooms) {
			int dash = room.lastIndexOf('-');
			int bracket = room.indexOf('[');
			int roomId = Integer.parseInt(room.substring(dash + 1, bracket));

			total += roomId;
		}
		return total;
	}

	private ArrayList<String> processLines(ArrayList<String> lines) {
		ArrayList<String> rooms = new ArrayList<String>();

		for (String line : lines) {
			if (isRoom(line)) {
				rooms.add(line);
			}

		}
		return rooms;
	}

	private boolean isRoom(String line) {

		int dash = line.lastIndexOf('-');
		int bracket = line.indexOf('[');
		String roomName = line.substring(0, dash).replace("-", "");
		String checkSum = line.substring(bracket + 1, line.length() - 1);

		String lettersLeft = roomName;
		int correct = 0;
		char[] checkSumArray = checkSum.toCharArray();
		for (int i = 0; i < checkSumArray.length; i++) {
			char check = checkSumArray[i];
			// if the character isn't in the checksum, it can't be right
			if (!roomName.contains(String.valueOf(check))) {
				break;
			}

			int max = 0;
			char top = 0;

			char[] lettersLeftArray = lettersLeft.toCharArray();
			Arrays.sort(lettersLeftArray);

			for (int j = 0; j < lettersLeftArray.length; j++) {
				char letter = lettersLeftArray[j];
				int num = line.length() - line.replace(String.valueOf(letter), "").length();
				if (num > max) {
					top = letter;
					max = num;
				}
			}
			if (top != check) {
				break;
			} else {
				correct++;
				lettersLeft = lettersLeft.replace(String.valueOf(check), "");
			}
			if (correct == 5) {
				return true;
			}
		}
		return false;
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
