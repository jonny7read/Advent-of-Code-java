package main;

import java.awt.Point;
import java.util.ArrayList;

public class Task1 {

	private static int hqLocation = 0;

	public static void main(String[] args) {
		String example1 = "R2, L3";
		String example2 = "R2, R2, R2";
		String example3 = "R5, L5, R5, R3";
		String answer = "L3, R2, L5, R1, L1, L2, L2, R1, R5, R1, L1, L2, R2, R4, L4, L3, L3, R5, L1, R3, L5, L2, R4, L5, R4, R2, L2, L1, R1, L3, L3, R2, R1, L4, L1, L1, R4, R5, R1, L2, L1, R188, R4, L3, R54, L4, R4, R74, R2, L4, R185, R1, R3, R5, L2, L3, R1, L1, L3, R3, R2, L3, L4, R1, L3, L5, L2, R2, L1, R2, R1, L4, R5, R4, L5, L5, L4, R5, R4, L5, L3, R4, R1, L5, L4, L3, R5, L5, L2, L4, R4, R4, R2, L1, L3, L2, R5, R4, L5, R1, R2, R5, L2, R4, R5, L2, L3, R3, L4, R3, L2, R1, R4, L5, R1, L5, L3, R4, L2, L2, L5, L5, R5, R2, L5, R1, L3, L2, L2, R3, L3, L4, R2, R3, L1, R2, L5, L3, R4, L4, R4, R3, L3, R1, L3, R5, L5, R1, R5, R3, L1";
		String example4 = "R8, R4, R4, R8";

		System.out.println("Example 1: " + getMoves(example1));
		System.out.println("Example 2: " + getMoves(example2));
		System.out.println("Example 3: " + getMoves(example3));
		System.out.println("Main: " + getMoves(answer) + ", HQ Location: " + hqLocation);
		System.out.println("Main: " + getMoves(example4) + ", HQ Location: " + hqLocation);
	}

	public static int getMoves(String input) {
		String[] moves = input.split(", ");
		ArrayList<Point> points = new ArrayList<Point>();
		Point point = new Point(0, 0);
		String dir = "N";
		int dist = 0;
		hqLocation = 0;

		for (String move : moves) {
			dir = getDir(dir, move.substring(0, 1));
			dist = Integer.parseInt(move.substring(1));
			for (int i = 0; i < dist; i++) {
				point = makeMove(dir, point);
				if (hqLocation == 0 && checkPosition(points, point)) {
					hqLocation = Math.abs(point.x) + Math.abs(point.y);
				}
			}

		}
		return Math.abs(point.x) + Math.abs(point.y);
	}

	private static boolean checkPosition(ArrayList<Point> points, Point point) {
		if (points.contains(point)) {
			return true;
		} else {
			points.add(point);
			return false;
		}

	}

	private static String getDir(String currentDir, String newDir) {
		if (!currentDir.equals(newDir)) {
			if (currentDir.equals("N")) {
				if (newDir.equals("L")) {
					return "W";
				} else return "E";
			} else if (currentDir.equals("E")) {
				if (newDir.equals("L")) {
					return "N";
				} else return "S";
			} else if (currentDir.equals("S")) {
				if (newDir.equals("L")) {
					return "E";
				} else return "W";
			} else if (currentDir.equals("W")) {
				if (newDir.equals("L")) {
					return "S";
				} else return "N";
			}
		}
		return null;
	}

	private static Point makeMove(String dir, Point p) {
		Point newPoint = new Point(p);

		switch (dir) {
			case "N":
				newPoint.y++;
				break;
			case "E":
				newPoint.x++;
				break;
			case "S":
				newPoint.y--;
				break;
			case "W":
				newPoint.x--;
				break;
		}
		;
		return newPoint;
	}

}
