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
-- Table structure for table `visit`
--

DROP TABLE IF EXISTS `visit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visit` (
  `idvisit` int NOT NULL AUTO_INCREMENT,
  `patient` int NOT NULL,
  `dateofvisit` varchar(45) DEFAULT NULL,
  `symptomsmyhurt` varchar(600) DEFAULT NULL,
  `symptomsifeel` varchar(600) DEFAULT NULL,
  `symptomsicant` varchar(600) DEFAULT NULL,
  `bloodpressure` varchar(45) DEFAULT NULL,
  `admissionstatuscheckin` varchar(45) DEFAULT NULL,
  `admissionstatuscheckout` varchar(45) DEFAULT NULL,
  `admissionstatusroom` varchar(45) DEFAULT NULL,
  `primaryphysician` int NOT NULL,
  `givenmedication` varchar(400) DEFAULT NULL,
  `injectionsgiven` varchar(200) DEFAULT NULL,
  `potentialdiagnosis` varchar(600) DEFAULT NULL,
  `notesandobservations` varchar(700) DEFAULT NULL,
  `docrequesttest` varchar(200) DEFAULT NULL,
  `docdiagnosis` varchar(600) DEFAULT NULL,
  `docdischargeinstructions` varchar(800) DEFAULT NULL,
  `docnotesandobservations` varchar(700) DEFAULT NULL,
  `lasteditedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idvisit`,`patient`),
  UNIQUE KEY `idvisit_UNIQUE` (`idvisit`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
INSERT INTO `visit` VALUES (7,1111111,'4/26/2022','[abdomen, chest, pelvis, tooth, leg, chronic pain, ]','[mouth dry, ]','[move one side (arm and or leg), ]','','','','',12345681,'[oxygen]','[Intramuscular injection (IM)]','[Other chest pain, chest pain unspecified]','',NULL,NULL,NULL,NULL,'nurse'),(8,1111120,'4/20/2022','[]','[]','[]','','','','',12345694,'[]','[]','[]','',NULL,NULL,NULL,NULL,'doctor'),(9,1111120,'4/20/2022','[]','[]','[]','','','','',12345694,'[]','[]','[]','',NULL,NULL,NULL,NULL,'Harley Quinzel'),(10,1111111,'4/13/2022','[]','[]','[]','','','','',0,'[]','[]','[]','','[]','[]','','','nurse'),(11,1111111,'5/1/2022','[abdomen, chest, tooth, skin, leg, chronic pain, ]','[fever, nauseated, ]','[move one side (arm and or leg), ]','123','4/18/2022','4/27/2022','123',12345694,'[oxygen]','[Intramuscular injection (IM)]','[Other chest pain, chest pain unspecified]','He is not good','[Red Blood Cell Test, Electrolyte Test, Stool Test, other: morgue]','[Chest Pain, Gastrointestinal hemorrhage, Myocardial infarction, other: dead]','don\'t be unalive','he\'s dead whoops','doctor'),(12,1111121,'4/20/2022','[back, ]','[fever, ]','[breathe, ]','123','','','',12345694,'[oxygen]','[Intramuscular injection (IM)]','[Epigastric pain]','knees still wobble','[Renal Function Test, Stool Test]','[Pneumonia, Intracranial injury]','be more careful with joints','don\'t jump anymore','doctor');
/*!40000 ALTER TABLE `visit` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-25 16:26:18
