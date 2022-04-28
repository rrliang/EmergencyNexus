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
  `potentialdiagnosis` varchar(800) DEFAULT NULL,
  `notesandobservations` varchar(700) DEFAULT NULL,
  `docrequesttest` varchar(200) DEFAULT NULL,
  `docdiagnosis` varchar(600) DEFAULT NULL,
  `docdischargeinstructions` varchar(800) DEFAULT NULL,
  `docnotesandobservations` varchar(700) DEFAULT NULL,
  `lasteditedby` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idvisit`,`patient`),
  UNIQUE KEY `idvisit_UNIQUE` (`idvisit`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visit`
--

LOCK TABLES `visit` WRITE;
/*!40000 ALTER TABLE `visit` DISABLE KEYS */;
INSERT INTO `visit` VALUES (23,1111129,'4/21/2022','[abdomen, back, chest, ear, head, pelvis, tooth, rectum, skin, leg, arm, chronic pain, joints]','[chills, fever, paresthesia, light-headed, dizzy: about to black out, dizzy: the room is spinning, mouth dry, nauseated, short of breath, sick: like I have the flu, sick: like I have to vomit, sleepy, sweaty, thirsty, tired, weak, joints]','[breathe, hear: losing hearing, hear: sounds are too loud, move one side (arm and or leg), pass a bowel action normally, pass urine normally, remember, see: blindness, see: double vision, see: blurred vision, sleep, smell things normally, speak normally, passing watery bowl actions, scratching, sweating, swallow normally, taste properly, walk normally, write normally, stay awake]','123/7','4/3/2022','4/21/2022','32',12345681,'[oxygen, Epinephrine (mg): 13, Nitroglycerin (mg): 12, Diphenhydramine (mg): 11, Albuterol/salbutamol (mg): 15, Aspirin (mg): 14, Glucose (mg): 16, Atropine (mg): 17, Hydrocortisone (mg): 18, Morphine or nitrous oxide (mg): 19, Nalaxone (mg): 20, Lorazepam or Midazolam (mg): 31, Flumazenil (mg): 34, Melanin (mg): 23]','[Intramuscular injection (IM), Intravascular injection (IV): 342, Subcutaneous Injection (SC), P.O (Oral medication)]','[Other chest pain; chest pain unspecified, Acute upper respiratory infection; unspecified, Urinary tract infection; site not specified, Headache, Unspecified abdominal pain, Syncope and collapse, Non-infective gastroenteritis and colitis; unspecified, Dizziness and giddiness, Low back pain, Unspecified injury of head; initial encounter, Nausea with vomiting; unspecified, Acute pharyngitis; unspecified, Unspecified asthma with (acute) exacerbation, Constipation; unspecified, Acute bronchitis; unspecified, Strain of muscle; fascia and tendon at neck level; initial encounter, Fever: unspecified, Other specified disorders of teeth and supporting structures, Epigastric pain, Strain of muscle; fascia and tendon of lower back; initial encounter, Dead]','This man is completely dead','[]','[]','','',NULL);
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

-- Dump completed on 2022-04-27 13:42:06
