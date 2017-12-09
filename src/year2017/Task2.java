package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

import utils.InputTask;

public class Task2 extends InputTask {

	public int getTotalSum(List<String> lines, int part) {
		int sum = 0;
		for (String line : lines) {
			sum += getNumForLine(line, part);
		}
		return sum;
	}

	/**
	 * Main beef of this task. This gets the sum of all valid digits in the list, given the
	 * appropriate rule.<br>
	 * The rule is dependent on if this method is run for part 1 or part 2 of the challenge.<br>
	 * The part of the challenge is determined by the part parameter.<br>
	 * In part 1, we get the difference between the max and min digits on the line.<br>
	 * In part 2, we get the result of the division between the only two digits that can be evenly
	 * divided with no remainder.<br>
	 * For more details, see www.adventofcode.com/2017/day/1
	 * 
	 * @param line
	 *            The String line of numbers to read
	 * @param part
	 *            the part of the challenge, determining the rule to use when checking digits.
	 * @return an int representing the sum of all valid digits in the input line
	 */
	private int getNumForLine(String line, int part) {
		int num = 0;

		if (line != null && line.length() > 0) {
			int[] nums = Arrays.stream(line.split("\t")).mapToInt(Integer::valueOf).toArray();

			if (part == 1) {
				int min = Arrays.stream(nums).min().getAsInt();
				int max = Arrays.stream(nums).max().getAsInt();

				num = max - min;
			} else {
				for (int i : nums) {
					OptionalInt divisor = Arrays.stream(nums).filter(n -> n != i && i % n == 0).findFirst();

					if (divisor.isPresent()) {
						num = i / divisor.getAsInt();
						break;
					}
				}
			}
		}

		return num;
	}

	public static void main(String[] args) {
		Task2 task2 = new Task2();

		ArrayList<String> input = task2.getInput("2017Task2Input.txt");

		System.out.println("For Part 1, the sum is: " + task2.getTotalSum(input, 1));
		System.out.println("For Part 2, the sum is: " + task2.getTotalSum(input, 2));
	}

}
