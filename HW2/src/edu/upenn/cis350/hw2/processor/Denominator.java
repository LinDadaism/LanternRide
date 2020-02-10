package edu.upenn.cis350.hw2.processor;
/** This interface is part of the strategy design pattern.
 *  @author LZ
 */
import java.util.ArrayList;
import edu.upenn.cis350.hw2.data.Property;

public interface Denominator {
    public double sum(ArrayList<Property> properties, String zip);
}
