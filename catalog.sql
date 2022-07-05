-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 16, 2021 at 03:36 PM
-- Server version: 8.0.21
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mypenbox`
--

-- --------------------------------------------------------

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
CREATE TABLE IF NOT EXISTS `catalog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `brand` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `color_mark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `color_swatch` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `color_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `img` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `catalog`
--

INSERT INTO `catalog` (`id`, `category`, `brand`, `type`, `color_mark`, `color_swatch`, `color_name`, `img`) VALUES
(1, 'pencil', 'Derwent', 'Inktense', '0100', '#F4ED5F', 'Sherbet Lemon', 'img'),
(2, 'pencil', 'Derwent', 'Inktense', '0200', '#FEE859', 'Sun Yellow', 'img'),
(3, 'pencil', 'Derwent', 'Inktense', '0300', '#F79725', 'Tangerine', 'img'),
(4, 'pencil', 'Derwent', 'Inktense', '0400', '#EE3523', 'Poppy Red', 'img'),
(5, 'pencil', 'Derwent', 'Inktense', '0500', '#BA101B', 'Chilli Red', 'img'),
(6, 'pencil', 'Derwent', 'Inktense', '0600', '#8E0C3A', 'Shiraz', 'img'),
(7, 'pencil', 'Derwent', 'Inktense', '0700', '#C5016B', 'Fuchsia', 'img'),
(8, 'pencil', 'Derwent', 'Inktense', '0800', '#343191', 'Violet', 'img'),
(9, 'pencil', 'Derwent', 'Inktense', '0900', '#0075C2', 'Iris Blue', 'img'),
(10, 'pencil', 'Derwent', 'Inktense', '1000', '#025DA5', 'Bright Blue', 'img'),
(11, 'pencil', 'Derwent', 'Inktense', '1100', '#012A5E', 'Deep Indigo', 'img'),
(12, 'pencil', 'Derwent', 'Inktense', '1200', '#005C82', 'Sea Blue', 'img'),
(13, 'pencil', 'Derwent', 'Inktense', '1300', '#00968B', 'Teal Green', 'img'),
(14, 'pencil', 'Derwent', 'Inktense', '1400', '#72C166', 'Apple Green', 'img'),
(15, 'pencil', 'Derwent', 'Inktense', '1500', '#00AB4E', 'Field Green', 'img'),
(16, 'pencil', 'Derwent', 'Inktense', '1600', '#556C11', 'Leaf Green', 'img'),
(17, 'pencil', 'Derwent', 'Inktense', '1700', '#76A231', 'Mustard', 'img'),
(18, 'pencil', 'Derwent', 'Inktense', '1800', '#D07117', 'Baked Earth', 'img'),
(19, 'pencil', 'Derwent', 'Inktense', '1900', '#6B3420', 'Willow', 'img'),
(20, 'pencil', 'Derwent', 'Inktense', '2000', '#413F2A', 'Bark', 'img'),
(21, 'pencil', 'Derwent', 'Inktense', '2100', '#4E5B52', 'Charcoal Grey', 'img'),
(22, 'pencil', 'Derwent', 'Inktense', '2200', '#261C03', 'Ink Black', 'img'),
(23, 'pencil', 'Derwent', 'Inktense', '2300', '#FDFBF4', 'Antique White', 'img'),
(24, 'pencil', 'Derwent', 'Inktense', '2400', '#271B01', 'Outliner', 'img');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
