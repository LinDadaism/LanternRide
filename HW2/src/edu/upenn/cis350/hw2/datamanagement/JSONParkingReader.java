package edu.upenn.cis350.hw2.datamanagement;
/** This class reads and stores data of parking violations from a JSON file.
 * 
 * @author Linda Zhu
 */

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import edu.upenn.cis350.hw2.data.Violation;
import edu.upenn.cis350.hw2.logging.Logger;

public class JSONParkingReader implements ParkingReader {
    private String filename;
    
    public JSONParkingReader(String name) {
        filename = name;
    }
    
    public ArrayList<Violation> getAllViolations() {
        Logger l = Logger.getInstance();
        ArrayList<Violation> violations = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray parking = (JSONArray)parser.parse(new FileReader(filename));
            l.log("processing: " + filename);
            Iterator iter = parking.iterator();
            while (iter.hasNext()) {
                JSONObject pv = (JSONObject)iter.next();
                String timestamp = (String)pv.get("date");
                int fine = (int)((long)pv.get("fine"));
                String desc = (String)pv.get("violation");
                String anonId = (String)pv.get("plate_id");
                String state = (String)pv.get("state");
                String uniqId = Long.toString((long)(pv.get("ticket_number")));
                String zip = (String)pv.get("zip_code");
                // ignore violations for which ZIP is unknown
                if (!zip.equals("")) {
                    violations.add(new Violation(timestamp, fine, desc,
                                                 anonId, state, uniqId, zip));
                }
            }
        } catch (Exception e) {
            System.out.println("Unreadable file");
            l.log("Unreadable file");
            System.exit(0);
        }
        
        return violations;
    }
}
