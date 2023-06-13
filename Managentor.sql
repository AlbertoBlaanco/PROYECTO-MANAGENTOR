-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2023 a las 20:37:12
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `managentor`
--
CREATE DATABASE IF NOT EXISTS `managentor` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `managentor`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquilados`
--

CREATE TABLE `alquilados` (
  `id_alquilados` int(11) NOT NULL,
  `id_inmueble` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `Fecha` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `alquilados`
--

INSERT INTO `alquilados` (`id_alquilados`, `id_inmueble`, `id_cliente`, `Fecha`) VALUES
(1, 1, 1, '2023-05-02'),
(7, 3, 1, '2023-05-09'),
(8, 2, 2, '2023-05-09'),
(9, 6, 1, '2023-05-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `idInmueble` int(11) DEFAULT NULL,
  `idCliente` int(11) DEFAULT NULL,
  `Hora` varchar(255) DEFAULT NULL,
  `Lugar` varchar(255) DEFAULT NULL,
  `MotivoCita` varchar(255) DEFAULT NULL,
  `Dia` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`idCita`, `idInmueble`, `idCliente`, `Hora`, `Lugar`, `MotivoCita`, `Dia`) VALUES
(1, 1, 2, '10:00', 'Calle Mayor 12', 'Visita de inmueble', '2023-05-02'),
(2, 2, 3, '16:30', 'Avenida del Sur 25', 'Firma de contrato', '2023-05-05'),
(3, 3, 1, '11:00', 'Calle del Pilar 8', 'Entrega de llaves', '2023-05-08'),
(4, 2, 4, '12:00', 'Calle Granada 5', 'Visita de inmueble', '2023-05-09'),
(5, 4, 3, '14:00', 'Calle Alcalá 50', 'Firma de contrato', '2023-05-12'),
(6, 1, 5, '15:30', 'Calle Toledo 20', 'Entrega de llaves', '2023-05-15'),
(7, 3, 6, '09:30', 'Calle Valencia 12', 'Visita de inmueble', '2023-05-17'),
(8, 4, 2, '17:00', 'Calle Bilbao 7', 'Firma de contrato', '2023-05-20'),
(9, 2, 5, '18:00', 'Calle Mayor 12', 'Entrega de llaves', '2023-05-23'),
(10, 1, 6, '10:30', 'Calle Granada 5', 'Visita de inmueble', '2023-05-25');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `idInmueble` int(11) NOT NULL,
  `Fecha_nacicli` varchar(50) DEFAULT NULL,
  `ApellidoCli` varchar(255) DEFAULT NULL,
  `NombreCli` varchar(255) DEFAULT NULL,
  `DireccionCli` varchar(255) DEFAULT NULL,
  `TelefonoCli` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `idInmueble`, `Fecha_nacicli`, `ApellidoCli`, `NombreCli`, `DireccionCli`, `TelefonoCli`) VALUES
(1, 1, '1989-07-15', 'González', 'Laura', 'Calle Mayor 1', 111222333),
(2, 2, '1995-05-08', 'López', 'Pedro', 'Calle Pez 2', 222333444),
(3, 1, '1987-11-22', 'Martín', 'Sofía', 'Calle Alcalá 3', 333444555),
(4, 1, '1998-03-17', 'García', 'Antonio', 'Calle Gran Vía 4', 444555666),
(5, 1, '1991-09-30', 'Hernández', 'María', 'Calle Princesa 5', 555666777),
(6, 1, '1985-01-25', 'Fernández', 'Javier', 'Calle Atocha 6', 666777888),
(7, 3, '1993-08-12', 'Gómez', 'Isabel', 'Calle Goya 7', 777888999),
(8, 4, '1983-12-07', 'Pérez', 'Ricardo', 'Calle Velázquez 8', 888999000),
(9, 5, '1990-04-18', 'Sánchez', 'Carlos', 'Calle Serrano 9', 999000111),
(10, 1, '1988-06-27', 'Romero', 'Ana', 'Calle Mayor 10', 123456789);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `idEmple` int(11) NOT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `Fecha_nac` varchar(255) DEFAULT NULL,
  `DNI` varchar(255) DEFAULT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Apellido` varchar(255) DEFAULT NULL,
  `Password` varchar(255) NOT NULL,
  `Empresa` varchar(255) DEFAULT NULL,
  `Telefono` varchar(11) DEFAULT NULL,
  `Ciudad` varchar(255) DEFAULT NULL,
  `Email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idEmple`, `Direccion`, `Fecha_nac`, `DNI`, `Nombre`, `Apellido`, `Password`, `Empresa`, `Telefono`, `Ciudad`, `Email`) VALUES
