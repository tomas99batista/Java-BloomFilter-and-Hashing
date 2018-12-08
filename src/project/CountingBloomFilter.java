package project;

import java.util.*;

public class CountingBloomFilter {
	// TODO cleanCountingBF()

	// Things needed
	private static int[] countingBloomFilter; // The bloom filter itself
	private static List<String> dont_exist = new ArrayList<String>();
	private static List<String> exist = new ArrayList<String>();
	private static int dont_exist_count = 0;
	private static int exist_count = 0;
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

	public static int[] returnArray() {
		return countingBloomFilter;
	}

	// Remove one occurrence on the element passed by argument on the CBF
	public void removeOccurence(String element_RemoveOcc) {
		// Can't remove if the BloomFilter is empty
		if (countElements <= 0) {
			System.out.println("Not possible to remove, Counting Bloom Filter is empty");
			System.exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				element_RemoveOcc += Integer.toString(i);
				// Verify if it is not at zero
				if (countingBloomFilter[element_RemoveOcc.hashCode() % m] > 0) {
					countingBloomFilter[element_RemoveOcc.hashCode() % m] -= 1;
				}
			}
		}
		countElements--;
	}

	// Remove the Element passed by argument on the BloomFilter
	public void removeElement(String element_Remove_Element) {
		// Can't remove if the BloomFilter is empty
		if (countElements <= 0) {
			System.out.println("Not possible to remove, Counting Bloom Filter is empty");
			System.exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				element_Remove_Element += Integer.toString(i);
				countingBloomFilter[element_Remove_Element.hashCode() % m] = 0;
			}
		}
		countElements--;
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

	// Print the ones that belongs to the 10k most common passwords but are not
	// banned by twitter
	public static void print_Dexist() {
		System.out.println("Passwords that are on the 10k most common passwords but are not banned by twitter:");
		for (int i = 0; i < dont_exist.size(); i++) {
			System.out.print(">");
			System.out.println(dont_exist.get(i));
		}
		System.out.format("TOTAL: %d \n", dont_exist_count);
		//370 are the number of passwords banned by twitter
		int passwords_not = 370 - dont_exist_count;
		System.out.format("\n%d passwords of 370 banned by Twitter are on the 10k most common password!!!\n", passwords_not);

	}

	// NOT SURE ABOUT THIS ONE
	// Tests if the Bloom Filter contains the argument passed to the function
	public void numOccurences(String element_numOccurences) {
		if (countElements <= 0) {
			System.out.println("Counting Bloom Filter it is empty");
			System.exit(0);
		}
		int verification = 1;
		int occurences = 0;
		for (int i = 0; i < k; i++) {
			element_numOccurences += Integer.toString(i);
			if (countingBloomFilter[element_numOccurences.hashCode() % m] == 0) {
				verification = 0;
				System.out.format("The Counting Bloom Filter does not contain %s", element_numOccurences);
				System.exit(1);
			} else {
				// NOT SURE ABOUT THIS VVVV
				occurences += 1;
			}
		}
		if (verification == 1) {
			System.out.format("The Counting Bloom Filter contains %s %d times", element_numOccurences, occurences);
		}
	}

	// Give the number of elements added
	public int numOfElements() {
		return countElements;
	}

	// Set all index of countingBloomFilter to 0 to clean
	public void cleanCountingBF() {
		for (int i = 0; i < countingBloomFilter.length; i++) {
			countingBloomFilter[i] = 0;
		}
	}
}
