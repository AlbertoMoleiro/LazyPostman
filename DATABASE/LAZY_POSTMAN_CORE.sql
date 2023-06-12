CREATE TABLE PROVINCES (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE towns (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    postal_code VARCHAR(10) NOT NULL,
    id_province INT,
    FOREIGN KEY (id_province) REFERENCES PROVINCES(id) ON DELETE SET NULL
);

CREATE TABLE COMPANIES (
    id SERIAL PRIMARY KEY,
    cif VARCHAR(255) NOT NULL UNIQUE,
    business_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255),
    email VARCHAR(255),
    id_town INT,
    address VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_town) REFERENCES TOWNS(id) ON DELETE SET NULL
);

CREATE TABLE PERMISSIONS (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE ROLES (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE ROLES_PERMISSIONS (
    id_role INT NOT NULL,
    id_permission INT NOT NULL,
    PRIMARY KEY (id_role, id_permission),
    FOREIGN KEY (id_role) REFERENCES ROLES(id) ON DELETE CASCADE,
    FOREIGN KEY (id_permission) REFERENCES PERMISSIONS(id) ON DELETE CASCADE
);

CREATE TABLE ROUTES (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    route JSON NOT NULL,
	itinerary JSON NOT NULL
);

CREATE TABLE USERS (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lastname1 VARCHAR(255) NOT NULL,
	lastname2 VARCHAR(255),
    phone_number VARCHAR(255),
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    register DATE NOT NULL DEFAULT CURRENT_DATE,
	id_manager INT,
    id_company INT NOT NULL,
    id_role INT NOT NULL,	
    FOREIGN KEY (id_company) REFERENCES COMPANIES(id) ON DELETE CASCADE,
    FOREIGN KEY (id_role) REFERENCES ROLES(id) ON DELETE SET NULL
);

CREATE TABLE USERS_ROUTES (
    id_user INT NOT NULL,
    id_route INT NOT NULL,
    PRIMARY KEY (id_user, id_route),
    FOREIGN KEY (id_user) REFERENCES USERS(id) ON DELETE CASCADE,
    FOREIGN KEY (id_route) REFERENCES ROUTES(id) ON DELETE CASCADE
);


INSERT INTO PROVINCES (name) VALUES ('Madrid');


COPY towns(id, name, postal_code, id_province)
FROM 'C:\cargadatos\POSTAL_CODES_CORE.csv' 
DELIMITER ';' 
CSV HEADER
ENCODING 'UTF8';

INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('B12345678', 'Lazy Postman S.L', '612612612', 'lazy@postman.com', 127, 'Ctra. de La Coruña, 38, 28231');

INSERT INTO PERMISSIONS (name) VALUES ('Create_Users');
INSERT INTO PERMISSIONS (name) VALUES ('Read_Users');
INSERT INTO PERMISSIONS (name) VALUES ('Update_Users');
INSERT INTO PERMISSIONS (name) VALUES ('Delete_Users');
INSERT INTO PERMISSIONS (name) VALUES ('Create_Routes');
INSERT INTO PERMISSIONS (name) VALUES ('Read_Routes');
INSERT INTO PERMISSIONS (name) VALUES ('Delete_Routes');
INSERT INTO PERMISSIONS (name) VALUES ('Create_Users_Routes');
INSERT INTO PERMISSIONS (name) VALUES ('Delete_Users_Routes');



INSERT INTO ROLES (name) VALUES ('Admin');
INSERT INTO ROLES (name) VALUES ('Manager');
INSERT INTO ROLES (name) VALUES ('Employee');


INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 1);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 2);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 3);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 4);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 5);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 6);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 7);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (2, 2);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (2, 5);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (2, 6);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (2, 7);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (2, 8);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (2, 9);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (3, 2);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (3, 4);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (3, 5);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (3, 6);



INSERT INTO ROUTES (name, route, itinerary) VALUES ('Ruta Softtek', '[{"lat": 40.4814392, "lng": -3.8552678}, {"lat": 40.4806225, "lng": -3.8528335}, {"lat": 40.4790337, "lng": -3.8562055}, {"lat": 40.4790553, "lng": -3.8548369}, {"lat": 40.4803319, "lng": -3.857549499999999}, {"lat": 40.4803319, "lng": -3.857549499999999}, {"lat": 40.4782661, "lng": -3.8626948}, {"lat": 40.48341, "lng": -3.8613}, {"lat": 40.4824463, "lng": -3.8612602}, {"lat": 40.4823654, "lng": -3.862258699999999}, {"lat": 40.4810165, "lng": -3.8598636}, {"lat": 40.4811517, "lng": -3.860088}, {"lat": 40.4816195, "lng": -3.8597624}, {"lat": 40.4829287, "lng": -3.858824}, {"lat": 40.482845, "lng": -3.8584971}]', '[{"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 1}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 3}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 5}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 7}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 9}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 11}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 13}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 15}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 2}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 4}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 6}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 10}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 12}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 14}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 16}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 31}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 27}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 29}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 25}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 23}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 21}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 19}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "TRAMONTANA", "roadType": "Calle", "roadNumber": 17}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 25}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 27}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 29}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 31}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 33}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 35}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 26}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 28}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 37}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 10}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 23}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 21}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 11}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 9}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 7}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 10}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 12}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 14}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 25}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 19}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 13}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 15}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 17}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 16}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 18}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 20}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 22}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 24}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 6}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 4}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 3}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 5}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 1}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "HURACAN", "roadType": "Calle", "roadNumber": 2}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 2}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 4}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 5}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 9}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 7}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 11}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 6}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 10}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 10}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "MONTERREY", "roadType": "Calle", "roadNumber": 12}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 1}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 6}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 10}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 5}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 7}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 9}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 16}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 14}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 12}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 3}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "ACAPULCO", "roadType": "Calle", "roadNumber": 2}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAHERMOSA", "roadType": "Calle", "roadNumber": 1}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAHERMOSA", "roadType": "Calle", "roadNumber": 3}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAHERMOSA", "roadType": "Calle", "roadNumber": 5}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAHERMOSA", "roadType": "Calle", "roadNumber": 7}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAS MARQUÉS", "roadType": "Calle", "roadNumber": 2}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAS MARQUÉS", "roadType": "Calle", "roadNumber": 4}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAS MARQUÉS", "roadType": "Calle", "roadNumber": 6}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAS MARQUÉS", "roadType": "Calle", "roadNumber": 8}, {"town": {"cdmuni": 127, "dsmuni": "Las Rozas de Madrid"}, "postCode": 28231, "province": "Madrid", "roadName": "NAVAS MARQUÉS", "roadType": "Calle", "roadNumber": 10}]');



INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Lazy Postman', 'Cartero', 'Perezoso', '612612612', 'lazy@postman.com', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', NULL, 1, 1);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Jane', 'Smith', NULL, '987654321', 'janesmith', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', 1, 1, 2);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('David', 'Johnson','Johnson', '456789123', 'davidjohnson', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', 2, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Sarah', 'Williams', NULL, '321654987', 'sarahwilliams', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', 2, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Michael', 'Brown', NULL, '789123456', 'michaelbrown', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', 2, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Emily', 'Taylor', 'Lopez','654987321', 'emilytaylor', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', 2, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Daniel', 'Anderson', 'Brown', NULL, 'danielanderson', '$2a$10$m/oRxiY.1JczZON2UstiweLvPpe9qQjM92WMQjCv2N9xEz1z34xvK', NULL, 1, 3);

INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (3, 1);


