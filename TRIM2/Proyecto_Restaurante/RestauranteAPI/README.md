## Restaurante API

Este es el proyecto que contiene la API REST para el proyecto de Restaurante. En este documento
se explican los endpoints de la api y se proporciona las sentencias para la creación de la
base de datos.

## Endpoints

Los endpoints son rutas a las que responde o reacciona la api. A continuación veremos una lista completa de endpoints que contiene
o contendrá la API. 
* Los endpoints marcados con [x] están programados
* Los endpoints marcados con [x] y en **negrita** están programados y testeados
* Los endpoints marcados con [ ] están aún por hacer 

### Acceso público/anónimo
Las rutas de acceso público/anónimo son aquellas que no requieren de ningún tipo de autenticación
y podrán ser accedidos por cualquiera.

* LOGIN Y MESAS
	
**[x] POST /api/login  (LoginController)**
		Este endpoint permitirá enviar por URLENCODE un 'username' y un 'password' donde la API
		responderá con un mensaje de error o un TOKEN válido para su posterior uso en la API y
		autenticación.
	
**[x] GET /api/v1/platos**
		Devolverá la lista de platos del restaurante
	
**[x] GET /api/v1/mesas?time=123456789&ocupantes=2  (MesasRest)**
		Esta URL devolverá las mesas disponibles para la fecha y hora que se le indica.
		
### Acceso autorizado: ROLE_USER
Las rutas autorizadas de tipo ROLE_USER deberán enviar la cabecera AUTHORIZATION con un token válido
generado por la propia API bajo la URL /api/login y que sea un token con Authority ROLE_USER.

* MESAS Y SERVICIOS  (MesasREST)
	
**[x] GET /api/v2/mesas**
		Devolverá la lista de mesas del restaurante
	
**[x] GET /api/v2/mesas/{id}**
		Devolverá el objeto mesa solicitado
	
**[x] GET /api/v2/mesas/{id}/servicios**
		Devolverá la lista de servicios que ha tenido esta mesa
	
**[x] GET /api/v2/mesas/{id}/servicios/{id}**
		Recibiremos el objeto Servicio solicitado por id. Se comrpbará que el identificador de mesa coincide.

**[x] POST /api/v2/mesas/{id}/servicios**
		Enviamos una peticion POST sin ningun cuerpo ya que se generará un nuevo servicio automaticamente segun id de mesa indicado,
		y la fecha y hora actual de la peticion
	
**[x] PUT /api/v2/mesas/{id}/servicios/{id}**
		Enviaremos un JSON de tipo Servicio que actualizará el servicio con el id indicado
	
**[x] DELETE /api/v2/mesas/{id}/servicios/{id}**
		Eliminará el servicio de la base de datos en cascada con todos sus detallefactura.
	
**[x] GET /api/v2/mesas/{id}/servicio_actual**
		Devolverá el id del servicio actual de la mesa indicada
	
**[x] GET /api/v2/mesas/{id}/servicios/{id}/detallesfactura**
		Devolverá el servicio con sus pedidos realizados hasta el momento
		
**[x] POST /api/v2/mesas/{id}/servicios/{id}/detallesfactura**
		Enviaremos un JSON de tipo Detallefactura que será añadido al servicio indicado
	
**[x] PUT /api/v2/mesas/{id}/servicios/{id}/detallesfactura/{id}**
		Enviaremos un JSON de tipo Detallefactura que será actualizado en el servicio indicado
		
**[x] DELETE /api/v2/mesas/{id}/servicios/{id}/detallesfactura/{id}**
		Eliminará el detallefactura indicado de un servicio indicado
		
**[x] GET /api/v2/mesas/{id}/servicios/{id}/total**
		El total se calcula recogiendo del servicio todos los detallefactura y sumando la cantidad*preciounidad
		de cada detallefactura.
		Devolverá la cantidad total a pagar por el cliente según sus detallefactura realizados
		
* PLATOS  (PlatosREST)
	
**[x] GET /api/v2/platos**
		Devolverá la lista completa de platos disponibles y no disponibles
	
**[x] GET /api/v2/platos?available=true/false**
		Devolverá la lista completa de platos disponibles o no disponibles
			
**[x] GET /api/v2/platos/{id}**
		Devolverá el objeto indicado completo
		
**[x] PUT /api/v2/platos/{id}**
		Los Usuarios están autorizados únicamente a cambiar la disponibilidad de un plato.
		Se enviará un JSON de tipo Plato del que solo se usará el ID y el campo DISPONIBLE.
		Por esta razón el JSON enviado no tiene por que tener más campos que los indicados.
		
		
### Acceso autorizado: ROLE_ADMIN
Las rutas autorizadas de tipo ROLE_ADMIN deberán enviar la cabecera AUTHORIZATION con un token válido
generado por la propia API bajo la URL /api/login y que sea un token con Authority ROLE_ADMIN.

* MESAS  (MesasREST)
	
**[x] POST /api/v3/mesas**
		Enviaremos un JSON de tipo Mesa que se añadirá a la lista de mesas
		
**[x] PUT /api/v3/mesas/{id}**
		Enviaremos un JSON de tipo Mesa para actualizar el objeto con el identificador indicado
	
**[x] DELETE /api/v3/mesas/{id}**
		Eliminaremos la mesa con el identificador indicado
	
* PLATOS  (PlatosREST)
	
**[x] POST /api/v3/platos**
		Enviaremos un JSON de tipo Plato que se añadirá a la lista de platos
		
**[x] PUT /api/v3/platos/{id}**
		Enviaremos un JSON de tipo Plato para actualizar el plato con el identificador indicado
		
**[x] DELETE /api/v3/platos/{id}**
		Eliminaremos el plato con el identificador indicado
	
	
	
## Base de Datos
Para crear la base de datos con informaciones predeterminados de inicio ejecutamos las siguientes
líneas SQL.

```ruby
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
```

Queries importantes para su uso en la API

```ruby
/* QUERIES */

/* AVERIGUAR MESAS LIBRES */
SELECT * FROM mesas WHERE nummesa IN (SELECT DISTINCT m.nummesa
FROM mesas m LEFT JOIN servicio s ON s.fknummesa=m.nummesa AND (s.fechacomienzo NOT BETWEEN FECHA and FECHA+120MINUTOS) AND s.pagada=1 WHERE m.ocupantesmax > OCUPANTESINDICADOS);

```
	
