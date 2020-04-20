package ca_practice;

import java.io.*;
import java.util.ArrayList;

public class DvdCollection {
    DVD[] dvds = new DVD[4];
    int i = 0;

    public void addDvd(String title, String category, Integer year, Integer quantity_in_stock, Integer quantity_checked_out){
        dvds[i] = new DVD(title, category, year, quantity_in_stock, quantity_checked_out);
        i++;
    }

    public void checkOutDvd(Integer dvd_choice) throws OutOfStockException {
        try {
            dvds[dvd_choice-1].checkOut();
        } catch (OutOfStockException e){
            throw new OutOfStockException(e.getMessage());
        }

    }

    public Boolean returnDvd(Integer dvd_choice){
        return dvds[dvd_choice-1].returnDvd();
    }

    public void rateDvd(Integer dvd_choice, Double rating){
        dvds[dvd_choice].rateDvd(rating);
    }

    public DVD mostPopular(){
        double highest_rating = dvds[0].getAverageRating();
        DVD highest_rated = dvds[0];
        for (DVD dvd : dvds) {
            if (dvd.getAverageRating() > highest_rating) {
                highest_rating = dvd.getAverageRating();
                highest_rated = dvd;
            }
        }

        return highest_rated;
    }

    public void displayDvd(){
        int i = 1;

        System.out.println("DVDs");
        for (DVD dvd: dvds){
            System.out.printf("DVD number: %d Title: %-25s Number available: %d%n", i, dvd.getTitle(), dvd.getQuantity_in_stock());
            i++;
        }
    }

    public void writeToFile(){
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File("files", "movies_out.txt"))))){
            writer.printf("%s %10s %10s %10s %10s %10s %10s%n", "Title", "Category", "Year", "Quantity", "Checked out", "Rating sum", "Rating count");
            for (DVD dvd: dvds){
                writer.println(dvd);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


}
