--Antes de ejecutar este archivo debes crear una carpeta en C:\ llamada cargadatos y copiar los archivos callejerov1.csv y POSTAL_CODES.csv en ella

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
ENCODING 'UTF8';

CREATE TABLE postal_codes(
	id serial PRIMARY KEY,
	post_code INT,
	cdmuni INT,
	dsmuni VARCHAR(200)
);

COPY postal_codes (post_code,cdmuni,dsmuni)
FROM 'C:\cargadatos\POSTAL_CODES.csv' 
DELIMITER ';' 
CSV HEADER
ENCODING 'UTF8';

--Formatear codigos de municipio para que encajen
   UPDATE postal_codes SET cdmuni = cdmuni % 1000;




