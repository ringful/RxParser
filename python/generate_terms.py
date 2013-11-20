
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
rows = c_in.fetchall();
for row in rows:
    c_in = conn_in.cursor();
    c_in.execute("SELECT STR, SAB, CODE FROM RXNCONSO WHERE RXCUI=?", (row['RXCUI'],));
    crows = c_in.fetchall();

    MDDB_ID = "";
    for crow in crows:
        if crow['SAB'] == "MDDB":
            MDDB_ID = crow['CODE'];

    c_out.execute("INSERT INTO RxTerms (RXCUI, MDDB_ID, FULL_NAME, RXN_DOSE_FORM, FULL_GENERIC_NAME, BRAND_NAME, DISPLAY_NAME, ROUTE, NEW_DOSE_FORM, STRENGTH, SXDG_NAME, SEARCH_STR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (row['RXCUI'], MDDB_ID, row['FULL_NAME'], row['RXN_DOSE_FORM'], row['FULL_GENERIC_NAME'], row['BRAND_NAME'], row['DISPLAY_NAME'], row['ROUTE'], row['NEW_DOSE_FORM'], row['STRENGTH'], row['SXDG_NAME'], row['FULL_NAME'].lower()));

conn_in.commit();
conn_in.close();
conn_out.commit();
conn_out.close();
