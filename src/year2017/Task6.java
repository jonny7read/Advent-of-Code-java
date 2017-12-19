package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utils.InputTask;

public class Task6 extends InputTask {

	public static void main(String[] args) {
		Task6 task = new Task6();

		ArrayList<String> input = task.getInput("2017Task6Input.txt");

		List<Integer> banks = task.parseInput(input);

		task.printCycleCount(Arrays.asList(0, 2, 7, 0));
		task.printCycleCount(banks);
	}

	private void printCycleCount(List<Integer> banks) {
		banks = new ArrayList<Integer>(banks);
		List<String> seen = new ArrayList<String>();
		int cycles = 0;

		while (true) {
			int maxIndex = getMaxIndex(banks);
			String redistributeBlock = redistributeBlock(banks, maxIndex);
			cycles++;

			if (seen.contains(redistributeBlock)) {
				System.out.println("Cycles: " + cycles);

				int loopSize = 0;
				for (int i = 0; i < seen.size(); i++) {
					String string = seen.get(i);
					if (string.equals(redistributeBlock)) {
						loopSize = cycles - (i + 1);
						break;
					}
				}

				System.out.println("Loop size: " + loopSize);
				break;
			} else {
				seen.add(redistributeBlock);
			}
		}
	}

	private int getMaxIndex(List<Integer> banks) {
		int maxValue = 0;
		int maxIndex = 0;
		for (int index = 0; index < banks.size(); index++) {
			Integer value = banks.get(index);
			if (value > maxValue) {
				maxValue = value;
				maxIndex = index;
			}
		}
		return maxIndex;
	}

	private String redistributeBlock(List<Integer> banks, int maxIndex) {
		int maxValue = banks.get(maxIndex);
		int currIndex = (maxIndex + 1) % banks.size();

		// reset the max position
		banks.set(maxIndex, 0);

		// distribute accordingly
		while (maxValue > 0) {
			Integer currentValue = banks.get(currIndex);

			banks.set(currIndex, currentValue + 1);

			currIndex = (currIndex + 1) % banks.size();

			maxValue--;
		}
		String listState = banks.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(","));
		// System.out.println(listState);
		return listState;
	}

	private List<Integer> parseInput(ArrayList<String> input) {
		String[] numsAsStrings = input.get(0).split("\t");
		List<Integer> banks = Arrays.stream(numsAsStrings).map(s -> Integer.valueOf(s)).collect(Collectors.toList());
		return banks;
	}

}
