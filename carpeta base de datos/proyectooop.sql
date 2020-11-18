-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2020 a las 00:51:07
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectooop`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bodega1`
--

CREATE TABLE `bodega1` (
  `codigo` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `no_ventas` int(11) NOT NULL,
  `no_compras` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `bodega1`
--

INSERT INTO `bodega1` (`codigo`, `nombre`, `no_ventas`, `no_compras`) VALUES
(1, 'bodega1', 0, 197),
(2, 'bodega2', 0, 315),
(3, 'bodega3', 0, 246);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cantidad`
--

CREATE TABLE `cantidad` (
  `codigo` int(11) NOT NULL,
  `codigo_bodega` int(11) NOT NULL,
  `codigo_producto` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cantidad`
--

INSERT INTO `cantidad` (`codigo`, `codigo_bodega`, `codigo_producto`, `cantidad`) VALUES
(1, 1, 'VN1', 20),
(2, 1, 'VN2', 50),
(3, 1, 'VN3', 55),
(4, 1, 'GA1', 50),
(5, 1, 'GA2', 12),
(6, 1, 'VOC', 5),
(7, 2, 'BT1', 10),
(8, 2, 'BT2', 15),
(9, 2, 'TMT1', 80),
(10, 2, 'TMB1', 70),
(11, 2, 'FL1', 40),
(12, 3, 'CLT1', 90),
(13, 3, 'SXO1', 54),
(14, 3, 'PND1', 30),
(15, 3, 'GA3', 40),
(16, 3, 'TMBL1', 22),
(17, 3, 'TB1', 10),
(18, 1, 'TB2', 5),
(19, 2, 'VN4', 80),
(20, 2, 'GA4', 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instrumento_cuerda`
--

CREATE TABLE `instrumento_cuerda` (
  `codigo` int(11) NOT NULL,
  `codigo_producto` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo_cuerda` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `resonancia` int(11) NOT NULL,
  `cantidad_cuerdas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `instrumento_cuerda`
--

INSERT INTO `instrumento_cuerda` (`codigo`, `codigo_producto`, `tipo_cuerda`, `resonancia`, `cantidad_cuerdas`) VALUES
(1, 'VN1', 'Kaplan Amo', 0, 4),
(2, 'VN2', 'Kaplan Amo', 0, 4),
(3, 'VN3', 'Kaplan Amo', 0, 4),
(4, 'GA1', 'Catgut', 0, 6),
(5, 'GA2', 'Nickel-plated steel', 1, 6),
(6, 'VOC', 'Larsen', 0, 4),
(7, 'GA3', 'Catgut y Nylon', 1, 6),
(8, 'VN4', 'Prelude', 0, 4),
(9, 'GA4', 'Catgut', 0, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instrumento_percusion`
--

CREATE TABLE `instrumento_percusion` (
  `codigo` int(11) NOT NULL,
  `codigo_producto` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `elemento_percutor` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `elemento_vibrante` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `instrumento_percusion`
--

INSERT INTO `instrumento_percusion` (`codigo`, `codigo_producto`, `elemento_percutor`, `elemento_vibrante`) VALUES
(1, 'BT1', 'Palillo Remate', 'Bombo'),
(2, 'BT2', 'Palillos Bell', 'Caja'),
(3, 'PND1', 'Palillo o Mano', 'Ferreñas '),
(4, 'TMBL1', 'Palillo Ride', 'Caja');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instrumento_viento`
--

CREATE TABLE `instrumento_viento` (
  `codigo` int(11) NOT NULL,
  `codigo_producto` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `largo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `instrumento_viento`
--

INSERT INTO `instrumento_viento` (`codigo`, `codigo_producto`, `largo`) VALUES
(1, 'TMT1', 180),
(2, 'TMB1', 110),
(3, 'FL1', 66),
(4, 'CLT1', 40),
(5, 'SXO1', 100),
(6, 'TB1', 300),
(7, 'TB2', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codigo` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `precio` double NOT NULL,
  `marca` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `modelo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo_material` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `peso` double NOT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigo`, `precio`, `marca`, `modelo`, `nombre`, `tipo_material`, `peso`, `activo`) VALUES
('BT1', 2515, 'The Sound Percussion Labs', 'D2518 Kicker Pro ', 'Bateria Acustica 5 piezas', 'Acero ', 77.4, 1),
('BT2', 10476, 'ddrum', 'Dios', 'Bateria Acustica 3 piezas', 'Acero', 50.4, 1),
('CLT1', 5340, 'Buffet Crampon', 'Tradicional Clarinet 2.0', 'Clarinete', 'Madera', 4.1, 1),
('FL1', 155, 'Kaizer', 'FLT-1500NK', 'Flauta', 'Plata Alemana', 0.874, 1),
('GA1', 754, 'Rogue', 'RA-090 Dreadnought', 'Guitarra', 'Abeto', 2.5, 1),
('GA2', 6200, 'D´Angelico', 'SS Semi-Hollowbody', 'Guitarra', 'Arce', 8.3, 1),
('GA3', 3355, 'Alvarez', 'AG610CEARB Armrest Grand Auditorium', 'Guitarra', 'Caoba', 2.6, 1),
('GA4', 1023, 'Mitchell', 'D120 Dreadnought ', 'Guitarra', 'Caoba', 2.4, 1),
('PND1', 2700, 'LP jam', 'LP1207-T', 'Panderta', 'Acero', 2.4, 1),
('SXO1', 399, 'Kaizer Altol', '1000BKGK', 'Saxofon', 'Laton', 7, 1),
('TB1', 4729, 'Jupiter', 'JTU1010 BBb', 'Tuba', 'Acero Inoxidable', 17.1, 1),
('TB2', 5359, 'Jupiter', 'JTU1010', 'Tuba', 'Acero Inoxidable', 19.5, 1),
('TMB1', 114, 'Etude', 'ETB-100', 'Tombon', 'Acero Inoxidable', 6.1, 1),
('TMBL1', 700, 'LP', 'LP1445-KP', 'Timbales', 'Acero', 10.6, 1),
('TMT1', 120, 'Etude', 'ETR-100', 'Trompeta', 'Acero Inoxidable', 6.7, 1),
('VN1', 1200, 'Holstein', 'Traditional Panette Violin', 'Violin', 'Madera', 0.89, 1),
('VN2', 3900, 'Holstein', 'Bench Cannone 1973', 'Violin', 'Palo Santo', 0.88, 1),
('VN3', 2100, 'Holstein', 'Workshop Cannone', 'Violin', 'Abeto', 0.86, 1),
('VN4', 359, 'FIDDLERMAN', 'OB1', 'Violin', 'Abeto', 0.87, 1),
('VOC', 7500, 'StringWorks', 'Kallo Bartok', 'Violonchelo', 'Abeto', 3.2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bodega1`
--
ALTER TABLE `bodega1`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `cantidad`
--
ALTER TABLE `cantidad`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `codigo_bodega` (`codigo_bodega`),
  ADD KEY `codigo_producto` (`codigo_producto`);

--
-- Indices de la tabla `instrumento_cuerda`
--
ALTER TABLE `instrumento_cuerda`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `codigo_producto` (`codigo_producto`);

--
-- Indices de la tabla `instrumento_percusion`
--
ALTER TABLE `instrumento_percusion`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `codigo_producto` (`codigo_producto`);

--
-- Indices de la tabla `instrumento_viento`
--
ALTER TABLE `instrumento_viento`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `codigo_producto` (`codigo_producto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bodega1`
--
ALTER TABLE `bodega1`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cantidad`
--
ALTER TABLE `cantidad`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `instrumento_cuerda`
--
ALTER TABLE `instrumento_cuerda`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `instrumento_percusion`
--
ALTER TABLE `instrumento_percusion`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `instrumento_viento`
--
ALTER TABLE `instrumento_viento`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cantidad`
--
ALTER TABLE `cantidad`
  ADD CONSTRAINT `cantidad_ibfk_1` FOREIGN KEY (`codigo_producto`) REFERENCES `producto` (`codigo`),
  ADD CONSTRAINT `cantidad_ibfk_2` FOREIGN KEY (`codigo_bodega`) REFERENCES `bodega1` (`codigo`);

--
-- Filtros para la tabla `instrumento_cuerda`
--
ALTER TABLE `instrumento_cuerda`
  ADD CONSTRAINT `instrumento_cuerda_ibfk_1` FOREIGN KEY (`codigo_producto`) REFERENCES `producto` (`codigo`);

--
-- Filtros para la tabla `instrumento_percusion`
--
ALTER TABLE `instrumento_percusion`
  ADD CONSTRAINT `instrumento_percusion_ibfk_1` FOREIGN KEY (`codigo_producto`) REFERENCES `producto` (`codigo`);

--
-- Filtros para la tabla `instrumento_viento`
--
ALTER TABLE `instrumento_viento`
  ADD CONSTRAINT `instrumento_viento_ibfk_1` FOREIGN KEY (`codigo_producto`) REFERENCES `producto` (`codigo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
