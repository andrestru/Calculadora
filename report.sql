-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-07-2021 a las 14:37:49
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `report`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `identificationT` varchar(30) NOT NULL,
  `identificationS` varchar(30) NOT NULL,
  `startDate` varchar(30) NOT NULL,
  `finalDate` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `report`
--

INSERT INTO `report` (`id`, `identificationT`, `identificationS`, `startDate`, `finalDate`) VALUES
(29, '8', 'j9', '2021-07-01T15:46:00', '2021-07-01T16:46:00'),
(30, '8', 'j8', '2021-07-01T18:13:00', '2021-07-01T19:13:00'),
(31, '8', 'd', '2021-07-02T20:46:00', '2021-07-02T21:49:00'),
(32, '9', 'k', '2021-07-02T21:12:00', '2021-07-02T23:12:00'),
(33, '89hlh', 'lku7', '2021-07-03T18:16:00', '2021-07-03T20:16:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
