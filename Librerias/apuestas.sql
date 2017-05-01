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
-- Base de datos: `Simulador de apuestas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--
CREATE TABLE IF NOT EXISTS `usuario` (
  `Cedula` varchar(15) NOT NULL,
  `Nombres` varchar(30) NOT NULL,
  `Apellidos` varchar(30) NOT NULL,
  `Email` varchar(120) NOT NULL,
  `Contrasena` varchar(50) NOT NULL,
  PRIMARY KEY (`Cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcar la base de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Cedula`, `Nombres`, `Apellidos`, `Email`, `Contrasena`) VALUES
('1182934564', 'Sergio Andres', 'Llanos Garcia', 'sergitoelmasbonito@gmail.com','asdfgh'),
('1022485934', 'Camilo', 'Bedoya', 'camilobedoy@gmail.com', 'bedoyacomolaolla'),
('1031123121', 'Andres', 'Ceballos Sanchez', 'sanchezceballito@gmail.com', 'c1b4ll0s'),
('1162923102', 'Lucky', 'Llanos', 'luckynoesmiperrita@gmail.com', 'luckylamejor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apuesta`
--
CREATE TABLE IF NOT EXISTS `apuesta` (
  `Id` int(12) NOT NULL AUTO_INCREMENT,
  `Valor` int(20) NOT NULL,
  `Estado` varchar(12) NOT NULL,
  `Evento` varchar(12) NOT NULL,
  `OpcionSeleccionada` varchar(15) NOT NULL,
  `OpcionCorrecta` varchar(15) DEFAULT NULL,
  `periodosimulacion` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`),
   KEY `periodosimulacion` (`periodosimulacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

INSERT INTO `apuesta` (`Id`, `Valor`, `Estado`, `Evento`, `OpcionSeleccionada`, `OpcionCorrecta`, `periodosimulacion`) VALUES

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `periodosimulación`
--
CREATE TABLE IF NOT EXISTS `periodosimulacion` (
  `Id` int(12) NOT NULL AUTO_INCREMENT,
  `FechaInicio` date NOT NULL,
  `FechaFin` date DEFAULT NULL,
  `Usuario` varchar(120) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Usuario` (`Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Volcar la base de datos para la tabla `periodosimulacion`
--

INSERT INTO `periodosimulacion` (`Id`, `FechaInicio`, `FechaFin`, `Usuario`) VALUES
(12, '24/02/2016', '2/03/2016', '1022485934'),
(8, '12/07/2016', '25/07/2016', '1031123121'),
(24, '13/07/2016', '25/08/2016', '1162923102'),
(34, '19/07/2016', '20/07/2016', '1182934564');