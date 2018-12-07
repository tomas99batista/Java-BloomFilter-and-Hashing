package project;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Similarity {

    public static void main(String[] args) throws IOException{
        //ArrayList<String> filelist = new ArrayList();
        Map<Integer,ArrayList<Integer>> reviewData = readReviewData("src/project/u.data");
//        Map<Integer,ArrayList<Integer>> reviewData = readReviewData("u.data");
        //MinHash teste = new MinHash(100,10007,3);
        int nusers = reviewData.size();   
        MinHash filmes = new MinHash(100, 10007, nusers);
        for (Map.Entry<Integer, ArrayList<Integer>> entry : reviewData.entrySet())
        {
            filmes.insertMinHash(entry.getKey(), entry.getValue());
        }
        filmes.distancesMinHash();
        filmes.findSimilar(0.4);

        /*
        for (Map.Entry<Integer,ArrayList<Integer>> entry : reviewData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
       }
       */

    }

    public static Map<Integer,ArrayList<Integer>> readReviewData(String filename) throws IOException{
        Scanner read = new Scanner(new FileReader(filename));
        Map<Integer,ArrayList<Integer>> reviewData = new TreeMap<>();
        while(read.hasNext()){
            String[] review = read.nextLine().split("\t");
            int user = Integer.parseInt(review[0]);
            int movieid = Integer.parseInt(review[1]);
            if(!reviewData.containsKey(user)){
                reviewData.put(user,new ArrayList<>());
                reviewData.get(user).add(movieid);
            }
            else{
                reviewData.get(user).add(movieid);
            }
        }
        read.close();
        return reviewData;
    }

}

