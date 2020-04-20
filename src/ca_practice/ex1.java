package ca_practice;

import java.io.*;

public class ex1 {
    public static void createMoviesFile(String path){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))))){
            writer.println("The Great Wall,Thriller,2017,3,2");
            writer.println("John Wick Chapter 2,Action,2017,5,0");
            writer.println("Logan,Action,2017,0,5");
            writer.println("Loving,Drama,2017,4,1");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
