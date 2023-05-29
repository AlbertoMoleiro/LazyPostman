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
    route JSON NOT NULL
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
INSERT INTO PROVINCES (name) VALUES ('Barcelona');
INSERT INTO PROVINCES (name) VALUES ('Valencia');
INSERT INTO PROVINCES (name) VALUES ('Sevilla');
INSERT INTO PROVINCES (name) VALUES ('Málaga');
INSERT INTO PROVINCES (name) VALUES ('Alicante');
INSERT INTO PROVINCES (name) VALUES ('Murcia');
INSERT INTO PROVINCES (name) VALUES ('Bilbao');
INSERT INTO PROVINCES (name) VALUES ('Granada');
INSERT INTO PROVINCES (name) VALUES ('Zaragoza');


INSERT INTO towns (name, postal_code, id_province) VALUES ('Madrid', '28001', 1);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Barcelona', '08001', 2);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Valencia', '46001', 3);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Sevilla', '41001', 4);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Málaga', '29001', 5);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Alicante', '03001', 6);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Murcia', '30001', 7);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Bilbao', '48001', 8);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Granada', '18001', 9);
INSERT INTO towns (name, postal_code, id_province) VALUES ('Zaragoza', '50001', 10);


INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('123456789A', 'Company A', '123456789', 'companya@example.com', 1, 'Address A');
INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('987654321B', 'Company B', '987654321', 'companyb@example.com', 2, 'Address B');
INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('456789123C', 'Company C', '456789123', 'companyc@example.com', 3, 'Address C');
INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('321654987D', 'Company D', '321654987', 'companyd@example.com', 4, 'Address D');
INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('789123456E', 'Company E', '789123456', 'companye@example.com', 5, 'Address E');
INSERT INTO COMPANIES (cif, business_name, phone_number, email, id_town, address) VALUES ('654987321F', 'Company F', '654987321', 'companyf@example.com', 6, 'Address F');


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
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 8);
INSERT INTO ROLES_PERMISSIONS (id_role, id_permission) VALUES (1, 9);
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



INSERT INTO ROUTES (name, route) VALUES ('Route A', '{"start": "Location A", "end": "Location B"}');
INSERT INTO ROUTES (name, route) VALUES ('Route B', '{"start": "Location C", "end": "Location D"}');
INSERT INTO ROUTES (name, route) VALUES ('Route C', '{"start": "Location E", "end": "Location F"}');
INSERT INTO ROUTES (name, route) VALUES ('Route D', '{"start": "Location G", "end": "Location H"}');
INSERT INTO ROUTES (name, route) VALUES ('Route E', '{"start": "Location I", "end": "Location J"}');
INSERT INTO ROUTES (name, route) VALUES ('Route F', '{"start": "Location K", "end": "Location L"}');
INSERT INTO ROUTES (name, route) VALUES ('Route G', '{"start": "Location M", "end": "Location N"}');
INSERT INTO ROUTES (name, route) VALUES ('Route H', '{"start": "Location O", "end": "Location P"}');
INSERT INTO ROUTES (name, route) VALUES ('Route I', '{"start": "Location Q", "end": "Location R"}');
INSERT INTO ROUTES (name, route) VALUES ('Route J', '{"start": "Location S", "end": "Location T"}');


INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('John', 'Doe', 'Martinez', '123456789', 'johndoe', 'password', NULL, 1, 1);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Jane', 'Smith', NULL, '987654321', 'janesmith', 'password', NULL, 2, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('David', 'Johnson','Johnson', '456789123', 'davidjohnson', 'password', 1, 1, 2);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Sarah', 'Williams', NULL, '321654987', 'sarahwilliams', 'password', 1, 2, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Michael', 'Brown', NULL, '789123456', 'michaelbrown', 'password', 2, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Emily', 'Taylor', 'Lopez','654987321', 'emilytaylor', 'password', 2, 2, 2);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Daniel', 'Anderson', 'Brown', NULL, 'danielanderson', 'password', NULL, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Olivia', 'Thomas', NULL, NULL, 'oliviathomas', 'password', NULL, 2, 2);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('James', 'Jackson', NULL, NULL, 'jamesjackson', 'password', 1, 1, 3);
INSERT INTO USERS (name, lastname1, lastname2, phone_number, login, password, id_manager, id_company, id_role) VALUES ('Sophia', 'Harris', 'White', NULL, 'sophiaharris', 'password', 2, 2, 3);


INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (1, 1);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (1, 2);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (2, 2);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (2, 3);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (3, 1);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (4, 4);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (5, 5);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (6, 6);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (7, 8);
INSERT INTO USERS_ROUTES (id_user, id_route) VALUES (8, 10);