(1, 'Calle del Trabajo 123', '1990-05-15', '12345678A', 'Juan', 'Pérez', '123456', 'ABC Inc.', '123456789', 'Madrid', 'jperez@managentor.com'),
(2, 'Avenida de la Empresa 456', '1985-08-10', '87654321B', 'María', 'García', '123456', 'DEF S.A.', '987654321', 'Barcelona', 'mgarcia@managentor.com'),
(3, 'Calle del Progreso 789', '1995-02-25', '13579246C', 'Pedro', 'Sánchez', '123456', 'GHI SL', '369258147', 'Valencia', 'psanchez@managentor.com'),
(4, 'Calle del Futuro 321', '1992-11-18', '24681357D', 'Ana', 'Jiménez', '123456', 'JKL Inc.', '258147369', 'Sevilla', 'ajimenez@managentor.com'),
(5, 'Calle del Éxito 456', '1988-04-30', '46813579E', 'Carlos', 'Gómez', '123456', 'MNO S.A.', '147258369', 'Bilbao', 'cgomez@managentor.com'),
(6, 'Calle del Progreso 789', '1993-09-22', '57924681F', 'Marta', 'Rodríguez', '123456', 'PQR SL', '369852147', 'Murcia', 'mrodriguez@managentor.com'),
(7, 'Calle del Trabajo 123', '1986-06-05', '35791346G', 'David', 'Hernández', '123456', 'STU Inc.', '123987456', 'Granada', 'dhernandez@managentor.com'),
(8, 'Avenida de la Empresa 456', '1991-12-11', '46813579H', 'Sara', 'López', '123456', 'VWX S.A.', '456789123', 'Zaragoza', 'slopez@managentor.com'),
(9, 'Calle del Futuro 321', '1989-03-27', '57924681I', 'Javier', 'Muñoz', '123456', 'YZA Inc.', '789456123', 'Málaga', 'jmunoz@managentor.com'),
(11, 'Pepe', 'Pedri', '13245678N', 'Alberto', 'Blanco', '123456', 'ss', '132456789', 'SIUU', 'ablanco@managentor.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes`
--

CREATE TABLE `imagenes` (
  `id_Imagen` int(11) NOT NULL,
  `Id_Inmueble` int(11) NOT NULL,
  `Url` varchar(550) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `imagenes`
--

INSERT INTO `imagenes` (`id_Imagen`, `Id_Inmueble`, `Url`) VALUES
(1, 1, 'https://img.freepik.com/foto-gratis/hermosa-foto-casa-moderna-cocina-comedor_181624-2870.jpg?w=1380&t=st=1682777431~exp=1682778031~hmac=9c4ae71df28d5264a3580b3e0c3e4ce7ade20856152035c329c9d72ebaacac5d'),
(2, 1, 'https://img.freepik.com/foto-gratis/sala-estar-nadie-ella_482257-55.jpg?w=1380&t=st=1682777446~exp=1682778046~hmac=66b66cd05de1792d4adf73116cc895e0789d09952ac4b99ef461758821db5438'),
(3, 1, 'https://img.freepik.com/foto-gratis/hermosa-foto-casa-moderna-cocina-comedor_181624-2870.jpg?w=1380&t=st=1682777431~exp=1682778031~hmac=9c4ae71df28d5264a3580b3e0c3e4ce7ade20856152035c329c9d72ebaacac5d'),
(4, 4, 'https://i.ytimg.com/vi/Gwt3Wz7vviU/hqdefault.jpg'),
(5, 5, 'https://casasinhaus.com/wp-content/uploads/2022/05/casas-modulares-madrid-lujo-2.jpg'),
(6, 6, 'https://www.yaencontre.com/noticias/wp-content/uploads/2018/03/Casa-en-Madrid.jpg'),
(7, 7, 'https://www.promora.com/images/layout/search-by-map-middle-image.jpg'),
(8, 8, 'https://theconcretehome.com/wp-content/uploads/2021/06/casas-prefabricadas-baratas-madrid-1030x580.jpg'),
(9, 9, 'https://vidamodular.com/wp-content/uploads/2017/12/casas-prefabricadas-madrid-990x594.jpg'),
(10, 10, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTTpD-tBO47QrohG3aqVgWF2a4UdRaxCEDQfw&usqp=CAU'),
(22, 8, 'https://loff.it/wp-content/uploads/2019/05/loffit-madrid-tambien-tiene-casas-de-lujo-sothebys-vende-una-de-ellas-en-pozuelo-01.jpg'),
(23, 2, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2020/08/casa-campo-de-cuento.png'),
(24, 2, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2021/08/casas-de-campo-en-fotocasa.png'),
(25, 3, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2022/10/casa-exclusiva.jpg.optimal.jpg'),
(26, 3, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2021/08/1.-PERATALLADA2.jpg.optimal.jpg'),
(27, 4, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2016/05/Chaet_Marbella.jpg.optimal.jpg'),
(28, 4, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/07/jardin-4-1.jpg.optimal.jpg'),
(29, 2, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/09/4.vanguardista.jpg.optimal.jpg'),
(30, 3, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/05/2.-Imagen-1.jpg.optimal.jpg'),
(31, 5, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2021/08/1.-PERATALLADA1.jpg.optimal.jpg'),
(32, 5, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/07/bellas-6.jpg.optimal.jpg'),
(33, 6, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2021/04/CasaGeosolarGrupoIndex-4.jpg.optimal.jpg'),
(34, 6, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2021/09/casas-curiosas-fotos-en-fotocasa.png'),
(35, 7, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2020/03/1-Casa-rural-reformada.jpg.optimal.jpg'),
(36, 7, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/07/soñar-5.jpg.optimal.jpg'),
(37, 8, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/07/bellas-5.jpg.optimal.jpg'),
(38, 8, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/07/bellas-5.jpg.optimal.jpg'),
(39, 9, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2020/04/4-Paz.jpg.optimal.jpg'),
(40, 9, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2016/05/Chalet-en-Ametlla-De-Mar.jpg.optimal.jpg'),
(41, 10, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2020/01/1.-Jardín-1.jpg.optimal.jpg'),
(42, 10, 'https://static.fotocasa.es/images/anuncio/2020/10/07/157451997/867169044.jpg?rule=web_412x257'),
(43, 10, 'https://s37805.pcdn.co/fotocasa-life/wp-content/uploads/2019/08/jardin-6.jpg.optimal.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inmueble`
--

CREATE TABLE `inmueble` (
  `idInmueble` int(11) NOT NULL,
  `Titular` varchar(255) DEFAULT NULL,
  `CodPostal` int(11) DEFAULT NULL,
  `Ciudad` varchar(150) NOT NULL,
  `Metros` int(11) DEFAULT NULL,
  `Estado` varchar(255) DEFAULT NULL,
  `Precio` float DEFAULT NULL,
  `Tipo` varchar(255) DEFAULT NULL,
  `Direccion` varchar(255) DEFAULT NULL,
  `NumBath` int(11) DEFAULT NULL,
  `NumHabit` int(11) DEFAULT NULL,
  `hasElevator` tinyint(1) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `Imagen` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `inmueble`
--

INSERT INTO `inmueble` (`idInmueble`, `Titular`, `CodPostal`, `Ciudad`, `Metros`, `Estado`, `Precio`, `Tipo`, `Direccion`, `NumBath`, `NumHabit`, `hasElevator`, `descripcion`, `Imagen`) VALUES
(1, 'Juan Perez', 28001, 'Zaragoza', 70, 'Alquilado', 150000, 'Piso', 'Calle Gran Via 1', 1, 2, 0, 'Piso céntrico en buena ubicación', 'https://img.freepik.com/foto-gratis/hermosa-foto-interior-casa-moderna-paredes-blancas-relajantes-muebles-tecnologia_181624-3828.jpg?w=1380&t=st=1682777413~exp=1682778013~hmac=8e653b070878922b7ec068afe8a053f9df0580013f104c69b29e2a8de8e9a0aa'),
(2, 'Maria Garcia', 28002, 'Zaragoza', 100, 'Alquilado', 250000, 'Casa', 'Calle Alcala 2', 2, 3, 0, 'Casa con patio en zona tranquila', 'https://img.freepik.com/foto-gratis/sala-estar-lujo-loft-representacion-3d-estanteria_105762-2104.jpg?w=1380&t=st=1682777382~exp=1682777982~hmac=dca59437c5067370705dde07fde78364acb82834169138cd9540bb70262faf52'),
(3, 'Pedro Rodriguez', 28003, 'Madrid', 50, 'Alquilado', 100000, 'Habitación', 'Calle Serrano 3', 1, 0, 1, 'Habitación con gran potencial', 'https://img.freepik.com/foto-gratis/hermosa-foto-casa-moderna-cocina-comedor_181624-2870.jpg?w=1380&t=st=1682777431~exp=1682778031~hmac=9c4ae71df28d5264a3580b3e0c3e4ce7ade20856152035c329c9d72ebaacac5d'),
(4, 'Laura Perez', 28004, 'Vigo', 80, 'Alquilado', 200000, 'Habitación', 'Calle Princesa 4', 2, 2, 1, 'Habitación con vistas espectaculares', 'https://img.freepik.com/foto-gratis/sala-estar-nadie-ella_482257-55.jpg?w=1380&t=st=1682777446~exp=1682778046~hmac=66b66cd05de1792d4adf73116cc895e0789d09952ac4b99ef461758821db5438'),
(5, 'Roberto Sanchez', 28005, 'Sevilla', 90, 'No alquilado', 180000, 'Piso', 'Calle Mayor 5', 2, 3, 0, 'Piso amplio y luminoso', 'https://img.freepik.com/foto-gratis/3d-renderizado-loft-sala-estar-escandinava-mesa-trabajo-estanteria_105762-2094.jpg?w=1380&t=st=1682777461~exp=1682778061~hmac=4f23cafbd50d581b2b1f925e444842527560444848b8230a6772ffa887fa4d77'),
(6, 'Ana Jimenez', 28006, 'Barcelona', 60, 'Alquilado', 120000, 'Habitación', 'Calle Goya 6', 1, 0, 1, 'Habitación luminosa en zona céntrica', 'https://img.freepik.com/foto-gratis/interior-cocina-moderna-colores-brillantes_181624-61502.jpg?w=1380&t=st=1682777468~exp=1682778068~hmac=cc0719f68d3b2144d592cf7c64c8f0f0e461a048b506d58aa2c02e7e6f798d38'),
(7, 'Carlos Gomez', 28007, 'Castellon', 75, 'Alquilado', 170000, 'Piso', 'Calle Arturo Soria 7', 1, 2, 1, 'Piso amplio en zona tranquila', 'https://img.freepik.com/foto-gratis/representacion-3d-comedor-moderno-sala-estar-decoracion-lujo_105762-1934.jpg?w=1380&t=st=1682777474~exp=1682778074~hmac=474e5bddb34837a92360791f62587c32f0525122cb27be06f8e7c053e89fe710'),
(8, 'Sara Fernandez', 28008, 'Malaga', 110, 'No alquilado', 300000, 'Casa', 'Calle Velazquez 8', 2, 4, 0, 'Casa de lujo en zona exclusiva', 'https://img.freepik.com/foto-gratis/interior-habitacion-blanca-ventanas_181624-58245.jpg?w=1380&t=st=1682777480~exp=1682778080~hmac=d999226216cd2257e116ddb4eee72a64ea1fa096c02679f8f92b6f475ce0a927'),
(9, 'Marta Ruiz', 28009, 'Madrid', 55, 'No alquilado', 130000, 'Piso', 'Calle Orense 9', 1, 1, 0, 'Piso acogedor en zona comercial', 'https://img.freepik.com/foto-gratis/sala-estar-lujo-loft-renderizado-3d-estanteria-cerca-estanteria_105762-2224.jpg?w=1380&t=st=1682777486~exp=1682778086~hmac=d3e7b2aabf8caa6eb597a0b505dc11aa7aa72ca1e720febe6e07833c83e42ee2'),
(10, 'Javier Lopez', 28010, 'Zaragoza', 95, 'Alquilado', 220000, 'Habitación', 'Calle Santa Engracia 10', 2, 2, 1, 'Habitación con gran terraza en zona céntrica', 'https://img.freepik.com/foto-gratis/3d-renderizado-loft-sala-estar-escandinava-mesa-trabajo-estanteria_105762-2162.jpg?w=1380&t=st=1682777513~exp=1682778113~hmac=8dc8cb97ffdb920540698b1f30d027c03068f6e11dadecc21fca82191dd6d5da');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `propietario`
--

CREATE TABLE `propietario` (
  `idPropietario` int(11) NOT NULL,
  `Nombre` varchar(255) DEFAULT NULL,
  `Correo` varchar(255) DEFAULT NULL,
  `Telefono` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `propietario`
--

INSERT INTO `propietario` (`idPropietario`, `Nombre`, `Correo`, `Telefono`) VALUES
(1, 'Arturo Napoles', 'arturonapoles@gmail.com', 123456789),
(2, 'Alberto Blanco', 'haddadMchaddad@gmail.com', 987654321),
(3, 'Enrique Alonso', 'enriquealonso@gmail.com', 555444333),
(4, 'Ana Lopez', 'analopez@gmail.com', 111222333),
(5, 'Pedro Salamanca', 'pedrosalamanca@gmail.com', 888777666),
(6, 'Juan Perez', 'juanperez@hotmail.com', 555666777),
(7, 'Roberto Carlos', 'robertocarlos@yahoo.com', 444333222),
(8, 'Pedro Guayar', 'pedroguayar@gmail.com', 777888999),
(9, 'Maria agustin', 'mariaagustin@hotmail.com', 222111000),
(10, 'Lolo Gomez', 'lolo@yahoo.com', 333222111);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alquilados`
--
ALTER TABLE `alquilados`
  ADD PRIMARY KEY (`id_alquilados`),
  ADD UNIQUE KEY `id_inmueble_2` (`id_inmueble`,`id_cliente`),
  ADD KEY `id_inmueble` (`id_inmueble`,`id_cliente`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`),
  ADD KEY `idInmueble` (`idInmueble`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`idEmple`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indices de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  ADD PRIMARY KEY (`id_Imagen`),
  ADD KEY `Id_Inmueble` (`Id_Inmueble`);

--
-- Indices de la tabla `inmueble`
--
ALTER TABLE `inmueble`
  ADD PRIMARY KEY (`idInmueble`);

--
-- Indices de la tabla `propietario`
--
ALTER TABLE `propietario`
  ADD PRIMARY KEY (`idPropietario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alquilados`
--
ALTER TABLE `alquilados`
  MODIFY `id_alquilados` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `idCliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `idEmple` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  MODIFY `id_Imagen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `inmueble`
--
ALTER TABLE `inmueble`
  MODIFY `idInmueble` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `propietario`
--
ALTER TABLE `propietario`
  MODIFY `idPropietario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquilados`
--
ALTER TABLE `alquilados`
  ADD CONSTRAINT `alquilados_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`idCliente`),
  ADD CONSTRAINT `alquilados_ibfk_2` FOREIGN KEY (`id_inmueble`) REFERENCES `inmueble` (`idInmueble`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`idInmueble`) REFERENCES `inmueble` (`idInmueble`);

--
-- Filtros para la tabla `imagenes`
--
ALTER TABLE `imagenes`
  ADD CONSTRAINT `imagenes_ibfk_1` FOREIGN KEY (`Id_Inmueble`) REFERENCES `inmueble` (`idInmueble`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
