-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: moviereservationdb
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cinema_halls`
--

DROP TABLE IF EXISTS `cinema_halls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cinema_halls` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `capacity` int NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `uuid` varchar(255) DEFAULT NULL,
  `hall_name` varchar(255) NOT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKhupqt63h269wpihk2dsee1ue3` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(1000) DEFAULT NULL,
  `duration` int NOT NULL,
  `genre` varchar(255) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `title` varchar(255) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKra84yut93fexpdigt13t9ckei` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `isActive` bit(1) DEFAULT b'1',
  `status` enum('CANCELLED','CONFIRMED','CREATED') DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `screening_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `seat_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfqnltu91huaa725xcjje48cei` (`uuid`),
  KEY `FKila3vnna00hi6r7nwubaflkp` (`screening_id`),
  KEY `FKbqc054ubmoqj00bl3mey759qx` (`user_id`),
  CONSTRAINT `FKbqc054ubmoqj00bl3mey759qx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKila3vnna00hi6r7nwubaflkp` FOREIGN KEY (`screening_id`) REFERENCES `screenings` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `screenings`
--

DROP TABLE IF EXISTS `screenings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `screenings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `isActive` bit(1) DEFAULT b'1',
  `price` double NOT NULL,
  `screeningDateTime` datetime(6) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `cinema_hall_id` bigint NOT NULL,
  `movie_id` bigint NOT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK7bhrq9ghgi897xdid0b7v4lsp` (`uuid`),
  KEY `FKdevbg1ukvx1dw2molrxvvrwlg` (`cinema_hall_id`),
  KEY `FK3sx2flvifibi9f7p9wxmh9ion` (`movie_id`),
  CONSTRAINT `FK3sx2flvifibi9f7p9wxmh9ion` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`),
  CONSTRAINT `FKdevbg1ukvx1dw2molrxvvrwlg` FOREIGN KEY (`cinema_hall_id`) REFERENCES `cinema_halls` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6j5t70rd2eub907qysjvvd76n` (`email`),
  UNIQUE KEY `UKlgkd7iin2rkv9xkrkvdf6do2v` (`username`),
  UNIQUE KEY `UK6gyaymdekihum67hqjkipaqjs` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-09  1:49:44
