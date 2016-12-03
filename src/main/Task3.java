package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {

	private static final int	HORIZONAL	= 0;
	private static final int	VERTICAL	= 1;

	public static void main(String[] args) {
		new Task3();
	}

	private Task3() {
		run();
	}

	private void run() {
		ArrayList<int[]> triangles = getInput("Task3Input.txt", this.HORIZONAL);
		int validTriangles = getValidTriangles(triangles);

		System.out.println("Total triangles: " + triangles.size());
		System.out.println("Valid triangles: " + validTriangles);

	}

	private int getValidTriangles(ArrayList<int[]> triangles) {
		int validTriangleCount = 0;
		int first, second, third;

		for (int[] triangle : triangles) {
			first = triangle[0];
			second = triangle[1];
			third = triangle[2];
			if (first + second > third && first + third > second && second + third > first) {
				validTriangleCount++;
			}
		}
		return validTriangleCount;
	}

	private ArrayList<int[]> getInput(String fileName, int direction) {
		ArrayList<int[]> triangles = new ArrayList<int[]>();

		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(fileName);
		File file = new File(url.getFile());
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				int[][] triangleGroup = new int[3][3];
				for (int line = 0; line < triangleGroup.length; line++) {
					for (int col = 0; col < triangleGroup[line].length; col++) {
						triangleGroup[col][line] = scanner.nextInt();
					}
				}

				scanner.nextLine();
				triangles.add(triangleGroup[0]);
				triangles.add(triangleGroup[1]);
				triangles.add(triangleGroup[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			scanner.close();
		}

		return triangles;
	}

}
