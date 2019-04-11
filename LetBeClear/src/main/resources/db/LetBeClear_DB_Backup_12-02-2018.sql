CREATE DATABASE  IF NOT EXISTS `letbeclear` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `letbeclear`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: letbeclear
-- ------------------------------------------------------
-- Server version	5.5.45

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `ADDRESS_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL,
  `FIRSTNAME` varchar(256) DEFAULT NULL,
  `LASTNAME` varchar(256) DEFAULT NULL,
  `ADDRESS_TYPE` varchar(4) DEFAULT NULL,
  `ADDRESS1` varchar(512) DEFAULT NULL,
  `ADDRESS2` varchar(512) DEFAULT NULL,
  `CITY` varchar(256) DEFAULT NULL,
  `STATE` varchar(256) DEFAULT NULL,
  `COUNTRY` varchar(256) DEFAULT NULL,
  `ZIPCODE` int(10) DEFAULT NULL,
  `EMAIL` varchar(128) NOT NULL,
  `MOBILEPHONE` bigint(20) DEFAULT NULL,
  `FIELD1` int(11) DEFAULT NULL,
  `FIELD2` varchar(256) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LASTCREATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ADDRESS_ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `SQL160708173153850` (`USER_ID`),
  CONSTRAINT `SQL160708173153850` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,2,'harikesh','harikesh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'harikesh@gmail.com',9843943431,NULL,NULL,'2018-02-11 06:25:36','2018-02-11 06:25:36'),(2,4,'harikesh','harikesh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'hari@gmail.com',9843943431,NULL,NULL,'2018-02-11 14:45:33','2018-02-11 14:45:33'),(3,5,'harikesh','harikesh',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'abcd@gmail.com',9843943431,NULL,NULL,'2018-02-11 17:13:21','2018-02-11 17:13:21'),(4,6,'ab','ab',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'abxy@gmail.com',9876543210,NULL,NULL,'2018-02-12 07:29:23','2018-02-12 07:29:23');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments_like`
--

