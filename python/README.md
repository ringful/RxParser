
CLEAN UP
* rm work/work.db
* rm work/drugs.db

INIT
* sqlite3 work/work.db < work.sql
* sqlite3 work/drugs.db < drugs.sql

READ
* python read_rxnsat.py data/RXNSAT.RRF work/work.db
* python read_rxnconso.py data/RXNCONSO.RRF work/work.db
* python read_rxterm.py data/RxTerms201307.txt work/work.db

WRITE
* python generate_ndc.py work/work.db work/drugs.db
* python generate_terms.py work/work.db work/drugs.db
