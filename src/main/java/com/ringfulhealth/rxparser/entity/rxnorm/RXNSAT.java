package com.ringfulhealth.rxparser.entity.rxnorm;

/* 
  http://www.nlm.nih.gov/research/umls/rxnorm/overview.html
  http://www.nlm.nih.gov/research/umls/rxnorm/docs/2013/rxnorm_doco_full_2013-3.html#sat
*/

public class RXNSAT {
    
    String RXCUI;
    String LUI;
    String SUI;
    String RXAUI;
    String STYPE;
    String CODE;
    String ATUI;
    String SATUI;
    String ATN;
    String SAB;
    String ATV;
    String SUPPRESS;
    String CVF;
	
	public RXNSAT(String RXCUI,String LUI,String SUI,String RXAUI,String STYPE,
					String CODE,String ATUI,String SATUI,String ATN,String SAB,
					String ATV,String SUPPRESS,String CVF){
		this.RXCUI = RXCUI;
		this.LUI = LUI;
		this.SUI = SUI;
		this.RXAUI = RXAUI;
		this.STYPE = STYPE;
		this.CODE = CODE;
		this.ATUI = ATUI;
		this.SATUI = SATUI;
		this.ATN = ATN;
		this.SAB = SAB;
		this.ATV = ATV;
		this.SUPPRESS = SUPPRESS;
		this.CVF = CVF;
		
	}
	public String getRXCUI(){
		return RXCUI;
	}
	public void setRXCUI(String RXCUI){
		this.RXCUI = RXCUI;
	}
	
	public String getLUI(){
		return LUI;
	}
	public void setLUI(String LUI){
		this.LUI = LUI;
	}
	
	public String getSUI(){
		return SUI;
	}
	public void setSUI(String SUI){
		this.SUI = SUI;
	}
	
	public String getRXAUI(){
		return RXAUI;
	}
	public void setRXAUI(String RXAUI){
		this.RXAUI = RXAUI;
	}

	public String getSTYPE(){
		return STYPE;
	}
	public void setSTYPE(String STYPE){
		this.STYPE = STYPE;
	}

	public String getCODE(){
		return CODE;
	}
	public void setCODE(String CODE){
		this.CODE = CODE;
	}
	
	public String getATUI(){
		return ATUI;
	}
	public void setATUI(String ATUI){
		this.ATUI = ATUI;
	}
	
	public String getSATUI(){
		return SATUI;
	}
	public void setSATUI(String SATUI){
		this.SATUI = SATUI;
	}
	
	public String getATN(){
		return ATN;
	}
	public void setATN(String ATN){
		this.ATN = ATN;
	}

	public String getSAB(){
		return SAB;
	}
	public void setSAB(String SAB){
		this.SAB = SAB;
	}	

	public String getATV(){
		return ATV;
	}
	public void setATV(String ATV){
		this.ATV = ATV;
	}

	public String getSUPPRESS(){
		return SUPPRESS;
	}
	public void setSUPPRESS(String SUPPRESS){
		this.SUPPRESS = SUPPRESS;
	}	
	
	public String getCVF(){
		return CVF;
	}
	public void setCVF(String CVF){
		this.CVF = CVF;
	}	
	
	
	
	
    
}