package year2017;

import java.util.ArrayList;

import utils.InputTask;

public class Task1 extends InputTask {

	public Task1() {
		ArrayList<String> input = getInput("2017Task1Input.txt");

		int sum;
		if (input.size() != 1) {
			sum = 0;
		} else {
			sum = getSum(input.get(0));
		}

		System.out.println(sum);
	}

	private int getSum(String input) {
		int sum = 0;

		if (input != null && input.length() > 0) {

			for (int i = 0; i < input.length(); i++) {
				int next = i + 1;
				if (i == input.length() - 1) {
					next = 0;
				}

				char num = input.charAt(i);

				System.out.print(num + "," + input.charAt(next) + " - ");
				if (num == input.charAt(next)) {
					sum += Character.getNumericValue(num);
					System.out.println("match, new sum = " + sum);
				} else {
					System.out.println("no match");
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		new Task1();
	}

}
