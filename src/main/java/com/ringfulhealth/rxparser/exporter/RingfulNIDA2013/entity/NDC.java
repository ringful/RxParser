package com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity;


public class NDC {
    
   private String NDC_CODE;
   private String RXCUI;
  
 
public NDC(String NDC_CODE,String RXCUI){
    this.NDC_CODE = NDC_CODE;
    this.RXCUI = RXCUI;
   }

public String getNDC_CODE(){
    return NDC_CODE;
}

public String getRXCUI(){
    return RXCUI;
}


}