package project;

public class ContadorEstocastico {

	//TODO:
	//Add clean ContadorEstocastico (Probability and the counter itself reset to 0)
	
	// Things needed
	private static int countElements;
	private static double randNumber;
	private static double probability;
	private static int elementsAdded;

	// Defines the stochastic counter with a probability
	public ContadorEstocastico(double probability) {
		this.probability = probability;
	}

	// This will increment the count
	public static void increment() {
		randNumber = Math.random();
		if (randNumber < probability) {
			countElements++;		
		}
	}

	// This will return the number of elements
	public static void numOfElements() {
		elementsAdded = (int) Math.round(countElements / probability);
		System.out.format("%d elements estimated by the Contador Estocastico\n", elementsAdded);
	}
}
