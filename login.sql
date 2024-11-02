-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 02-11-2024 a las 01:43:13
-- Versión del servidor: 8.3.0
-- Versión de PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `login`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fechahora`
--

DROP TABLE IF EXISTS `fechahora`;
CREATE TABLE IF NOT EXISTS `fechahora` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DIA` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `ENTRADASALIDA` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `FECHA` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `HORAACTUAL` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish2_ci DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_FECHAHORA_usuario_id` (`usuario_id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish2_ci;

--
-- Volcado de datos para la tabla `fechahora`
--

INSERT INTO `fechahora` (`ID`, `DIA`, `ENTRADASALIDA`, `FECHA`, `HORAACTUAL`, `usuario_id`) VALUES
(1, 'Miércoles', 'Entrada', '02/10/2024', '20:34', 1),
(2, 'Miércoles', 'Salida', '02/10/2024', '20:34', 1),
(3, 'Sábado', 'Salida', '26/10/2024', '20:10', 60),
(4, 'Sábado', 'Vacaciones', '26/10/2024', '20:13', 64),
(5, 'Sábado', 'Licencia', '26/10/2024', '20:13', 65),
(6, 'Sábado', 'Salida', '26/10/2024', '20:22', 77),
(7, 'Sábado', 'Entrada', '26/10/2024', '20:31', 77),
(8, 'Sábado', 'Vacaciones', '26/10/2024', '20:36', 77),
(9, 'Sábado', 'Entrada', '26/10/2024', '20:41', 23),
(10, 'Sábado', 'Entrada', '26/10/2024', '20:41', 14),
(11, 'Sábado', 'Licencia', '26/10/2024', '20:41', 14),
(12, 'Sábado', 'Vacaciones', '26/10/2024', '21:22', 77),
(13, 'Sábado', 'Entrada', '26/10/2024', '21:38', 14),
(14, 'Sábado', 'Vacaciones', '26/10/2024', '21:38', 14),
(15, 'Domingo', 'Entrada', '27/10/2024', '19:09', 1),
(16, 'Domingo', 'Vacaciones', '27/10/2024', '19:11', 1),
(17, 'Domingo', 'Licencia', '27/10/2024', '19:14', 74);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `ID` int NOT NULL,
  `DESCRIPCION` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `NOMBREROL` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`ID`, `DESCRIPCION`, `NOMBREROL`) VALUES
(1, 'Rol para administradores', 'Jefe'),
(2, 'Rol para administradores', 'RRHH'),
(3, 'Rol para usuarios', 'Administrativa'),
(4, 'Rol para usuarios', 'Administrativo'),
(5, 'Rol para usuarios', 'Cadete'),
(6, 'Rol para usuarios', 'Maestranza'),
(7, 'Rol para usuarios', 'Seguridad'),
(8, 'Rol para usuarios', 'Gerente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

DROP TABLE IF EXISTS `sequence`;
CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `ID` int NOT NULL,
  `DNI` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `APELLIDOUSUARIO` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `NOMBREUSUARIO` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_spanish_ci DEFAULT NULL,
  `fk_rol` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USUARIO_fk_rol` (`fk_rol`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID`, `DNI`, `APELLIDOUSUARIO`, `NOMBREUSUARIO`, `fk_rol`) VALUES
