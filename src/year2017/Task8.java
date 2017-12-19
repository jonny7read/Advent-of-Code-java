package year2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import utils.InputTask;

public class Task8 extends InputTask {

	private int maxAtEnd;
	private HashMap<String, Integer> registers;
	private int maxEver;

	public static void main(String[] args) {
		Task8 task = new Task8();
		task.run("2017Task8Input.txt");

		System.out.println("Max value at the end: " + task.getMaxAtEnd());
		System.out.println("Highest value reached: " + task.getMaxEver());
	}

	public void run(String inputFileName) {
		ArrayList<String> input = getInput(inputFileName);

		setRegisters(input);

		executeInstructions(input);

		setMaxValue();
	}

	private void setRegisters(ArrayList<String> input) {
		registers = new HashMap<String, Integer>();

		for (String string : input) {
			String registerName = string.substring(0, string.indexOf(" "));

			if (!registers.containsKey(registerName)) {
				registers.put(registerName, 0);
			}
		}
	}

	private void executeInstructions(List<String> instructions) {
		for (String line : instructions) {
			Scanner scan = new Scanner(line);

			String registerName = scan.next();
			String action = scan.next();
			int step = scan.nextInt();
			String condition = scan.nextLine().trim();

			scan.close();
			scan = new Scanner(condition);

			scan.next(); // if
			String dependancy = scan.next();
			Integer dependancyValue = registers.get(dependancy);

			String operator = scan.next();
			int conditionValue = scan.nextInt();

			scan.close();

			if (isConditionMet(dependancyValue, operator, conditionValue)) {
				int newValue = parseAction(registerName, action, step);

				maxEver = Math.max(maxEver, newValue);

				registers.put(registerName, newValue);
			}
		}
	}

	private int parseAction(String registerName, String action, int step) {
		Integer currValue = registers.get(registerName);
		int newValue = 0;
		if (action.equals("inc")) {
			newValue = currValue + step;
		} else {
			newValue = currValue - step;
		}
		return newValue;
	}

	private boolean isConditionMet(int value, String operator, int conditionValue) {
		switch (operator) {
			case ">":
				return value > conditionValue;
			case "<":
				return value < conditionValue;
			case "==":
				return value == conditionValue;
			case "!=":
				return value != conditionValue;
			case ">=":
				return value >= conditionValue;
			case "<=":
				return value <= conditionValue;
		}
		return false;
	}

	private void setMaxValue() {
		int max = 0;

		for (int value : registers.values()) {
			max = Math.max(max, value);
		}

		this.maxAtEnd = max;
	}

	public int getMaxAtEnd() {
		return maxAtEnd;
	}

	public int getMaxEver() {
		return maxEver;
	}
}
