package project;

public class ContadorEstocastico {

	//TODO:
	//Add clean ContadorEstocastico (Probability and the counter itself reset to 0)
	
	// Things needed
	private int countElements;
	private double randNumber;
	private double probability;
	private int elementsAdded;

	// Defines the stochastic counter with a probability
	public ContadorEstocastico(double probability) {
		this.probability = probability;
	}

	// This will increment the count
	public void increment() {
		randNumber = Math.random();
		if (randNumber < probability) {
			countElements++;
		}
	}

	// This will return the number of elements
	public void numOfElements() {
		elementsAdded = (int) Math.round(countElements / probability);
		System.out.format("%d elements were added", elementsAdded);
	}
}
