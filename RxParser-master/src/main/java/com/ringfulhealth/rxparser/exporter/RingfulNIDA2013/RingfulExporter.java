package com.ringfulhealth.rxparser.exporter.RingfulNIDA2013;
import com.ringfulhealth.rxparser.entity.rxnorm.*;
import com.ringfulhealth.rxparser.entity.rxterms.RxTerm;
import com.ringfulhealth.rxparser.exporter.Exporter;
import com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity.NDC;
import com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity.Term;
import java.util.ArrayList;
import java.sql.*;


public class RingfulExporter implements Exporter {
    private PreparedStatement insertNDC = null;
    private PreparedStatement insertTerm = null;
    private PreparedStatement createTable = null;
    private Connection conn = null;
    
    public void ConnectDatabase() throws ClassNotFoundException{
        try{
		Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:sample_drug_data.s3db");
                System.out.println ("Database connection established");
	}
        catch(SQLException e){
	      System.err.println(e.getMessage());
        }
	    
    }
    @Override
    public void export (RXNSAT SATObj) throws Exception {

        // Iterate through all RXNSAT objects
            // Match RXNSAT.ATN == NDC
      try{
            if (SATObj.getATN().equals("NDC")  ){
                //   Create NDC object with RXNSAT.ATV and RXNSAT.RXCUI
                NDC ndcObj = new NDC(SATObj.getATV(),SATObj.getRXCUI());         
                insertNDC = conn.prepareStatement("INSERT INTO NDC VALUES(?,?)");
                insertNDC.setString(1, ndcObj.getNDC_CODE());
                insertNDC.setString(2, ndcObj.getRXCUI());
                insertNDC.executeUpdate();     
            }
        }
        catch(SQLException e){
          System.err.println(e.getMessage());
        } 
      save();
              	
    }
                              
         
    public void export (RXNCONSO CONSOObj, RxTerm rxTermObj) throws Exception{}
  /*  
            //   Iterate through RXNCONSO objects with RXNCONSO.RXCUI == RxTerm.RXCUI
     try{       
                if(CONSOObj.getRXCUI().equals(rxTermObj.getRXCUI())){
                    //     Match RXNCONSO.SAB == MDDB
                    if(CONSOObj.getSAB().equals("MDDB")){
                        // Create Term object
                        Term termObj = new Term(rxTermObj.getRXCUI(),rxTermObj.getFULL_NAME(),rxTermObj.getRXN_DOSE_FORM(),
                                                    rxTermObj.getFULL_GENERIC_NAME(),rxTermObj.getBRAND_NAME(),
                                                    rxTermObj.getDISPLAY_NAME(),rxTermObj.getROUTE(),rxTermObj.getNEW_DOSE_FORM(),
                                                    rxTermObj.getSTRENGTH(),rxTermObj.getSXDG_NAME(),CONSOObj.getCODE());
                                                    //Last argument:  Set Term.MDDB_ID = RXNCONSO.CODE                                       
                        insertTerm = conn.prepareStatement("INSERT INTO Term VALUES(?,?,?,?,?,?,?,?,?,?)");
                        insertTerm.setString(1,termObj.getRXCUI() );
                        insertTerm.setString(2,termObj.getFULL_NAME() );
                        insertTerm.setString(3,termObj.getRXN_DOSE_FORM() );
                        insertTerm.setString(4,termObj.getFULL_GENERIC_NAME() );
                        insertTerm.setString(5,termObj.getBRAND_NAME() );
                        insertTerm.setString(6,termObj.getDISPLAY_NAME() );
                        insertTerm.setString(7,termObj.getROUTE() );
                        insertTerm.setString(8,termObj.getNEW_DOSE_FORM() );
                        insertTerm.setString(9,termObj.getSTRENGTH() );
                        insertTerm.setString(10,termObj.getSXDG_NAME() );
                        insertTerm.setString(11,termObj.getMDDB_ID() );
                        insertNDC.executeUpdate();
                    }
                }
        
     }
    catch(SQLException e){
      System.err.println(e.getMessage());
    } 
      save();  

    } 

            */
            
            
            
            
      /*  
        System.out.println("Constructing Term array");
        
         // Iterate through all RxTerm objects
        for(RxTerm rxTermObj : RxTermsList){
            //   Iterate through RXNCONSO objects with RXNCONSO.RXCUI == RxTerm.RXCUI
            for(RXNCONSO conSoObj : CONSOList){
                if(conSoObj.getRXCUI().equals(rxTermObj.getRXCUI())){
                    //     Match RXNCONSO.SAB == MDDB
                    if(conSoObj.getSAB().equals("MDDB")){
                        
                        Term termObj = new Term(rxTermObj.getRXCUI(),rxTermObj.getFULL_NAME(),rxTermObj.getRXN_DOSE_FORM(),
                                                    rxTermObj.getFULL_GENERIC_NAME(),rxTermObj.getBRAND_NAME(),
                                                    rxTermObj.getDISPLAY_NAME(),rxTermObj.getROUTE(),rxTermObj.getNEW_DOSE_FORM(),
                                                    rxTermObj.getSTRENGTH(),rxTermObj.getSXDG_NAME(),conSoObj.getCODE());
                                                                //Last argument:  Set Term.MDDB_ID = RXNCONSO.CODE
                        TermList.add(termObj);
                       
                    }
                }
            }
        }
       */
      // Adding the two arrays to sqlite database
        
        

       
    /*                          
        //Adding table Term
        createTable = conn.prepareStatement("CREATE TABLE Term(RXCUI VARCHAR(10), FULL_NAME VARCHAR(35),"
                             + "RXN_DOSE_FORM VARCHAR(35),FULL_GENERIC_NAME VARCHAR(35),"
                         + "BRAND_NAME VARCHAR(35), DISPLAY_NAME VARCHAR(35),ROUTE VARCHAR(35),"
                      + "NEW_DOSE_FORM VARCHAR(35), STRENGTH VARCHAR(35),SXDG_NAME VARCHAR(35),MDDB_ID VARCHAR(35);");
        if(insertTerm == null){
            for(Term t:TermList){
                insertTerm = conn.prepareStatement("INSERT INTO Term VALUES(?,?,?,?,?,?,?,?,?,?)");
                insertTerm.setString(1,t.getRXCUI() );
                insertTerm.setString(2,t.getFULL_NAME() );
                insertTerm.setString(3,t.getRXN_DOSE_FORM() );
                insertTerm.setString(4,t.getFULL_GENERIC_NAME() );
                insertTerm.setString(5,t.getBRAND_NAME() );
                insertTerm.setString(6,t.getDISPLAY_NAME() );
                insertTerm.setString(7,t.getROUTE() );
                insertTerm.setString(8,t.getNEW_DOSE_FORM() );
                insertTerm.setString(9,t.getSTRENGTH() );
                insertTerm.setString(10,t.getSXDG_NAME() );
                insertTerm.setString(11,t.getMDDB_ID() );
                insertNDC.executeUpdate();
                            
            }
        }
       
        */

    
    
    private void save() throws Exception{
		try{
                    if(insertNDC != null)
                    {
                            insertNDC.close();
                    }
                    if(insertTerm != null)
                    {
                            insertTerm.close();
                    }                    
                }
                catch(Exception e){
                    throw e;
		}
}
}