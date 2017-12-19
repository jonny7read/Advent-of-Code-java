package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.InputTask;

public class Task7 extends InputTask {

	public static void main(String[] args) {
		Task7 task = new Task7();

		ArrayList<String> input = task.getInput("2017Task7Input2.txt");

		List<Program> programs = task.parseInput(input);

		int totalWeight = task.getParent(programs, programs.get(0));

		System.out.println("Root found: " + totalWeight);
	}

	private int getParent(List<Program> programs, Program pet) {
		for (Program program : programs) {
			if (program.hasPet(pet.name)) {
				program.checkPetWeightSame();
				int petWeight = program.getPetWeight();
				return petWeight + getParent(programs, program);
			}
		}
		return 0;
	}

	private List<Program> parseInput(ArrayList<String> input) {
		List<Program> programs = new ArrayList<Program>();

		for (String line : input) {
			String name = line.substring(0, line.indexOf(" "));
			int weight = Integer.parseInt(line.substring(line.indexOf("(") + 1, line.indexOf(")")));

			int petsIndex = line.indexOf("-> ");
			List<String> pets = new ArrayList<String>();
			if (petsIndex > 0) {
				pets = Arrays.asList(line.substring(petsIndex + 3).split(", "));
			}

			Program program = new Program(name, weight, pets);
			programs.add(program);
		}

		for (Program parent : programs) {
			if (parent.petNames.size() == 0) {
				continue;
			}

			for (Program pet : programs) {
				if (parent.hasPet(pet.name)) {
					parent.addPet(pet);
				}
			}
		}

		return programs;
	}

	class Program {
		private String			name;
		private int				weight;
		private List<String>	petNames;
		private List<Program>	pets;

		protected Program(String name, int weight, List<String> petNames) {
			this.name = name;
			this.weight = weight;
			this.petNames = petNames;
			this.pets = new ArrayList<Program>();
		}

		public int getPetWeight() {
			int total = 0;

			for (Program pet : pets) {
				total += pet.weight;
			}

			System.out.println("Pet weight for " + name + " is: " + total);

			return total;
		}

		public void checkPetWeightSame() {
			int petWeight = pets.get(0).weight;

			for (Program pet : pets) {
				if (pet.weight != petWeight) {
					System.out.println(pet.name + " has an incorrect weight of " + pet.weight + " but should be " + petWeight);
					break;
				}
			}
		}

		public void addPet(Program pet) {
			this.pets.add(pet);
		}

		protected boolean hasPet(String name) {
			return petNames.contains(name);
		}

		@Override
		public String toString() {
			return "Name: " + name + ", weight: " + weight + ", pets: " + petNames;
		}
	}

}
