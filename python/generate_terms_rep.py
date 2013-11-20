# Problem with this script:
#   The RXCUI is NOT unique in RXNCONSO. We need the rows that has MDDB.

import sys, sqlite3;

conn_in = sqlite3.connect(sys.argv[1]);
conn_in.text_factory = str;
conn_in.row_factory = sqlite3.Row;
c_in = conn_in.cursor();
conn_out = sqlite3.connect(sys.argv[2]);
conn_out.text_factory = str;
conn_out.row_factory = sqlite3.Row;
c_out = conn_out.cursor();

c_in.execute("SELECT DISTINCT RXCUI, STR, SAB, CODE FROM RXNCONSO");
rows = c_in.fetchall();
for row in rows:
    MDDB_ID = "";
    if row['SAB'] == "MDDB":
        MDDB_ID = row['CODE'];

    c_in = conn_in.cursor();
    c_in.execute("SELECT * FROM RXTERMS WHERE RXCUI=?", (row[0],));
    trow = c_in.fetchone();
    if trow is None:
        c_out.execute("INSERT INTO RxTerms (RXCUI, RxNormName, MDDB_ID, SEARCH_STR) VALUES (?, ?, ?, ?)", (row[0], row[1], MDDB_ID, row[1].lower()));
    else:
        c_out.execute("INSERT INTO RxTerms (RXCUI, MDDB_ID, FULL_NAME, RXN_DOSE_FORM, FULL_GENERIC_NAME, BRAND_NAME, DISPLAY_NAME, ROUTE, NEW_DOSE_FORM, STRENGTH, SXDG_NAME, SEARCH_STR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (row[0], MDDB_ID, trow['FULL_NAME'], trow['RXN_DOSE_FORM'], trow['FULL_GENERIC_NAME'], trow['BRAND_NAME'], trow['DISPLAY_NAME'], trow['ROUTE'], trow['NEW_DOSE_FORM'], trow['STRENGTH'], trow['SXDG_NAME'], trow['FULL_NAME'].lower()));

conn_in.commit();
conn_in.close();
conn_out.commit();
conn_out.close();
