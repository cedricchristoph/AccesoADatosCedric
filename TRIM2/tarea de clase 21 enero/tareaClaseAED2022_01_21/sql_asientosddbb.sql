DROP DATABASE IF EXISTS asientosddbb;
CREATE DATABASE asientosddbb;
USE asientosddbb;
CREATE TABLE asientos(
	idasiento INT(4) AUTO_INCREMENT,    
    asunto VARCHAR(45) NOT NULL,
    cuantia DOUBLE NOT NULL,
    fecha BIGINT NOT NULL,
    CONSTRAINT pk_asientos PRIMARY KEY(idasiento)
);

INSERT INTO `asientos` (`idasiento`, `asunto`, `cuantia`, `fecha`) VALUES (NULL, 'PAGO INICIAL TRABAJO 23', '800', '968972400000');
INSERT INTO `asientos` (`idasiento`, `asunto`, `cuantia`, `fecha`) VALUES (NULL, 'RECIBO LUZ', '-30', '969802400000');
INSERT INTO `asientos` (`idasiento`, `asunto`, `cuantia`, `fecha`) VALUES (NULL, 'PAGO FINAL TRABAJO 14', '420', '969802400000');
INSERT INTO `asientos` (`idasiento`, `asunto`, `cuantia`, `fecha`) VALUES (NULL, 'AYUNTAMIENTO RECIBO IBI', '-170', '101802400000');
