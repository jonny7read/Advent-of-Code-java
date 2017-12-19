package year2017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.InputTask;

public class Task7 extends InputTask {

	public static void main(String[] args) {
		Task7 task = new Task7();

		ArrayList<String> input = task.getInput("2017Task7Input.txt");

		List<Program> programs = task.parseInput(input);

		Program rootProgram = task.getParent(programs, programs.get(0));

		if (rootProgram == null) {
			System.out.println("no root found - error");
		} else {
			System.out.println("Root found: " + rootProgram.name);
		}
	}

	private Program getParent(List<Program> programs, Program pet) {
		for (Program program : programs) {
			if (program.hasPet(pet.name)) {
				return getParent(programs, program);
			}
		}
		return pet;
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

		public void addPet(Program pet) {
			this.pets.add(pet);
		}

		protected boolean hasPet(String name) {
			return petNames.contains(name);
		}

		@Override
		public String toString() {
			return "Name: " + name + ", weight: " + weight + ", pets: " + pets;
		}
	}

}
