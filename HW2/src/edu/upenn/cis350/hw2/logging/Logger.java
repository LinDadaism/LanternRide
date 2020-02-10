package edu.upenn.cis350.hw2.logging;
/** This class records the user inputs and activities by writing 
 *  to a log file named “log.txt”.
 * @author LZ
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Logger {
    private FileWriter fw;
    private BufferedWriter bw;
    private PrintWriter out;
    
    private Logger(String filename) {
        try { 
            fw = new FileWriter(filename, true); 
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
        } catch (Exception e) { }
    }
    
    // singleton instance
    private static Logger instance = new Logger("log.txt");
    
    // singleton accessor method
    public static Logger getInstance() { 
        return instance; 
    }
    
    public void log(String msg) {
        out.println(msg);
        out.flush();
    }
}
