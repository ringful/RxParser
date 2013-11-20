package com.ringfulhealth.rxparser.exporter.RingfulNIDA2013;
import com.ringfulhealth.rxparser.entity.rxnorm.*;
import com.ringfulhealth.rxparser.entity.rxterms.RxTerm;
import com.ringfulhealth.rxparser.exporter.Exporter;
import com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity.NDC;
import com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity.Term;
import java.util.ArrayList;
import java.sql.*;
import java.io.*;
import java.util.*;
import org.apache.commons.lang.StringUtils;

public class RingfulExporter implements Exporter {
    private PreparedStatement insertNDC = null;
    private PreparedStatement insertTerm = null;
    private PreparedStatement createTable = null;
    private Connection conn = null;
    private ArrayList<NDC> NDCList = null;
    
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

    //CREATE NDC TABLE
    @Override
    public void export (RXNSAT SATObj) throws Exception {
        try{
        	if (SATObj.getATN().equals("NDC")  ){
                //   Create NDC object with RXNSAT.ATV and RXNSAT.RXCUI
                NDC ndcObj = new NDC(SATObj.getATV(),SATObj.getRXCUI());  
                
             /*   insertNDC = conn.prepareStatement("INSERT INTO NDC VALUES(?,?)");
                insertNDC.setString(1, ndcObj.getNDC_CODE());
                insertNDC.setString(2, ndcObj.getRXCUI());
                insertNDC.executeUpdate(); 
          
             */
            }

        
        }
        catch(Exception e){
          System.err.println(e.getMessage());
        } 
      save();
              	
    }
            
 // CREATING TERM TABLE
 public void export (ArrayList<RXNCONSO> CONSOList, 
                            ArrayList<RxTerm> RxTermsList) throws Exception{ 
     ArrayList<NDC> NDCList = new ArrayList<NDC>();
     Scanner file = new Scanner(new File("C:/resource/RRF/NDC.txt"));
     String NDC_CODE, NDC_RXCUI,stringRead = "";
    // boolean s = StringUtils.isAlphanumeric(stringRead);
     while(file.hasNext()){
         stringRead = file.nextLine();
         StringTokenizer st = new StringTokenizer(stringRead,",");
         while(st.hasMoreTokens()){
             NDC_CODE = st.nextToken();
             NDC_RXCUI = st.nextToken();
             NDC nd = new NDC(NDC_CODE,NDC_RXCUI);
             NDCList.add(nd);            
         }
     } 
     System.out.println("Created NDC List");
     
    System.out.println("Adding to Term table....");
     try{        
         ArrayList<RXNCONSO> selected_CONSO_List =  new ArrayList<RXNCONSO>();  
         ArrayList<RXNCONSO> CONSO_NOT_MDDB_List = new ArrayList<RXNCONSO>();
         ArrayList<RXNCONSO> CONSO_MDDB_List = new ArrayList<RXNCONSO>();
                           
         //For each unique RXCUI in the NDC table, find all matching rows in RXNCONSO  
         // If matched, add to selected_CONSO_List
               
      String saveKey = "unknown";
      for ( int k = 0; k < NDCList.size() ; k++) {         
          if(!(NDCList.get(k).getRXCUI().equals(saveKey))){          
             for(RXNCONSO consoObj : CONSOList){
                if((NDCList.get(k).getRXCUI().equals(consoObj.getRXCUI()))){
                    selected_CONSO_List.add(consoObj);
                }
             }
             
             //Check if selected_CONSO_List has only 1 row or more than 1 row
             
             //if selected_CONSO_List has more than 1 row
             if (!selected_CONSO_List.isEmpty()){
                 if(selected_CONSO_List.size() == 1){
                     WRITE_TO_TERM_TABLE(selected_CONSO_List.get(0),RxTermsList);
                     //System.out.println("Return only one row: " + selected_CONSO_List.get(0).getRXCUI());
                 }
                 else{
                    for( RXNCONSO selected_CONSO_Obj :selected_CONSO_List){
                        if (selected_CONSO_Obj.getSAB().equals("MDDB")){
                            CONSO_MDDB_List.add(selected_CONSO_Obj);
                         //   System.out.println("Added to CONSO_MDDB_List: "+ selected_CONSO_Obj.getRXCUI());
                        }                      
                    }
                    if(CONSO_MDDB_List.size() >= 1){
                         WRITE_TO_TERM_TABLE(CONSO_MDDB_List.get(0),RxTermsList);      
                      //   System.out.println("Return only one or multiple row that has MDDB: " + CONSO_MDDB_List.get(0).getRXCUI());
                        }
                    if(CONSO_MDDB_List.isEmpty()){
                         WRITE_TO_TERM_TABLE(selected_CONSO_List.get(0),RxTermsList);
                         //System.out.println("Return first row that does not have MDDB: " + selected_CONSO_List.get(0).getRXCUI());         
                               }
                                              
                 }
               selected_CONSO_List.clear();
                 CONSO_MDDB_List.clear();
                 
          }
             saveKey = NDCList.get(k).getRXCUI();
          }
          else
              saveKey = NDCList.get(k).getRXCUI();
             
      }     
     }
     catch(Exception e){
     System.err.println(e.getMessage());
     } 
      save();
 }  
    
