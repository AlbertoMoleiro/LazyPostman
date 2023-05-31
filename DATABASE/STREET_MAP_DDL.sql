CREATE TABLE madrid_streets (
    id serial PRIMARY KEY,
    CDMUNI int,
    DSMUNI VARCHAR(100),
    CDTVIA VARCHAR(10),
    DSPART VARCHAR(10),
    DSVIAL VARCHAR(200),
    DSVIAL_NOR VARCHAR(200),
    NUMERO int,
    COORD_X real,
    COORD_Y real
);

COPY madrid_streets (CDMUNI, DSMUNI, CDTVIA, DSPART, DSVIAL, DSVIAL_NOR, NUMERO, COORD_X, COORD_Y)
FROM 'C:\cargadatos\callejerov1.csv' 
DELIMITER ';' 
CSV HEADER
ENCODING 'windows-1251';



