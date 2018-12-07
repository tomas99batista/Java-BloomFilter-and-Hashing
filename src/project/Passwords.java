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

		//--------------------------------------------FILES--------------------------------------------

		File file_passwords = new File("src/project/WorstPasswords_10k.txt");
		Scanner scan_passwords = new Scanner(file_passwords);
		File file_twitter = new File("src/project/Twitter_Passwords.txt");
		Scanner scan_twitter = new Scanner(file_twitter);

		//------------------------------------CONTADOR ESTOCASTICO--------------------------------------
		
		// Probability added to the ContadorEstocastico
		new ContadorEstocastico(0.5);

		while (scan_passwords.hasNext()) {
			String word = scan_passwords.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
		}
		while (scan_twitter.hasNext()) {
			String word = scan_twitter.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
		}
		// Print the number of elements added to the ContadorEstocastico and the total number of elements
		ContadorEstocastico.numOfElements();

		//------------------------------------COUNTING BLOOMFILTER--------------------------------------
		
		long size = file_passwords.length() + file_twitter.length();
		System.out.println(size);
		//public CountingBloomFilter(int n, double fpr)
		
	}

}
