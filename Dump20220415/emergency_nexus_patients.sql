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
  `idpatients` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(45) DEFAULT NULL,
  `dateofbirth` varchar(45) DEFAULT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `homeaddress` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `guardianname` varchar(45) DEFAULT NULL,
  `guardianphonenumber` varchar(45) DEFAULT NULL,
  `bloodtype` varchar(45) DEFAULT NULL,
  `healthinsuranceprovider` varchar(45) DEFAULT NULL,
  `covidshot1date` varchar(45) DEFAULT NULL,
  `covidshot1type` varchar(45) DEFAULT NULL,
  `covidshot2date` varchar(45) DEFAULT NULL,
  `covidshot2type` varchar(45) DEFAULT NULL,
  `boosterdate` varchar(45) DEFAULT NULL,
  `boostertype` varchar(45) DEFAULT NULL,
  `allergies` varchar(600) DEFAULT NULL,
  `preexistingconditions` varchar(45) DEFAULT NULL,
  `priormedications` varchar(45) DEFAULT NULL,
  `historyofsubstance` varchar(45) DEFAULT NULL,
  `height` varchar(45) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=1111122 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1111111,'John Smith','12/3/1999','123-444-4444','60 Cherry Road','jsmith@hotmail.com','Marge Smith','778-574-2344','A','Connecticare .Inc',NULL,NULL,NULL,NULL,NULL,NULL,'','diabetes','Insulin','alcohol','69','180.8','White','Non-Hispanic','Religious','False','Male','Male','He/Him','True'),(1111118,'Amelia Bedelia','8/3/1999','870-324-2345','133 Streat Lane','ABed@gmail.com','Rose Bedilia','953-495-3421','B','Health Markets','4/5/2022','Pfizer-BioNTech','4/22/2022','Pfizer-BioNTech','5/7/2022','Pfizer-BioNTech','[drug: Morphine, food, insect, latex, mold, pet]','','[insulin]','[recreational drug use]','60','240','White','non-hispanic','religious','no','female','Female','She/her','no'),(1111119,'fdasdas','4/28/2022','','','','','',NULL,NULL,'',NULL,'',NULL,'',NULL,'[other: ]','','[]','[]','','',NULL,'','','no','',NULL,'',''),(1111120,'john ','4/13/2022','','','','','','','','','','','','','','[other: ]','','[]','[]','','','','','','no','','','',''),(1111121,'Gary Johnson','4/15/1992','893-423-4324','392 Geary Lane','gearybeary@gmail.com','Martha Johnson','392-453-4324','B','Shelter Insurance','4/4/2022','Moderna ','4/14/2022','Moderna ','4/28/2022','Johnson & Johnson’s Janssen','[drug: antibiotics, food, insect, pollen, other: ]','wobbly knees','[insulin, antibiotics, ibuprofen]','[alcohol]','123','190','White','non-hispanic','religious','no','male','Male','he/him','no');
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

-- Dump completed on 2022-04-25 16:26:18
