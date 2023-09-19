-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: attendance
-- ------------------------------------------------------
-- Server version	8.0.34-0ubuntu0.22.04.1

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
-- Table structure for table `Attendance`
--

DROP TABLE IF EXISTS `Attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Attendance` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `day` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhqaa9frrymljsesnb8u1nhqg8` (`student_id`),
  CONSTRAINT `FKhqaa9frrymljsesnb8u1nhqg8` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendance`
--

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;
INSERT INTO `Attendance` VALUES (1,'2023-09-19 16:54:28.327000','2023-09-19 00:00:00.000000',_binary '','2023-09-19 16:54:28.327000',1),(2,'2023-09-19 16:54:28.371000','2023-09-19 00:00:00.000000',_binary '\0','2023-09-19 16:54:28.371000',2),(3,'2023-09-19 16:54:28.391000','2023-09-19 00:00:00.000000',_binary '','2023-09-19 16:54:28.391000',3),(4,'2023-09-19 16:54:28.416000','2023-09-19 00:00:00.000000',_binary '\0','2023-09-19 16:54:28.416000',4),(5,'2023-09-19 16:54:28.447000','2023-09-19 00:00:00.000000',NULL,'2023-09-19 16:54:28.447000',5),(6,'2023-09-19 16:54:50.578000','2023-09-18 00:00:00.000000',_binary '','2023-09-19 16:54:50.578000',1),(7,'2023-09-19 16:54:50.599000','2023-09-18 00:00:00.000000',_binary '','2023-09-19 16:54:50.599000',2),(8,'2023-09-19 16:54:50.620000','2023-09-18 00:00:00.000000',_binary '','2023-09-19 16:54:50.620000',3),(9,'2023-09-19 16:54:50.642000','2023-09-18 00:00:00.000000',_binary '','2023-09-19 16:54:50.642000',4),(10,'2023-09-19 16:54:50.664000','2023-09-18 00:00:00.000000',_binary '','2023-09-19 16:54:50.664000',5),(11,'2023-09-19 16:55:14.382000','2023-09-17 00:00:00.000000',_binary '','2023-09-19 16:55:14.382000',1),(12,'2023-09-19 16:55:14.420000','2023-09-17 00:00:00.000000',_binary '','2023-09-19 16:55:14.420000',2),(13,'2023-09-19 16:55:14.448000','2023-09-17 00:00:00.000000',_binary '','2023-09-19 16:55:14.448000',3),(14,'2023-09-19 16:55:14.475000','2023-09-17 00:00:00.000000',_binary '\0','2023-09-19 16:55:14.475000',4),(15,'2023-09-19 16:55:14.503000','2023-09-17 00:00:00.000000',_binary '\0','2023-09-19 16:55:14.503000',5),(16,'2023-09-19 16:55:40.543000','2023-09-16 00:00:00.000000',_binary '','2023-09-19 16:55:40.543000',1),(17,'2023-09-19 16:55:40.583000','2023-09-16 00:00:00.000000',_binary '\0','2023-09-19 16:55:40.583000',2),(18,'2023-09-19 16:55:40.612000','2023-09-16 00:00:00.000000',_binary '\0','2023-09-19 16:55:40.612000',3),(19,'2023-09-19 16:55:40.636000','2023-09-16 00:00:00.000000',_binary '\0','2023-09-19 16:55:40.636000',4),(20,'2023-09-19 16:55:40.659000','2023-09-16 00:00:00.000000',_binary '\0','2023-09-19 16:55:40.659000',5),(21,'2023-09-19 17:03:03.308000','2023-09-15 00:00:00.000000',_binary '','2023-09-19 17:03:03.308000',1),(22,'2023-09-19 17:03:03.374000','2023-09-15 00:00:00.000000',_binary '\0','2023-09-19 17:03:03.374000',2),(23,'2023-09-19 17:03:03.398000','2023-09-15 00:00:00.000000',_binary '','2023-09-19 17:03:03.398000',3),(24,'2023-09-19 17:03:03.429000','2023-09-15 00:00:00.000000',_binary '','2023-09-19 17:03:03.429000',4),(25,'2023-09-19 17:03:03.453000','2023-09-15 00:00:00.000000',_binary '','2023-09-19 17:03:03.453000',5),(26,'2023-09-19 17:03:24.517000','2023-09-14 00:00:00.000000',_binary '\0','2023-09-19 17:03:24.517000',1),(27,'2023-09-19 17:03:24.540000','2023-09-14 00:00:00.000000',NULL,'2023-09-19 17:03:24.540000',2),(28,'2023-09-19 17:03:24.571000','2023-09-14 00:00:00.000000',_binary '','2023-09-19 17:03:24.571000',3),(29,'2023-09-19 17:03:24.605000','2023-09-14 00:00:00.000000',_binary '','2023-09-19 17:03:24.605000',4),(30,'2023-09-19 17:03:24.634000','2023-09-14 00:00:00.000000',_binary '\0','2023-09-19 17:03:24.634000',5),(31,'2023-09-19 17:03:43.130000','2023-09-13 00:00:00.000000',NULL,'2023-09-19 17:03:43.130000',1),(32,'2023-09-19 17:03:43.158000','2023-09-13 00:00:00.000000',_binary '','2023-09-19 17:03:43.158000',2),(33,'2023-09-19 17:03:43.179000','2023-09-13 00:00:00.000000',_binary '','2023-09-19 17:03:43.179000',3),(34,'2023-09-19 17:03:43.204000','2023-09-13 00:00:00.000000',_binary '\0','2023-09-19 17:03:43.204000',4),(35,'2023-09-19 17:03:43.234000','2023-09-13 00:00:00.000000',NULL,'2023-09-19 17:03:43.234000',5),(36,'2023-09-19 17:04:16.474000','2023-09-12 00:00:00.000000',_binary '','2023-09-19 17:04:16.474000',1),(37,'2023-09-19 17:04:16.505000','2023-09-12 00:00:00.000000',_binary '','2023-09-19 17:04:16.505000',2),(38,'2023-09-19 17:04:16.531000','2023-09-12 00:00:00.000000',_binary '','2023-09-19 17:04:16.531000',3),(39,'2023-09-19 17:04:16.561000','2023-09-12 00:00:00.000000',_binary '','2023-09-19 17:04:16.561000',4),(40,'2023-09-19 17:04:16.584000','2023-09-12 00:00:00.000000',_binary '','2023-09-19 17:04:16.584000',5);
/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'2023-09-19 12:19:23.465000','sandeep@gmail.com',_binary '','Sandeep Balli','2023-09-19 12:19:23.465000'),(2,'2023-09-19 12:19:50.613000','smruti@gmail.com',_binary '','Smruti Ranjan','2023-09-19 12:19:50.613000'),(3,'2023-09-19 12:20:05.564000','bishwajit@gmail.com',_binary '','Bishwajit Sahu','2023-09-19 12:20:05.564000'),(4,'2023-09-19 12:20:23.088000','manajit@gmail.com',_binary '','Manajit Pradhan','2023-09-19 12:20:23.088000'),(5,'2023-09-19 12:20:35.245000','akash@gmail.com',_binary '','Akash Ghosh','2023-09-19 12:20:35.245000');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-19 18:25:24
