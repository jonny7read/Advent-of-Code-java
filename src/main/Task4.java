package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Task4 extends InputTask {

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

		finishPart1(rooms);
		finishPart2(rooms);
	}

	private void finishPart2(ArrayList<String> rooms) {
		HashMap<String, String> realNames = new HashMap<String, String>();
		for (String room : rooms) {
			int dash = room.lastIndexOf('-');
			int bracket = room.indexOf('[');
			int roomId = Integer.parseInt(room.substring(dash + 1, bracket));
			String roomName = room.substring(0, room.lastIndexOf('-'));

			char[] roomNameArray = roomName.toCharArray();
			int shift = roomId % 26;

			for (int i = 0; i < roomNameArray.length; i++) {
				if (roomNameArray[i] == '-') {
					roomNameArray[i] = ' ';
				} else {
					roomNameArray[i] += shift;
					if (roomNameArray[i] > 'z') {
						roomNameArray[i] -= 26;
					}
				}
			}
			realNames.put(String.valueOf(roomNameArray), room);
		}

		// search the names!
		for (String name : realNames.keySet()) {
			if (name.contains("north")) {
				System.out.println(name);
				System.out.println(realNames.get(name));
			}
		}
	}

	private void finishPart1(ArrayList<String> rooms) {

		int total = getTotalFromIds(rooms);

		System.out.println("Rooms: " + rooms.size());
		System.out.println("Total of Ids: " + total);
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
}
