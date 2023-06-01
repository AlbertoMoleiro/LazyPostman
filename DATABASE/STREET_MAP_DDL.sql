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
-- La carpeta cargadatos debe tener permisos
COPY madrid_streets (CDMUNI, DSMUNI, CDTVIA, DSPART, DSVIAL, DSVIAL_NOR, NUMERO, COORD_X, COORD_Y)
FROM 'C:\cargadatos\callejerov1.csv' 
DELIMITER ';' 
CSV HEADER
ENCODING 'windows-1251';

CREATE TABLE postal_codes(
	id serial PRIMARY KEY,
	post_code INT,
	cdmuni INT,
	dsmuni VARCHAR(200)
)

COPY postal_codes (post_code,cdmuni,dsmuni)
FROM 'C:\cargadatos\POSTAL_CODES.csv' 
DELIMITER ',' 
CSV HEADER
ENCODING 'windows-1251';

--Procedimiento para que cuadren los codigos de municipio de las dos tablas
CREATE OR REPLACE PROCEDURE update_last_three_digits()
LANGUAGE plpgsql
AS $$
BEGIN
   UPDATE postal_codes SET cdmuni = cdmuni % 1000;
END;
$$;

CALL update_last_three_digits();



