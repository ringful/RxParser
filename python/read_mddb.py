import sys, sqlite3;

conn = sqlite3.connect(sys.argv[2]);
conn.text_factory = str;
c = conn.cursor();

in_file_name = sys.argv[1] + "/IMMCNCPT"
with open (in_file_name) as f:
    for line in f:
        ConceptID = line[0:10];
        ConceptType = line[11:16];
        CountryCode = line[16:18];
        NDC11 = line[18:38];
        if ConceptType == "00007" and CountryCode == "01":
            c.execute("INSERT INTO IMMCNCPT (ConceptID, NDC11) VALUES (?, ?)", (ConceptID.strip(), NDC11.strip()));

in_file_name = sys.argv[1] + "/IMMLINK"
with open (in_file_name) as f:
    for line in f:
        ConceptID = line[0:10];
        ContentType = line[10:15];
        LangCode = line[15:17];
        Filename = line[17:49];
        if LangCode == "01":
            c.execute("INSERT INTO IMMLINK (ConceptID, ContentType, Filename) VALUES (?, ?, ?)", (ConceptID.strip(), ContentType.strip(), Filename.strip()));



in_file_name = sys.argv[1] + "/IM2UDRUG"
with open (in_file_name) as f:
    for line in f:
        DrugID = line[0:10];
        NDC11 = line[16:36];
        c.execute("INSERT INTO IM2UDRUG (DrugID, NDC11) VALUES (?, ?)", (DrugID.strip(), NDC11.strip()));

in_file_name = sys.argv[1] + "/IM2UDIJ"
with open (in_file_name) as f:
    for line in f:
        DrugID = line[0:10];
        ImageID = line[27:37];
        c.execute("INSERT INTO IM2UDIJ (DrugID, ImageID) VALUES (?, ?)", (DrugID.strip(), ImageID.strip()));

in_file_name = sys.argv[1] + "/IM2IMG"
with open (in_file_name) as f:
    for line in f:
        ImageID = line[0:10];
        Filename = line[11:31];
        c.execute("INSERT INTO IM2IMG (ImageID, Filename) VALUES (?, ?)", (ImageID.strip(), Filename.strip()));



conn.commit();
conn.close();
