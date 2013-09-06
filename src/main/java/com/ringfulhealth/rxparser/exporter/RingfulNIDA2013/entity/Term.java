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
}