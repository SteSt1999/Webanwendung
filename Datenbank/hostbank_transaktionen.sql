-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: hostbank
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `transaktionen`
--

DROP TABLE IF EXISTS `transaktionen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transaktionen` (
  `Kunden_ID` varchar(95) NOT NULL,
  `Zugangsweg` int(11) NOT NULL COMMENT '0: Geld erhalten\\\\n1: Mitarbeiter\\\\n2: Online-Banking\\\\nZahl > 2: ATM-ID',
  `Transaktions_ID` int(11) NOT NULL COMMENT '1: Ueberweisung senden\\n2. Ueberweisung erhalten\\n3: Abheben\\n4: Einzahlen',
  `Betrag` int(20) NOT NULL,
  `Empfaenger_ID` varchar(45) DEFAULT NULL,
  `EmpfaengerBank_ID` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaktionen`
--

LOCK TABLES `transaktionen` WRITE;
/*!40000 ALTER TABLE `transaktionen` DISABLE KEYS */;
INSERT INTO `transaktionen` VALUES ('otto.ben2',2,1,-123456789,'otto.ben','hostbank'),('stump.stefan',42,1,-50000,'holzmann.julia','hostbank'),('stump.stefan',5,3,-7500,NULL,NULL),('holzmann.julia',1,3,-1212,NULL,NULL),('stump.stefan',42,4,1234,NULL,NULL),('holzmann.julia',0,2,50000,'stump.stefan','hostbank'),('otto.ben',0,2,123456789,'otto.ben2','hostbank'),('stump.stefan',3,3,-123456,NULL,NULL),('stump.stefan',42,4,4500,NULL,NULL),('stump.stefan',42,4,230,NULL,NULL),('stump.stefan',42,4,230,NULL,NULL);
/*!40000 ALTER TABLE `transaktionen` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-26  6:30:55
