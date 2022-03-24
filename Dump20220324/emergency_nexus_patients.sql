-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: emergency_nexus
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `idpatients` int NOT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  `dateofbirth` date DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `homeaddress` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `guardianname` varchar(45) DEFAULT NULL,
  `guardianphonenumber` varchar(45) DEFAULT NULL,
  `bloodtype` varchar(45) DEFAULT NULL,
  `socialsecurity` varchar(45) DEFAULT NULL,
  `healthinsuranceprovider` varchar(45) DEFAULT NULL,
  `covidshot1date` date DEFAULT NULL,
  `covidshot1type` varchar(45) DEFAULT NULL,
  `covidshot2date` date DEFAULT NULL,
  `covidshot2type` varchar(45) DEFAULT NULL,
  `boosterdate` date DEFAULT NULL,
  `boostertype` varchar(45) DEFAULT NULL,
  `allergies` varchar(45) DEFAULT NULL,
  `preexistingconditions` varchar(45) DEFAULT NULL,
  `priormedications` varchar(45) DEFAULT NULL,
  `historyofsubstance` varchar(45) DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `race` varchar(45) DEFAULT NULL,
  `ethnicity` varchar(45) DEFAULT NULL,
  `religion` varchar(45) DEFAULT NULL,
  `pregnancy` varchar(45) DEFAULT NULL,
  `assignedbirthsex` varchar(45) DEFAULT NULL,
  `genderidentity` varchar(45) DEFAULT NULL,
  `pronouns` varchar(45) DEFAULT NULL,
  `sexualactivity` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpatients`),
  UNIQUE KEY `idpatients_UNIQUE` (`idpatients`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1111111,'John Smith','1988-12-08','123-444-4444','60 Cherry Road','jsmith@hotmail.com','Marge Smith','778-574-2344','A','123-22-3443','Connecticare .Inc',NULL,NULL,NULL,NULL,NULL,NULL,'','diabetes','Insulin','alcohol',69,180.8,'White','Non-Hispanic','Religious','False','Male','Male','He/Him','True');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-24 16:03:07
