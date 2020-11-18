-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2020 a las 23:11:46
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
(1, 'bodega1', 0, 0),
(2, 'bodega2', 0, 0),
(3, 'bodega3', 0, 0);

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `instrumento_viento`
--

CREATE TABLE `instrumento_viento` (
  `codigo` int(11) NOT NULL,
  `codigo_producto` varchar(25) COLLATE utf8_spanish2_ci NOT NULL,
  `largo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

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
  `peso` int(11) NOT NULL,
  `activo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

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
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `instrumento_cuerda`
--
ALTER TABLE `instrumento_cuerda`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `instrumento_percusion`
--
ALTER TABLE `instrumento_percusion`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `instrumento_viento`
--
ALTER TABLE `instrumento_viento`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT;

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
