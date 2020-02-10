package edu.upenn.cis350.hw2.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;

/** This class is responsible for interacting with the user and processing data
* as it relates to displaying it.
* @author LZ
*/

import java.util.Scanner;

import edu.upenn.cis350.hw2.data.FinePerCapita;
import edu.upenn.cis350.hw2.logging.Logger;
import edu.upenn.cis350.hw2.processor.Processor;

public class UserInterface {
    private Processor processor;
    private Scanner in;
    private Logger l;
    
    public UserInterface(Processor processor) {
        this.processor = processor;
        in = new Scanner(System.in);
        l = Logger.getInstance();
    }
    
    public void start() {
        System.out.println("Explore the OpenDataPhilly Dataset!\n" +
                         "There are 6 different actions you can perform.\n" +
                         "Enter your selection:");
        int choice = in.nextInt();
        l.log("selection: " + choice);
        if (choice == 0) {
            doGetPopulation();
        } else if (choice == 1) {
            doGetTotalPopulation();
        } else if (choice == 2) {
            doGetAllFines();
        } else if (choice == 3) {
            doAvgByMarketValue();
        } else if (choice == 4) {
            doAvgByLivableArea();
        } else if (choice == 5) {
            doTotalValuePerCapita();
        } else {
            System.out.println("Invalid choice");
            l.log("Invalid choice");
            System.exit(0);
        }
        in.close();
    }
    
    // check if the user input of ZIP is valid
    // y -> 1; n -> 0
    protected int checkZipValidity(String zip) {
        int size = zip.length();
        if (size != 5) {
            return 0;
        } else {
            int i = 0;
            while (i < size) {
                if (zip.charAt(i) >= '0' && zip.charAt(i) <= '9') {
                    i++;
                } else {
                    return 0;
                }
            }
        }
        return 1;
    }
    
    protected void doGetPopulation() {
        System.out.println("Enter a zip code:");
        String zip = in.next();
        l.log("zip: " + zip);
        if (checkZipValidity(zip) == 1) {
            System.out.println(processor.getPopulation(zip));
        } else {
            System.out.println("0");
        }
    }
    
    protected void doGetTotalPopulation() {
        System.out.println(processor.getTotalPopulation());
    }
    
    protected void doGetAllFines() {
        ArrayList<FinePerCapita> sorted = processor.getAllFines();
        DecimalFormat df = new DecimalFormat("0.0000");
        for (FinePerCapita f: sorted) {
            System.out.println(f.getZip() + " " + df.format(f.getFinePerCapita()));
        }
    }
    
    protected void doAvgByMarketValue() {
        System.out.println("Enter a zip code:");
        String zip = in.next();
        l.log("zip: " + zip);
        if (checkZipValidity(zip) == 1) {
            int rounded = (int) Math.round(processor.avgByMarketValue(zip));
            System.out.println(rounded);
        } else {
            System.out.println("0");
        }
    }
    
    protected void doAvgByLivableArea() {
        System.out.println("Enter a zip code:");
        String zip = in.next();
        l.log("zip: " + zip);
        if (checkZipValidity(zip) == 1) {
            int rounded = (int) Math.round(processor.avgByLivableArea(zip));
            System.out.println(rounded);
        } else {
            System.out.println("0");
        }
    }
    
    protected void doTotalValuePerCapita() {
        System.out.println("Enter a zip code:");
        String zip = in.next();
        l.log("zip: " + zip);
        if (checkZipValidity(zip) == 1) {
            int rounded = (int) Math.round(processor.totalValuePerCapita(zip));
            System.out.println(rounded);
        } else {
            System.out.println("0");
        }
    }
}
