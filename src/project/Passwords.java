package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Passwords {

	public Passwords() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		Scanner file_passwords = new Scanner(new File("src/project/WorstPasswords_10k.txt"));
		Scanner file_twitter = new Scanner(new File("src/project/Twitter_Passwords.txt"));

		//------------------------------------CONTADOR ESTOCASTICO--------------------------------------
		
		// Probability added to the ContadorEstocastico
		new ContadorEstocastico(0.5);

		while (file_passwords.hasNext()) {
			String word = file_passwords.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
		}
		while (file_twitter.hasNext()) {
			String word = file_twitter.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
		}
		// Print the number of elements added to the ContadorEstocastico and the total
		// number of elements
		ContadorEstocastico.numOfElements();

		
		//------------------------------------COUNTING BLOOMFILTER--------------------------------------
		
				
		
	}

}
