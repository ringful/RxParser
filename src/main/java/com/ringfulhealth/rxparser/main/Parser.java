package com.ringfulhealth.rxparser.main;

import com.ringfulhealth.rxparser.exporter.Exporter;

public class Parser {

    public static void main(String[] args) throws Exception {
        
        System.out.println("Implement database ingestion here ...");
        // 1. Read in an array of RXNCONSO
        // 2. Read in an array of RXNSAT
        // 3. Read in an array of RxTerm
        
        System.out.println("Export using the dummy exporter: com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
        try {
            Class theClass  = Class.forName("com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
            Exporter exporter = (Exporter) theClass.newInstance();
            exporter.export();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
