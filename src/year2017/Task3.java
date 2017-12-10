package year2017;

public class Task3 {

	public static void main(String[] args) {
		Task3 task = new Task3();

		System.out.println(task.getSteps(361527));
	}

	public int getSteps(int num) {
		if (num == 1) {
			return 0;
		}

		int steps = 1;

		for (int numbersOnRingSide = 3;; numbersOnRingSide += 2) {
			int maxOnRing = (int) Math.pow(numbersOnRingSide, 2);
			int stepsBetweenCorners = numbersOnRingSide - 1;

			if (num > maxOnRing) {
				steps++;
			} else {
				for (int side = 0; side < 4; side++) {
					int maxOnSide = maxOnRing - (stepsBetweenCorners * side);
					int minOnSide = maxOnSide - stepsBetweenCorners;
					int middleOnSide = minOnSide + (stepsBetweenCorners / 2);

					if (num >= minOnSide && num <= maxOnSide && num != middleOnSide) {
						int stepsToMiddleOfSide = Math.abs(num - middleOnSide);
						steps += stepsToMiddleOfSide;
					}
				}

				break;
			}
		}

		return steps;
	}

}
