package project;

import java.io.*;
import java.util.*;

public class Passwords {
	public static void main(String[] args) throws IOException {
		// ----------------------------FILES----------------
		File file_passwords = new File("src/project/WorstPasswords_10k.txt");
		File file_twitter = new File("src/project/Twitter_Passwords.txt");
		
		// ---------------CONTADOR ESTOCASTICO----------------------
		Scanner scan_passwords = new Scanner(file_passwords);
		Scanner scan_twitter = new Scanner(file_twitter);
		System.out.println("CONTADOR ESTOCASTICO");
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
		// Print the number of elements added to the ContadorEstocastico and the total
		// number of elements
		ContadorEstocastico.numOfElements();
		System.out.println("----------------------------------");
		
		// --------COUNTING BLOOMFILTER------------------
		System.out.println("COUNTING BLOOM FILTER");
		Scanner scan_passwordsV2 = new Scanner(file_passwords);
		Scanner scan_twitterV2 = new Scanner(file_twitter);
		// Sum of the sizes of: WorstPasswords_10k.txt
		int n_CBF = 10000;
		// Create the CBF with the number of elements of both files and a fpr of 0.01
		CountingBloomFilter.CreateCountingBloomFilter(n_CBF, 0.01);
		while (scan_passwordsV2.hasNext()) {
			String word = scan_passwordsV2.next();
			// Increment the ContadorEstocastico and the count
			CountingBloomFilter.insert(word);
		}
		int[] CBF = CountingBloomFilter.returnArray();
		while (scan_twitterV2.hasNext()) {
			String word = scan_twitterV2.next();
			for (int i = 0; i < CBF.length; i++) {
				CountingBloomFilter.belongs(word);
			}
		}
		CountingBloomFilter.print_Dexist();
		System.out.println("----------------------------------");
		
		// --------------MINHASH----------------------
		System.out.println("MinHash");
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		System.out.println("CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		//CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		System.out.println("----------------------------------");
	}
}
