package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.InputTask;

public class Task10 extends InputTask {

	public static void main(String[] args) {
		Task10 task = new Task10();
		try {
			task.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() throws Exception {
		ArrayList<String> input = getInput("2017Task10Input.txt");
		String line = input.get(0);

		String[] rawLengths = line.split(",");
		int[] lengths = Arrays.stream(rawLengths).mapToInt(Integer::parseInt).toArray();

		int skip = 0;
		int position = 0;

		List<Integer> list = IntStream.rangeClosed(0, 255).boxed().collect(Collectors.toList());
		// List<Integer> list = IntStream.rangeClosed(0, 4).boxed().collect(Collectors.toList());

		for (int length : lengths) {
			printList(list, position, length);

			reverseNumbers(list, position, length);

			position = increaseAndWrap(position, length + skip++, list.size());
		}
		System.out.println(list);
		List<Integer> subList = list.subList(0, 2);
		System.out.println("first two numbers: " + subList);
		System.out.println("product: " + list.get(0) * list.get(1));
	}

	private void printList(List<Integer> list, int position, int length) {
		int end = increaseAndWrap(position, length, list.size());
		String output = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == position) {
				output += "(";
			} else {
				output += " ";
			}
			output += list.get(i);
			if (i == end - 1) {
				output += ")";
			} else {
				output += " ";
			}

			if (i < list.size()) output += " ";
		}

		System.out.println(output);

	}

	/**
	 * Returns num plus increase. If the result is greater than the max, then return the modulus.
	 * <br>
	 * e.g. if parameters are (1,2,4), 1 + 2 = 3, 3 !> 4, return 3.<br>
	 * however, if parameters are (3,4,5), 3 + 4 = 7, 7 > 5, return 7 % 5 = 2.
	 * 
	 * @param num
	 *            initial number
	 * @param increase
	 *            amount to increase
	 * @param max
	 *            maximum boundary (exclusive)
	 * @return sum of num and increase, or modulus of max if result is greater than max
	 */
	public int increaseAndWrap(int num, int increase, int max) {
		num = num + increase;

		if (num >= max) {
			num = num % max;
		}

		return num;
	}

	public void reverseNumbers(List<Integer> list, int position, int length) {
		if (length < 2) {
			return;
		}

		List<Integer> sublist = new ArrayList<Integer>();

		// grab the numbers from position to position+length
		for (int j = 0; j < length; j++) {
			int curr = increaseAndWrap(position, j, list.size());
			sublist.add(list.get(curr));
		}
		// overwrite them in reverse order
		for (int j = 0; j < length; j++) {
			int curr = increaseAndWrap(position, j, list.size());
			list.set(curr, sublist.get(length - j - 1));
		}
	}

}
