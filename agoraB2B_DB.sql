-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: SWT
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `SWT`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `SWT` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `SWT`;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Account` (
  `idAccount` int(11) NOT NULL,
  PRIMARY KEY (`idAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Adresse`
--

DROP TABLE IF EXISTS `Adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Adresse` (
  `idAdresse` int(11) NOT NULL,
  `strasse` varchar(45) DEFAULT NULL,
  `hausnummer` varchar(7) DEFAULT NULL,
  `plz` varchar(5) DEFAULT NULL,
  `stadt` varchar(45) DEFAULT NULL,
  `land` varchar(45) DEFAULT NULL,
  `adressTyp` varchar(45) DEFAULT NULL,
  `fk_idBenutzer` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAdresse`),
  KEY `fk_idBenutzer_idx` (`fk_idBenutzer`),
  CONSTRAINT `fk_idBenutzer` FOREIGN KEY (`fk_idBenutzer`) REFERENCES `Benutzer` (`idBenutzer`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Adresse`
--

LOCK TABLES `Adresse` WRITE;
/*!40000 ALTER TABLE `Adresse` DISABLE KEYS */;
/*!40000 ALTER TABLE `Adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bankkonto`
--

DROP TABLE IF EXISTS `Bankkonto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bankkonto` (
  `idBankkonto` int(11) NOT NULL,
  `guthaben` double DEFAULT NULL,
  `iBan` varchar(34) DEFAULT NULL,
  `bic` varchar(15) DEFAULT NULL,
  `bankname` varchar(70) DEFAULT NULL,
  `idAccount` int(11) NOT NULL,
  PRIMARY KEY (`idBankkonto`),
  KEY `idAccount_idx` (`idAccount`),
  CONSTRAINT `fk_idAccount` FOREIGN KEY (`idAccount`) REFERENCES `Account` (`idAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bankkonto`
--

LOCK TABLES `Bankkonto` WRITE;
/*!40000 ALTER TABLE `Bankkonto` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bankkonto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Benutzer`
--

DROP TABLE IF EXISTS `Benutzer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Benutzer` (
  `idBenutzer` int(11) NOT NULL,
  `vorname` varchar(45) DEFAULT NULL,
  `nachname` varchar(45) DEFAULT NULL,
  `firma` varchar(100) DEFAULT NULL,
  `telefonnummer` varchar(30) DEFAULT NULL,
  `idAccount` int(11) DEFAULT NULL,
  PRIMARY KEY (`idBenutzer`),
  KEY `idAccount_idx` (`idAccount`),
  CONSTRAINT `idAccount` FOREIGN KEY (`idAccount`) REFERENCES `Account` (`idAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Benutzer`
--

LOCK TABLES `Benutzer` WRITE;
/*!40000 ALTER TABLE `Benutzer` DISABLE KEYS */;
/*!40000 ALTER TABLE `Benutzer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Bestellung`
--

DROP TABLE IF EXISTS `Bestellung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bestellung` (
  `idBestellung` int(11) NOT NULL,
  `fk_idAccount_1` int(11) DEFAULT NULL,
  `idAccount_2` int(11) DEFAULT NULL,
  `bestellDatum` datetime DEFAULT NULL,
  PRIMARY KEY (`idBestellung`),
  KEY `fk_idAccount_1_idx` (`fk_idAccount_1`),
  CONSTRAINT `fk_idAccount_1` FOREIGN KEY (`fk_idAccount_1`) REFERENCES `Account` (`idAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bestellung`
--

LOCK TABLES `Bestellung` WRITE;
/*!40000 ALTER TABLE `Bestellung` DISABLE KEYS */;
/*!40000 ALTER TABLE `Bestellung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Produkte`
--

DROP TABLE IF EXISTS `Produkte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Produkte` (
  `produktID` int(11) NOT NULL,
  `bezeichnung` varchar(100) DEFAULT NULL,
  `beschreibung` varchar(1000) DEFAULT NULL,
  `preis` double DEFAULT NULL,
  `produktKategorie` varchar(45) DEFAULT NULL,
  `idAccount` int(11) DEFAULT NULL,
  PRIMARY KEY (`produktID`),
  KEY `fk_Produkte_1_idx` (`idAccount`),
  CONSTRAINT `fk_Produkte_1` FOREIGN KEY (`idAccount`) REFERENCES `Account` (`idAccount`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Produkte`
--

LOCK TABLES `Produkte` WRITE;
/*!40000 ALTER TABLE `Produkte` DISABLE KEYS */;
/*!40000 ALTER TABLE `Produkte` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-18 17:58:06
