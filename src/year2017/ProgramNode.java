package year2017;

import java.util.ArrayList;
import java.util.List;

/**
 * This object stores some grouped information about nodes, used in Task 7
 * 
 * @author jonny
 *
 */
class ProgramNode {

	private String name;
	private int weight;
	private List<String> petNames;
	private List<ProgramNode> pets;

	protected ProgramNode(String name, int weight, List<String> petNames) {
		this.name = name;
		this.weight = weight;
		this.petNames = petNames;
		this.pets = new ArrayList<ProgramNode>();
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public List<String> getPetNames() {
		return petNames;
	}

	public List<ProgramNode> getPets() {
		return pets;
	}

	public int getPetWeight() {
		int total = 0;

		for (ProgramNode pet : pets) {
			total += pet.weight;
		}

		System.out.println("Pet weight for " + name + " is: " + total);

		return total;
	}

	public void checkPetWeightSame() {
		int petWeight = pets.get(0).weight;

		for (ProgramNode pet : pets) {
			if (pet.weight != petWeight) {
				System.out.println(pet.name + " has an incorrect weight of " + pet.weight + " but should be " + petWeight);
				break;
			}
		}
	}

	public void addPet(ProgramNode pet) {
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