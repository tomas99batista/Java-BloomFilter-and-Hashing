package project;

import java.io.*;
import java.util.Scanner;

public class TestEstocastico {
	public static void main(String[] args) throws FileNotFoundException {
		// Tests to the ContadorEstocastico
		System.out.println("ContadorEstocastico TESTS with u.data: \n");
		// Probability added to the ContadorEstocastico
		new ContadorEstocastico(0.5);

		// Using the file u.data provided to the practical exercises
		Scanner input = new Scanner(new File("src/project/u.data"));
		//Scanner input = new Scanner(new File("u.data"));


		// Made this counter to know how many words are on the file to compare to the
		// added to ContadorEstocastico
		int count = 0;
		while (input.hasNext()) {
			input.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
			count = count + 1;
		}
		// Print the number of elements added to the ContadorEstocastico and the total
		// number of elements
		ContadorEstocastico.numOfElements();
		System.out.println("Real Value: " + count);
		input.close();
	}
}
