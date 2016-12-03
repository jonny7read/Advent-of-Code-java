package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {

	public static void main(String[] args) {
		new Task3();
	}

	private Task3() {
		run();
	}

	private void run() {
		ArrayList<int[]> triangles = getInput("Task3Input.txt");
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

	private ArrayList<int[]> getInput(String fileName) {
		ArrayList<int[]> triangles = new ArrayList<int[]>();

		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(fileName);
		File file = new File(url.getFile());
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				int[] dimensions = new int[3];
				for (int i = 0; i < dimensions.length; i++) {
					dimensions[i] = scanner.nextInt();
				}
				scanner.nextLine();
				triangles.add(dimensions);
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
