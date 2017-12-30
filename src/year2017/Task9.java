package year2017;

import utils.InputTask;

public class Task9 extends InputTask {

	public static void main(String[] args) {
		Task9 task = new Task9();
		task.run("2017Task9Input.txt");
	}

	public void run(String inputFilename) {
		String input = getInput(inputFilename).get(0);

		getScore(input);
	}

	public int getScore(String input) {
		System.out.println(input);

		char[] chars = input.toCharArray();

		boolean garbage = false;
		int score = 0;
		int depth = 1;
		int garbageCount = 0;

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			if (c == '!') i++;
			else if (garbage && c != '>') garbageCount++;
			else if (c == '<') garbage = true;
			else if (c == '>') garbage = false;
			else if (c == '{') score += depth++;
			else if (c == '}') depth--;
		}

		System.out.println("score: " + score + ", garbageCount: " + garbageCount);
		return score;
	}
}
