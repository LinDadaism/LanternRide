package edu.upenn.cis350.hw2.processor;
/** This class does calculations and sorts specific data in ascending
 *  order of ZIP.
 * @author LZ
 */

import java.util.ArrayList;
import edu.upenn.cis350.hw2.data.*;
import edu.upenn.cis350.hw2.datamanagement.*;

public class Processor {
    private ParkingReader reader;
    private CSVResReader readerRes;
    private TXTReader readerPpl;
    private ArrayList<Violation> violations;
    private ArrayList<Property> properties;
    private ArrayList<Population> population;
    
    public Processor(ParkingReader reader, CSVResReader readerRes, TXTReader readerPpl) {
        this.reader = reader;
        this.readerRes = readerRes;
        this.readerPpl = readerPpl;
        violations = reader.getAllViolations();
        properties = readerRes.getAllProperty();
        population = readerPpl.getAllPpl();
    }

    // Step 0: get population for the argument ZIP code
    public int getPopulation(String zip) {
        int pop = 0;
        for (Population p: population) {
            if (p.getZip().equals(zip)) {
                pop = p.getPopulation();
            }
        }
        return pop;
    }
    
    // Step 1: sum total population for all ZIP codes
    public int getTotalPopulation() {
        int sum = 0;
        for (Population p: population) {
            sum += p.getPopulation();
        }   
        return sum;
    }
    
    // Step 2: calculate total parking fines per capita for each ZIP code
    public ArrayList<FinePerCapita> getAllFines() {
        CalcFinePerCapita calculator = new CalcFinePerCapita();
        ArrayList<FinePerCapita> sortedFines = calculator.getAllFPC(violations, population);
        
        return sortedFines;
    }
    
    // using Denominator to average
    public double average(String zip, Denominator denom) {
        int numRes = 0;
        for (Property res: properties) {
            if (res.getZip().equals(zip)) {
                numRes++;
            }
        }
        double sum = denom.sum(properties, zip);
        if (sum != 0) {
            return sum / numRes;
        } else {
            return 0;
        }
    }
    
    // Step 3: calculate average residential market value
    // returns 0 if the total residential market value is 0
    public double avgByMarketValue(String zip) {
        return average(zip, new ValueDenominator());
    }
    
    // Step 4: calculate average residential total livable area
    // returns 0 if the total residential livable area is 0
    public double avgByLivableArea(String zip) {
        return average(zip, new AreaDenominator());
    }    
    
    // Step 5: calculate total residential market value per capita
    // returns 0 if the total market value is 0/ the population is 0
    public double totalValuePerCapita(String zip) {
        ValueDenominator valDenom = new ValueDenominator();
        double sumValue = (valDenom.sum(properties, zip));
        int pop = getPopulation(zip);
        if (sumValue == 0 || pop == 0) {
            return 0;
        } else {
            return sumValue / pop;
        }
    }
}
