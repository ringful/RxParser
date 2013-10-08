package com.ringfulhealth.rxparser.exporter;
import com.ringfulhealth.rxparser.exporter.RingfulNIDA2013.entity.NDC;
import com.ringfulhealth.rxparser.entity.rxnorm.RXNCONSO;
import com.ringfulhealth.rxparser.entity.rxnorm.RXNSAT;
import com.ringfulhealth.rxparser.entity.rxterms.RxTerm;
import java.util.ArrayList;

public interface Exporter {
    
    public void ConnectDatabase()throws Exception;
    public void export (RXNSAT SATObj) throws Exception;
    public void export (ArrayList<RXNCONSO> CONSOList, 
                            ArrayList<RxTerm> RxTermsList) throws Exception;
    public void NORMALIZE_NDC(NDC ndc, String RXCUI, String str) throws Exception;
    
    
}