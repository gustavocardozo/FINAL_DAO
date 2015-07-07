-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-07-2015 a las 01:20:31
-- Versión del servidor: 5.6.24
-- Versión de PHP: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
use grupo_6_db;
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `avion`
--

CREATE TABLE IF NOT EXISTS `avion` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(30) NOT NULL,
  `CAPACIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `avion`
--

INSERT INTO `avion` (`ID`, `NOMBRE`, `CAPACIDAD`) VALUES
(1, 'TANGO01', 50),
(2, 'TANGO02', 50),
(3, 'TANGO03', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `ID` int(11) NOT NULL,
  `DIRECCION` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `TELEFONO` varchar(30) NOT NULL,
  `DNI` varchar(8) NOT NULL,
  `NOMBRE` varchar(40) NOT NULL,
  `APELLIDO` varchar(40) NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`ID`, `DIRECCION`, `EMAIL`, `TELEFONO`, `DNI`, `NOMBRE`, `APELLIDO`, `FECHA_NACIMIENTO`) VALUES
(1, 'Calle falsa 123', 'admin@admin.com.ar', '11111111111', '11111111', 'Guillermo', 'Telijo', '1994-04-11'),
(2, 'Av Siempre Viva', 'root@admin.com.ar', '2222222222', '22222222', 'Esteban', 'Quito', '1994-10-18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

CREATE TABLE IF NOT EXISTS `destinos` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `destinos`
--

INSERT INTO `destinos` (`ID`, `NOMBRE`, `DESCRIPCION`) VALUES
(1, 'Ezeiza, Buenos Aires, Argentina', 'Aeropuerto de Ezeiza'),
(2, 'Santiado de Chile, Chile', 'Aeropuerto de Santiago de Chile');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paquete`
--

CREATE TABLE IF NOT EXISTS `paquete` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `PRECIO` decimal(8,2) NOT NULL,
  `CANT_PERSONAS` int(11) NOT NULL,
  `DESCRIPCION` varchar(100) DEFAULT NULL,
  `DESDE` int(11) NOT NULL REFERENCES destinos(ID),
  `HACIA` int(11) NOT NULL REFERENCES destinos(ID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `paquete`
--

INSERT INTO `paquete` (`ID`, `NOMBRE`, `PRECIO`, `CANT_PERSONAS`, `DESCRIPCION`, `DESDE`, `HACIA`) VALUES
(1, 'America BS as Chile', '888.00', 1, 'null', '1', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE IF NOT EXISTS `reserva` (
  `ID` int(11) NOT NULL,
  `ID_PAQUETE` int(11) NOT NULL,
  `ID_VUELO` int(11) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_cliente`
--

CREATE TABLE IF NOT EXISTS `reserva_cliente` (
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_RESERVA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

CREATE TABLE IF NOT EXISTS `vuelo` (
  `ID` int(11) NOT NULL,
  `ID_AVION` int(11) DEFAULT NULL,
  `DESDE` int(11) NOT NULL REFERENCES destinos(ID),
  `HACIA` int(11) NOT NULL REFERENCES destinos(ID),
  `HORARIO_PARTIDA` date DEFAULT NULL,
  `HORARIO_LLEGADA` date DEFAULT NULL,
  `PRECIO` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `avion`
--
ALTER TABLE `avion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `reserva_cliente`
--
ALTER TABLE `reserva_cliente`
  ADD PRIMARY KEY (`ID_CLIENTE`,`ID_RESERVA`);

--
-- Indices de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `destinos`
--
ALTER TABLE `destinos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
