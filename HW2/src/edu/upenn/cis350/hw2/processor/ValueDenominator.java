package edu.upenn.cis350.hw2.processor;

import java.util.ArrayList;
import edu.upenn.cis350.hw2.data.Property;

public class ValueDenominator implements Denominator {
    public double sum(ArrayList<Property> properties, String zip) {
        ArrayList<Property> copy = new ArrayList<Property>(properties);
        double sum = 0;
        for (Property res: copy) {
            if (res.getZip().equals(zip)) {
                sum += res.getValue();
            }
        }
        return sum;
    }
}
