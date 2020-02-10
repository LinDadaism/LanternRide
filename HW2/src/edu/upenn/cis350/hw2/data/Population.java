package edu.upenn.cis350.hw2.data;
/** This object class represents an instance of local population based
 *  on ZIP code.
 * @author LZ
 */

public class Population {
    private final String zip;
    private final int ppl;
    
    public Population(String zip, int ppl) {
        this.zip = zip;
        this.ppl = ppl;
    }
    
    public int getPopulation() {
        return ppl;
    }
    
    public String getZip() {
        return zip;
    }
}
