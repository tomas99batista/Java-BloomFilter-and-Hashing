package project;

import java.io.*;
import java.util.Scanner;

public class TestEstocastico {
	public static void main(String[] args) throws FileNotFoundException {
		// Tests to the ContadorEstocastico

		// Probability added to the ContadorEstocastico
		new ContadorEstocastico(0.5);

		// Using the file u.data provided to the practical exercises
		Scanner input = new Scanner(new File("u.data"));
		//Scanner input = new Scanner(new File("u.data"));


		// Made this counter to know how many words are on the file to compare to the
		// added to ContadorEstocastico
		int count = 0;
		while (input.hasNext()) {
			String word = input.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
			count = count + 1;
		}
		// Print the number of elements added to the ContadorEstocastico and the total
		// number of elements
		ContadorEstocastico.numOfElements();
		System.out.println("Word count: " + count);
	}
}
