package com.ringfulhealth.rxparser.entity.rxnorm;

/* 
  http://www.nlm.nih.gov/research/umls/rxnorm/overview.html
  http://www.nlm.nih.gov/research/umls/rxnorm/docs/2013/rxnorm_doco_full_2013-3.html#conso
*/

public class RXNCONSO {
    
    String RXCUI;
    String LAT;
    String TS;
    String LUI;
    String STT;
    String SUI;
    String ISPREF;
    String RXAUI;
    String SAUI;
    String SCUI;
    String SDUI;
    String SAB;
    String TTY;
    String CODE;
    String STR;
    String SRL;
    String SUPPRESS;
    String CVF;



	
	
	
	public RXNCONSO(String RXCUI,String LAT,String TS,String LUI,String STT,
					String SUI,String ISPREF,String RXAUI,String SAUI,String SCUI,
					String SDUI,String SAB,String TTY,String CODE,String STR,
					String SRL,String SUPPRESS,String CVF){
		this.RXCUI = RXCUI;
		this.LAT = LAT;
		this.TS = TS;
		this.LUI = LUI;
		this.STT = STT;
		this.SUI = SUI;
		this.ISPREF = ISPREF;
		this.SAUI = SAUI;
		this.SCUI = SCUI;
		this.SDUI = SDUI;
		this.SAB = SAB;
		this.TTY = TTY;
		this.CODE = CODE;
		this.STR = STR;
		this.SRL = SRL;
		this.SUPPRESS = SUPPRESS;
		this.CVF = CVF;
		
	}
	
	
	
	public String getRXCUI(){
		return RXCUI;
	}
	public void setRXCUI(String RXCUI){
		this.RXCUI = RXCUI;
	}

	public String getLAT(){
		return LAT;
	}
	public void setLAT(String LAT){
		this.LAT = LAT;
	}    
	
	public String getTS(){
		return TS;
	}
	public void setTS(String TS){
		this.TS = TS;
	}
	
	public String getLUI(){
		return LUI;
	}
	public void setLUI(String LUI){
		this.LUI = LUI;
	}
	
	public String getSTT(){
		return STT;
	}
	public void setSTT(String STT){
		this.STT = STT;
	}	
	
	public String getSUI(){
		return SUI;
	}
	public void setSUI(String SUI){
		this.SUI = SUI;
	}
	
	public String getISPREF(){
		return ISPREF;
	}
	public void setISPREF(String ISPREF){
		this.ISPREF = ISPREF;
	}	

	public String getRXAUI(){
		return RXAUI;
	}
	public void setRXAUI(String RXAUI){
		this.RXAUI = RXAUI;
	}	
	
	public String getSAUI(){
		return SAUI;
	}
	public void setSAUI(String SAUI){
		this.SAUI = SAUI;
	}

	public String getSCUI(){
		return SCUI;
	}
	public void setSCUI(String SCUI){
		this.SCUI = SCUI;
	}

	public String getSDUI(){
		return SDUI;
	}
	public void setSDUI(String SDUI){
		this.SDUI = SDUI;
	}
	
	public String getSAB(){
		return SAB;
	}
	public void setSAB(String SAB){
		this.SAB = SAB;
	}

	public String getTTY(){
		return TTY;
	}
	public void setTTY(String TTY){
		this.TTY = TTY;
	}

	public String getCODE(){
		return CODE;
	}
	public void setCODE(String CODE){
		this.CODE = CODE;
	}

	public String getSTR(){
		return STR;
	}
	public void setSTR(String STR){
		this.STR = STR;
	}
	
	public String getSRL(){
		return SRL;
	}
	public void setSRL(String SRL){
		this.SRL = SRL;
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