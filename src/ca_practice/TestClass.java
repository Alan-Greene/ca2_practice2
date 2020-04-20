package ca_practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TestClass {

    public static ArrayList<DVD> getDvdsFromFile(String path){
        ArrayList<DVD> dvds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            for (String line; (line = reader.readLine()) != null;) {
                String[] values = line.split(",");
                dvds.add(new DVD(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4])));
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-2);
        }

        return dvds;
    }

    public static void menu(){
        System.out.println("Please press 1 to rent a DVD");
        System.out.println("Please press 2 if you want to return a DVD");
        System.out.println("Please press 3 to save details to the file");
        System.out.println("Please press 4 to quit");
    }

    public static int menuInput(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static void main(String[] args) throws OutOfStockException {
        DvdCollection dvd_collection = new DvdCollection();

        ArrayList<DVD> dvds = getDvdsFromFile("files/movies.txt");

        for (DVD dvd: dvds){
            dvd_collection.addDvd(dvd.getTitle(), dvd.getCategory(), dvd.getYear(), dvd.getQuantity_in_stock(), dvd.getQuantity_checked_out());
        }

        while (true){
            menu();
            int user_choice = menuInput();
            switch (user_choice){
                case 1:
                    dvd_collection.displayDvd();
                    System.out.println("What dvd would you like to rent?");
                    int rent_choice = menuInput();

                    try{
                        dvd_collection.checkOutDvd(rent_choice);
                    } catch (OutOfStockException e){
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    dvd_collection.displayDvd();
                    System.out.println("What dvd would you like to return?");
                    int return_choice = menuInput();
                    dvd_collection.returnDvd(return_choice);
                    break;
                case 3:
                    dvd_collection.writeToFile();
                    break;
                case 4:
                    System.exit(-1);
                    break;
                default:
            }
        }

    }
}
