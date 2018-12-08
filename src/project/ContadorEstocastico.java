package project;

public class ContadorEstocastico {
	// Things needed
	private static int countElements; // Number of elements
	private static double randNumber; // Random number
	private static double probability; // Probability
	private static int elementsAdded; // Number of elements added

	// Defines the stochastic counter with a probability
	public ContadorEstocastico(double probability) {
		ContadorEstocastico.probability = probability;
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

	// Set all the variables to 0
	public static void cleanContadorEstocastico() {
		ContadorEstocastico.probability = 0;
		ContadorEstocastico.elementsAdded = 0;
		ContadorEstocastico.countElements = 0;
	}
}
