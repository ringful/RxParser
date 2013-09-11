package com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity;

public class Term {
    // from RxTerm
    String RXCUI;
    String FULL_NAME;
    String RXN_DOSE_FORM;
    String FULL_GENERIC_NAME;
    String BRAND_NAME;
    String DISPLAY_NAME;
    String ROUTE;
    String NEW_DOSE_FORM;
    String STRENGTH;
    String SXDG_NAME;
    
    // from RXNCONSO.CODE where SAB==MDDB
    String MDDB_ID;
    
    
    public Term(String RXCUI,String FULL_NAME,String RXN_DOSE_FORM,
		String FULL_GENERIC_NAME,String BRAND_NAME,String DISPLAY_NAME,String ROUTE,String NEW_DOSE_FORM,
                String STRENGTH,String SXDG_NAME,String MDDB_ID){
		this.RXCUI = RXCUI; 
		this.FULL_NAME = FULL_NAME;
		this.RXN_DOSE_FORM = RXN_DOSE_FORM;
		this.FULL_GENERIC_NAME = FULL_GENERIC_NAME;
		this.BRAND_NAME = BRAND_NAME;
		this.DISPLAY_NAME = DISPLAY_NAME;
		this.ROUTE = ROUTE;
		this.NEW_DOSE_FORM = NEW_DOSE_FORM;
		this.STRENGTH = STRENGTH;
		this.SXDG_NAME = SXDG_NAME;  
                this.MDDB_ID = MDDB_ID;
    }
    
	public String getRXCUI(){
		return RXCUI;
	}
	public String getFULL_NAME(){
		return FULL_NAME;
	}
	public String getRXN_DOSE_FORM(){
		return RXN_DOSE_FORM;
	}
	public String getFULL_GENERIC_NAME(){
		return FULL_GENERIC_NAME;
	}
	public String getBRAND_NAME(){
		return BRAND_NAME;
	}
	public String getDISPLAY_NAME(){
		return DISPLAY_NAME;
	}
	public String getROUTE(){
		return ROUTE;
	}
	public String getNEW_DOSE_FORM(){
		return NEW_DOSE_FORM;
	}
	public String getSTRENGTH(){
		return STRENGTH;
	}
	public String getSXDG_NAME(){
		return SXDG_NAME;
	}
	public String getMDDB_ID(){
		return MDDB_ID;
	}       

    public void setMDDB_ID(String MDDB_ID){
        this.MDDB_ID = MDDB_ID;
    }
}