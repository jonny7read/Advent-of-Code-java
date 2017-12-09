package year2017;

import java.util.ArrayList;

import utils.InputTask;

public class Task1 extends InputTask {

	public Task1() {
		ArrayList<String> input = getInput("2017Task1Input.txt");

		if (input.size() != 1) {
			System.out.println("Input was not a single line of input");
		}

		int sum1 = getSum(input.get(0), 1);
		int sum2 = getSum(input.get(0), 2);

		System.out.println("For Part 1, the sum is: " + sum1);
		System.out.println("For Part 2, the sum is: " + sum2);
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
	 * @param input
	 *            The String line of numbers to read
	 * @param part
	 *            the part of the challenge, determining the rule to use when checking digits.
	 * @return an int representing the sum of all valid digits in the input line
	 */
	private int getSum(String input, int part) {
		int sum = 0;

		if (input != null && input.length() > 0) {

			for (int i = 0; i < input.length(); i++) {
				int next = getNextIndex(i, input.length(), part);

				char num = input.charAt(i);

				if (num == input.charAt(next)) {
					sum += Character.getNumericValue(num);
				}
			}
		}

		return sum;
	}

	private int getNextIndex(int i, int length, int part) {
		int next = 0;

		if (part == 1) {
			if (i == length - 1) {
				next = 0;
			} else {
				next = i + 1;
			}
		} else if (part == 2) {
			next = (i + (length / 2)) % length;
		} else {
			System.out.println("Unknown Part: " + part);
			System.exit(0);
		}

		return next;
	}

	public static void main(String[] args) {
		new Task1();
	}

}
