package edu.upenn.cis350.hw2.data;
/** This object class represents an instance of local property with
 *  market_value, total_livable_area, and zip_code fields.
 * @author LZ
 */

public class Property {
    private final double value;
    private final double area;
    private final String zip;
    
    public Property(double val, double sq, String code) {
        this.value = val;
        this.area = sq;
        this.zip = code;
    }
    
    /*public Property() {
        this.value = 0;
        this.area = 0;
        this.zip = "";
    }*/
    
    public double getValue() {
        return value;
    }
    
    public double getArea() {
        return area;
    }
    
    public String getZip() {
        return zip;
    }
}
