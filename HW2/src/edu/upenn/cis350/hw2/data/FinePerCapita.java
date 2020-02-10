package edu.upenn.cis350.hw2.data;
/** This class represents the fine per capita for a ZIP code.
 * 
 * @author LZ
 */
public class FinePerCapita {
    private final double fine;
    private final String zip;
    
    public FinePerCapita(double f, String z) {
        this.fine = f;
        this.zip = z;
    }
    
    public double getFinePerCapita() {
        return fine;
    }
    
    public String getZip() {
        return zip;
    }
}
