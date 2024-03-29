/* BASE DE DATOS RESTAURANTE */
/* CEDRIC CHRISTOPH */

/* Crear base de datos */
DROP DATABASE IF EXISTS restaurante;
CREATE DATABASE restaurante;
use restaurante;

/* Crear tablas */
CREATE TABLE platos (
    idplato INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    descripcion VARCHAR(100),
    preciounidad DOUBLE,
    disponible boolean default false
);

CREATE TABLE mesas (
    nummesa INTEGER PRIMARY KEY,
    ocupantesmax INTEGER
);

CREATE TABLE servicio (
    idservicio INTEGER PRIMARY KEY AUTO_INCREMENT,
    fknummesa INTEGER, /* CREAR FOREIGN KEY: mesas*/
    fechacomienzo BIGINT NOT NULL,
    fechafin BIGINT,
    reservada VARCHAR(50),
    pagada boolean default false
);

CREATE TABLE detallefactura (
    iddetallefactura INTEGER PRIMARY KEY AUTO_INCREMENT,
    fkidservicio INTEGER, /* CREAR FOREIGN KEY: servicio*/
    fkidplato INTEGER, /* CREAR FOREIGN KEY: platos */
    cantidad INTEGER,
    preciounidad DOUBLE
);

CREATE TABLE operario (
    idoperario INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) UNIQUE,
    password VARCHAR(200),
    rol VARCHAR(50)
);

/* INTRODUCES VALORES PREDETERMINADOS */
INSERT INTO mesas VALUES (1, 4);
INSERT INTO mesas VALUES (2, 2);
INSERT INTO mesas VALUES (3, 4);
INSERT INTO mesas VALUES (4, 5);
INSERT INTO mesas VALUES (5, 2);
INSERT INTO mesas VALUES (6, 4);
INSERT INTO mesas VALUES (7, 4);


INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Ensalada Especial", "Ensalada con tomate, millo, aguacate, bacon, olivas verdes sin pipa, aceite de oliva y balsámico", 7.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Sandwich Mixto", "Sandwich con jamón y queso a la plancha", 1.80, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Sandwich Jamón Serrano", "Sandwich con jamón serrano", 2.20, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Bocadillo de pollo", "Bocadillo de pollo con lechuga, queso y tomate", 2.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Bocadillo de pollo especial", "Bocadillo de pollo con lechuga, doble de queso, tomate y huevo frito", 2.40, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Bocadillo con queso de cabra", "Bocadillo con queso de cabra", 1.90, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Sopa de Pollo", "Sopa de pollo fresca", 2.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Sopa de Tomate", "Sopa de Tomate", 2.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pasta Boloñesa", "Pasta boloñesa. Pasta a libre elección del cliente", 5.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pasta Carbonara", "Pasta carbonara. Pasta a libre elcción del cliente", 5.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pasta Arrabiata", "Pasta arrabiata con aceite de oliva, guindillas, chilli y ajo. Pasta a libre elcción del cliente", 5.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pizza Margarita", "Pizza Margarita. Pizza básica con salsa de tomate y mozarella", 5.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pizza Prosciutto", "Pizza Prosciutto. Pizza con salsa de tomate, mozarella y jamón cocido", 6.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pizza Cuatro Estaciones", "Pizza Cuatro Estaciones. Pizza con salsa de tomate, mozarella y cuatro quesos distintos", 6.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Pizza Napolitana", "Pizza Napolitana. Pizza con salsa de tomate, mozarella, jamón y olivas negras", 7.00, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Angus Steak 150g", "Angus Steak de 150 gramos acompañado con papas y salsas diversas (tomate, mostaza, mayonesa, ...)", 23.40, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Angus Steak 300g", "Angus Steak de 300 gramos acompañado con papas y salsas diversas (tomate, mostaza, mayonesa, ...)", 42.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Costillas de Cerdo 300g", "Costillas de Cerdo de 300 gramos a la barbacoa acompañado con papas", 19.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Refresco", "Coca Cola, Nestea, Aquarius, ...", 2.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Agua Mineral", "Agua Mineral", 1.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Caña Heiniken Barril", "Caña Heiniken Barril", 1.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("Jarra Heiniken Barril", "Caña Heiniken Barril", 2.50, true);
INSERT INTO platos (nombre, descripcion, preciounidad, disponible) VALUES ("1906 Reserva Especial", "1906 Reserva Especial", 3.00, true);

INSERT INTO operario (nombre, password, rol) VALUES ("admin", "$2a$12$P7wpgXFkDZ.3/KIbTr9xUul1Vczda7j4XxMdpxx6FBC9kN9ODMa3G", "ROLE_ADMIN");
INSERT INTO operario (nombre, password, rol) VALUES ("operario1", "$2a$12$TqZjNaGF8JMSxirsRWvP2u6Wk6sylzvhmIwVZ6U/cWrCxxlS5Jo1q", "ROLE_USER");
INSERT INTO operario (nombre, password, rol) VALUES ("operario2", "$2a$12$vLlS8Vmcq9Jv61wivAuNmOC616jhH3gJWid/fTQxytkZNpBcTxADa", "ROLE_USER");

INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (1, 1643725815000, 1643731215000, NULL, true);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (1, 1643731215000, 1643736615000, NULL, true);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (2, 1643725815000, 1643731215000, "Pedro Alfonso", true);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (3, 1643725815000, 1643731215000, NULL, true);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (4, 1643725815000, 1643731215000, "Alejandra Gonzalez", true);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (5, 1643725815000, 1643731215000, NULL, true);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (1, 1645111140000, 1645118400000, NULL, false);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (2, 1645111140000, 1645118400000, NULL, false);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (3, 1645111140000, 1645118400000, NULL, false);
INSERT INTO servicio(fknummesa, fechacomienzo, fechafin, reservada, pagada) VALUES (4, 1645111140000, 1645118400000, NULL, false);

INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (1, 4, 2, 2.00);
INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (1, 5, 1, 2.40);
INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (1, 6, 1, 1.90);
INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (1, 7, 1, 2.50);

INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (2, 4, 1, 2.00);
INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (2, 5, 3, 2.40);
INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (2, 6, 2, 1.90);
INSERT INTO detallefactura (fkidservicio, fkidplato, cantidad, preciounidad) VALUES (3, 7, 1, 2.50);

/* ESTABLECER FOREIGN KEYS */
ALTER TABLE servicio ADD FOREIGN KEY (fknummesa) REFERENCES mesas(nummesa);

ALTER TABLE detallefactura ADD FOREIGN KEY (fkidservicio) REFERENCES servicio(idservicio);
ALTER TABLE detallefactura ADD FOREIGN KEY (fkidplato) REFERENCES platos(idplato);

/* DONE */
