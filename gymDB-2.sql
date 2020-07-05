-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: gym
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `class_ID` int NOT NULL AUTO_INCREMENT,
  `class_Name` varchar(100) NOT NULL,
  `class_Date` date DEFAULT NULL,
  `gym_ID` int DEFAULT NULL,
  `trainer_ID` int DEFAULT NULL,
  `start_Time` time NOT NULL,
  `class_Length` int NOT NULL,
  PRIMARY KEY (`class_ID`),
  KEY `gym_ID` (`gym_ID`),
  KEY `trainer_ID` (`trainer_ID`),
  CONSTRAINT `classes_ibfk_1` FOREIGN KEY (`gym_ID`) REFERENCES `gym` (`gym_ID`),
  CONSTRAINT `classes_ibfk_2` FOREIGN KEY (`trainer_ID`) REFERENCES `trainer` (`trainer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (3,'Kickboxing','2020-07-30',2,3,'12:30:00',45);
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classes_scheduled`
--

DROP TABLE IF EXISTS `classes_scheduled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes_scheduled` (
  `schedule_ID` int NOT NULL AUTO_INCREMENT,
  `member_ID` int NOT NULL,
  `class_ID` int NOT NULL,
  PRIMARY KEY (`schedule_ID`),
  KEY `member_ID` (`member_ID`),
  KEY `class_ID` (`class_ID`),
  CONSTRAINT `classes_scheduled_ibfk_1` FOREIGN KEY (`member_ID`) REFERENCES `membership` (`member_ID`),
  CONSTRAINT `classes_scheduled_ibfk_2` FOREIGN KEY (`class_ID`) REFERENCES `classes` (`class_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes_scheduled`
--

LOCK TABLES `classes_scheduled` WRITE;
/*!40000 ALTER TABLE `classes_scheduled` DISABLE KEYS */;
INSERT INTO `classes_scheduled` VALUES (17,3,3);
/*!40000 ALTER TABLE `classes_scheduled` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym`
--

DROP TABLE IF EXISTS `gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gym` (
  `gym_ID` int NOT NULL AUTO_INCREMENT,
  `gym_Name` varchar(100) NOT NULL,
  `phone_Number` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip_Code` int DEFAULT NULL,
  PRIMARY KEY (`gym_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym`
--

LOCK TABLES `gym` WRITE;
/*!40000 ALTER TABLE `gym` DISABLE KEYS */;
INSERT INTO `gym` VALUES (1,'Slytherin','480-444-1417','123 E My Street','Mesa','AZ',85213),(2,'Hogwarts','4804441414','963 W My Street','Gilbert','AZ',85297);
/*!40000 ALTER TABLE `gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership` (
  `member_ID` int NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(100) NOT NULL,
  `last_Name` varchar(100) NOT NULL,
  `phone_Number` varchar(14) DEFAULT NULL,
  `birth_Date` date DEFAULT NULL,
  `gym_ID` int NOT NULL,
  PRIMARY KEY (`member_ID`),
  KEY `gym_ID` (`gym_ID`),
  CONSTRAINT `membership_ibfk_1` FOREIGN KEY (`gym_ID`) REFERENCES `gym` (`gym_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` VALUES (2,'Rubeus','Hagrid','480-333-1515','1980-01-30',1),(3,'Severus','Snape','4803332829','1890-02-15',2),(4,'Draco','Malfoy','480-632-7894','1965-08-17',1),(5,'Siriur','Black','480-444-7874','1985-12-01',1),(6,'Luna','Lovegood','480-888-7784','2000-09-06',1);
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trainer` (
  `trainer_ID` int NOT NULL AUTO_INCREMENT,
  `first_Name` varchar(100) NOT NULL,
  `last_Name` varchar(100) NOT NULL,
  `gym_ID` int DEFAULT NULL,
  PRIMARY KEY (`trainer_ID`),
  KEY `gym_ID` (`gym_ID`),
  CONSTRAINT `trainer_ibfk_1` FOREIGN KEY (`gym_ID`) REFERENCES `gym` (`gym_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES (3,'Ron','Weasley',2);
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-04 17:41:14
