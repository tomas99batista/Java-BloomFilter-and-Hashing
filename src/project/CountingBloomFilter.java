package project;

public class CountingBloomFilter {
	// TODO cleanCountingBF()

	// Things needed
	private int[] countingBloomFilter; // The bloom filter itself
	private int k; // Number of hash functions
	private int m; // Number of bits in the filter
	private int n; // Number of elements in the Filters
	private double fpr; // Probability of false positives
	private int countElements; // Count the number of elements

	// -------------------------------------------------
	// CREDITS: https://hur.st/bloomfilter/
	// n = ceil(m / (-k / log(1 - exp(log(p) / k))))
	// p = pow(1 - exp(-k / (m / n)), k)
	// m = ceil((n * log(p)) / log(1 / pow(2, log(2))));
	// k = round((m / n) * log(2));
	// -------------------------------------------------

	// Creates the BloomFilter with optimal m and k
	public CountingBloomFilter(int n, double fpr) {
		this.n = n;
		this.fpr = fpr;
		m = (int) ((n * Math.log(fpr)) / Math.log(1 / Math.pow(2, Math.log(2))));
		k = (int) ((m / n) * Math.log(2));
		countingBloomFilter = new int[m];
	}

	// Insert the passed arguments on the BloomFilter
	public void insert(String element_Insert) {
		// Can't add if the BloomFilter is full
		if (countElements >= m) {
			System.out.println("Not possible to add, Counting Bloom Filter is full");
			System.exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				element_Insert += Integer.toString(i);
				countingBloomFilter[element_Insert.hashCode() % m] += 1;
			}
			countElements++;
		}
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
	public void contains(String element_Contains) {
		if (countElements <= 0) {
			System.out.println("Counting Bloom Filter it is empty");
			System.exit(0);
		}
		int verification = 1;
		for (int i = 0; i < k; i++) {
			element_Contains += Integer.toString(i);
			if (countingBloomFilter[element_Contains.hashCode() % m] == 0) {
				verification = 0;
				System.out.format("The Counting Bloom Filter does not contain %s", element_Contains);
				System.exit(1);
			}
		}
		if (verification == 1) {
			System.out.format("The Counting Bloom Filter contains %s", element_Contains);
		}
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

	//Set all index of countingBloomFilter to 0 to clean
	public void cleanCountingBF() {
		for (int i = 0; i < countingBloomFilter.length; i++) {
			countingBloomFilter[i] = 0;
		}
	}
}
