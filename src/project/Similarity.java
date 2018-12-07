package project;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;

public class Similarity {

    public static void main(String[] args) throws IOException{
        //isto aqui lê os ficheiros a ser abertos para um arraylist
        ArrayList<String> filelist = new ArrayList();
        Scanner files = new Scanner(new FileReader("filelist.txt"));
        while(files.hasNext()){
            filelist.add(files.nextLine());
        }
        files.close();
        for(String file : filelist){
            File f = new File(file);
            if(f.isFile()){
                System.out.println("dab");
            }
        }

        int nfiles = filelist.size();   
        MinHash passwordfiles = new MinHash(200, 10007, nfiles);

        Map<Integer,String> fileindexes = new TreeMap<>();
        for(String filenames : filelist){
            Map<Integer,ArrayList<Integer>> PasswordData = processPasswordFile(filenames,fileindexes);

            for (Map.Entry<Integer, ArrayList<Integer>> entry : PasswordData.entrySet())
            {
                passwordfiles.insertMinHash(entry.getKey()+1, entry.getValue());
            }
        }
        passwordfiles.distancesMinHash();
        passwordfiles.findSimilarTranslate(0.65,fileindexes);

        /*
        for (Map.Entry<Integer,ArrayList<Integer>> entry : PasswordData.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
       }
       */

    }

    public static Map<Integer,ArrayList<Integer>> processPasswordFile(String filename, Map<Integer,String> fileindexes) throws IOException{
        int index = fileindexes.size();
        fileindexes.put(index, filename);
        Scanner read = new Scanner(new FileReader(filename));
        Map<Integer,ArrayList<Integer>> PasswordData = new TreeMap<>();
        while(read.hasNext()){
            String password = read.nextLine();
            int passhashcode = password.hashCode();
            if(!PasswordData.containsKey(index)){
                PasswordData.put(index,new ArrayList<>());
                PasswordData.get(index).add(passhashcode);
            }
            else{
                PasswordData.get(index).add(passhashcode);
            }
        }
        read.close();
        return PasswordData;
    }

}

