-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 34.141.0.193    Database: henez
-- ------------------------------------------------------
-- Server version	5.7.36-google

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `new_category` bit(1) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_46ccwnsi9409t36lurvtyljak` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'2022-02-11 23:19:47.884000',NULL,'https://firebasestorage.googleapis.com/v0/b/henez-web.appspot.com/o/images%2Fcategories%2Fneck.png?alt=media&token=f8545d9c-9be5-4171-80bd-cf126d09681d','Necklaces',_binary '\0',NULL),(7,'2022-02-11 23:19:54.999000',NULL,'https://firebasestorage.googleapis.com/v0/b/henez-web.appspot.com/o/images%2Fcategories%2Fbrac.png?alt=media&token=8ea00bc5-8eba-4223-a33a-a2f2345e2d24','Bracelets',_binary '\0',NULL),(8,'2022-02-11 23:19:47.884000',NULL,'https://firebasestorage.googleapis.com/v0/b/henez-web.appspot.com/o/images%2Fcategories%2Fear.png?alt=media&token=e13c7520-0098-415c-908a-9fc90817186a','Earrings',_binary '\0',NULL),(9,'2022-02-16 21:07:56.961268',NULL,'https://firebasestorage.googleapis.com/v0/b/henez-web.appspot.com/o/images%2Fcategories%2Fglasses.png?alt=media&token=74318670-e2fd-4935-bd31-3f667c137649','Glasses',_binary '\0',NULL),(10,'2022-02-16 21:08:18.523000',NULL,'https://firebasestorage.googleapis.com/v0/b/henez-web.appspot.com/o/images%2Fcategories%2Fring.png?alt=media&token=fb4cad66-ca9e-469a-8c9a-5b726a793ebf','Rings',_binary '\0',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
