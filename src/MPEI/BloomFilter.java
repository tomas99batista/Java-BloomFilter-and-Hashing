package MPEI;

import java.util.*;
import java.lang.Math;

public class BloomFilter {

	// Things needed
	private int[] bloomFilter; // The bloom filter itself
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
	public BloomFilter(int n) {
		this.n = n;
		m = (int) ((n * Math.log(fpr)) / Math.log(1 / Math.pow(2, Math.log(2))));
		bloomFilter = new int[m];
		k = (int) ((m / n) * Math.log(2));
	}

	// Insert the passed arguments on the BloomFilter
	public void insert(String Input) {
		for (int i = 0; i < k; i++) {
			// TODO
			// member_i = [member_i num2str(i)];
			bloomFilter[Input.hashCode() % m] = 1;
		}
		countElements++; // Give the number of elements added *might be useful....or not*
	}

	// TODO
	// Tests if the Bloom Filter contains the argument passed to the function
	public void contains(String Input) {
	}

	// Give the number of elements added *might be useful....or not*
	public int NumOfElements() {
		return countElements;
	}

	// Give the probability of false positive
	public double FalsePositiveProbb() {
		return Math.pow(1 - Math.exp(-k / (m / n)), k);
	}

	// TODO
	// FiX tHiS pRiNt
	@Override
	public String toString() {
		return "BloomFilter [bloomFilter=" + Arrays.toString(bloomFilter) + ", k=" + k + ", m=" + m + ", n=" + n
				+ ", fpr=" + fpr + ", countElements=" + countElements + ", FalsePositiveProbb()=" + FalsePositiveProbb()
				+ "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}