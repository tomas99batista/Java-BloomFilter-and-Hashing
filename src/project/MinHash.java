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
		minhash_matrix[0][0] = 1;
		System.out.println(minhash_matrix[0][0]);
	}
	
	public void insertMinHash(String iputInserMH) {
		//Calculate the MinHash to insert on the table
/*
    filmes = Set{u};
    for i=1:nhf
        minimo = hash_function(filmes(1),i,randlist,p);
        for t=2:countElements
            temp = hash_function(filmes(t),i,randlist,p);
            if temp < minimo
                minimo = temp;
            end
        end
        minhash_matrix(i,u) = minimo;
    end
*/

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