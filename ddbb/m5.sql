-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2024 a las 02:38:07
-- Versión del servidor: 8.0.37
-- Versión de PHP: 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `m5`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `rut` varchar(14) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `nombre`, `rut`, `password`, `email`) VALUES
(1, 'Alberto Jara', '15536288-k', '15536288', ''),
(2, 'Teresa Rojas', '8114322-6', '1234', 'admin@admin.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `idCuenta` int NOT NULL,
  `id_cliente` int DEFAULT NULL,
  `id_divisa` int DEFAULT NULL,
  `numerocuenta` int DEFAULT NULL,
  `saldo` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`idCuenta`, `id_cliente`, `id_divisa`, `numerocuenta`, `saldo`) VALUES
(1, 1, 1, 123456, 1040),
(2, 2, NULL, 8114322, 27830);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `divisa`
--

CREATE TABLE `divisa` (
  `idDivisa` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `conversion` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `divisa`
--

INSERT INTO `divisa` (`idDivisa`, `nombre`, `conversion`) VALUES
(1, 'CLP', 1),
(2, 'USD', 900);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `idTransaccion` int NOT NULL,
  `cuentaOrigen` int NOT NULL,
  `cuentaDestino` int NOT NULL,
  `monto` float NOT NULL,
  `detalle` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `transaccion`
--

INSERT INTO `transaccion` (`idTransaccion`, `cuentaOrigen`, `cuentaDestino`, `monto`, `detalle`) VALUES
(1, 1, 1, 10, 'DEPOSITO'),
(2, 1, 2, 20, 'TRANSFERENCIA'),
(3, 2, 1, 10, 'TRANSFERENCIA'),
(4, 1, 2, 10, 'TRANSFERENCIA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`idCuenta`),
  ADD KEY `id_divisa_idx` (`id_divisa`),
  ADD KEY `id_cliente_idx` (`id_cliente`);

--
-- Indices de la tabla `divisa`
--
ALTER TABLE `divisa`
  ADD PRIMARY KEY (`idDivisa`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`idTransaccion`),
  ADD KEY `idCuentaOrigen_idx` (`cuentaOrigen`),
  ADD KEY `idCuentaDestino_idx` (`cuentaDestino`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `idCuenta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `divisa`
--
ALTER TABLE `divisa`
  MODIFY `idDivisa` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  MODIFY `idTransaccion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `id_divisa` FOREIGN KEY (`id_divisa`) REFERENCES `divisa` (`idDivisa`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `fk_cuenta_destino` FOREIGN KEY (`cuentaDestino`) REFERENCES `cuenta` (`idCuenta`),
  ADD CONSTRAINT `fk_cuenta_origen` FOREIGN KEY (`cuentaOrigen`) REFERENCES `cuenta` (`idCuenta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
