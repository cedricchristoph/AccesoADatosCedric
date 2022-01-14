-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 14-01-2022 a las 17:05:52
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
-- Base de datos: `instituto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos`
--

CREATE TABLE `alumnos` (
  `dni` char(20) NOT NULL,
  `nombre` char(50) DEFAULT NULL,
  `apellidos` char(50) DEFAULT NULL,
  `fechanacimiento` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `alumnos`
--

INSERT INTO `alumnos` (`dni`, `nombre`, `apellidos`, `fechanacimiento`) VALUES
('12312312K', 'María Luisa', 'Gutiérrez', 821234400000),
('12345678Z', 'Ana', 'Martín', 968972400000),
('87654321X', 'Marcos', 'Afonso Jiménez', 874278000000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignaturas`
--

CREATE TABLE `asignaturas` (
  `idasignatura` int(11) NOT NULL,
  `nombre` char(50) DEFAULT NULL,
  `curso` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `asignaturas`
--

INSERT INTO `asignaturas` (`idasignatura`, `nombre`, `curso`) VALUES
(4, 'AED', '2º DAM'),
(1, 'BAE', '1º DAM'),
(6, 'DPL', '2º DAW'),
(5, 'DSW', '2º DAW'),
(9, 'FYQ', '3º ESO'),
(3, 'LND', '1º DAM'),
(8, 'PGL', '2º DAM'),
(2, 'PGV', '2º DAM'),
(7, 'PRO', '1º DAM');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura_matricula`
--

CREATE TABLE `asignatura_matricula` (
  `id` int(11) NOT NULL,
  `idmatricula` int(11) DEFAULT NULL,
  `idasignatura` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `asignatura_matricula`
--

INSERT INTO `asignatura_matricula` (`id`, `idmatricula`, `idasignatura`) VALUES
(3, 2, 2),
(4, 2, 4),
(5, 2, 8),
(6, 3, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `idmatricula` int(11) NOT NULL,
  `dni` char(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`idmatricula`, `dni`, `year`) VALUES
(4, '12312312K', 2021),
(3, '12345678Z', 2021),
(2, '87654321X', 2020),
(12, '87654321X', 2022);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `hash` varchar(200) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '0',
  `type` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user`, `email`, `hash`, `active`, `type`) VALUES
('admin', 'cedric@icecraft.es', '$2a$10$BBUNahRkh/qitbCHWYbB/uxdoo9reZY7qgxxwexryessV5S1oH8X6', 1, 1),
('jesussosa', 'sosita@gmail.com', '$2a$10$aKs4NmNXF3ZcdBv9aUdZcugZ9yJGGauvijJRJwbprI3Hy6r.tOhiu', 1, 0),
('jorge', '', '$2a$10$xRseWMPEf6UhUcFu987zKe/96u237BvUdOpCoOvikmJvx0fgWlVKO', 1, 0),
('jose', '', '$2a$10$hpbEb2gjRSPhZ3SlLS8Cg.H9unmcnATi1nOTuKEOa4XSiWJRrtvSK', 1, 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnos`
--
ALTER TABLE `alumnos`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD PRIMARY KEY (`idasignatura`),
  ADD UNIQUE KEY `uc_nombrecurso` (`nombre`,`curso`);

--
-- Indices de la tabla `asignatura_matricula`
--
ALTER TABLE `asignatura_matricula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_matriculas` (`idmatricula`),
  ADD KEY `fk_asignaturas` (`idasignatura`);

--
-- Indices de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD PRIMARY KEY (`idmatricula`),
  ADD UNIQUE KEY `uc_dniyear` (`dni`,`year`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  MODIFY `idasignatura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `asignatura_matricula`
--
ALTER TABLE `asignatura_matricula`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  MODIFY `idmatricula` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignatura_matricula`
--
ALTER TABLE `asignatura_matricula`
  ADD CONSTRAINT `fk_asignaturas` FOREIGN KEY (`idasignatura`) REFERENCES `asignaturas` (`idasignatura`),
  ADD CONSTRAINT `fk_matriculas` FOREIGN KEY (`idmatricula`) REFERENCES `matriculas` (`idmatricula`);

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `fk_alumnos` FOREIGN KEY (`dni`) REFERENCES `alumnos` (`dni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
