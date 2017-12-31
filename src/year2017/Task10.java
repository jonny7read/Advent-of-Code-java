package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
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
		StringJoiner output = new StringJoiner("\n");
		ArrayList<String> input = getInput("2017Task10Input.txt");
		String line = input.get(0);

		// part 1
		int[] lengths = parseInputToLengths(line, 1);
		List<Integer> list = IntStream.rangeClosed(0, 255).boxed().collect(Collectors.toList());

		list = doRound(lengths, list, 1);
		List<Integer> subList = list.subList(0, 2);
		output.add("### PART " + 1 + " ###");
		output.add("first two numbers: " + subList);
		output.add("product: " + list.get(0) * list.get(1));

		// part 2
		lengths = parseInputToLengths(line, 2);
		list = IntStream.rangeClosed(0, 255).boxed().collect(Collectors.toList());

		list = doRound(lengths, list, 64);
		List<Integer> denseHash = getDenseHash(list);
		String knotHash = getKnotHash(denseHash);

		output.add("### Part 2 ###");
		output.add("Sparse Hash: " + list);
		output.add("Dense Hash: " + denseHash);
		output.add("Knot Hash: " + knotHash);

		System.out.println(output);
	}

	private String getKnotHash(List<Integer> denseHash) {
		String knotHash = "";

		for (Integer num : denseHash) {
			String hex = Integer.toHexString(num);
			if (hex.length() < 2) {
				hex = "0" + hex;
			}
			knotHash += hex;
		}

		return knotHash;
	}

	private List<Integer> getDenseHash(List<Integer> sparseHash) {
		List<Integer> denseHash = new ArrayList<Integer>(16);

		for (int i = 0; i < 16; i++) {
			int start = i * 16;
			List<Integer> block = sparseHash.subList(start, start + 16);

			int blockNumber = block.stream().reduce((a, b) -> (a ^ b)).get();
			denseHash.add(blockNumber);
		}

		return denseHash;
	}

	private List<Integer> doRound(int[] lengths, List<Integer> list, int iterations) {
		int skip = 0;
		int position = 0;

		for (int i = 0; i < iterations; i++) {
			for (int length : lengths) {
				// printList(list, position, length);

				reverseNumbers(list, position, length);

				position = increaseAndWrap(position, length + skip++, list.size());
			}
		}
		return list;
	}

	private int[] parseInputToLengths(String line, int part) {
		String[] rawLengths;
		int[] lengths;

		if (part == 1) {
			rawLengths = line.split(",");
			lengths = Arrays.stream(rawLengths).mapToInt(Integer::parseInt).toArray();
		} else {
			int chars = line.length();
			lengths = new int[chars + 5];
			byte[] bytes = line.getBytes();
			for (int i = 0; i < bytes.length; i++) {
				lengths[i] = bytes[i];
			}
			int[] suffix = new int[] { 17, 31, 73, 47, 23 };
			for (int i = 0; i < suffix.length; i++) {
				lengths[bytes.length + i] = suffix[i];
			}
		}
		return lengths;
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
