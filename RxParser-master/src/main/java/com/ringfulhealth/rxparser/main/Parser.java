package com.ringfulhealth.rxparser.main;

import com.ringfulhealth.rxparser.exporter.Exporter;
import com.ringfulhealth.rxparser.entity.rxnorm.*;
import com.ringfulhealth.rxparser.entity.rxterms.RxTerm;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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
                exporter.export(satObj);
                
                   
             }
         }
         catch(Exception ioe){
             ioe.printStackTrace();
         }
		System.out.println("Finish adding RXNSAT objects");
              
                
*/
                
                
                
                
         // 1. Read in an array of RXNCONSO
	
             ArrayList<RXNCONSO> consoList = new ArrayList<RXNCONSO>();
         try{  
             
		Scanner file = new Scanner(new File("C:/resource/RRF/RXNCONSO.RRF"));		
		while (file.hasNext()){
                stringRead = file.nextLine();
             //  System.out.println(stringRead);
                 String [] result1 = stringRead.split("[|]");
             
                RXCUI = result1[0] ;   
           //System.out.println("1"+RXCUI+"  ");     
                LAT = result1[1];   
          // System.out.println("2"+LAT+"  ");      
            TS = result1[2];    
      //   System.out.println("3"+TS+"  ");        
                
                LUI = result1[3];              
       //    System.out.print("4"+LUI+"  ");      
              STT = result1[4];            
         //  System.out.print("5"+STT+"  ");      
                SUI = result1[5];           
           //System.out.print("6"+SUI+"  ");      
                ISPREF = result1[6];
  //         System.out.print("7"+ISPREF+"  ");      
               RXAUI = result1[7];
        //   System.out.println("8"+RXAUI+"  ");      
                SAUI = result1[8];
      ///      System.out.print("9"+SAUI+"  ");     
                SCUI = result1[9];
        //   System.out.print("10"+SCUI+"  ");      
                SDUI = result1[10];
        //   System.out.print("11"+SDUI+"  ");      
                SAB = result1[11];
        //   System.out.print("12"+SAB+"  ");      
                TTY = result1[12];
        //   System.out.print("13"+TTY+"  ");      
                CODE = result1[13];
       //    System.out.print("14"+CODE+"  ");      
                
                STR = result1[14];
         //  System.out.print("15"+STR+"  ");      
                SRL = result1[15];
        //    System.out.print("16"+SRL+"  ");     
                SUPPRESS = result1[16];
        //   System.out.print("17"+SUPPRESS+"  ");
                if(result1.length == 17)
                    CVF = "";
                else
                    CVF = result1[17];
             
              //  if (ct == 300000)
               //     System.gc();
		/*RXNCONSO conSoObj = new RXNCONSO(RXCUI,LAT, TS, LUI,STT,SUI,ISPREF,RXAUI,SAUI,SCUI,SDUI,SAB,
					TTY,CODE,STR,SRL,SUPPRESS,CVF);
                consoList.add(conSoObj);*/
                    
           
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
    /*    try {
            Class theClass  = Class.forName("com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.RingfulExporter");
            Exporter exporter = (Exporter) theClass.newInstance();
            exporter.ConnectDatabase();
            exporter.export(consoList,satList,termList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
    }
    
}