 void WRITE_TO_TERM_TABLE (RXNCONSO CONSOObj,ArrayList<RxTerm> RxTermsList) throws Exception{
     String RXCUI = CONSOObj.getRXCUI();
     //  System.out.println(RXCUI);
     String RxNormName = CONSOObj.getSTR();
     String MDDB_ID = CONSOObj.getCODE();
     boolean find = true;
     int INDEX_FOUND = 0;
   try{    
     //Look up the RXCUI from RxTerms and populate the rest of the fields if available
     while(INDEX_FOUND < RxTermsList.size() && find){ 
        
         if(RxTermsList.get(INDEX_FOUND).getRXCUI().equals(RXCUI)){          
        //     RxTermsList.get(INDEX_FOUND).setRxNormName(RxNormName);
        //  System.out.println("Machted RXCUI: "+RxTermsList.get(INDEX_FOUND).getFULL_NAME());   
             //MDDB ID (use -1 if missing) RXNCONSO.CODE
             if (MDDB_ID.length() > 0)
                RxTermsList.get(INDEX_FOUND).setMDDB_ID(MDDB_ID);
             else 
                RxTermsList.get(INDEX_FOUND).setMDDB_ID("-1");
         find = false;
         } 
         INDEX_FOUND++;
         
     }
     if(!find){
            insertTerm = conn.prepareStatement("INSERT INTO Term VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            insertTerm.setString(1,RXCUI );
            insertTerm.setString(2,RxNormName );
            insertTerm.setString(3,MDDB_ID );
            insertTerm.setString(4,RxTermsList.get(INDEX_FOUND).getGENERIC_RXCUI() );
            insertTerm.setString(5,RxTermsList.get(INDEX_FOUND).getTTY() );
            insertTerm.setString(6,RxTermsList.get(INDEX_FOUND).getFULL_NAME());
            insertTerm.setString(7,RxTermsList.get(INDEX_FOUND).getRXN_DOSE_FORM());
            insertTerm.setString(8,RxTermsList.get(INDEX_FOUND).getFULL_GENERIC_NAME() );
            insertTerm.setString(9,RxTermsList.get(INDEX_FOUND).getBRAND_NAME());
            insertTerm.setString(10,RxTermsList.get(INDEX_FOUND).getDISPLAY_NAME() );
            insertTerm.setString(11,RxTermsList.get(INDEX_FOUND).getROUTE());
            insertTerm.setString(12,RxTermsList.get(INDEX_FOUND).getNEW_DOSE_FORM() );
            insertTerm.setString(13,RxTermsList.get(INDEX_FOUND).getSTRENGTH() );
            insertTerm.setString(14,RxTermsList.get(INDEX_FOUND).getSUPPRESS_FOR() );
            insertTerm.setString(15,RxTermsList.get(INDEX_FOUND).getDISPLAY_NAME_SYNONYM() );
            insertTerm.setString(16,RxTermsList.get(INDEX_FOUND).getIS_RETIRED() );
            insertTerm.setString(17,RxTermsList.get(INDEX_FOUND).getSXDG_RXCUI() );
            insertTerm.setString(18,RxTermsList.get(INDEX_FOUND).getSXDG_TTY());
            insertTerm.setString(19,RxTermsList.get(INDEX_FOUND).getSXDG_NAME() );
            insertTerm.executeUpdate();  
            
           find = true; 
        //   System.out.println("Added to Term table: " +RXCUI);
     }
         else{
            // System.out.println(" NOT Machted RXCUI: "+RxTermsList.get(INDEX_FOUND).getFULL_NAME());   
            insertTerm = conn.prepareStatement("INSERT INTO Term VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            insertTerm.setString(1,RXCUI );
            insertTerm.setString(2,RxNormName );
            insertTerm.setString(3,MDDB_ID );
            insertTerm.setString(4,"-1" );
            insertTerm.setString(5,"-1");
            insertTerm.setString(6,"-1");
            insertTerm.setString(7,"-1");
            insertTerm.setString(8,"-1");
            insertTerm.setString(9,"-1");
            insertTerm.setString(10,"-1");
            insertTerm.setString(11,"-1");
            insertTerm.setString(12,"-1" );
            insertTerm.setString(13,"-1" );
            insertTerm.setString(14,"-1" );
            insertTerm.setString(15,"-1");
            insertTerm.setString(16,"-1");
            insertTerm.setString(17,"-1");
            insertTerm.setString(18,"-1");
            insertTerm.setString(19,"-1" );
            insertTerm.executeUpdate();  
            find = true;
      //       System.out.println("Added to Term table: " +RXCUI);
         }
     
     
 }
    catch(SQLException e){
        System.out.println("ERROR: CAUGHT");
      System.err.println(e.getMessage());
    } 
      save();
 }
 public void NORMALIZE_NDC(NDC ndc, String RXCUI, String v_rsab)throws Exception{
     String v_format="";
     String v_ndc = ndc.getNDC_CODE();
     String ret_ndc="";
		if( v_ndc.length() - v_ndc.replaceAll("-","").length() == 2){ // If NDC string contains 2 dashes
			// find the format of the NDC string.  Format could be 6-4-2, 6-4-1, 5-4-2, etc.
			int first = v_ndc.indexOf('-'); //  find number of digits before first dash
			int mid = v_ndc.indexOf('-',(v_ndc.indexOf('-')+1)) - (v_ndc.indexOf('-')+1) ;	// digit number after first dash
				// number of digits after second dash
			int last =v_ndc.length() - (v_ndc.indexOf('-') +(v_ndc.indexOf('-',(v_ndc.indexOf('-')+1)) - (v_ndc.indexOf('-')+1)) ) -2 ;
			v_format = (first+"-"+mid+"-"+last);
			//System.out.println(v_format);
			//  v_format now contains ‘X-X-X’
			//  Format has been identified.  Convert into normalized NDC
			
			if(!v_format.isEmpty()){ //  source NDC string contains dashes
				if (v_format.equals("6-4-2")){
					ret_ndc = v_ndc.substring(1,6)  // drop first digit of string
								+ v_ndc.substring(7,11) // all four digits after first dash
									+ v_ndc.substring(12); //  last 2 digits after second dash
				}
				else if (v_format.equals("6-4-1")){ // drop first digit of string
					ret_ndc = v_ndc.substring(1,6) 
								+ v_ndc.substring(7,11) 
									+ "0"  			// pad last digit to 2 chars
										+ v_ndc.substring(12);
				}
				else if (v_format.equals("6-3-2")){     // drop first digit of string
					ret_ndc = v_ndc.substring(1,6) 
								+ "0"				// drop first digit of string
									+ v_ndc.substring(7,10)
										+ v_ndc.substring(11);
				}
				else if (v_format.equals("6-3-1")){    // drop first digit of string
					ret_ndc = v_ndc.substring(1,6) 
								+ "0"				// pad second digit to 4 chars
									 + v_ndc.substring(7,10)
										+ "0"			// pad third digit to 2 chars
											+ v_ndc.substring(11);
				}
				else if (v_format.equals("5-4-2")){	// keep all digits
					ret_ndc = v_ndc.substring(0,5)
								+ v_ndc.substring(6,10)
									+ v_ndc.substring(11);
				}
				else if (v_format.equals("5-4-1")){    // keep all digits
					ret_ndc = v_ndc.substring(0,5)
								+ v_ndc.substring(6,10)
									+ "0"				// pad third digit to 2 chars
										+  v_ndc.substring(11);
				}
				else if (v_format.equals("5-3-2")){		// keep all digits
					ret_ndc = v_ndc.substring(0,5)
								+ "0"				//  pad second digit to 4 chars
									+ v_ndc.substring(6,9)
										+ v_ndc.substring(10);
				}
				else if (v_format.equals("4-4-2")){			// keep all digits
					ret_ndc = "0"						// pad first digit to 5 chars
								+ v_ndc.substring(0,4)
									+  v_ndc.substring(5,9)
										+ v_ndc.substring(10);
									
				}
			}
		}
		
		     //  If NDC passed has 11 digits without any '-' then return input NDC as Normalized NDC value
			 //  NDC string is already in normalized format, it does not contain any dashes

		else if (v_ndc.indexOf('-') == -1 && v_ndc.length() == 11)
			ret_ndc = v_ndc;
			
			  //  If NDC passed has 12 digits and first char is '0' and its from VANDF then trim first char 
			  //  VA sometimes pads '0' to make it 12 digit NDC
		else if (v_ndc.indexOf('-') == -1 // no dashes in string
					&& v_ndc.length() == 12
						&& v_ndc.substring(0,1).equals("0") // first digit is 0
							&& v_rsab.equals("VANDF")){
			ret_ndc = v_ndc.substring(1);			
		}
		//  For any other cases, return NULL.  String cannot be normalized
		else{
			ret_ndc = "";
			//System.out.println(ret_ndc.length());
		}
	      //  Replace '*' with '0' as some of the NDCs from MTHFDA contain * instead of 0	
		ret_ndc = ret_ndc.replaceAll("\\*","0");
		  //  Check to see if NDC value contains any Alphanumeric values, if yes, then its an invalid NDC code
		if (ret_ndc.replaceAll("[0-9]","").trim().length() != 0){
			ret_ndc = "";
		}
		//System.out.println("length "+ret_ndc.length());
		System.out.println(ret_ndc);
                
                if(ret_ndc.length() > 0){
                    insertNDC = conn.prepareStatement("INSERT INTO NDC VALUES(?,?)");
                    insertNDC.setString(1, ret_ndc);
                    insertNDC.setString(2, RXCUI);
                    insertNDC.executeUpdate();       
                }
		
     
     
 }
 
 
 
 
 
    
         
    //public void export (RXNCONSO CONSOObj, RxTerm rxTermObj) throws Exception{}
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
                      + "NEW_DOSE_FORM VARCHAR(35), STRENGTH VARCHAR(35),SXDG_NAME VARCHAR(35),MDDB_ID VARCHAR(35));");
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