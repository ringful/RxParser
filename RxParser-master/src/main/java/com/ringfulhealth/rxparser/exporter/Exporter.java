package com.ringfulhealth.rxparser.exporter;

import com.ringfulhealth.rxparser.entity.rxnorm.RXNCONSO;
import com.ringfulhealth.rxparser.entity.rxnorm.RXNSAT;
import com.ringfulhealth.rxparser.entity.rxterms.RxTerm;
import java.util.ArrayList;

public interface Exporter {
    
    public void ConnectDatabase()throws Exception;
    public void export (RXNSAT SATObj) throws Exception;
    public void export (RXNCONSO CONSOObj, RxTerm TermObj) throws Exception;
    
    
}