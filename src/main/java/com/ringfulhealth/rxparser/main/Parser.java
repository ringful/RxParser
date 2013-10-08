package com.ringfulhealth.rxparser.main;

import com.ringfulhealth.rxparser.exporter.Exporter;
import com.ringfulhealth.rxparser.entity.rxnorm.*;
import com.ringfulhealth.rxparser.entity.rxterms.RxTerm;
import com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity.NDC;
import java.io.*;
import java.util.*;

public class Parser {

    public static void main(String[] args) throws Exception {
        
        System.out.println("Implement database ingestion here ...");
 
        String RXCUI,LAT, TS, LUI,STT,SUI,ISPREF,RXAUI,SAUI,SCUI,SDUI,SAB,
               CODE,STR,SRL,SUPPRESS,CVF,STYPE,ATUI, SATUI, ATN, ATV,
               GENERIC_RXCUI, TTY, FULL_NAME, RXN_DOSE_FORM,
	       FULL_GENERIC_NAME, BRAND_NAME, DISPLAY_NAME, ROUTE, NEW_DOSE_FORM,
               STRENGTH, SUPPRESS_FOR, DISPLAY_NAME_SYNONYM, IS_RETIRED, SXDG_RXCUI,
	       SXDG_TTY, SXDG_NAME= "";
                String  stringRead ="";
                String [] result;
    

        //2. Read in an array of RXNSAT
     /*   
         try{   
               Class theClass  = Class.forName("com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
               Exporter exporter = (Exporter) theClass.newInstance();
               exporter.ConnectDatabase();
                FileWriter fw = new FileWriter("outputNDC.txt",false);
                BufferedWriter bw = new BufferedWriter(fw);
                
           
		Scanner file1 = new Scanner(new File("C:/resource/RRF/RXNSAT.RRF"));
                
	    while (file1.hasNext()){
                stringRead = file1.nextLine();
                result = stringRead.split("\\|");
                RXCUI = result[0] ;    
                LUI = result[1]; 
                SUI = result[2];    
                RXAUI = result[3];     
                STYPE = result[4];     
                CODE = result[5]; 
                ATUI = result[6];
                SATUI = result[7];
                ATN = result[8];
                SAB = result[9];
                ATV = result[10];
                if (result.length == 11){
                    SUPPRESS = ""; 
                    CVF = "";
                }
                else{
                    SUPPRESS = result[11];
                    if (result.length == 12)
                        CVF = "";
                    else
                        CVF = result[12];
                } 
                
                
		RXNSAT satObj = new RXNSAT(RXCUI, LUI, SUI, RXAUI, STYPE,
					 CODE, ATUI, SATUI, ATN, SAB,
					 ATV, SUPPRESS, CVF);
                if (satObj.getATN().equals("NDC")){
                    bw.write(satObj.getATV()+","+satObj.getRXCUI()+","+satObj.getSAB());
                    bw.newLine();
                    
                }
               //exporter.export(satObj);
                       
             }
            
            bw.close();
         }
         catch(Exception ioe){
             ioe.printStackTrace();
         }
		System.out.println("Finish adding RXNSAT objects");
              
                

                
                
                
               
         // 1. Read in an array of RXNCONSO
/*	
             ArrayList<RXNCONSO> consoList = new ArrayList<RXNCONSO>();
         try{  
             
		Scanner file = new Scanner(new File("C:/resource/RXNCONSO.txt"));		
		while (file.hasNext()){
                stringRead = file.nextLine();
          //     System.out.println(stringRead);
                 String [] result1 = stringRead.split("[|]");
             
                RXCUI = result1[0] ;     
                LAT = result1[1];         
                TS = result1[2];                          
                LUI = result1[3];                   
                STT = result1[4];                  
                SUI = result1[5];                
                ISPREF = result1[6];   
                RXAUI = result1[7];   
                SAUI = result1[8];    
                SCUI = result1[9];      
                SDUI = result1[10];      
                SAB = result1[11];     
                TTY = result1[12];      
                CODE = result1[13];                    
                STR = result1[14];      
                SRL = result1[15];    
                SUPPRESS = result1[16];
                if(result1.length == 17)
                    CVF = "";
                else
                    CVF = result1[17];
		RXNCONSO conSoObj = new RXNCONSO(RXCUI,LAT, TS, LUI,STT,SUI,ISPREF,RXAUI,SAUI,SCUI,SDUI,SAB,
					TTY,CODE,STR,SRL,SUPPRESS,CVF);
                consoList.add(conSoObj);
                    
           
                        }
         }
         catch(IOException ioe){
             ioe.printStackTrace();
         }
		System.out.println("Finish adding RXCONSO objects");
                
                
                
        // 3. Read in an array of RxTerm
  
       
         ArrayList<RxTerm> termList = new ArrayList<RxTerm>();
         try{       
         
		Scanner file1 = new Scanner(new File("C:/resource/RRF/RxTerms201307.txt"));		
		while (file1.hasNext()){
                stringRead = file1.nextLine();
                result = stringRead.split("\\|");
                RXCUI = result[0] ;  
                GENERIC_RXCUI = result[1];  
                TTY = result[2];  
                FULL_NAME = result[3];     
                RXN_DOSE_FORM = result[4];     
                FULL_GENERIC_NAME = result[5];
                BRAND_NAME = result[6];
                DISPLAY_NAME = result[7];
                ROUTE = result[8];
                NEW_DOSE_FORM = result[9];
                STRENGTH = result[10];
                SUPPRESS_FOR = result[11];     
                DISPLAY_NAME_SYNONYM = result[12];      
                IS_RETIRED = result[13];                  
                SXDG_RXCUI = result[14];  
                SXDG_TTY = result[15]; 
                SXDG_NAME = result[16];  
          RxTerm rt = new RxTerm( RXCUI, GENERIC_RXCUI, TTY, FULL_NAME, RXN_DOSE_FORM,
					 FULL_GENERIC_NAME, BRAND_NAME, DISPLAY_NAME, ROUTE, NEW_DOSE_FORM,
					 STRENGTH, SUPPRESS_FOR, DISPLAY_NAME_SYNONYM, IS_RETIRED, SXDG_RXCUI,
					 SXDG_TTY, SXDG_NAME);
            termList.add(rt);
        
                     
                        }
         }
         catch(IOException ioe){
             ioe.printStackTrace();
         }
      
		System.out.println("Finish adding RxTerms objects");       
       
        
        
        System.out.println("Export using the dummy exporter: com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
        try {
            Class theClass  = Class.forName("com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
            Exporter exporter = (Exporter) theClass.newInstance();
            exporter.ConnectDatabase();
            exporter.export(consoList,termList);
            System.out.println("Term table created!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
                
    //Normalize NDC
    try{
        Class theClass  = Class.forName("com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
        Exporter exporter = (Exporter) theClass.newInstance();
        exporter.ConnectDatabase();
        Scanner file1 = new Scanner(new File("C:/resource/outputNDC.txt"));
                
        while (file1.hasNext()){
              stringRead = file1.nextLine();
              StringTokenizer st = new StringTokenizer(stringRead,",");
              String NDC_CODE = st.nextToken();
              String NDC_RXCUI = st.nextToken();
              String NDC_SAB = st.nextToken();
              NDC ndc1 = new NDC(NDC_CODE,NDC_RXCUI);
              exporter.NORMALIZE_NDC(ndc1,NDC_RXCUI,NDC_SAB);
              
              
        }
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
    
}
