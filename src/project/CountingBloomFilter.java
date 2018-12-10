package project;

import java.util.*;
import java.util.Map.Entry;

public class CountingBloomFilter {
	// Things needed
	private static int[] countingBloomFilter; // The bloom filter itself
	private static List<String> dont_exist = new ArrayList<String>();// To store the ones that do not exist
	private static List<String> exist = new ArrayList<String>(); // To store the ones that exist
	static Map<String, Integer> freq = new HashMap<String, Integer>(); // To store the number of occurrences
	private static int dont_exist_count = 0; // Counts the ones that do not exist
	private static int exist_count = 0; // Counts the existing
	private static int k; // Number of hash functions
	private static int m; // Number of bits in the filter
	private static int n; // Number of elements in the Filters
	private static double fpr; // Probability of false positives
	private static int countElements; // Count the number of elements

	// -------------------------------------------------
	// CREDITS: https://hur.st/bloomfilter/
	// n = ceil(m / (-k / log(1 - exp(log(p) / k))))
	// p = pow(1 - exp(-k / (m / n)), k)
	// m = ceil((n * log(p)) / log(1 / pow(2, log(2))));
	// k = round((m / n) * log(2));
	// -------------------------------------------------

	// Creates the BloomFilter with optimal m and k
	public static void CreateCountingBloomFilter(int n_arg, double fpr_arg) {
		n = n_arg;
		fpr = fpr_arg;
		m = (int) ((n * Math.log(fpr)) / Math.log(1 / Math.pow(2, Math.log(2))));
		k = (int) ((m / n) * Math.log(2));
		countingBloomFilter = new int[m];
	}

	// Insert the passed arguments on the BloomFilter
	public static void insert(String element_Insert) {
		// Can't add if the BloomFilter is full
		if (countElements >= m) {
			System.out.println("Not possible to add, Counting Bloom Filter is full");
			System.exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				element_Insert += Integer.toString(i);
				long hash = element_Insert.hashCode() & 0xffffffffL;
				countingBloomFilter[(int) ((hash) % m)] += 1;
			}
			countElements++;
		}
	}

	// Tests if the Bloom Filter contains the argument passed to the function
	public static void belongs(String element_Contains_arg) {
		if (countElements <= 0) {
			System.out.println("Counting Bloom Filter it is empty");
			System.exit(0);
		} else {
			String element_Contains = element_Contains_arg;
			for (int i = 0; i < k; i++) {
				element_Contains += Integer.toString(i);
				long hash = element_Contains.hashCode() & 0xffffffffL;
				if (countingBloomFilter[(int) ((hash) % m)] == 0) {
					if (!dont_exist.contains(element_Contains_arg)) {
						dont_exist.add(element_Contains_arg);
						dont_exist_count++;
					}
				}
			}

		}
	}

	// Version 2, because of time optimization
	// Tests if the Bloom Filter contains the argument passed to the function
	public static void belongsV2(String element_Contains_arg) {
		if (countElements <= 0) {
			System.out.println("Counting Bloom Filter it is empty");
			System.exit(0);
		} else {
			String element_Contains = element_Contains_arg;
			for (int i = 0; i < k; i++) {
				element_Contains += Integer.toString(i);
				long hash = element_Contains.hashCode() & 0xffffffffL;
				if (countingBloomFilter[(int) ((hash) % m)] == 1) {
					if (!exist.contains(element_Contains_arg)) {
						exist.add(element_Contains_arg);
						exist_count++;
					}
				}
			}
		}
	}

	// Needed this to development, returning the size so I can iterate over it with
	// a for
	public static int[] returnArray() {
		return countingBloomFilter;
	}

	// Remove one occurrence on the element passed by argument on the CBF
	public void removeOccurrence(String element_RemoveOccarg) {
		// Can't remove if the BloomFilter is empty
		if (countElements <= 0) {
			System.out.println("Not possible to remove, Counting Bloom Filter is empty");
			System.exit(0);
		} else {
			String element_RemoveOcc = element_RemoveOccarg;
			for (int i = 0; i < k; i++) {
				element_RemoveOcc += Integer.toString(i);
				long hash = element_RemoveOcc.hashCode() & 0xffffffffL;
				if (countingBloomFilter[(int) ((hash) % m)] > 0) {
					countingBloomFilter[(int) ((hash) % m)] -= 1;
					// If after the removal of the occurrence it does not belong anymore to the CBF,
					// decrements the countElements
					if (countingBloomFilter[(int) ((hash) % m)] == 0) {
						countElements--;
					}
				}
			}
		}
	}

	// Print the ones that belongs to the 10k most common passwords but are not
	// banned by twitter
	public static void print_Dexist() {
		System.out.println("Passwords that aren't on the 10.000 most common passwords list but are banned by twitter:");
		for (int i = 0; i < dont_exist.size(); i++) {
			System.out.print(">");
			System.out.println(dont_exist.get(i));
		}
		System.out.format("TOTAL: %d \n", dont_exist_count);
		// 370 are the number of passwords banned by twitter
		int passwords_not = 370 - dont_exist_count;
		System.out.format("\n%d passwords out of the 370 banned by Twitter aren't on the list of the 10.000"
				+ " most common passwords.\n", passwords_not);
	}

	// Print the ones that exists on the CBF
	public static void print_exist() {
		for (Entry<String, Integer> entry : freq.entrySet()) {
			System.out.format("STRING: %s | OCCURRENCES: %d\n", entry.getKey(), entry.getValue());
		}
		System.out.format("\nRandom Strings Generated that are on the CBF: %d\n", exist_count);

	}

	// Remove the Element passed by argument on the BloomFilter
	public void removeElement(String element_Remove_Elementarg) {
		// Can't remove if the BloomFilter is empty
		if (countElements <= 0) {
			System.out.println("Not possible to remove, Counting Bloom Filter is empty");
			System.exit(0);
		} else {
			String element_Remove_Element = element_Remove_Elementarg;
			for (int i = 0; i < k; i++) {
				element_Remove_Element += Integer.toString(i);
				long hash = element_Remove_Element.hashCode() & 0xffffffffL;
				countingBloomFilter[(int) ((hash) % m)] = 0;
			}
		}
		countElements--;
	}

	// Tests if the CBF contains the argument passed to the function and
	// give the number of Occurrences
	public static void numOccurences(String element_numOccurencesarg) {
		if (countElements <= 0) {
			System.out.println("Counting Bloom Filter it is empty");
			System.exit(0);
		}
		String element_numOccurences = element_numOccurencesarg;
		for (int i = 0; i < k; i++) {
			element_numOccurences += Integer.toString(i);
			long hash = element_numOccurences.hashCode() & 0xffffffffL;
			if (countingBloomFilter[(int) ((hash) % m)] == 1) {
				int count = freq.containsKey(element_numOccurencesarg) ? freq.get(element_numOccurencesarg) : 0;
				freq.put(element_numOccurencesarg, count + 1);
			}
		}
	}

	// Give the number of elements added to the CBF
	public static int numOfElements() {
		return countElements;
	}

	// Set all index of countingBloomFilter to 0 to clean
	public static void cleanCountingBF() {
		for (int i = 0; i < countingBloomFilter.length; i++) {
			countingBloomFilter[i] = 0;
		}
	}

}