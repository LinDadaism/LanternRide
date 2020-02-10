package edu.upenn.cis350.hw2;

import edu.upenn.cis350.hw2.datamanagement.*;
import edu.upenn.cis350.hw2.logging.Logger;
import edu.upenn.cis350.hw2.processor.Processor;
import edu.upenn.cis350.hw2.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        Logger l = Logger.getInstance();
        if (args.length < 4) {
            System.out.println("Incorrect number of arguments");
            l.log("Incorrect number of arguments");
            System.exit(0);
        }
        
        String parkingType = args[0];
        if (!parkingType.equals("csv") && !parkingType.equals("json")) {
            System.out.println("First argument must be csv or json");
            l.log("First argument must be csv or json");
            System.exit(0);
        }

        String parkingFile = args[1];
        String resFile = args[2];
        String pplFile = args[3];
        
        ParkingReader reader = null;
        if (parkingType.equals("csv")) {
            reader = new CSVParkingReader(parkingFile);
        } else if (parkingType.equals("json")) {
            reader = new JSONParkingReader(parkingFile);
        }

        CSVResReader readerRes = new CSVResReader(resFile);
        TXTReader readerPpl = new TXTReader(pplFile);
        Processor processor = new Processor(reader, readerRes, readerPpl);
        UserInterface ui = new UserInterface(processor);
            
        ui.start();
    }
}
