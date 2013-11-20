
import sys, sqlite3;

conn = sqlite3.connect(sys.argv[2]);
conn.text_factory = str;
c = conn.cursor();

with open (sys.argv[1]) as f:
    for line in f:
        fdata = line.strip().split("|");
        c.execute("INSERT INTO RXNSAT (RXCUI, LUI, SUI, RXAUI, STYPE, CODE, ATUI, SATUI, ATN, SAB, ATV, SUPPRESS, CVF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", (fdata[0], fdata[1], fdata[2], fdata[3], fdata[4], fdata[5], fdata[6], fdata[7], fdata[8], fdata[9], fdata[10], fdata[11], fdata[12]));

conn.commit();
conn.close();
