package com.ringfulhealth.rxparser.exporter.RingfulNIDA2013;

import com.ringfulhealth.rxparser.exporter.Exporter;
import java.sql.*;


public class RingfulExporter implements Exporter {
    
    public void export () throws Exception {
        
        System.out.println("Constructing NDC array");
        // Iterate through all RXNSAT objects
        //   Match RXNSAT.ATN == NDC
        //   Create NDC object with RXNSAT.ATV and RXNSAT.RXCUI
        
        System.out.println("Constructing Term array");
        // Iterate through all RxTerm objects
        //   Create Term object with a subset of RxTerm fields
        //   Iterate through RXNCONSO objects with RXNCONSO.RXCUI == RxTerm.RXCUI
        //     Match RXNCONSO.SAB == MDDB
        //     Set Term.MDDB_ID = RXNCONSO.CODE
        
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:sample_drug_data.db");
        System.out.println("Write arrays into sample_drug_data.db file");
        conn.close();
    }

}
