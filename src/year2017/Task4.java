package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import utils.InputTask;

public class Task4 extends InputTask {

	public static void main(String[] args) {
		Task4 task = new Task4();

		ArrayList<String> lines = task.getInput("2017Task4Input.txt");

		int validPassphraseCount1 = task.getValidPassphraseCount(lines, 1);
		int validPassphraseCount2 = task.getValidPassphraseCount(lines, 2);

		System.out.println("Part 1: " + validPassphraseCount1);
		System.out.println("Part 2: " + validPassphraseCount2);
	}

	private int getValidPassphraseCount(ArrayList<String> lines, int part) {
		int validCount = 0;

		for (String line : lines) {
			List<String> phrases = Arrays.asList(line.split(" "));

			if (!hasDuplicate(phrases, part)) {
				validCount++;
			}
		}

		return validCount;
	}

	private boolean hasDuplicate(List<String> phrases, int part) {
		int check = 0;
		while (check < phrases.size()) {
			for (int i = 0; i < phrases.size(); i++) {
				if (check == i) {
					continue;
				}

				String first = phrases.get(check);
				String second = phrases.get(i);

				if (part == 2) {
					first = Stream.of(first.split("")).sorted().collect(Collectors.joining());
					second = Stream.of(second.split("")).sorted().collect(Collectors.joining());
				}

				if (first.equals(second)) {
					return true;
				}
			}
			check++;
		}
		return false;
	}

}