DROP TABLE IF EXISTS `comments_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments_like` (
  `LIKE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMMENT_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `FIELD1` varchar(256) DEFAULT NULL,
  `FIELD2` int(11) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`LIKE_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `COMMENT_ID` (`COMMENT_ID`),
  CONSTRAINT `comments_like_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE,
  CONSTRAINT `comments_like_ibfk_2` FOREIGN KEY (`COMMENT_ID`) REFERENCES `post_comment` (`COMMENT_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments_like`
--

LOCK TABLES `comments_like` WRITE;
/*!40000 ALTER TABLE `comments_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_comment`
--

DROP TABLE IF EXISTS `post_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_comment` (
  `COMMENT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POST_ID` bigint(20) NOT NULL,
  `COMMENT` varchar(500) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL,
  `FIELD1` varchar(256) DEFAULT NULL,
  `FIELD2` int(11) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`COMMENT_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `POST_ID` (`POST_ID`),
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE,
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `user_post` (`POST_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_comment`
--

LOCK TABLES `post_comment` WRITE;
/*!40000 ALTER TABLE `post_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_like`
--

DROP TABLE IF EXISTS `post_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_like` (
  `LIKE_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `POST_ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `FIELD1` varchar(256) DEFAULT NULL,
  `FIELD2` int(11) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`LIKE_ID`),
  KEY `USER_ID` (`USER_ID`),
  KEY `POST_ID` (`POST_ID`),
  CONSTRAINT `post_like_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE,
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`POST_ID`) REFERENCES `user_post` (`POST_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_like`
--

LOCK TABLES `post_like` WRITE;
/*!40000 ALTER TABLE `post_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_post`
--

DROP TABLE IF EXISTS `user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_post` (
  `USER_ID` bigint(20) NOT NULL,
  `POST_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `IMAGE_PATH` varchar(512) DEFAULT NULL,
  `PRIMARY_IMAGE` varchar(512) DEFAULT NULL,
  `POST_TITLE` varchar(100) DEFAULT NULL,
  `NO_OF_IMAGE` int(6) DEFAULT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `LATITUDE` decimal(10,8) DEFAULT NULL,
  `LONGITUDE` decimal(11,8) DEFAULT NULL,
  `POST_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `FIELD1` varchar(512) DEFAULT NULL,
  `FIELD2` int(11) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`POST_ID`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `user_post_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_post`
--

LOCK TABLES `user_post` WRITE;
/*!40000 ALTER TABLE `user_post` DISABLE KEYS */;
INSERT INTO `user_post` VALUES (2,4,'/images/posts//timeline/lbc_2_1_0.jpg,','/images/posts//timeline/lbc_2_1_0.jpg',NULL,1,NULL,13.00000000,12.00000000,'2018-02-11 17:54:10',NULL,NULL,'2018-02-11 17:54:10'),(2,5,'/images/posts//timeline/lbc_2_5_0.jpg,/images/posts//timeline/lbc_2_5_1.jpg,/images/posts//timeline/lbc_2_5_2.jpg,/images/posts//timeline/lbc_2_5_3.jpg,/images/posts//timeline/lbc_2_5_4.jpg,','/images/posts//timeline/lbc_2_5_4.jpg',NULL,5,NULL,NULL,NULL,'2018-02-12 06:45:42',NULL,NULL,'2018-02-12 06:45:42');
/*!40000 ALTER TABLE `user_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `USER_ID` bigint(20) NOT NULL,
  `GENDER` varchar(10) DEFAULT NULL,
  `PHOTO` varchar(512) DEFAULT NULL,
  `DISPLAYNAME` varchar(256) DEFAULT NULL,
  `MARITALSTATUS` varchar(28) DEFAULT NULL,
  `DATEOFBIRTH` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `FIELD1` int(11) DEFAULT NULL,
  `FIELD2` varchar(256) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `SQL160708130608560` (`USER_ID`),
  CONSTRAINT `SQL160708130611810` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (2,NULL,NULL,NULL,NULL,'2018-02-11 06:25:36',NULL,NULL,'2018-02-11 06:25:36'),(4,NULL,NULL,NULL,NULL,'2018-02-11 14:45:33',NULL,NULL,'2018-02-11 14:45:33'),(5,NULL,NULL,NULL,NULL,'2018-02-11 17:13:21',NULL,NULL,'2018-02-11 17:13:21'),(6,NULL,NULL,NULL,NULL,'2018-02-12 07:29:23',NULL,NULL,'2018-02-12 07:29:23');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_reg`
--

DROP TABLE IF EXISTS `user_reg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_reg` (
  `USER_ID` bigint(20) NOT NULL,
  `LOGINID` varchar(128) NOT NULL,
  `LOGONPASSWORD` varchar(256) NOT NULL,
  `PASSWORDEXPIRED` smallint(6) DEFAULT NULL,
  `RESETPASSWORDCODE` varchar(256) DEFAULT NULL,
  `TIMEOUT` smallint(6) NOT NULL DEFAULT '-1',
  `PASSWORDCREATION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `RESETPASSTIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `PASSWORDINVALID` smallint(6) DEFAULT NULL,
  `FIELD1` int(11) DEFAULT NULL,
  `FIELD2` varchar(256) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `LOGINID` (`LOGINID`),
  CONSTRAINT `SQL160708173521240` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_reg`
--

LOCK TABLES `user_reg` WRITE;
/*!40000 ALTER TABLE `user_reg` DISABLE KEYS */;
INSERT INTO `user_reg` VALUES (2,'harikesh@gmail.com','$2a$04$dB8YmPQtCMwXL.qA3zJuMO8PWHdjgCy4grxNFxpHpqsZ24GMpHXae',NULL,NULL,0,'2018-02-11 06:25:36','2018-02-11 06:25:36',NULL,NULL,NULL,'2018-02-11 06:25:36'),(4,'hari@gmail.com','$2a$04$tl8.LiTinMmM1UJ/aEDOuey0OAC.fj4DID24OKW9eA0HAvBxoRawe',NULL,NULL,0,'2018-02-11 14:45:33','2018-02-11 14:45:33',NULL,NULL,NULL,'2018-02-11 14:45:33'),(5,'abcd@gmail.com','$2a$04$Iak9e5U3ns/qzRA24OwMQOdw03FI8N9OJbPcqhZe/Obcroz1ZKaua',NULL,NULL,0,'2018-02-11 17:13:21','2018-02-11 17:13:21',NULL,NULL,NULL,'2018-02-11 17:13:21'),(6,'abxy@gmail.com','$2a$04$KaTOGjbM0/3HZIWNUhXCeON/5NqeoAbfgqE3CcFQKQwqwliBCHDkW',NULL,NULL,0,'2018-02-12 07:29:23','2018-02-12 07:29:23',NULL,NULL,NULL,'2018-02-12 07:29:23');
/*!40000 ALTER TABLE `user_reg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REGISTERTYPE` varchar(4) DEFAULT NULL,
  `PROFILETYPE` varchar(4) DEFAULT NULL,
  `LANGUAGE_ID` varchar(4) DEFAULT NULL,
  `REGISTRATION_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LASTSESSION` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `REGISTRATIONUPDATE` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `FIELD1` int(11) DEFAULT NULL,
  `FIELD2` varchar(256) DEFAULT NULL,
  `FIELD3` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'R','U','-1','2018-02-11 06:25:36','2018-02-11 06:25:36','2018-02-11 06:25:36',NULL,NULL,'2018-02-11 06:25:36'),(4,'R','U','-1','2018-02-11 14:45:33','2018-02-11 14:45:33','2018-02-11 14:45:33',NULL,NULL,'2018-02-11 14:45:33'),(5,'R','U','-1','2018-02-11 17:13:21','2018-02-11 17:13:21','2018-02-11 17:13:21',NULL,NULL,'2018-02-11 17:13:21'),(6,'R','U','-1','2018-02-12 07:29:23','2018-02-12 07:29:23','2018-02-12 07:29:23',NULL,NULL,'2018-02-12 07:29:23');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-12 13:11:10
