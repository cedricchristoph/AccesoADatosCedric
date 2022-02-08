-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 07-02-2022 a las 16:30:19
-- Versión del servidor: 5.7.28
-- Versión de PHP: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `seguimientomonedasconseguridad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historicocambioeuro`
--

CREATE TABLE `historicocambioeuro` (
  `idhistoricocambioeuro` int(11) NOT NULL,
  `fkidmoneda` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `equivalenteeuro` decimal(19,4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `historicocambioeuro`
--

INSERT INTO `historicocambioeuro` (`idhistoricocambioeuro`, `fkidmoneda`, `fecha`, `equivalenteeuro`) VALUES
(2, 2, '2020-03-25', '0.2500'),
(3, 2, '2019-01-14', '0.5000'),
(4, 1, '2019-12-27', '0.9000'),
(5, 1, '2020-12-30', '0.9500'),
(6, 1, '2021-11-08', '2.0000'),
(8, 1, '2021-12-16', '3.0000');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `monedas`
--

CREATE TABLE `monedas` (
  `idmoneda` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `pais` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `monedas`
--

INSERT INTO `monedas` (`idmoneda`, `nombre`, `pais`) VALUES
(1, 'dolar', 'Estados Unidos'),
(2, 'Yen', 'Japan'),
(4, 'franco', 'Francia'),
(5, 'libra', 'UK'),
(6, 'euro', 'europa'),
(7, 'jesu', 'africa'),
(11, 'Peso', 'Mexico'),
(12, 'Un Nombre', 'Un Pais');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `nombre`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN'),
(3, 'usuario'),
(4, 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idusuario`, `nombre`, `password`) VALUES
(7, 'root', '$2a$10$WvhH/Cgd2cVElEEQfeF.8uOQci5KDn9bXP1vlxQBmI5pOmpkcOJ9i'),
(12, 'lui', '$2a$10$0xezsvQnVm/I.DOTqprt9esmD4wava7gVY/oZB2HlaUz.ZaJP7sa2'),
(15, 'anitita', '$2a$10$zvuDxpKR.9xerEth4PzoDOySS8h2La5euRlVKEPabr9CkBqISnYGa'),
(16, 'anita', '$2a$10$zvuDxpKR.9xerEth4PzoDOySS8h2La5euRlVKEPabr9CkBqISnYGa'),
(17, 'rivaldo', '$2a$10$zvuDxpKR.9xerEth4PzoDOySS8h2La5euRlVKEPabr9CkBqISnYGa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarioconrol`
--

CREATE TABLE `usuarioconrol` (
  `idusuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `rol` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarioconrol`
--

INSERT INTO `usuarioconrol` (`idusuario`, `nombre`, `password`, `rol`) VALUES
(7, 'root', '$2a$10$WvhH/Cgd2cVElEEQfeF.8uOQci5KDn9bXP1vlxQBmI5pOmpkcOJ9i', 'ROLE_ADMIN'),
(12, 'lui', '$2a$10$0xezsvQnVm/I.DOTqprt9esmD4wava7gVY/oZB2HlaUz.ZaJP7sa2', 'ROLE_USER'),
(15, 'anitita', '$2a$10$zvuDxpKR.9xerEth4PzoDOySS8h2La5euRlVKEPabr9CkBqISnYGa', 'ROLE_USER'),
(16, 'anita', '$2a$10$zvuDxpKR.9xerEth4PzoDOySS8h2La5euRlVKEPabr9CkBqISnYGa', 'ROLE_USER'),
(17, 'rivaldo', '$2a$10$zvuDxpKR.9xerEth4PzoDOySS8h2La5euRlVKEPabr9CkBqISnYGa', 'ROLE_USER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_rol`
--

CREATE TABLE `usuario_rol` (
  `idusuario_rol` int(11) NOT NULL,
  `fk_usuario` int(11) NOT NULL,
  `fk_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario_rol`
--

INSERT INTO `usuario_rol` (`idusuario_rol`, `fk_usuario`, `fk_rol`) VALUES
(1, 15, 2),
(2, 7, 1),
(7, 12, 1),
(8, 17, 1),
(13, 7, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `historicocambioeuro`
--
ALTER TABLE `historicocambioeuro`
  ADD PRIMARY KEY (`idhistoricocambioeuro`),
  ADD KEY `fk_monedas_idx` (`fkidmoneda`);

--
-- Indices de la tabla `monedas`
--
ALTER TABLE `monedas`
  ADD PRIMARY KEY (`idmoneda`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `usuarioconrol`
--
ALTER TABLE `usuarioconrol`
  ADD PRIMARY KEY (`idusuario`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`);

--
-- Indices de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD PRIMARY KEY (`idusuario_rol`),
  ADD KEY `fk_usuario_idx` (`fk_usuario`),
  ADD KEY `fk_roll_idx` (`fk_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `historicocambioeuro`
--
ALTER TABLE `historicocambioeuro`
  MODIFY `idhistoricocambioeuro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `monedas`
--
ALTER TABLE `monedas`
  MODIFY `idmoneda` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `usuarioconrol`
--
ALTER TABLE `usuarioconrol`
  MODIFY `idusuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  MODIFY `idusuario_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historicocambioeuro`
--
ALTER TABLE `historicocambioeuro`
  ADD CONSTRAINT `fk_monedas` FOREIGN KEY (`fkidmoneda`) REFERENCES `monedas` (`idmoneda`);

--
-- Filtros para la tabla `usuario_rol`
--
ALTER TABLE `usuario_rol`
  ADD CONSTRAINT `fk_roll` FOREIGN KEY (`fk_rol`) REFERENCES `rol` (`idrol`),
  ADD CONSTRAINT `fk_usuarioo` FOREIGN KEY (`fk_usuario`) REFERENCES `usuario` (`idusuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
