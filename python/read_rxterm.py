
import sys, sqlite3;

conn = sqlite3.connect(sys.argv[2]);
conn.text_factory = str;
c = conn.cursor();

with open (sys.argv[1]) as f:
    for line in f:
        fdata = line.strip().split("|");
        if fdata[0] != "RXCUI":
            c.execute("INSERT INTO RXTERMS (RXCUI, GENERIC_RXCUI, TTY, FULL_NAME, RXN_DOSE_FORM, FULL_GENERIC_NAME, BRAND_NAME, DISPLAY_NAME, ROUTE, NEW_DOSE_FORM, STRENGTH, SUPPRESS_FOR, DISPLAY_NAME_SYNONYM, IS_RETIRED, SXDG_RXCUI, SXDG_TTY, SXDG_NAME) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (fdata[0], fdata[1], fdata[2], fdata[3], fdata[4], fdata[5], fdata[6], fdata[7], fdata[8], fdata[9], fdata[10], fdata[11], fdata[12], fdata[13], fdata[14], fdata[15], fdata[16]));

conn.commit();
conn.close();
