package edu.upenn.cis350.hw2.datamanagement;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import edu.upenn.cis350.hw2.data.Population;
import edu.upenn.cis350.hw2.logging.Logger;

/** This class reads and stores population data in ZIP code area from a txt file.
 * @author LZ
 */

public class TXTReader {
    private String filename;
    
    public TXTReader(String name) {
        filename = name;
    }
    
    public ArrayList<Population> getAllPpl() {
        Logger l = Logger.getInstance();
        ArrayList<Population> population = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(filename));
            l.log("processing: " + filename);
            in.useDelimiter("\\s+");
            while (in.hasNext()) {
                String zip = in.next();
                int num = in.nextInt();
                if (!zip.equals("")) {
                    population.add(new Population(zip, num));
                }
            }
        } catch (Exception e) {
            System.out.println("Unreadable file");
            l.log("Unreadable file");
            System.exit(0);
        } finally {
            in.close();
        }
        
        return population;
    }
}
