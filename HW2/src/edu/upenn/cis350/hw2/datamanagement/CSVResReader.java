package edu.upenn.cis350.hw2.datamanagement;
/** This class reads and stores data of property values from a CSV file.
 * @author LZ
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import edu.upenn.cis350.hw2.data.Property;
import edu.upenn.cis350.hw2.logging.Logger;

public class CSVResReader {
    private String filename;
    
    public CSVResReader(String name) {
        filename = name;
    }
    
    // convert a string to an  with safety check
    public double strToDouble(double d, String[] arr, int pos) {
        if (arr[pos] == null || arr[pos].equals("")) {
            return d;
        } else {
            return Double.parseDouble(arr[pos]);
        }
    }
    
    // parse each line after the first of the CSV file
    public String[] parseByQuotes(String line) {
        ArrayList<String> fields = new ArrayList<>();
        String remain = line.substring(0);
        int pt = 0;
        
        while (remain.indexOf(',') > -1) {
            /* check if the remaining part of the line starts with a double quote: 
             * y -> the first entry ends after a double quote followed by a comma
             * n -> the first entry ends after the next comma */
            if (remain.charAt(0) == '"') {
                pt = remain.indexOf("\",", 1);
                fields.add(remain.substring(1, pt));
                remain = remain.substring(pt + 2);
            } else {
                pt = remain.indexOf(',');
                fields.add(remain.substring(0, pt));
                remain = remain.substring(pt + 1);
            }
        }
        fields.add(remain);

        // convert arrayList to array
        String[] output = fields.toArray(new String[0]);
        
        return output;
    }
    
    // extract info we care about of all property
    public ArrayList<Property> getAllProperty() {
        Logger l = Logger.getInstance();
        ArrayList<Property> properties = new ArrayList<>();
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(filename));
            l.log("processing: " + filename);
            // find the indices of the desired labels
            String firstLine = in.nextLine(); 
            String[] labels = firstLine.split(",");
            int numLabel = labels.length;
            
            int valuePos = 0, areaPos = 0, zipPos = 0;
            for (int i = 0; i < labels.length; i++) {
                if (labels[i].equals("market_value")) {
                    valuePos = i;
                } else if (labels[i].equals("total_livable_area")) {
                    areaPos = i;
                } else if (labels[i].equals("zip_code")) {
                    zipPos = i;
                }
            }

            // find corresponding fields of each property
            while (in.hasNext()) {
                double value = 0, area = 0;
                String zip = "";
                
                String line = in.nextLine();
                String[] fields;

                // count the number of commas to decide how to split
                int numComma = (int)(line.chars().filter(c -> c == ',').count());  
                if (numComma == (numLabel - 1)) { // split the normal way
                    fields = line.split(",");
                    value = strToDouble(value, fields, valuePos);
                    area = strToDouble(area, fields, areaPos);
                    zip = fields[zipPos].substring(0, 5);
                } else {
                    // use double quotes to split
                    fields = parseByQuotes(line);
                    value = strToDouble(value, fields, valuePos);
                    area = strToDouble(area, fields, areaPos);
                    zip = fields[zipPos].substring(0, 5); 
                }
                
                // ignore properties whose ZIP code is unknown
                if (!zip.equals("")) {
                    properties.add(new Property(value, area, zip));
                }
            }
        } catch (Exception e) {
            System.out.println("Unreadable file");
            l.log("Unreadable file");
            System.exit(0);
        } finally {
            in.close();
        }
        
        return properties;
    }
}
