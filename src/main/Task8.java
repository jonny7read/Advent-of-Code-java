package main;

import java.awt.Point;
import java.util.ArrayList;

public class Task8 extends InputTask {

	private static final int	PIXEL_WIDTH		= 50;
	private static final int	PIXEL_HEIGHT	= 6;
	private boolean[][]			mPixels			= new boolean[PIXEL_WIDTH][PIXEL_HEIGHT];

	public static void main(String[] args) {
		new Task8();
	}

	public Task8() {
		ArrayList<String> input = getInput("Task8Input.txt", 2);
		processInput(input);
		int pixelsLeftOn = getNumberOfPixelsOn();

		System.out.println(pixelsLeftOn);
	}

	private int getNumberOfPixelsOn() {
		int count = 0;
		for (int x = 0; x < PIXEL_WIDTH; x++) {
			for (int y = 0; y < PIXEL_HEIGHT; y++) {
				if (mPixels[x][y]) {
					count++;
				}
			}
		}
		return count;
	}

	private void processInput(ArrayList<String> input) {
		System.out.println("Inital State:");
		printState();
		for (String command : input) {
			System.out.println(command);
			processCommand(command);
			printState();
		}
	}

	private void printState() {
		for (int y = 0; y < PIXEL_HEIGHT; y++) {
			for (int x = 0; x < PIXEL_WIDTH; x++) {
				System.out.print(mPixels[x][y] ? '#' : '.');
			}
			System.out.println();
		}
	}

	private void processCommand(String command) {
		if (command.startsWith("rect")) {
			int indexOfX = command.indexOf('x');
			int rectX = Integer.parseInt(command.substring(indexOfX - 1, indexOfX));
			int rectY = Integer.parseInt(command.substring(indexOfX + 1));

			fillRect(new Point(rectX, rectY));
		} else if (command.startsWith("rotate")) {
			String orientation = command.substring(7, 10); // row or col
			int lastSpace = command.lastIndexOf(' ');
			int steps = Integer.parseInt(command.substring(lastSpace + 1));
			int indexOfEquals = command.indexOf('=');
			int rowOrCol = Integer.parseInt(command.substring(indexOfEquals + 1, indexOfEquals + 2));

			if ("row".equals(orientation)) {
				shiftRow(rowOrCol, steps);
			} else {
				shiftCol(rowOrCol, steps);
			}
		} else {
			System.out.println("invalid action in command: " + command);
		}
	}

	private void fillRect(Point point) {
		for (int x = 0; x < point.x; x++) {
			for (int y = 0; y < point.y; y++) {
				mPixels[x][y] = true;
			}
		}
	}

	private void shiftRow(int row, int steps) {
		for (int i = 0; i < steps; i++) {
			mPixels[PIXEL_WIDTH - 1][row] = mPixels[0][row];
			for (int y = 1; y < PIXEL_WIDTH - 1; y++) {
				mPixels[y + 1][row] = mPixels[y][row];
				printState();
				System.out.println();
			}
		}
	}

	private void shiftCol(int col, int steps) {
		for (int i = 0; i < steps; i++) {
			mPixels[col][PIXEL_HEIGHT - 1] = mPixels[col][0];
			for (int x = 1; x < PIXEL_HEIGHT - 1; x++) {
				mPixels[col][x + 1] = mPixels[col][x];
			}
		}
	}
}
