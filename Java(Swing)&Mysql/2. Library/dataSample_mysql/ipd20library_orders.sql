CREATE DATABASE  IF NOT EXISTS `ipd20library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ipd20library`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ipd20library
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `memberId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `dateBorrowed` date NOT NULL,
  `dueDate` date NOT NULL,
  `dateReturned` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bookId_idx` (`bookId`),
  KEY `memberId_idx` (`memberId`),
  CONSTRAINT `bookId` FOREIGN KEY (`bookId`) REFERENCES `books` (`id`),
  CONSTRAINT `memberId` FOREIGN KEY (`memberId`) REFERENCES `members` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=506 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,8,19,'2019-08-26','2019-09-25','2019-09-02'),(2,1,16,'2020-01-28','2020-02-27',NULL),(3,3,95,'2020-01-25','2020-02-24',NULL),(4,12,91,'2019-12-10','2020-01-09','2019-12-18'),(5,17,60,'2019-11-23','2019-12-23','2019-12-07'),(6,5,74,'2019-12-18','2020-01-17','2020-01-14'),(7,9,34,'2019-11-29','2019-12-29','2019-12-12'),(8,6,33,'2019-09-11','2019-10-11','2019-10-05'),(9,17,9,'2019-09-28','2019-10-28','2019-10-04'),(10,6,85,'2019-08-04','2019-09-03','2019-08-22'),(11,7,16,'2019-09-01','2019-10-01','2019-09-02'),(12,2,81,'2020-01-26','2020-02-25',NULL),(13,1,75,'2020-02-22','2020-03-23',NULL),(14,11,82,'2019-12-17','2020-01-16','2019-12-23'),(15,5,36,'2019-12-03','2020-01-02',NULL),(16,12,75,'2019-10-02','2019-11-01','2019-10-05'),(17,12,68,'2019-12-16','2020-01-15',NULL),(18,12,8,'2019-11-17','2019-12-17','2019-11-22'),(19,5,62,'2019-11-17','2019-12-17','2019-12-14'),(20,17,14,'2019-12-02','2020-01-01',NULL),(21,11,96,'2020-01-19','2020-02-18','2020-02-15'),(22,2,85,'2019-08-07','2019-09-06','2019-09-01'),(23,6,54,'2019-12-22','2020-01-21','2019-12-25'),(24,18,44,'2019-11-24','2019-12-24','2019-12-06'),(25,2,47,'2019-09-19','2019-10-19','2019-10-12'),(26,15,95,'2019-08-15','2019-09-14','2019-08-21'),(27,14,92,'2019-08-21','2019-09-20','2019-09-15'),(28,12,2,'2019-11-23','2019-12-23','2019-12-11'),(29,17,86,'2020-02-10','2020-03-11',NULL),(30,11,91,'2019-08-05','2019-09-04','2019-08-20'),(31,17,12,'2019-11-25','2019-12-25','2019-11-29'),(32,10,78,'2019-11-17','2019-12-17',NULL),(33,9,31,'2019-10-15','2019-11-14','2019-10-25'),(34,5,34,'2019-12-25','2020-01-24','2020-01-14'),(35,17,5,'2020-01-24','2020-02-23',NULL),(36,17,10,'2019-09-09','2019-10-09','2019-09-29'),(37,15,69,'2019-10-11','2019-11-10','2019-10-30'),(38,1,34,'2019-11-23','2019-12-23','2019-12-12'),(39,17,83,'2019-09-27','2019-10-27',NULL),(40,3,35,'2020-01-21','2020-02-20','2020-01-24'),(41,17,68,'2019-09-08','2019-10-08','2019-09-21'),(42,15,23,'2019-12-31','2020-01-30',NULL),(43,17,29,'2019-12-27','2020-01-26',NULL),(44,20,6,'2019-11-17','2019-12-17','2019-11-27'),(45,16,92,'2019-10-18','2019-11-17','2019-10-22'),(46,9,71,'2020-02-08','2020-03-09',NULL),(47,7,16,'2019-11-27','2019-12-27','2019-12-11'),(48,5,100,'2019-11-27','2019-12-27','2019-12-03'),(49,2,14,'2019-09-12','2019-10-12',NULL),(50,14,7,'2019-10-29','2019-11-28','2019-11-19'),(51,4,57,'2019-12-21','2020-01-20','2019-12-28'),(52,10,73,'2020-01-21','2020-02-20','2020-02-10'),(53,5,70,'2019-12-22','2020-01-21','2019-12-25'),(54,2,81,'2019-08-17','2019-09-16','2019-09-15'),(55,4,28,'2019-08-16','2019-09-15','2019-08-18'),(56,4,46,'2020-01-14','2020-02-13','2020-01-18'),(57,16,7,'2019-10-31','2019-11-30','2019-11-16'),(58,8,74,'2019-12-02','2020-01-01','2019-12-19'),(59,7,69,'2019-12-15','2020-01-14','2019-12-30'),(60,5,38,'2020-01-23','2020-02-22','2020-01-26'),(61,8,71,'2020-02-13','2020-03-14',NULL),(62,8,82,'2019-11-21','2019-12-21','2019-11-23'),(63,1,81,'2020-02-01','2020-03-02',NULL),(64,20,57,'2019-10-23','2019-11-22','2019-11-02'),(65,13,2,'2020-02-07','2020-03-08',NULL),(66,3,100,'2020-01-30','2020-02-29',NULL),(67,12,37,'2019-12-15','2020-01-14','2020-01-02'),(68,2,59,'2019-09-29','2019-10-29',NULL),(69,1,18,'2019-11-19','2019-12-19','2019-12-13'),(70,20,3,'2019-12-15','2020-01-14','2019-12-27'),(71,14,95,'2020-02-08','2020-03-09',NULL),(72,8,72,'2020-01-28','2020-02-27',NULL),(73,18,84,'2019-08-23','2019-09-22','2019-09-18'),(74,1,10,'2020-02-10','2020-03-11','2020-02-24'),(75,11,70,'2019-08-02','2019-09-01','2019-08-11'),(76,12,20,'2019-09-15','2019-10-15','2019-10-12'),(77,1,75,'2020-02-11','2020-03-12',NULL),(78,12,91,'2019-09-06','2019-10-06','2019-09-11'),(79,10,57,'2019-11-18','2019-12-18',NULL),(80,19,2,'2019-09-02','2019-10-02','2019-09-22'),(81,6,99,'2019-11-14','2019-12-14','2019-11-29'),(82,15,24,'2019-09-05','2019-10-05','2019-09-22'),(83,12,23,'2019-11-28','2019-12-28','2019-12-05'),(84,19,90,'2019-10-18','2019-11-17','2019-11-08'),(85,14,14,'2020-01-29','2020-02-28',NULL),(86,10,33,'2019-08-30','2019-09-29','2019-09-02'),(87,15,12,'2020-01-20','2020-02-19','2020-01-25'),(88,15,3,'2019-12-02','2020-01-01','2019-12-10'),(89,10,90,'2020-02-20','2020-03-21',NULL),(90,10,30,'2019-08-17','2019-09-16','2019-09-16'),(91,19,100,'2019-12-27','2020-01-26','2020-01-18'),(92,7,93,'2019-12-01','2019-12-31','2019-12-20'),(93,10,27,'2020-01-18','2020-02-17','2020-02-05'),(94,19,21,'2019-12-25','2020-01-24',NULL),(95,4,12,'2020-01-23','2020-02-22',NULL),(96,5,10,'2019-10-15','2019-11-14',NULL),(97,12,29,'2019-10-11','2019-11-10','2019-11-03'),(98,8,95,'2019-11-14','2019-12-14','2019-11-15'),(99,3,99,'2020-01-13','2020-02-12','2020-01-20'),(100,11,36,'2019-09-10','2019-10-10','2019-10-03'),(101,3,27,'2019-08-19','2019-09-18','2019-09-03'),(102,18,53,'2020-02-09','2020-03-10',NULL),(103,5,80,'2019-09-29','2019-10-29','2019-10-01'),(104,19,46,'2019-11-17','2019-12-17','2019-12-09'),(105,13,61,'2019-10-28','2019-11-27','2019-11-18'),(106,11,95,'2019-12-07','2020-01-06','2019-12-29'),(107,4,22,'2019-10-16','2019-11-15','2019-11-13'),(108,4,96,'2020-01-03','2020-02-02','2020-01-10'),(109,13,31,'2019-10-22','2019-11-21','2019-10-29'),(110,8,43,'2019-12-18','2020-01-17','2020-01-14'),(111,2,34,'2019-09-14','2019-10-14','2019-09-16'),(112,1,67,'2019-12-11','2020-01-10','2020-01-10'),(113,10,95,'2019-12-08','2020-01-07','2019-12-17'),(114,13,40,'2019-08-13','2019-09-12','2019-09-04'),(115,20,89,'2019-10-25','2019-11-24','2019-11-12'),(116,16,9,'2019-10-01','2019-10-31','2019-10-14'),(117,13,62,'2020-02-14','2020-03-15',NULL),(118,11,45,'2020-01-29','2020-02-28',NULL),(119,5,30,'2019-09-18','2019-10-18','2019-10-12'),(120,4,100,'2019-09-06','2019-10-06','2019-09-10'),(121,15,84,'2019-12-30','2020-01-29','2020-01-01'),(122,1,31,'2019-12-30','2020-01-29','2020-01-18'),(123,18,6,'2019-12-24','2020-01-23','2020-01-07'),(124,2,29,'2019-12-10','2020-01-09','2019-12-14'),(125,10,18,'2019-11-23','2019-12-23','2019-11-29'),(126,5,75,'2019-12-15','2020-01-14','2019-12-24'),(127,1,35,'2019-10-08','2019-11-07','2019-10-29'),(128,14,1,'2019-12-30','2020-01-29','2020-01-07'),(129,9,4,'2019-11-03','2019-12-03','2019-11-08'),(130,17,19,'2019-09-24','2019-10-24','2019-10-11'),(131,17,2,'2020-01-15','2020-02-14','2020-01-16'),(132,7,2,'2019-10-29','2019-11-28','2019-11-14'),(133,13,4,'2020-02-20','2020-03-21',NULL),(134,5,99,'2019-09-06','2019-10-06','2019-10-06'),(135,10,18,'2019-12-13','2020-01-12','2019-12-27'),(136,12,14,'2020-01-08','2020-02-07','2020-01-19'),(137,13,3,'2020-01-09','2020-02-08','2020-01-31'),(138,7,30,'2019-12-25','2020-01-24','2019-12-30'),(139,16,55,'2019-11-06','2019-12-06','2019-11-30'),(140,19,15,'2019-11-03','2019-12-03','2019-12-01'),(141,7,65,'2019-08-25','2019-09-24','2019-09-13'),(142,16,84,'2019-09-16','2019-10-16','2019-09-29'),(143,8,72,'2019-11-27','2019-12-27','2019-12-07'),(144,14,35,'2019-10-20','2019-11-19','2019-11-11'),(145,19,40,'2020-02-13','2020-03-14',NULL),(146,17,2,'2020-02-13','2020-03-14',NULL),(147,10,51,'2019-09-09','2019-10-09','2019-09-13'),(148,7,25,'2019-12-19','2020-01-18','2019-12-26'),(149,12,100,'2020-01-29','2020-02-28',NULL),(150,4,34,'2020-01-31','2020-03-01',NULL),(151,10,70,'2019-08-08','2019-09-07','2019-08-21'),(152,14,98,'2020-02-01','2020-03-02',NULL),(153,4,4,'2019-09-10','2019-10-10','2019-09-29'),(154,7,82,'2019-10-30','2019-11-29','2019-11-22'),(155,16,22,'2019-12-09','2020-01-08','2019-12-23'),(156,12,7,'2019-08-21','2019-09-20','2019-08-27'),(157,12,94,'2019-09-29','2019-10-29','2019-10-16'),(158,1,7,'2020-01-11','2020-02-10','2020-02-03'),(159,12,71,'2020-01-22','2020-02-21','2020-01-23'),(160,9,44,'2020-02-08','2020-03-09',NULL),(161,2,51,'2020-02-02','2020-03-03',NULL),(162,11,20,'2019-08-09','2019-09-08','2019-08-29'),(163,13,7,'2019-11-12','2019-12-12','2019-12-02'),(164,2,41,'2019-09-21','2019-10-21','2019-09-23'),(165,15,50,'2019-12-04','2020-01-03','2020-01-03'),(166,19,12,'2020-02-07','2020-03-08',NULL),(167,16,83,'2019-12-16','2020-01-15','2020-01-02'),(168,2,37,'2019-10-24','2019-11-23','2019-11-23'),(169,14,97,'2019-10-11','2019-11-10','2019-10-12'),(170,8,36,'2019-08-18','2019-09-17','2019-09-02'),(171,15,29,'2019-10-11','2019-11-10','2019-10-22'),(172,7,74,'2020-01-20','2020-02-19','2020-01-26'),(173,16,22,'2019-12-27','2020-01-26','2020-01-03'),(174,8,53,'2020-02-16','2020-03-17',NULL),(175,10,27,'2019-08-12','2019-09-11','2019-08-24'),(176,2,90,'2019-10-31','2019-11-30','2019-11-30'),(177,4,83,'2019-08-01','2019-08-31','2019-08-21'),(178,6,18,'2019-12-10','2020-01-09','2020-01-01'),(179,13,82,'2019-10-04','2019-11-03','2019-10-31'),(180,5,93,'2019-09-13','2019-10-13','2019-09-29'),(181,18,59,'2019-09-03','2019-10-03','2019-09-14'),(182,12,10,'2019-09-17','2019-10-17','2019-09-23'),(183,19,56,'2020-01-27','2020-02-26',NULL),(184,3,38,'2020-01-05','2020-02-04','2020-01-07'),(185,9,11,'2019-12-26','2020-01-25','2020-01-20'),(186,4,90,'2019-12-23','2020-01-22','2020-01-12'),(187,8,11,'2019-10-22','2019-11-21','2019-10-28'),(188,8,1,'2020-02-11','2020-03-12',NULL),(189,20,3,'2019-11-13','2019-12-13','2019-12-09'),(190,10,75,'2019-12-22','2020-01-21','2020-01-13'),(191,2,17,'2019-08-28','2019-09-27','2019-09-01'),(192,5,93,'2019-10-07','2019-11-06','2019-10-21'),(193,6,34,'2019-10-18','2019-11-17','2019-11-09'),(194,12,45,'2019-10-18','2019-11-17','2019-11-07'),(195,18,40,'2020-01-24','2020-02-23',NULL),(196,13,78,'2020-02-02','2020-03-03',NULL),(197,17,15,'2020-01-06','2020-02-05','2020-01-18'),(198,12,8,'2019-12-28','2020-01-27','2020-01-09'),(199,1,5,'2019-09-11','2019-10-11','2019-09-21'),(200,19,30,'2019-08-26','2019-09-25','2019-09-19'),(201,6,62,'2019-09-20','2019-10-20','2019-10-02'),(202,10,90,'2019-08-31','2019-09-30','2019-09-21'),(203,20,10,'2019-12-24','2020-01-23','2020-01-19'),(204,14,18,'2019-12-29','2020-01-28','2020-01-12'),(205,11,25,'2019-08-11','2019-09-10','2019-09-03'),(206,1,26,'2019-12-10','2020-01-09','2019-12-22'),(207,13,7,'2019-09-04','2019-10-04','2019-09-23'),(208,8,7,'2019-11-08','2019-12-08','2019-11-26'),(209,10,46,'2019-10-11','2019-11-10','2019-10-12'),(210,15,5,'2019-11-28','2019-12-28','2019-12-28'),(211,10,11,'2020-02-07','2020-03-08',NULL),(212,14,16,'2019-08-21','2019-09-20','2019-09-07'),(213,14,1,'2020-02-08','2020-03-09',NULL),(214,15,98,'2019-11-14','2019-12-14','2019-11-21'),(215,3,21,'2019-11-19','2019-12-19','2019-11-25'),(216,9,75,'2019-11-09','2019-12-09','2019-12-02'),(217,11,81,'2020-02-22','2020-03-23',NULL),(218,19,100,'2019-10-11','2019-11-10','2019-10-27'),(219,1,58,'2019-11-24','2019-12-24','2019-12-01'),(220,20,13,'2019-08-26','2019-09-25','2019-09-12'),(221,3,27,'2019-09-25','2019-10-25','2019-10-12'),(222,13,26,'2019-12-18','2020-01-17','2019-12-27'),(223,2,48,'2019-08-31','2019-09-30','2019-09-16'),(224,3,39,'2019-10-03','2019-11-02','2019-10-27'),(225,19,79,'2019-12-31','2020-01-30','2020-01-26'),(226,14,3,'2019-09-04','2019-10-04','2019-09-07'),(227,11,8,'2019-10-04','2019-11-03','2019-10-18'),(228,4,85,'2019-10-04','2019-11-03','2019-10-28'),(229,12,9,'2019-09-05','2019-10-05','2019-09-20'),(230,6,96,'2019-10-31','2019-11-30','2019-11-10'),(231,9,35,'2019-11-25','2019-12-25','2019-12-12'),(232,13,90,'2019-12-16','2020-01-15','2020-01-08'),(233,7,78,'2019-10-04','2019-11-03','2019-10-10'),(234,9,90,'2019-11-28','2019-12-28','2019-12-01'),(235,19,49,'2020-02-01','2020-03-02',NULL),(236,18,48,'2019-11-30','2019-12-30','2019-12-18'),(237,2,74,'2019-11-23','2019-12-23','2019-12-15'),(238,16,30,'2019-12-05','2020-01-04','2019-12-20'),(239,12,27,'2020-02-11','2020-03-12',NULL),(240,15,30,'2019-11-24','2019-12-24','2019-12-10'),(241,10,98,'2019-09-27','2019-10-27','2019-09-29'),(242,8,8,'2019-09-25','2019-10-25','2019-10-12'),(243,14,41,'2019-10-05','2019-11-04','2019-10-13'),(244,18,71,'2020-02-02','2020-03-03',NULL),(245,7,33,'2019-12-29','2020-01-28','2020-01-22'),(246,13,41,'2019-12-11','2020-01-10','2020-01-10'),(247,17,87,'2019-12-15','2020-01-14','2020-01-05'),(248,10,9,'2019-11-07','2019-12-07','2019-11-28'),(249,5,98,'2020-02-07','2020-03-08',NULL),(250,10,31,'2019-09-14','2019-10-14','2019-10-02'),(251,5,18,'2020-01-02','2020-02-01','2020-01-10'),(252,12,65,'2019-08-01','2019-08-31','2019-08-22'),(253,8,47,'2019-09-12','2019-10-12','2019-10-11'),(254,20,62,'2019-11-03','2019-12-03','2019-11-17'),(255,3,95,'2019-11-10','2019-12-10','2019-12-05'),(256,13,41,'2019-08-05','2019-09-04','2019-08-28'),(257,2,94,'2019-09-28','2019-10-28','2019-09-29'),(258,14,69,'2019-11-01','2019-12-01','2019-11-15'),(259,7,39,'2019-12-22','2020-01-21','2019-12-31'),(260,13,83,'2020-02-01','2020-03-02',NULL),(261,14,33,'2020-01-23','2020-02-22','2020-01-26'),(262,1,64,'2019-10-11','2019-11-10','2019-11-03'),(263,1,16,'2019-09-02','2019-10-02','2019-09-19'),(264,16,12,'2019-12-20','2020-01-19','2019-12-24'),(265,17,24,'2019-08-02','2019-09-01','2019-08-25'),(266,5,50,'2019-11-11','2019-12-11','2019-12-03'),(267,11,70,'2019-09-22','2019-10-22','2019-10-16'),(268,5,56,'2020-02-12','2020-03-13',NULL),(269,20,98,'2019-10-09','2019-11-08','2019-10-26'),(270,2,48,'2020-01-27','2020-02-26',NULL),(271,12,29,'2019-10-21','2019-11-20','2019-11-17'),(272,20,28,'2019-10-16','2019-11-15','2019-10-25'),(273,2,41,'2019-11-22','2019-12-22','2019-12-09'),(274,19,20,'2019-11-05','2019-12-05','2019-11-08'),(275,20,99,'2019-08-05','2019-09-04','2019-08-10'),(276,2,70,'2020-01-02','2020-02-01','2020-01-25'),(277,7,90,'2019-12-21','2020-01-20','2019-12-22'),(278,20,9,'2019-09-24','2019-10-24','2019-10-18'),(279,18,65,'2019-08-13','2019-09-12','2019-09-10'),(280,2,66,'2019-08-10','2019-09-09','2019-08-28'),(281,14,68,'2020-01-25','2020-02-24',NULL),(282,19,88,'2019-08-11','2019-09-10','2019-08-23'),(283,13,38,'2019-08-01','2019-08-31','2019-08-25'),(284,3,24,'2019-08-31','2019-09-30','2019-09-23'),(285,16,65,'2019-10-18','2019-11-17','2019-10-20'),(286,6,94,'2019-11-19','2019-12-19','2019-12-15'),(287,7,10,'2020-01-03','2020-02-02','2020-01-16'),(288,4,96,'2019-11-18','2019-12-18','2019-11-22'),(289,16,44,'2019-09-17','2019-10-17','2019-10-02'),(290,14,83,'2019-12-12','2020-01-11','2019-12-27'),(291,12,15,'2019-11-27','2019-12-27','2019-12-23'),(292,4,42,'2019-12-05','2020-01-04','2019-12-16'),(293,16,92,'2019-08-12','2019-09-11','2019-08-27'),(294,13,71,'2019-12-07','2020-01-06','2020-01-02'),(295,14,86,'2020-01-09','2020-02-08','2020-01-11'),(296,16,42,'2019-08-11','2019-09-10','2019-08-22'),(297,1,87,'2019-08-07','2019-09-06','2019-09-06'),(298,3,39,'2019-08-30','2019-09-29','2019-09-27'),(299,16,40,'2019-11-11','2019-12-11','2019-12-09'),(300,8,72,'2019-11-03','2019-12-03','2019-11-05'),(301,3,77,'2019-11-01','2019-12-01','2019-11-14'),(302,6,100,'2019-11-03','2019-12-03','2019-11-27'),(303,11,90,'2019-08-04','2019-09-03','2019-09-01'),(304,14,1,'2019-08-31','2019-09-30','2019-09-05'),(305,18,5,'2019-10-10','2019-11-09','2019-10-29'),(306,11,63,'2020-02-21','2020-03-22',NULL),(307,15,74,'2020-02-22','2020-03-23',NULL),(308,5,68,'2020-01-24','2020-02-23',NULL),(309,13,40,'2020-02-03','2020-03-04',NULL),(310,10,50,'2019-09-22','2019-10-22','2019-10-15'),(311,17,64,'2019-11-19','2019-12-19','2019-12-15'),(312,9,100,'2019-09-02','2019-10-02','2019-09-15'),(313,13,59,'2019-10-05','2019-11-04','2019-10-23'),(314,2,71,'2019-11-15','2019-12-15','2019-12-14'),(315,16,11,'2020-01-12','2020-02-11','2020-02-06'),(316,6,58,'2019-10-31','2019-11-30','2019-11-20'),(317,4,13,'2019-12-31','2020-01-30','2020-01-28'),(318,10,23,'2019-08-11','2019-09-10','2019-08-26'),(319,20,21,'2019-10-15','2019-11-14','2019-11-10'),(320,19,92,'2020-01-31','2020-03-01',NULL),(321,16,72,'2019-08-11','2019-09-10','2019-08-20'),(322,10,67,'2020-01-07','2020-02-06','2020-01-26'),(323,19,71,'2019-08-19','2019-09-18','2019-09-14'),(324,17,7,'2019-11-16','2019-12-16','2019-12-02'),(325,4,41,'2019-11-26','2019-12-26','2019-12-16'),(326,1,9,'2020-01-11','2020-02-10','2020-02-08'),(327,5,83,'2019-09-11','2019-10-11','2019-09-30'),(328,3,23,'2020-01-11','2020-02-10','2020-01-25'),(329,7,3,'2019-08-23','2019-09-22','2019-08-25'),(330,5,20,'2019-09-27','2019-10-27','2019-10-03'),(331,7,42,'2019-10-06','2019-11-05','2019-10-31'),(332,10,58,'2020-02-13','2020-03-14',NULL),(333,15,16,'2020-02-17','2020-03-18',NULL),(334,13,33,'2020-01-19','2020-02-18','2020-02-01'),(335,8,34,'2019-11-19','2019-12-19','2019-11-21'),(336,18,94,'2019-08-21','2019-09-20','2019-08-28'),(337,8,51,'2019-11-07','2019-12-07','2019-11-14'),(338,6,14,'2020-02-10','2020-03-11',NULL),(339,17,20,'2019-10-09','2019-11-08','2019-11-01'),(340,16,52,'2019-08-07','2019-09-06','2019-08-30'),(341,5,90,'2019-12-29','2020-01-28','2020-01-24'),(342,19,24,'2020-02-08','2020-03-09',NULL),(343,3,67,'2020-02-02','2020-03-03',NULL),(344,10,13,'2020-02-22','2020-03-23',NULL),(345,13,46,'2020-02-20','2020-03-21',NULL),(346,8,27,'2019-12-19','2020-01-18','2019-12-25'),(347,18,92,'2019-11-18','2019-12-18','2019-11-30'),(348,6,25,'2019-11-26','2019-12-26','2019-11-30'),(349,18,89,'2019-10-03','2019-11-02','2019-10-20'),(350,8,90,'2019-12-02','2020-01-01','2019-12-27'),(351,12,15,'2019-10-24','2019-11-23','2019-10-30'),(352,17,84,'2019-12-26','2020-01-25','2020-01-22'),(353,4,64,'2019-12-02','2020-01-01','2019-12-11'),(354,4,11,'2019-09-25','2019-10-25','2019-10-16'),(355,8,80,'2019-10-03','2019-11-02','2019-10-13'),(356,14,56,'2020-01-01','2020-01-31','2020-01-27'),(357,12,89,'2019-10-09','2019-11-08','2019-11-03'),(358,4,9,'2019-12-17','2020-01-16','2019-12-30'),(359,13,85,'2019-10-01','2019-10-31','2019-10-24'),(360,9,73,'2019-12-25','2020-01-24','2020-01-10'),(361,11,84,'2020-02-10','2020-03-11',NULL),(362,2,89,'2020-01-28','2020-02-27',NULL),(363,5,51,'2019-08-28','2019-09-27','2019-09-14'),(364,2,55,'2019-09-21','2019-10-21','2019-10-17'),(365,8,5,'2019-09-08','2019-10-08','2019-09-10'),(366,2,41,'2020-01-08','2020-02-07','2020-01-25'),(367,20,81,'2019-08-14','2019-09-13','2019-09-11'),(368,13,43,'2019-08-13','2019-09-12','2019-08-17'),(369,12,43,'2019-09-17','2019-10-17','2019-10-12'),(370,17,69,'2020-01-14','2020-02-13','2020-02-03'),(371,16,72,'2019-08-23','2019-09-22','2019-08-24'),(372,17,83,'2019-11-27','2019-12-27','2019-12-20'),(373,4,41,'2020-02-17','2020-03-18',NULL),(374,2,32,'2019-08-02','2019-09-01','2019-08-07'),(375,7,11,'2020-02-17','2020-03-18',NULL),(376,7,31,'2019-12-06','2020-01-05','2019-12-19'),(377,5,28,'2020-01-18','2020-02-17','2020-02-09'),(378,12,89,'2019-09-04','2019-10-04','2019-09-25'),(379,6,51,'2019-12-28','2020-01-27','2020-01-22'),(380,3,85,'2019-10-03','2019-11-02','2019-10-06'),(381,12,26,'2020-01-30','2020-02-29',NULL),(382,17,65,'2019-11-10','2019-12-10','2019-11-17'),(383,10,13,'2019-08-09','2019-09-08','2019-08-16'),(384,16,12,'2020-02-02','2020-03-03',NULL),(385,18,37,'2020-01-26','2020-02-25',NULL),(386,6,61,'2019-10-04','2019-11-03','2019-10-09'),(387,4,8,'2020-02-03','2020-03-04',NULL),(388,11,82,'2019-11-03','2019-12-03','2019-11-24'),(389,20,4,'2019-10-12','2019-11-11','2019-10-14'),(390,16,14,'2019-10-30','2019-11-29','2019-10-31'),(391,12,65,'2019-10-10','2019-11-09','2019-10-13'),(392,3,15,'2020-01-21','2020-02-20','2020-01-25'),(393,16,1,'2019-08-08','2019-09-07','2019-08-14'),(394,5,3,'2020-01-04','2020-02-03','2020-01-22'),(395,5,26,'2019-09-24','2019-10-24','2019-10-15'),(396,16,34,'2020-01-19','2020-02-18','2020-02-12'),(397,18,90,'2020-01-23','2020-02-22','2020-02-20'),(398,16,69,'2019-08-07','2019-09-06','2019-08-25'),(399,16,54,'2019-08-02','2019-09-01','2019-08-03'),(400,3,29,'2019-09-26','2019-10-26','2019-10-26'),(401,10,58,'2020-02-16','2020-03-17',NULL),(402,12,5,'2020-02-12','2020-03-13',NULL),(403,1,67,'2019-08-02','2019-09-01','2019-08-26'),(404,2,55,'2019-10-14','2019-11-13','2019-10-24'),(405,20,16,'2020-01-21','2020-02-20','2020-02-20'),(406,3,36,'2020-01-18','2020-02-17','2020-02-14'),(407,18,7,'2020-01-11','2020-02-10','2020-01-17'),(408,19,43,'2020-01-01','2020-01-31','2020-01-18'),(409,9,100,'2019-12-06','2020-01-05','2019-12-22'),(410,11,82,'2019-09-05','2019-10-05','2019-09-14'),(411,2,5,'2019-10-09','2019-11-08','2019-10-23'),(412,14,86,'2020-01-03','2020-02-02','2020-01-31'),(413,1,32,'2019-09-30','2019-10-30','2019-10-15'),(414,17,31,'2019-12-24','2020-01-23','2020-01-17'),(415,4,10,'2020-01-09','2020-02-08','2020-02-07'),(416,7,9,'2019-10-04','2019-11-03','2019-10-17'),(417,5,32,'2019-12-18','2020-01-17','2019-12-24'),(418,5,5,'2020-02-20','2020-03-21',NULL),(419,9,89,'2019-12-01','2019-12-31','2019-12-07'),(420,18,92,'2019-10-12','2019-11-11','2019-10-24'),(421,6,29,'2019-11-05','2019-12-05','2019-11-26'),(422,11,7,'2020-02-19','2020-03-20',NULL),(423,5,89,'2019-08-09','2019-09-08','2019-09-06'),(424,1,92,'2020-02-20','2020-03-21',NULL),(425,8,45,'2019-11-03','2019-12-03','2019-11-26'),(426,20,18,'2019-09-17','2019-10-17','2019-10-02'),(427,8,66,'2019-08-02','2019-09-01','2019-08-14'),(428,9,77,'2019-09-05','2019-10-05','2019-09-25'),(429,7,39,'2019-10-20','2019-11-19','2019-11-03'),(430,20,67,'2020-02-19','2020-03-20',NULL),(431,1,2,'2019-10-13','2019-11-12','2019-10-14'),(432,5,18,'2019-08-02','2019-09-01','2019-08-20'),(433,12,76,'2020-02-11','2020-03-12',NULL),(434,5,59,'2019-09-22','2019-10-22','2019-09-30'),(435,10,46,'2019-10-19','2019-11-18','2019-11-06'),(436,13,41,'2019-09-25','2019-10-25','2019-10-17'),(437,10,30,'2019-12-16','2020-01-15','2019-12-27'),(438,14,25,'2020-02-14','2020-03-15',NULL),(439,3,54,'2020-01-23','2020-02-22','2020-02-18'),(440,5,76,'2019-10-03','2019-11-02','2019-10-18'),(441,19,44,'2019-11-16','2019-12-16','2019-11-29'),(442,7,55,'2020-01-10','2020-02-09','2020-01-25'),(443,4,1,'2020-02-08','2020-03-09',NULL),(444,1,56,'2020-01-19','2020-02-18','2020-02-13'),(445,2,32,'2019-10-19','2019-11-18','2019-10-25'),(446,8,82,'2019-11-15','2019-12-15','2019-12-05'),(447,12,23,'2020-01-01','2020-01-31','2020-01-25'),(448,6,68,'2019-08-22','2019-09-21','2019-09-03'),(449,7,59,'2019-11-09','2019-12-09','2019-11-18'),(450,2,46,'2019-09-10','2019-10-10','2019-10-02'),(451,15,36,'2019-10-24','2019-11-23','2019-11-17'),(452,20,100,'2020-01-01','2020-01-31','2020-01-13'),(453,20,5,'2020-01-05','2020-02-04','2020-01-14'),(454,14,85,'2020-01-28','2020-02-27',NULL),(455,17,9,'2019-10-31','2019-11-30','2019-11-04'),(456,17,84,'2019-12-26','2020-01-25','2019-12-28'),(457,16,84,'2019-08-08','2019-09-07','2019-08-11'),(458,5,10,'2019-08-19','2019-09-18','2019-09-06'),(459,9,87,'2020-01-05','2020-02-04','2020-01-09'),(460,10,88,'2019-12-05','2020-01-04','2019-12-22'),(461,3,1,'2020-01-22','2020-02-21','2020-02-19'),(462,10,32,'2019-11-25','2019-12-25','2019-11-27'),(463,4,45,'2019-12-25','2020-01-24','2020-01-03'),(464,2,81,'2019-10-06','2019-11-05','2019-10-24'),(465,15,11,'2019-12-16','2020-01-15','2019-12-19'),(466,12,52,'2019-12-31','2020-01-30','2020-01-04'),(467,2,22,'2019-10-11','2019-11-10','2019-10-23'),(468,9,52,'2020-02-10','2020-03-11',NULL),(469,11,10,'2019-11-10','2019-12-10','2019-11-19'),(470,14,77,'2020-02-20','2020-03-21',NULL),(471,16,43,'2020-02-21','2020-03-22',NULL),(472,5,78,'2019-09-19','2019-10-19','2019-09-29'),(473,11,26,'2019-09-27','2019-10-27','2019-10-19'),(474,19,98,'2019-11-09','2019-12-09','2019-11-19'),(475,6,85,'2019-11-14','2019-12-14','2019-12-11'),(476,17,1,'2019-10-11','2019-11-10','2019-10-31'),(477,18,27,'2019-11-23','2019-12-23','2019-11-26'),(478,2,55,'2020-02-01','2020-03-02',NULL),(479,4,75,'2019-10-12','2019-11-11','2019-10-29'),(480,18,95,'2019-12-25','2020-01-24','2020-01-04'),(481,7,37,'2020-02-10','2020-03-11',NULL),(482,13,34,'2019-09-22','2019-10-22','2019-10-06'),(483,18,12,'2020-01-28','2020-02-27',NULL),(484,16,32,'2020-01-14','2020-02-13','2020-01-20'),(485,14,9,'2019-10-04','2019-11-03','2019-10-06'),(486,15,3,'2020-02-22','2020-03-23',NULL),(487,13,45,'2019-11-20','2019-12-20','2019-11-29'),(488,19,99,'2019-09-30','2019-10-30','2019-10-21'),(489,9,69,'2020-01-05','2020-02-04','2020-01-21'),(490,19,31,'2019-08-31','2019-09-30','2019-09-09'),(491,17,74,'2020-01-14','2020-02-13','2020-02-13'),(492,5,90,'2019-10-02','2019-11-01','2019-10-03'),(493,2,16,'2019-12-08','2020-01-07','2019-12-29'),(494,20,66,'2019-11-23','2019-12-23','2019-12-11'),(495,8,25,'2019-08-24','2019-09-23','2019-08-31'),(496,1,52,'2019-08-19','2019-09-18','2019-08-23'),(497,14,7,'2019-11-25','2019-12-25','2019-12-13'),(498,4,68,'2019-08-26','2019-09-25','2019-09-07'),(499,6,34,'2019-10-21','2019-11-20','2019-10-25'),(500,18,88,'2019-08-01','2019-08-31','2019-08-15'),(501,3,68,'2020-02-23','2020-05-23',NULL),(502,1,16,'2020-02-23','2020-03-04',NULL),(503,1,16,'2020-02-23','2020-03-04',NULL),(504,1,16,'2020-02-23','2020-03-24',NULL),(505,42,4,'2020-02-24','2020-03-05',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-24 16:26:09
