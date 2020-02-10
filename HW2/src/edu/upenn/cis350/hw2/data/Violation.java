package edu.upenn.cis350.hw2.data;
/** This object class represents a single parking violation.
 * @author Linda Zhu
 */

public class Violation {
    private final String timestamp;
    private final int fine;
    private final String desc;
    private final String anonId;
    private final String state;
    private final String uniqId;
    private final String zip;
    
    public Violation(String timestamp, int fine, String desc, String anonId, 
                        String state, String uniqId, String zip) {
        this.timestamp = timestamp;
        this.fine = fine;
        this.desc = desc;
        this.anonId = anonId;
        this.state = state;
        this.uniqId = uniqId;
        this.zip = zip;
    }
    
    public int getFine() {
        return fine;
    }
    public String getState() { 
        return state;
    }
    public String getZIP() {
        return zip;
    }
}
