package project;

import java.io.*;
import java.util.Random;

public class TestCountingBloomFilter {
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//Generate random strings
	public static String GenRandString() {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < 40; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private static int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public static void main(String[] args) throws IOException {
		CountingBloomFilter.CreateCountingBloomFilter(10000, 0.3);

		// Inserting 10k random Strings with 40 chars on the CBF
		for (int i = 0; i < 10000; i++) {
			String word = GenRandString();
			CountingBloomFilter.insert(word);
		}
		// With another 10k random Strings with 40 chars test if they are on the CBF
		for (int i = 0; i < 10000; i++) {
			String word = GenRandString();
			CountingBloomFilter.belongsV2(word);
			CountingBloomFilter.numOccurences(word);
		}

		CountingBloomFilter.print_exist();

		System.out.format("\nThere are %d elements on the CBF", CountingBloomFilter.numOfElements());

		// Set all the CBF to 0's
		CountingBloomFilter.cleanCountingBF();

	}
}
