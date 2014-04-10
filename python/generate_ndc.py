# This script only writes NDC codes that matches RxTerms
import sys, sqlite3;

def lookup_mddb_ndc (_conn, _ndc11) :
    # print "NDC11 is " + _ndc11;
    # MDDB stuff
    _mddb = ["", "", ""];

    concept_id = "NONE";
    for _row in _conn.execute("SELECT * FROM IMMCNCPT WHERE NDC11='" + _ndc11 + "'"):
        concept_id = _row['ConceptID'];
        # print "concept_id is " + concept_id;
    for _row in _conn.execute("SELECT * FROM IMMLINK WHERE ConceptID='" + concept_id + "'"):
        if _row['ContentType'] == '00001' :
            _mddb[0] = _row['Filename'];
        if _row['ContentType'] == '00002' :
            _mddb[1] = _row['Filename'];

    drug_id = "NONE";
    for _row in _conn.execute("SELECT * FROM IM2UDRUG WHERE NDC11='" + _ndc11 + "'"):
        drug_id = _row['DrugID'];
        # print "drug_id is " + drug_id;
    image_id = "NONE";
    for _row in _conn.execute("SELECT * FROM IM2UDIJ WHERE DrugID='" + drug_id + "'"):
        image_id = _row['ImageID'];
        # print "image_id is " + image_id;
    for _row in _conn.execute("SELECT * FROM IM2IMG WHERE ImageID='" + image_id + "'"):
        _mddb[2] = _row['Filename'];

    print "MDDB lookup ... NDC11:" + _ndc11 + " XML:" + _mddb[0] + " PDF:" + _mddb[1] + " IMG:" + _mddb[2];
    return _mddb;


conn_in = sqlite3.connect(sys.argv[1]);
conn_in.text_factory = str;
conn_in.row_factory = sqlite3.Row;
c_in = conn_in.cursor();
conn_out = sqlite3.connect(sys.argv[2]);
conn_out.text_factory = str;
conn_out.row_factory = sqlite3.Row;
c_out = conn_out.cursor();

print "FETCH from RXTERMS ...";
c_in.execute("SELECT * FROM RXTERMS");
trows = c_in.fetchall();
print "DONE";

print "FETCH from RXNSAT ...";
c_in.execute("SELECT DISTINCT ATV, RXCUI FROM RXNSAT WHERE ATN='NDC'");
rows = c_in.fetchall();
print "DONE";

for row in rows :
    ndc_code = row[0];
    rxcui = row[1];
    ndc_digits = ndc_code.replace("-", "");

    ndc11 = "";
    ndc_arr = ndc_code.strip().split("-");
    if len(ndc_arr) == 2 :
        if len(ndc_arr[0]) == 4 :
            ndc_arr[0] = "0" + ndc_arr[0];
        if len(ndc_arr[1]) == 5 :
            ndc_arr[1] = "0" + ndc_arr[1];
        ndc11 = ndc_arr[0] + ndc_arr[1];

    elif len(ndc_arr) == 3 :
        if len(ndc_arr[0]) == 4 :
            ndc_arr[0] = "0" + ndc_arr[0];
        if len(ndc_arr[1]) == 3 :
            ndc_arr[1] = "0" + ndc_arr[1];
        if len(ndc_arr[2]) == 1 :
            ndc_arr[2] = "0" + ndc_arr[2];
        ndc11 = ndc_arr[0] + ndc_arr[1] + ndc_arr[2];

    else :
        if len(ndc_digits) == 10 :
            ndc11 = "0" + ndc_digits;
        elif len(ndc_digits) == 9 :
            ndc11 = "00" + ndc_digits;
        else:
            ndc11 = ndc_digits;

    # print "Processing NDC RAW: " + ndc_code + " DIGITS: " + ndc_digits + " NDC11: " + ndc11 + " RXCUI: " + rxcui;

    valid_rxcui = 0;
    for trow in trows:
        if trow['RXCUI'] == rxcui:
            valid_rxcui = 1;
            break;

    if valid_rxcui == 1:
        if ndc_digits.find("*") == -1:
            mddb = lookup_mddb_ndc(c_in, ndc11);
            # c_out.execute("INSERT INTO NDC (RXCUI, NDC_DIGITS, NDC11, XML_FILENAME, PDF_FILENAME, IMG_FILENAME) VALUES (?, ?, ?, ?, ?, ?)", (rxcui, ndc_digits, ndc11, mddb[0], mddb[1], mddb[2]));
            c_out.execute("INSERT INTO NDC (RXCUI, NDC_DIGITS, PDF_FILENAME, IMG_FILENAME) VALUES (?, ?, ?, ?)", (rxcui, ndc_digits, mddb[1], mddb[2]));
        else:
            # This only deals with the case where we have one * in the NDC CODE
            for i in range(0, 9):
                ndc_digits_i = ndc_digits.replace("*", str(i));
                ndc11_i = ndc11.replace("*", str(i));
                mddb = lookup_mddb_ndc(c_in, ndc11_i);
                # c_out.execute("INSERT INTO NDC (RXCUI, NDC_DIGITS, NDC11, XML_FILENAME, PDF_FILENAME, IMG_FILENAME) VALUES (?, ?, ?, ?, ?, ?)", (rxcui, ndc_digits_i, ndc11_i, mddb[0], mddb[1], mddb[2]));
                c_out.execute("INSERT INTO NDC (RXCUI, NDC_DIGITS, PDF_FILENAME, IMG_FILENAME) VALUES (?, ?, ?, ?)", (rxcui, ndc_digits_i, mddb[1], mddb[2]));

conn_in.commit();
conn_in.close();
conn_out.commit();
conn_out.close();



