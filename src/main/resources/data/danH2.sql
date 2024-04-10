-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: mysql
-- Tiempo de generación: 12-09-2023 a las 00:09:22
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.8

-- No es necesario establecer el modo SQL en H2
-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";

-- No es necesario iniciar una transacción en H2
-- START TRANSACTION;

-- No es necesario cambiar la zona horaria en H2
-- SET time_zone = "+00:00";

--
-- Base de datos: `dan`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USR_CLIENTES`
--
-- Crear el esquema si no existe
CREATE SCHEMA IF NOT EXISTS DAN;

-- Utilizar el esquema DAN
SET SCHEMA DAN;

CREATE TABLE IF NOT EXISTS `USR_CLIENTES` (
  `ID` INT AUTO_INCREMENT PRIMARY KEY,
  `RAZON_SOCIAL` VARCHAR(250) NOT NULL,
  `CUIT` VARCHAR(12) NOT NULL,
  `CORREO_ELECTRONICO` VARCHAR(255) NOT NULL,
  `MAX_CTA_CTE` DOUBLE NOT NULL,
  `HABILITADO_ONLINE` TINYINT NOT NULL
);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USR_TIPO_USUARIO`
--

CREATE TABLE IF NOT EXISTS `USR_TIPO_USUARIO` (
  `ID` INT AUTO_INCREMENT PRIMARY KEY,
  `TIPO` VARCHAR(80) NOT NULL
);
--
-- Volcado de datos para la tabla `USR_TIPO_USUARIO`
--

INSERT INTO `USR_TIPO_USUARIO` (`ID`, `TIPO`) VALUES
(1, 'ADMIN'),
(2, 'EMPLEADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USR_USUARIOS`
--

CREATE TABLE IF NOT EXISTS `USR_USUARIOS` (
  `ID` INT AUTO_INCREMENT PRIMARY KEY,
  `USER_NAME` VARCHAR(80) NOT NULL,
  `PASSWORD` VARCHAR(255) NOT NULL,
  `CORREO_ELECTRONICO` VARCHAR(255) NOT NULL,
  `ID_CLIENTE` INT NOT NULL,
  `ID_TIPO_USUARIO` INT NOT NULL,
  CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`ID_CLIENTE`) REFERENCES `USR_CLIENTES` (`ID`),
  CONSTRAINT `FK_TIPO_USUARIO` FOREIGN KEY (`ID_TIPO_USUARIO`) REFERENCES `USR_TIPO_USUARIO` (`ID`)
);