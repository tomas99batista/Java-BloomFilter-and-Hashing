package project;

import java.util.*;

public class MinHash {

	// Things needed
	private int k; // Number of hash functions
	private int primeNum; // Prime Number
	private int n; // Number of elements in the Filters
	private double threshold; // Probability of false positives
	private int countElements; // Count the number of elements
	//private int prime; // Prime number that will be incremented
	private int[] hashfunctions;
	private int[][] minhash_matrix;
	private int numCols;

	public MinHash(int k, int primeNum, int numCols) {
		this.k = k;
		this.primeNum = primeNum;
		this.hashfunctions = createRandomHashFunctions();
		this.minhash_matrix = new int[k][numCols];
	}
	
	public void insertMinHash(int col, ArrayList<Integer> itemlist) {
		//Calculate the MinHash to insert on the table
		for(int i=0; i<k; i++){
			int minimo = hashFunction(itemlist.get(0), i);
			for (int j=1; j < itemlist.size(); j++) {
				int temp = hashFunction(itemlist.get(j), i);
				if(temp < minimo)
					minimo = temp;
			}
			minhash_matrix[i][col-1] = minimo;
		}
		for(int i=0; i<k; i++){
			System.out.println(minhash_matrix[i][col-1]);
		}
	}

	public int hashFunction(int item, int hash_num){
		int hash = hashfunctions[hash_num]*item % primeNum;
		return hash;
	}

	public int[] createRandomHashFunctions() {
		hashfunctions = new int[k];
		for(int i=0; i<k; i++){
			hashfunctions[i] = (int)(Math.random() * primeNum);
			//System.out.println(hashfunctions[i]);
		}
		return hashfunctions;
	}

	public void distancesMinHash(String inputDistances) {
		/*
		%matriz de distâncias
dist_matrix = zeros(nhf,Nu);

for u1=1:Nu
    for u2 = 1:u1
        dist_matrix(u1,u2) = 1 - (sum(minhash_matrix(:,u1) == minhash_matrix(:,u2)) /nhf);
        %a distância de u1 para u2 é igual à de u2 para u1 logo:
        dist_matrix(u2,u1) = dist_matrix(u1,u2);
    end
end
		*/
	}

	public void findSimilar(String similarSearch) {
		// Print of the similar items, the %% of similarity
	}

	public void numOfSimilar() {
		// Print the number of similar items. Saying your password it is similar to X
		// passwords on the most XXXX common passwords which represents %%%%%. Do an if
		// which says staying in this part of the percentage might be bad or good.
		// Investigate this last part

		/*
		SimilarUsers= zeros(1,num_pairs);

k= 1;
    for n1= 1:Nu
        for n2= n1+1:Nu
            if dist_matrix(n1,n2) < threshold
                SimilarUsers(k,:)= [n1 n2 dist_matrix(n1,n2)];
                k= k+1;
            end
        end
    end
    
end
*/
	}
}