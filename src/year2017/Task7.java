package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.InputTask;

public class Task7 extends InputTask {

	public static void main(String[] args) {
		Task7 task = new Task7();

		ArrayList<String> input = task.getInput("2017Task7Input2.txt");

		List<Task7ProgramNode> programs = task.parseInput(input);

		int totalWeight = task.getParent(programs, programs.get(0));

		System.out.println("Root found: " + totalWeight);
	}

	private int getParent(List<Task7ProgramNode> programs, Task7ProgramNode pet) {
		for (Task7ProgramNode program : programs) {
			if (program.hasPet(pet.getName())) {
				program.checkPetWeightSame();
				int petWeight = program.getPetWeight();
				return petWeight + getParent(programs, program);
			}
		}
		return 0;
	}

	private List<Task7ProgramNode> parseInput(ArrayList<String> input) {
		List<Task7ProgramNode> programs = new ArrayList<Task7ProgramNode>();

		for (String line : input) {
			String name = line.substring(0, line.indexOf(" "));
			int weight = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(")")));

			int petsIndex = line.indexOf("-> ");
			List<String> pets = new ArrayList<String>();
			if (petsIndex > 0) {
				pets = Arrays.asList(line.substring(petsIndex + 3).split(", "));
			}

			Task7ProgramNode program = new Task7ProgramNode(name, weight, pets);
			programs.add(program);
		}

		for (Task7ProgramNode parent : programs) {
			if (parent.getPetNames().size() == 0) {
				continue;
			}

			for (Task7ProgramNode pet : programs) {
				if (parent.hasPet(pet.getName())) {
					parent.addPet(pet);
				}
			}
		}

		return programs;
	}

}
