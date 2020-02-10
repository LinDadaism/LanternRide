package edu.upenn.cis350.hw2.datamanagement;
/** This interface declares a method to get all the parking violations.
 * 
 * @author LZ
 *
 */
import java.util.ArrayList;
import edu.upenn.cis350.hw2.data.Violation;

public interface ParkingReader {
    public ArrayList<Violation> getAllViolations();
}
