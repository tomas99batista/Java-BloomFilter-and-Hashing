package project;

public class MinHash {

	// Things needed
	private int k; // Number of hash functions
	private int primeNum; // Prime Number
	private int n; // Number of elements in the Filters
	private double threshold; // Probability of false positives
	private int countElements; // Count the number of elements
	private int prime; // Prime number that will be incremented

	public MinHash() {
		// TODO Auto-generated constructor stub
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
	}
}