(1, '25.896.341', 'Perez', 'Juan Pablo', 1),
(2, '34.625.736', 'Domínguez', 'Clara', 2),
(3, '67.584.920', 'Gómez', 'Armando', 8),
(4, '34.625.776', 'Garcia', 'Ernaldo', 5),
(5, '34.626.786', 'Domínguez', 'Mariano', 4),
(6, '37.652.554', 'Romero', 'Hugo', 8),
(7, '78.534.561', 'Romano', 'Zulma', 3),
(8, '32.198.765', 'Gordillo', 'Javier', 6),
(9, '77.712.345', 'Beltrán', 'Débora', 3),
(10, '87.998.761', 'Benítez', 'Maximiliano', 4),
(11, '12.349.876', 'Heredia', 'Pedro', 5),
(12, '54.321.234', 'Bravo', 'Roberto', 6),
(13, '14.321.3456', 'Delgado', 'Silvia', 3),
(14, '65.476.543', 'Ortega', 'Anibal', 1),
(15, '90.872.134', 'Perez', 'Adriana', 8),
(16, '21.134.567', 'Ortiz', 'Liliana', 6),
(17, '76.564.3216', 'Muñiz', 'Soledad', 3),
(18, '78.923.456', 'Gutierrez', 'Edith', 6),
(19, '98.712.876', 'Rios', 'Lucía', 3),
(20, '76.541.234', 'Ferrari', 'Daniela', 6),
(21, '23.456.787', 'Ibañez', 'Sergio', 4),
(22, '8.563.457', 'Herrero', 'Juan', 5),
(23, '88.812.345', 'Escalante', 'Maria', 3),
(24, '12.121.111', 'Reynoso', 'Hilda', 3),
(25, '98.765.421', 'Campos', 'Jaime', 4),
(26, '34.567.889', 'Muñoz', 'Estela', 3),
(27, '67.678.676', 'Garcia', 'Carina', 6),
(28, '78.934.675', 'Rodríguez', 'Diana', 6),
(29, '12.348.909', 'Garrido', 'Margarita', 3),
(31, '23.456.786', 'Cardoso', 'Lorena', 5),
(32, '98.238.456', 'Gandolfo', 'Isabel', 5),
(33, '90.782.347', 'Arevalo', 'Andrea', 3),
(34, '34.566.789', 'Bonelli', 'Carolina', 3),
(35, '65.476.219', 'Sequeira', 'Zulema', 3),
(36, '89.712.344', 'Estevez', 'Rodolfo', 5),
(37, '56.783.451', 'Navarro', 'Melisa', 7),
(38, '89.012.312', 'Galvan', 'Blanca', 5),
(39, '12.345.432', 'Suarez', 'Fabio', 5),
(40, '76.543.423', 'Iriarte', 'Mariela', 2),
(41, '23.123.456', 'Alonso', 'Anabel', 6),
(42, '76.5432.34', 'Martinez', 'Raquel', 3),
(43, '43.217.890', 'Peña', 'Gisela', 3),
(44, '87.654.321', 'Lobos', 'Daniel', 5),
(45, '12.395.678', 'Torres', 'Alfredo', 6),
(46, '78.654.321', 'Aguirre', 'Horacio', 4),
(47, '43.212.345', 'Montes', 'Alicia', 7),
(48, '65.432.234', 'Guevara', 'Enrique', 4),
(49, '98.761.123', 'Rivarola', 'Celia', 5),
(50, '12.342.178', 'Costas', 'Valentin', 5),
(51, '98.761.234', 'Arias', 'Marcelo', 6),
(52, '56.783.457', 'Vega', 'Ernesto', 4),
(53, '12.345.678', 'Soria', 'Martin', 4),
(54, '98.761.237', 'Barrios', 'Valentin', 5),
(55, '12.342.321', 'Funes', 'Anibal', 4),
(56, '65.432.234', 'Zamudio', 'Hector', 6),
(57, '34.567.891', 'Cabrera', 'Cintia', 2),
(58, '56.783.557', 'Montes', 'Ricardo', 7),
(59, '45.678.901', 'Galindez', 'Graciela', 2),
(60, '65.432.123', 'Ortega', 'Andres', 6),
(61, '87.654.921', 'Quiroga', 'Rosa', 5),
(62, '43.212.345', 'Campos', 'Luciano', 6),
(63, '65.537.129', 'Castillo', 'Monica', 3),
(64, '78.761.734', 'Rey', 'Bernardo', 5),
(65, '76.543.834', 'Escalante', 'Lidia', 6),
(66, '56.783.491', 'Nuñez', 'Roque', 6),
(67, '78.761.978', 'Herrero', 'Elida', 6),
(68, '87.618.393', 'Beltran', 'Rafael', 4),
(69, '78.761.234', 'Funes', 'Damian', 6),
(70, '49.212.395', 'Garcia', 'Alejandra', 3),
(71, '65.439.123', 'Correa', 'Mario', 4),
(72, '23.456.786', 'Romero', 'Susana', 3),
(73, '78.934.678', 'Zarate', 'Gerardo', 5),
(74, '34.567.890', 'Reynoso', 'Ester', 6),
(75, '23.496.986', 'Rios', 'Mariano', 4),
(76, '32.654.789', 'Pérez', 'María', 7),
(77, '15.487.932', 'Rodríguez', 'Juan', 2),
(78, '28.945.671', 'Gómez', 'Carlos', 2),
(79, '42.123.678', 'Fernández', 'Pablo', 4),
(80, '37.541.234', 'López', 'Lucía', 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
