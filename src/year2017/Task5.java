package year2017;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import utils.InputTask;

public class Task5 extends InputTask {

	public static void main(String[] args) {
		Task5 task = new Task5();

		// get input
		ArrayList<String> input = task.getInput("2017Task5Input.txt");

		// map String lines to integers
		List<Integer> list = input.stream().map(n -> Integer.parseInt(n)).collect(Collectors.toList());

		int totalSteps = task.findSteps(list, 1);
		System.out.println("Part 1: " + totalSteps);

		totalSteps = task.findSteps(list, 2);
		System.out.println("Part 2: " + totalSteps);

	}

	private int findSteps(List<Integer> input, int part) {
		List<Integer> list = new ArrayList<>(input);
		int totalSteps = 0;
		int current = 0;
		int step = 0;
		while (current < list.size() && current > -1) {
			// get the value at the current position
			step = list.get(current);

			// if (part == 2) {
			// System.out.println(summary(current, list));
			// }

			// increment current position value
			if (part == 2 && step >= 3) {
				list.set(current, step - 1);
			} else {
				list.set(current, step + 1);
			}

			// move pointer to next step
			current = current + step;
			totalSteps++;
		}
		return totalSteps;
	}

	private String summary(int current, List<Integer> collect) {
		String list = "";
		for (int i = 0; i < collect.size(); i++) {
			if (i == current) {
				list += " (" + collect.get(i) + ") ";
			} else {
				list += " " + collect.get(i) + " ";
			}
		}
		return list;
	}

}
