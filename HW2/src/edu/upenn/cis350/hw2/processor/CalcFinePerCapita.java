package edu.upenn.cis350.hw2.processor;
/** This class calculates the total parking fines per capita for each ZIP code,
 *  and sorts the results in ascending order of ZIP.
 *  
 * @author LZ
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.upenn.cis350.hw2.data.FinePerCapita;
import edu.upenn.cis350.hw2.data.Population;
import edu.upenn.cis350.hw2.data.Violation;

public class CalcFinePerCapita {

    // calculate and sort total fines per capita for all ZIP codes
    public ArrayList<FinePerCapita> getAllFPC(ArrayList<Violation> parkings,
                                              ArrayList<Population> pop) {
        ArrayList<FinePerCapita> fines = new ArrayList<>();
        ArrayList<Violation> unfiltered = new ArrayList<Violation>(parkings);
        ArrayList<Violation> filtered = filteredVio(unfiltered);
        ArrayList<Population> population = new ArrayList<Population>(pop);
        
        for (Population p: population) {
            if (p.getPopulation() != 0) {
                int ppl = p.getPopulation();
                String zip = p.getZip();
                double sum = 0;
                for (Violation v: filtered) {
                    if (v.getZIP().equals(zip)) {
                        sum += v.getFine();
                    }
                }
                if (sum != 0) {
                    double fine = sum / ppl;
                    fines.add(new FinePerCapita(fine, zip));
                }
            }
        }
        
        // sorting
        Collections.sort(fines, new Comparator<FinePerCapita>() {
            @Override
            public int compare(FinePerCapita f1, FinePerCapita f2) {
                return f1.getZip().compareTo(f2.getZip());
            }
        });
        return fines;
    }
    
    // filter out violations whose plate is non-PA and whose ZIP is unknown
    public ArrayList<Violation> filteredVio(ArrayList<Violation> parkings) {
        ArrayList<Violation> unfiltered = new ArrayList<Violation>(parkings);
        ArrayList<Violation> pa = new ArrayList<>();
        for (Violation v: unfiltered) {
            if (v.getState().equals("PA") && !v.getZIP().equals("")) {
                pa.add(v);
            }
        }
        return pa;
    }
}
