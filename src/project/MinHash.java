package project;

import java.util.*;

public class MinHash {

	// Things needed
	private int k; // Number of hash functions
	private int primeNum; // Prime Number
	private int[] hashfunctions;
	private int[][] minhash_matrix;
	private double[][] distance_matrix;
	private int numCols; // Number of users in the case of the PL07 test, documents in other cases

	public MinHash(int k, int primeNum, int numCols) {
		this.k = k;
		this.primeNum = primeNum;
		this.hashfunctions = createRandomHashFunctions();
		this.numCols = numCols;
		this.minhash_matrix = new int[k][numCols];
		this.distance_matrix = new double[numCols][numCols];
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
		/*
		for(int i=0; i<k; i++){
			System.out.println(minhash_matrix[i][col-1]);
		}
		*/
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

	public void distancesMinHash()
	{
		
		for(int u1=0; u1<numCols; u1++)
		{
			for(int u2=0; u2<u1; u2++)
			{
				double sum = 0;
				double dist = 0;
				for(int i=0; i<k ; i++)
				{
					if(minhash_matrix[i][u1] == minhash_matrix[i][u2])
						sum += 1;
				}
				dist = 1 - (sum / k);
				//System.out.println(dist);
				distance_matrix[u1][u2] = dist;
				distance_matrix[u2][u1] = dist;
			}
		}
	}

	public void findSimilar(double threshold) {
		// Print of the similar items, the %% of similarity
		for(int u1=0; u1<numCols; u1++)
		{
			for(int u2=u1+1; u2<numCols; u2++)
			{	
				//System.out.println(distance_matrix[u1][u2]);
			
				if(distance_matrix[u1][u2] < threshold && distance_matrix[u1][u2] != 0.0){
					System.out.print((u1+1)+" and "+(u2+1)+" are a similar pair - ");
					System.out.println(distance_matrix[u1][u2]);
				}
					
			
			}
		}
	}

	public void findSimilarTranslate(double threshold, Map<Integer,String> fileindexes) {
		// Print of the similar items, the %% of similarity
		for(int u1=0; u1<numCols; u1++)
		{
			for(int u2=u1+1; u2<numCols; u2++)
			{	
				//System.out.println(distance_matrix[u1][u2]);
			
				if(distance_matrix[u1][u2] < threshold && distance_matrix[u1][u2] != 0.0){
					System.out.print(fileindexes.get(u1)+" and "+fileindexes.get(u2)+" are a similar pair - ");
					System.out.println(distance_matrix[u1][u2]);
				}
					
			
			}
		}
	}
}