package com.ringfulhealth.rxparser.entity.rxterms;

public class RxTerm {
    String RXCUI;
    String RxNormName;
    String MDDB_ID;
    String GENERIC_RXCUI;
    String TTY;
    String FULL_NAME;
    String RXN_DOSE_FORM;
    String FULL_GENERIC_NAME;
    String BRAND_NAME;
    String DISPLAY_NAME;
    String ROUTE;
    String NEW_DOSE_FORM;
    String STRENGTH;
    String SUPPRESS_FOR;
    String DISPLAY_NAME_SYNONYM;
    String IS_RETIRED;
    String SXDG_RXCUI;
    String SXDG_TTY;
    String SXDG_NAME;
    
	
	public RxTerm(String RXCUI,String GENERIC_RXCUI,String TTY,String FULL_NAME,String RXN_DOSE_FORM,
					String FULL_GENERIC_NAME,String BRAND_NAME,String DISPLAY_NAME,String ROUTE,String NEW_DOSE_FORM,
					String STRENGTH,String SUPPRESS_FOR,String DISPLAY_NAME_SYNONYM,String IS_RETIRED,String SXDG_RXCUI,
					String SXDG_TTY,String SXDG_NAME){
		this.RXCUI = RXCUI;
		this.GENERIC_RXCUI = GENERIC_RXCUI;
		this.TTY = TTY;
		this.FULL_NAME = FULL_NAME;
		this.RXN_DOSE_FORM = RXN_DOSE_FORM;
		this.FULL_GENERIC_NAME = FULL_GENERIC_NAME;
		this.BRAND_NAME = BRAND_NAME;
		this.DISPLAY_NAME = DISPLAY_NAME;
		this.ROUTE = ROUTE;
		this.NEW_DOSE_FORM = NEW_DOSE_FORM;
		this.STRENGTH = STRENGTH;
		this.SUPPRESS_FOR = SUPPRESS_FOR;
		this.DISPLAY_NAME_SYNONYM = DISPLAY_NAME_SYNONYM;
		this.IS_RETIRED = IS_RETIRED;
		this.SXDG_RXCUI = SXDG_RXCUI;
		this.SXDG_TTY = SXDG_TTY;
		this.SXDG_NAME = SXDG_NAME;
	}
	
	public String getRXCUI(){
		return RXCUI;
	}
	public void setRXCUI(String RXCUI){
		this.RXCUI = RXCUI;
	}
        public String getRxNormName(){
                return RxNormName;
        }
	public void setRxNormName(String RxNormName){
		this.RxNormName = RxNormName;
        }	
 	public String getMDDB_ID(){
		return MDDB_ID;
	}
	public void setMDDB_ID(String MDDB_ID){
		this.MDDB_ID = MDDB_ID;
	}  
        
	public String getGENERIC_RXCUI(){
		return GENERIC_RXCUI;
	}
	public void setGENERIC_RXCUI(String GENERIC_RXCUI){
		this.GENERIC_RXCUI = GENERIC_RXCUI;
	}

	public String getTTY(){
		return TTY;
	}
	public void setTTY(String TTY){
		this.TTY = TTY;
	}

	public String getFULL_NAME(){
		return FULL_NAME;
	}
	public void setFULL_NAME(String FULL_NAME){
		this.FULL_NAME = FULL_NAME;
	}

	public String getRXN_DOSE_FORM(){
		return RXN_DOSE_FORM;
	}
	public void setRXN_DOSE_FORM(String RXN_DOSE_FORM){
		this.RXN_DOSE_FORM = RXN_DOSE_FORM;
	}

	public String getFULL_GENERIC_NAME(){
		return FULL_GENERIC_NAME;
	}
	public void setFULL_GENERIC_NAME(String FULL_GENERIC_NAME){
		this.FULL_GENERIC_NAME = FULL_GENERIC_NAME;
	}

	public String getBRAND_NAME(){
		return BRAND_NAME;
	}
	public void setBRAND_NAME(String BRAND_NAME){
		this.BRAND_NAME = BRAND_NAME;
	}

	public String getDISPLAY_NAME(){
		return DISPLAY_NAME;
	}
	public void setDISPLAY_NAME(String DISPLAY_NAME){
		this.DISPLAY_NAME = DISPLAY_NAME;
	}

	public String getROUTE(){
		return ROUTE;
	}
	public void setROUTE(String ROUTE){
		this.ROUTE = ROUTE;
	}	
	
	public String getNEW_DOSE_FORM(){
		return NEW_DOSE_FORM;
	}
	public void setNEW_DOSE_FORM(String NEW_DOSE_FORM){
		this.NEW_DOSE_FORM = NEW_DOSE_FORM;
	}	
	
	public String getSTRENGTH(){
		return STRENGTH;
	}
	public void setSTRENGTH(String STRENGTH){
		this.STRENGTH = STRENGTH;
	}

	public String getSUPPRESS_FOR(){
		return SUPPRESS_FOR;
	}
	public void setSUPPRESS_FOR(String SUPPRESS_FOR){
		this.SUPPRESS_FOR = SUPPRESS_FOR;
	}

	public String getDISPLAY_NAME_SYNONYM(){
		return DISPLAY_NAME_SYNONYM;
	}
	public void setDISPLAY_NAME_SYNONYM(String DISPLAY_NAME_SYNONYM){
		this.DISPLAY_NAME_SYNONYM = DISPLAY_NAME_SYNONYM;
	}

	public String getIS_RETIRED(){
		return IS_RETIRED;
	}
	public void setIS_RETIRED(String IS_RETIRED){
		this.IS_RETIRED = IS_RETIRED;
	}

	public String getSXDG_RXCUI(){
		return SXDG_RXCUI;
	}
	public void setSXDG_RXCUI(String SXDG_RXCUI){
		this.SXDG_RXCUI = SXDG_RXCUI;
	}

	public String getSXDG_TTY(){
		return SXDG_TTY;
	}
	public void setSXDG_TTY(String SXDG_TTY){
		this.SXDG_TTY = SXDG_TTY;
	}

	public String getSXDG_NAME(){
		return SXDG_NAME;
	}
	public void setSXDG_NAME(String SXDG_NAME){
		this.SXDG_NAME = SXDG_NAME;
	}		
	
	
}