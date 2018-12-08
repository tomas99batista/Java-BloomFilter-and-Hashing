package project;

import java.io.*;
import java.util.*;

public class Passwords {
	// CREDITS: https://github.com/danielmiessler/SecLists
	// --------------------MAIN------------------
	public static void main(String[] args) throws IOException {
		// Calls the menu function, this calls the 3 options
		menu();
	}

	// -------------------MENU-------------------
	public static void menu() throws IOException {
		int n;
		do {
			System.out.println("1: Contador Estocastico");
			System.out.println("	Estimates the number of passwords in the file \"Twitter_Passwords.txt\"\n");
			System.out.println("2: Counting BloomFilter");
			System.out.println("	Loads all the passwords to the CountingBloomFilter and compare wich ones");
			System.out.println("	are on the file \"WorstPasswords_10k\" but are not on the list of the 370 \n"
					+ "	passwords banned by Twitter\n");
			System.out.println("3: MinHash");
			System.out.println("	TODOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO\n");
			System.out.println("0: Exit");
			@SuppressWarnings("resource")
			Scanner reader = new Scanner(System.in); // Reading from System.in
			System.out.print("Opção? ");
			n = reader.nextInt();
			System.out.print("\n");
			switch (n) {
			case 1:
				option1();
				break;
			case 2:
				option2();
				break;
			case 3:
				option3();
				break;
			case 0:
				System.out.println("Exiting.... See you later!");
				break;
			default:
				System.out.println("NOT A VALID OPTION!");
				break;
			}
		} while (n != 0);
	}

	// ---------------CONTADOR ESTOCASTICO----------------
	public static void option1() throws IOException {
		System.out.println("------------------------------------------------");
		// FILES
		File file_passwords = new File("src/project/MostCommon10kPasswords.txt");
		File file_twitter = new File("src/project/Twitter_Passwords.txt");
		Scanner scan_passwords = new Scanner(file_passwords);
		Scanner scan_twitter = new Scanner(file_twitter);
		System.out.println("CONTADOR ESTOCASTICO\n");
		// Probability added to the ContadorEstocastico
		new ContadorEstocastico(0.5);
		while (scan_passwords.hasNext()) {
			scan_passwords.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
		}
		while (scan_twitter.hasNext()) {
			scan_twitter.next();
			// Increment the ContadorEstocastico and the count
			ContadorEstocastico.increment();
		}
		// Print the number of elements added to the ContadorEstocastico and the total
		// number of elements
		ContadorEstocastico.numOfElements();
		System.out.println("------------------------------------------------\n");
		scan_passwords.close();
		scan_twitter.close();
	}

	// ------------COUNTING BLOOMFILTER----------------
	public static void option2() throws IOException {
		System.out.println("------------------------------------------------");
		System.out.println("COUNTING BLOOM FILTER");
		System.out.println("Calculating...........\n");
		// FILES
		File file_passwords = new File("src/project/MostCommon10kPasswords.txt");
		File file_twitter = new File("src/project/Twitter_Passwords.txt");
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
		// To know the size
		int[] CBF = CountingBloomFilter.returnArray();
		// For every word on the Twitter banned passwords iterate over the CBF to know
		// if belongs to the 10k most common passwords
		while (scan_twitterV2.hasNext()) {
			String word = scan_twitterV2.next();
			for (int i = 0; i < CBF.length; i++) {
				CountingBloomFilter.belongs(word);
			}
		}
		// Print those who are not banned by twitter but are on the 10k most common
		// passwords
		CountingBloomFilter.print_Dexist();
		System.out.println("------------------------------------------------\n");
		scan_passwordsV2.close();
		scan_twitterV2.close();
	}

	// ----------------MINHASH----------------------
	public static void option3() throws IOException {
		System.out.println("------------------------------------------------");
		System.out.println("MINHASH\n");

		// CODIGO AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
		ArrayList<String> filelist = new ArrayList();
        Scanner files = new Scanner(new FileReader("src/project/filelist.txt"));
        while(files.hasNext()){
            filelist.add(files.nextLine());
        }
        files.close();

        int nfiles = filelist.size();   
        MinHash passwordfiles = new MinHash(200, 10007, nfiles);

        Map<Integer,String> fileindexes = new TreeMap<>();
        for(String filenames : filelist){
            Map<Integer,ArrayList<Integer>> PasswordData = processPasswordFile(filenames,fileindexes);

            for (Map.Entry<Integer, ArrayList<Integer>> entry : PasswordData.entrySet())
            {
                passwordfiles.insertMinHash(entry.getKey()+1, entry.getValue());
            }
        }
        passwordfiles.distancesMinHash();
        passwordfiles.findSimilarTranslate(0.65,fileindexes);

        /*
        for (Map.Entry<Integer,ArrayList<Integer>> entry : PasswordData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
       }
       */

		System.out.println("------------------------------------------------\n");
	}
	
	public static Map<Integer,ArrayList<Integer>> processPasswordFile(String filename, Map<Integer,String> fileindexes) throws IOException{
        int index = fileindexes.size();
        fileindexes.put(index, filename);
        Scanner read = new Scanner(new FileReader(filename));
        Map<Integer,ArrayList<Integer>> PasswordData = new TreeMap<>();
        while(read.hasNext()){
            String password = read.nextLine();
            int passhashcode = password.hashCode();
            if(!PasswordData.containsKey(index)){
                PasswordData.put(index,new ArrayList<>());
                PasswordData.get(index).add(passhashcode);
            }
            else{
                PasswordData.get(index).add(passhashcode);
            }
        }
        read.close();
        return PasswordData;
    }
}
