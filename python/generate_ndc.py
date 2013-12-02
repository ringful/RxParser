# This script only writes NDC codes that matches RxTerms
import sys, sqlite3;

conn_in = sqlite3.connect(sys.argv[1]);
conn_in.text_factory = str;
conn_in.row_factory = sqlite3.Row;
c_in = conn_in.cursor();
conn_out = sqlite3.connect(sys.argv[2]);
conn_out.text_factory = str;
conn_out.row_factory = sqlite3.Row;
c_out = conn_out.cursor();

c_in.execute("SELECT * FROM RXTERMS");
trows = c_in.fetchall();

for row in c_in.execute("SELECT DISTINCT ATV, RXCUI FROM RXNSAT WHERE ATN='NDC'"):
    ndc_code = row[0];
    rxcui = row[1];
    ndc_digits = ndc_code.replace("-", "");

    valid_rxcui = 0;
    for trow in trows:
        if trow['RXCUI'] == rxcui:
            valid_rxcui = 1;
            break;

    if valid_rxcui == 1:
        if ndc_digits.find("*") == -1:
            c_out.execute("INSERT INTO NDC (RXCUI, NDC_DIGITS) VALUES (?, ?)", (rxcui, ndc_digits));
        else:
            # This only deals with the case where we have one * in the NDC CODE
            for i in range(0, 9):
                ndc_digits_i = ndc_digits.replace("*", str(i));
                c_out.execute("INSERT INTO NDC (RXCUI, NDC_DIGITS) VALUES (?, ?)", (rxcui, ndc_digits_i));

conn_in.commit();
conn_in.close();
conn_out.commit();
conn_out.close();
