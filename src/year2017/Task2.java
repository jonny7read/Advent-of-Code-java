package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.InputTask;

public class Task2 extends InputTask {

	public int getTotalSum(List<String> lines, int part) {
		int sum = 0;
		for (String line : lines) {
			sum += getSum(line, part);
		}
		return sum;
	}

	/**
	 * Main beef of this task. This gets the sum of all valid digits in the list, given the
	 * appropriate rule.<br>
	 * The rule is dependant on if this method is run for part 1 or part 2 of the challenge.<br>
	 * The part of the challenge is determined by the part parameter.<br>
	 * In part 1, we sum all digits that are the same as the next digit in the line.<br>
	 * In part 2, we sum all digits that are the same as the digit that is exactly halfway round the
	 * list.<br>
	 * For more details, see www.adventofcode.com/2017/day/1
	 * 
	 * @param line
	 *            The String line of numbers to read
	 * @param part
	 *            the part of the challenge, determining the rule to use when checking digits.
	 * @return an int representing the sum of all valid digits in the input line
	 */
	private int getSum(String line, int part) {
		int sum = 0;

		if (line != null && line.length() > 0) {
			int[] nums = Arrays.stream(line.split("\t")).mapToInt(Integer::valueOf).toArray();
			int min = Arrays.stream(nums).min().getAsInt();
			int max = Arrays.stream(nums).max().getAsInt();

			sum += max - min;
		}

		return sum;
	}

	public static void main(String[] args) {
		Task2 task2 = new Task2();

		ArrayList<String> input = task2.getInput("2017Task2Input.txt");

		System.out.println("For Part 1, the sum is: " + task2.getTotalSum(input, 1));

	}

}
