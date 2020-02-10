package edu.upenn.cis350.hw2.datamanagement;
/** This class reads and stores data of parking violations from a CSV file.
 * 
 * @author Linda Zhu
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import edu.upenn.cis350.hw2.data.Violation;
import edu.upenn.cis350.hw2.logging.Logger;

public class CSVParkingReader implements ParkingReader {
    private String filename;
    
    public CSVParkingReader(String name) {
        filename = name;
    }
    
    public ArrayList<Violation> getAllViolations() {
        Logger l = Logger.getInstance();
        ArrayList<Violation> violations = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(filename));
            l.log("processing: " + filename);
            do {
                String line = in.nextLine();
                String[] fields = line.split(",");
                String timestamp = fields[0];
                int fine = Integer.parseInt(fields[1]);
                String desc = fields[2];
                String anonId = fields[3];
                String state = fields[4];
                String uniqId = fields[5];
                String zip = "";
                if (fields.length >= 7) {
                    zip = fields[6].substring(0, 5);
                }
                
                if (!zip.equals("")) {
                    violations.add(new Violation(timestamp, fine, desc,
                                                 anonId, state, uniqId, zip));
                }
            } while (in.hasNext());
        } catch (Exception e) {
            System.out.println("Unreadable file");
            l.log("Unreadable file");
            System.exit(0);
        } finally {
            in.close();
        }
        
        return violations;
    }
}
