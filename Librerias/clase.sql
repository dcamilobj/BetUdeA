-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 08-03-2014 a las 22:18:24
-- Versión del servidor: 5.1.36
-- Versión de PHP: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `clase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudades`
--

CREATE TABLE IF NOT EXISTS `ciudades` (
  `codigo` int(12) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `CodigoArea` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcar la base de datos para la tabla `ciudades`
--

INSERT INTO `ciudades` (`codigo`, `Nombre`, `CodigoArea`) VALUES
(1, 'Medellin', '4'),
(2, 'Bogota', '1'),
(3, 'Cali', '2'),
(4, 'Barranquilla', '3s'),
(10, 'Villavicencio', '34');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `Cedula` varchar(15) NOT NULL,
  `Nombres` varchar(30) NOT NULL,
  `Apellidos` varchar(30) NOT NULL,
  `Email` varchar(120) NOT NULL,
  `UsuarioCrea` varchar(15) NOT NULL,
  `FechaCreacion` date NOT NULL,
  `UsuarioModifica` varchar(15) DEFAULT NULL,
  `FechaModificacion` date DEFAULT NULL,
  `Eliminado` tinyint(1) DEFAULT NULL,
  `UsuarioElimina` varchar(15) DEFAULT NULL,
  `FechaEliminacion` date DEFAULT NULL,
  PRIMARY KEY (`Cedula`),
  KEY `UsuarioCrea` (`UsuarioCrea`),
  KEY `UsuarioModifica` (`UsuarioModifica`),
  KEY `UsuarioElimina` (`UsuarioElimina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcar la base de datos para la tabla `clientes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direcciones`
--

CREATE TABLE IF NOT EXISTS `direcciones` (
  `Codigo` int(12) NOT NULL,
  `Cliente` varchar(15) NOT NULL,
  `Direccion` varchar(120) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  `Ciudad` int(12) NOT NULL,
  `Preferida` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Cliente`,`Codigo`),
  KEY `Cliente` (`Cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `direcciones`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `Codigo` varchar(12) NOT NULL,
  `Nombre` varchar(35) NOT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `roles`
--

INSERT INTO `roles` (`Codigo`, `Nombre`) VALUES
('ADM', 'Administrador'),
('AUD', 'Auditor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `Login` varchar(15) NOT NULL,
  `Nombres` varchar(45) NOT NULL,
  `Apellidos` varchar(45) NOT NULL,
  `Contrasena` varchar(125) NOT NULL,
  `Rol` varchar(12) NOT NULL,
  PRIMARY KEY (`Login`),
  KEY `Rol` (`Rol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`Login`, `Nombres`, `Apellidos`, `Contrasena`, `Rol`) VALUES
('elver', 'Elver', 'Suarez', '1g0/KkFdhrmg1DYJWFdd2A==', 'ADM'),
('juan', 'Juan', 'Pinzon', 'qUZSqpfHIRuolU3RWjz4OA==', 'AUD');

--
-- Filtros para las tablas descargadas (dump)
--

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `Usuarios_ibfk_1` FOREIGN KEY (`Rol`) REFERENCES `roles` (`Codigo`) ON DELETE CASCADE ON UPDATE CASCADE;
