-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.13 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para banco
DROP DATABASE IF EXISTS `banco`;
CREATE DATABASE IF NOT EXISTS `banco` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `banco`;


-- Volcando estructura para tabla banco.cuentabancaria
DROP TABLE IF EXISTS `cuentabancaria`;
CREATE TABLE IF NOT EXISTS `cuentabancaria` (
  `idCuentaBancaria` int(10) NOT NULL,
  `idSucursalBancaria` int(10) NOT NULL,
  `numeroCuenta` varchar(50) NOT NULL,
  `dc` varchar(2) NOT NULL,
  `saldo` decimal(10,0) NOT NULL,
  `cif` varchar(50) NOT NULL,
  PRIMARY KEY (`idCuentaBancaria`),
  KEY `FK_cuentabancaria_sucursalbancaria` (`idSucursalBancaria`),
  CONSTRAINT `FK_cuentabancaria_sucursalbancaria` FOREIGN KEY (`idSucursalBancaria`) REFERENCES `sucursalbancaria` (`idSucursalBancaria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.cuentabancaria: ~0 rows (aproximadamente)
DELETE FROM `cuentabancaria`;
/*!40000 ALTER TABLE `cuentabancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentabancaria` ENABLE KEYS */;


-- Volcando estructura para tabla banco.entidadbancaria
DROP TABLE IF EXISTS `entidadbancaria`;
CREATE TABLE IF NOT EXISTS `entidadbancaria` (
  `idEntidadBancaria` int(10) NOT NULL,
  `codigoEntidad` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cif` varchar(50) NOT NULL,
  PRIMARY KEY (`idEntidadBancaria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.entidadbancaria: ~0 rows (aproximadamente)
DELETE FROM `entidadbancaria`;
/*!40000 ALTER TABLE `entidadbancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidadbancaria` ENABLE KEYS */;


-- Volcando estructura para tabla banco.movimientobancario
DROP TABLE IF EXISTS `movimientobancario`;
CREATE TABLE IF NOT EXISTS `movimientobancario` (
  `idMovimientoBancario` int(10) NOT NULL,
  `idCuentaBancaria` int(10) NOT NULL,
  `importe` decimal(10,0) NOT NULL,
  `fecha` date NOT NULL,
  `saldoTotal` decimal(10,0) NOT NULL,
  `concepto` varchar(50) NOT NULL,
  PRIMARY KEY (`idMovimientoBancario`),
  KEY `FK_movimientobancario_cuentabancaria` (`idCuentaBancaria`),
  CONSTRAINT `FK_movimientobancario_cuentabancaria` FOREIGN KEY (`idCuentaBancaria`) REFERENCES `cuentabancaria` (`idCuentaBancaria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.movimientobancario: ~0 rows (aproximadamente)
DELETE FROM `movimientobancario`;
/*!40000 ALTER TABLE `movimientobancario` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimientobancario` ENABLE KEYS */;


-- Volcando estructura para tabla banco.sucursalbancaria
DROP TABLE IF EXISTS `sucursalbancaria`;
CREATE TABLE IF NOT EXISTS `sucursalbancaria` (
  `idSucursalBancaria` int(10) NOT NULL,
  `idEntidadBancaria` int(10) DEFAULT NULL,
  `codigoSucursal` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idSucursalBancaria`),
  KEY `FK_SucursalBancaria_entidadbancaria` (`idEntidadBancaria`),
  CONSTRAINT `FK_SucursalBancaria_entidadbancaria` FOREIGN KEY (`idEntidadBancaria`) REFERENCES `entidadbancaria` (`idEntidadBancaria`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla banco.sucursalbancaria: ~0 rows (aproximadamente)
DELETE FROM `sucursalbancaria`;
/*!40000 ALTER TABLE `sucursalbancaria` DISABLE KEYS */;
/*!40000 ALTER TABLE `sucursalbancaria` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
