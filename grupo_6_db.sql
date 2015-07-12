-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2015 at 01:42 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `grupo_6_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `avion`
--

CREATE TABLE IF NOT EXISTS `avion` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(30) NOT NULL,
  `CAPACIDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avion`
--

INSERT INTO `avion` (`ID`, `NOMBRE`, `CAPACIDAD`) VALUES
(1, 'TANGO01', 50),
(2, 'TANGO02', 50),
(3, 'TANGO03', 50);

-- --------------------------------------------------------

--
-- Table structure for table `cliente`
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
-- Dumping data for table `cliente`
--

INSERT INTO `cliente` (`ID`, `DIRECCION`, `EMAIL`, `TELEFONO`, `DNI`, `NOMBRE`, `APELLIDO`, `FECHA_NACIMIENTO`) VALUES
(1, 'Calle falsa 123', 'admin@admin.com.ar', '11111111111', '11111111', 'Guillermo', 'Telijo', '1994-04-11'),
(2, 'Av Siempre Viva', 'root@admin.com.ar', '2222222222', '22222222', 'Esteban', 'Quito', '1994-10-18'),
(3, 'Einstein 65', 'gonzalo.miguel93@gmail.com', '1160534525', '37863377', 'Facundo Gonzalo', 'Miguel', '2015-07-01'),
(4, 'Eintein 65', 'julian@gmail.com', '1112223355', '39283955', 'Julian', 'Miguel', '1995-10-01'),
(5, 'Eintein 65', 'julian@gmail.com', '1112223355', '39283955', 'Julian', 'Miguel', '1995-10-01'),
(6, 'Eintein 65', 'julian@gmail.com', '1112223355', '39283955', 'Julian', 'Miguel', '1995-10-01'),
(7, 'Eintein 65', 'julian@gmail.com', '1112223355', '39283955', 'Julian', 'Miguel', '1995-10-01');

-- --------------------------------------------------------

--
-- Table structure for table `destinos`
--

CREATE TABLE IF NOT EXISTS `destinos` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `destinos`
--

INSERT INTO `destinos` (`ID`, `NOMBRE`, `DESCRIPCION`) VALUES
(1, 'Ezeiza, Buenos Aires, Argentina', 'Aeropuerto de Ezeiza'),
(2, 'Santiado de Chile, Chile', 'Aeropuerto de Santiago de Chile');

-- --------------------------------------------------------

--
-- Table structure for table `paquete`
--

CREATE TABLE IF NOT EXISTS `paquete` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `PRECIO` decimal(8,2) NOT NULL,
  `CANT_PERSONAS` int(11) NOT NULL,
  `DESCRIPCION` varchar(100) DEFAULT NULL,
  `DESDE` int(11) NOT NULL,
  `HACIA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paquete`
--

INSERT INTO `paquete` (`ID`, `NOMBRE`, `PRECIO`, `CANT_PERSONAS`, `DESCRIPCION`, `DESDE`, `HACIA`) VALUES
(1, 'America BS as Chile', '888.00', 1, 'null', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `reserva`
--

CREATE TABLE IF NOT EXISTS `reserva` (
  `ID` int(11) NOT NULL,
  `ID_PAQUETE` int(11) DEFAULT NULL,
  `ID_VUELO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserva`
--

INSERT INTO `reserva` (`ID`, `ID_PAQUETE`, `ID_VUELO`) VALUES
(0, NULL, 1),
(1, 1, 1),
(2, 1, 1),
(3, NULL, 1),
(4, 1, 1),
(5, NULL, 1),
(6, 1, 1),
(7, 1, 1),
(8, NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `reserva_cliente`
--

CREATE TABLE IF NOT EXISTS `reserva_cliente` (
  `ID_CLIENTE` int(11) NOT NULL,
  `ID_RESERVA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reserva_cliente`
--

INSERT INTO `reserva_cliente` (`ID_CLIENTE`, `ID_RESERVA`) VALUES
(1, 2),
(1, 7),
(1, 8),
(2, 0),
(2, 1),
(2, 2),
(2, 3),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(3, 8),
(4, 8),
(5, 8),
(6, 8),
(7, 8);

-- --------------------------------------------------------

--
-- Table structure for table `vuelo`
--

CREATE TABLE IF NOT EXISTS `vuelo` (
  `ID` int(11) NOT NULL,
  `ID_AVION` int(11) DEFAULT NULL,
  `DESDE` int(11) NOT NULL,
  `HACIA` int(11) NOT NULL,
  `HORARIO_PARTIDA` date DEFAULT NULL,
  `HORARIO_LLEGADA` date DEFAULT NULL,
  `PRECIO` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vuelo`
--

INSERT INTO `vuelo` (`ID`, `ID_AVION`, `DESDE`, `HACIA`, `HORARIO_PARTIDA`, `HORARIO_LLEGADA`, `PRECIO`) VALUES
(1, 1, 1, 2, '2015-07-07', '2015-07-07', '888');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `avion`
--
ALTER TABLE `avion`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `destinos`
--
ALTER TABLE `destinos`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `paquete`
--
ALTER TABLE `paquete`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `reserva_cliente`
--
ALTER TABLE `reserva_cliente`
  ADD PRIMARY KEY (`ID_CLIENTE`,`ID_RESERVA`);

--
-- Indexes for table `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `destinos`
--
ALTER TABLE `destinos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
