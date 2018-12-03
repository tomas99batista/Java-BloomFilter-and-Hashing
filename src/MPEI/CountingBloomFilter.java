package MPEI;

import java.util.*;
import java.lang.Math;

public class CountingBloomFilter {
	// Things needed
	private int[] countingBloomFilter; // The bloom filter itself
	private int k; // Num of hash functions
	private int m; // Number of bits in the filter
	private int n; // Number of elements in the Filters
	private double fpr; // Probability of false positives
	private int countElements; // Count the num of elements

	// -------------------------------------------------
	// CREDITS: https://hur.st/bloomfilter/
	// n = ceil(m / (-k / log(1 - exp(log(p) / k))))
	// p = pow(1 - exp(-k / (m / n)), k)
	// m = ceil((n * log(p)) / log(1 / pow(2, log(2))));
	// k = round((m / n) * log(2));
	// -------------------------------------------------

	// Creates the BloomFilter with optimal m and k
	public CountingBloomFilter(int n) {
		this.n = n;
		m = (int) ((n * Math.log(fpr)) / Math.log(1 / Math.pow(2, Math.log(2))));
		countingBloomFilter = new int[m];
		k = (int) ((m / n) * Math.log(2));
	}

	// Insert the passed arguments on the BloomFilter
	public void insert(String Input) {
		// Can't add if the BloomFilter is full
		if (countElements >= m) {
			System.out.println("Not possible to add, Counting Bloom Filter is full");
			Runtime.getRuntime().exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				Input += Integer.toString(i);
				countingBloomFilter[Input.hashCode() % m] += 1;
			}
			countElements++;
		}
	}

	// Remove one occurrence on the element passed by argument on the CBF
	public void removeOccurence(String element) {
		// Can't remove if the BloomFilter is empty
		if (countElements <= 0) {
			System.out.println("Not possible to remove, Counting Bloom Filter is empty");
			Runtime.getRuntime().exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				element += Integer.toString(i);
				// Verify if it is not at zero
				if (countingBloomFilter[element.hashCode() % m] > 0) {
					countingBloomFilter[element.hashCode() % m] -= 1;
				}
			}
		}
		countElements--;
	}

	// Remove the Element passed by argument on the BloomFilter
	public void removeElement(String element) {
		// Can't remove if the BloomFilter is empty
		if (countElements <= 0) {
			System.out.println("Not possible to remove, Counting Bloom Filter is empty");
			Runtime.getRuntime().exit(0);
		} else {
			for (int i = 0; i < k; i++) {
				element += Integer.toString(i);
				countingBloomFilter[element.hashCode() % m] = 0;
			}
		}
		countElements--;
	}

	// Tests if the Bloom Filter contains the argument passed to the function
	public void contains(String Input) {
		if (countElements <= 0) {
			System.out.println("Counting Bloom Filter it is empty");
			Runtime.getRuntime().exit(0);
		}
		int verification = 1;
		for (int i = 0; i < k; i++) {
			Input += Integer.toString(i);
			if (countingBloomFilter[Input.hashCode() % m] == 0) {
				verification = 0;
				System.out.format("The Counting Bloom Filter does not contain %s", Input);
				System.exit(1);
			}
		}
		if (verification == 1) {
			System.out.format("The Counting Bloom Filter contains %s", Input);
		}
	}

	// Give the number of elements added
	public int NumOfElements() {
		return countElements;
	}

	// Give the probability of false positive
	public double FalsePositiveProbb() {
		return Math.pow(1 - Math.exp(-k / (m / n)), k);
	}

	@Override
	public String toString() {
		return "CountingBloomFilter [countingBloomFilter=" + Arrays.toString(countingBloomFilter) + ", k=" + k + ", m="
				+ m + ", n=" + n + ", fpr=" + fpr + ", countElements=" + countElements + ", FalsePositiveProbb()="
				+ FalsePositiveProbb() + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
