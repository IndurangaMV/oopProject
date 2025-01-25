-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: student_attendance_system_kln
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `academic_year_semester`
--

DROP TABLE IF EXISTS `academic_year_semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `academic_year_semester` (
  `id` int NOT NULL AUTO_INCREMENT,
  `academicYear_semester` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_year_semester`
--

LOCK TABLES `academic_year_semester` WRITE;
/*!40000 ALTER TABLE `academic_year_semester` DISABLE KEYS */;
INSERT INTO `academic_year_semester` VALUES (1,'First Year First Semester'),(2,'First Year Second Semester'),(3,'Second Year First Semester'),(4,'Second Year Second Semester'),(5,'Third Year First Semester'),(6,'Third Year Second Semester'),(7,'Fourth Year First Semester'),(8,'Fourth Year Second Semester');
/*!40000 ALTER TABLE `academic_year_semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL,
  `contact_no` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'induranga','6204210','0789677660');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combination`
--

DROP TABLE IF EXISTS `combination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combination` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `faculty_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_combination_faculty1_idx` (`faculty_id`),
  CONSTRAINT `fk_combination_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combination`
--

LOCK TABLES `combination` WRITE;
/*!40000 ALTER TABLE `combination` DISABLE KEYS */;
INSERT INTO `combination` VALUES (1,'Electronic and Computer Science',1),(2,'Statistics',1),(3,'ENCM',1),(4,'PMAT',1),(5,'Applied Chemistry',1);
/*!40000 ALTER TABLE `combination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combination_has_department`
--

DROP TABLE IF EXISTS `combination_has_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combination_has_department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `combination_id` int NOT NULL,
  `department_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_combination_has_department_department1_idx` (`department_id`),
  KEY `fk_combination_has_department_combination1_idx` (`combination_id`),
  CONSTRAINT `fk_combination_has_department_combination1` FOREIGN KEY (`combination_id`) REFERENCES `combination` (`id`),
  CONSTRAINT `fk_combination_has_department_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combination_has_department`
--

LOCK TABLES `combination_has_department` WRITE;
/*!40000 ALTER TABLE `combination_has_department` DISABLE KEYS */;
INSERT INTO `combination_has_department` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `combination_has_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demonstrator`
--

DROP TABLE IF EXISTS `demonstrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demonstrator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `demonstrator_id` varchar(15) NOT NULL,
  `demonstrator_name` varchar(100) NOT NULL,
  `demonstrator_mail` varchar(50) DEFAULT NULL,
  `title` mediumtext,
  `contact_no` varchar(10) DEFAULT NULL,
  `faculty_id` int NOT NULL,
  `department_id` int NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_demonstrator_faculty1_idx` (`faculty_id`),
  KEY `fk_demonstrator_department1_idx` (`department_id`),
  CONSTRAINT `fk_demonstrator_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_demonstrator_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demonstrator`
--

LOCK TABLES `demonstrator` WRITE;
/*!40000 ALTER TABLE `demonstrator` DISABLE KEYS */;
INSERT INTO `demonstrator` VALUES (1,'DM/2022/004','Suresh','SureshDM/2022/004@dem.kln.ac.lk','BSc(Hons) Phisycal Science','0785411165',1,1,'123'),(2,'DM/2020/003','Anuradha','AnuradhaDM/2020/003@dem.kln.ac.lk','BSc.Electronics','0785263854',1,1,'123'),(3,'DM/2019/001','Tharindu','TharinduDM/2019/001@dem.kln.ac.lk','BSc.ComputerScience','0759877415',1,2,'123'),(4,'DM/2018/089','Sawindya','SawindyaDM/2018@dem.kln.ac.lk','BSc.Maths','0754563254',1,2,'123'),(5,'DM/2021/002','Devi','DeviDM/2021/002@dem.kln.ac.lk','BSc(hons)Mathematics','0728564521',1,3,'123');
/*!40000 ALTER TABLE `demonstrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) DEFAULT NULL,
  `faculty_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department_faculty1_idx` (`faculty_id`),
  CONSTRAINT `fk_department_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Physics and Electronics',1),(2,'Statistics and Computer Science',1),(3,'Mathematics',1);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faculty_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Faculty of Science'),(2,'Faculty of Medicine'),(3,'Faculty of Management'),(4,'Faculty of Computing & Technology'),(5,'Faculty of Humanities');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture`
--

DROP TABLE IF EXISTS `lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `module_module_id` varchar(50) NOT NULL,
  `date` date DEFAULT NULL,
  `time_from` time DEFAULT NULL,
  `time_to` time DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_lecture_module1_idx` (`module_module_id`),
  CONSTRAINT `fk_lecture_module1` FOREIGN KEY (`module_module_id`) REFERENCES `module` (`cause_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture`
--

LOCK TABLES `lecture` WRITE;
/*!40000 ALTER TABLE `lecture` DISABLE KEYS */;
INSERT INTO `lecture` VALUES (1,'A','BECS11234','2025-01-25','07:02:57','07:02:58'),(2,'','BECS11234','2025-01-20','08:00:00','10:00:00'),(3,'','BECS11234','2025-04-10','10:00:00','12:00:00'),(4,'','BECS11234','2025-01-20','10:00:00','12:00:00'),(5,'','BECS11234','2025-05-01','10:00:00','13:00:00'),(6,'','BECS11234','2025-10-03','12:00:00','13:00:00');
/*!40000 ALTER TABLE `lecture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_hall`
--

DROP TABLE IF EXISTS `lecture_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_hall` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hall_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_hall`
--

LOCK TABLES `lecture_hall` WRITE;
/*!40000 ALTER TABLE `lecture_hall` DISABLE KEYS */;
INSERT INTO `lecture_hall` VALUES (1,'A7 302'),(2,'A7 303'),(3,'A7 406'),(4,'B1 212'),(5,'A11 207'),(6,'A11 307');
/*!40000 ALTER TABLE `lecture_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_has_lecture_hall`
--

DROP TABLE IF EXISTS `lecture_has_lecture_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_has_lecture_hall` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecture_id` int NOT NULL,
  `lecture_hall_id` int NOT NULL,
  `time_from` time NOT NULL,
  `time_to` time NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lecture_has_lecture_hall_lecture_hall1_idx` (`lecture_hall_id`),
  KEY `fk_lecture_has_lecture_hall_lecture1_idx` (`lecture_id`),
  CONSTRAINT `fk_lecture_has_lecture_hall_lecture1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
  CONSTRAINT `fk_lecture_has_lecture_hall_lecture_hall1` FOREIGN KEY (`lecture_hall_id`) REFERENCES `lecture_hall` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_has_lecture_hall`
--

LOCK TABLES `lecture_has_lecture_hall` WRITE;
/*!40000 ALTER TABLE `lecture_has_lecture_hall` DISABLE KEYS */;
INSERT INTO `lecture_has_lecture_hall` VALUES (1,6,1,'12:00:00','13:00:00','2025-10-03');
/*!40000 ALTER TABLE `lecture_has_lecture_hall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecture_position`
--

DROP TABLE IF EXISTS `lecture_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_position` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `position` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_position`
--

LOCK TABLES `lecture_position` WRITE;
/*!40000 ALTER TABLE `lecture_position` DISABLE KEYS */;
INSERT INTO `lecture_position` VALUES (1,'Proffessor'),(2,'Doctor'),(3,'Mr');
/*!40000 ALTER TABLE `lecture_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecturer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lecturer_id` varchar(15) NOT NULL,
  `lecturer_name` varchar(100) NOT NULL,
  `lecture_position_post_id` int NOT NULL,
  `lecturer_title` mediumtext,
  `lecturer_mail` varchar(50) DEFAULT NULL,
  `contact_no` varchar(10) DEFAULT NULL,
  `faculty_id` int NOT NULL,
  `department_id` int NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lecturer_lecture_position_idx` (`lecture_position_post_id`),
  KEY `fk_lecturer_faculty1_idx` (`faculty_id`),
  KEY `fk_lecturer_department1_idx` (`department_id`),
  CONSTRAINT `fk_lecturer_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_lecturer_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `fk_lecturer_lecture_position` FOREIGN KEY (`lecture_position_post_id`) REFERENCES `lecture_position` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` VALUES (1,'LEC/2020/012','Aruna',1,'pHD','ArunaLEC/2020/012@dem.kln.ac.lk','0785698521',1,1,'123'),(2,'LEC/2020/089','Kumara',2,'pHD','KumaraLEC/2020/089@dem.kln.ac.lk','0784521544',1,1,'123');
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `from` varchar(25) DEFAULT NULL,
  `to` varchar(25) DEFAULT NULL,
  `text` longtext,
  `dateTime` datetime DEFAULT NULL,
  `viewStt` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (1,'1','1','1','2025-01-24 12:23:50',0),(2,'A','A','A','2025-01-24 12:23:50',0),(3,'DM/2022/004','DM/2022/004','HAI','2025-01-24 23:51:08',1),(4,'DM/2022/004','DM/2022/004','A PATIYOOO','2025-01-24 23:52:35',1),(5,'DM/2022/004','LEC/2020/012','DD','2025-01-25 00:51:42',0),(6,'DM/2022/004','DM/2022/004','KOHOMADA','2025-01-25 00:55:48',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `cause_code` varchar(50) NOT NULL,
  `module_name` varchar(50) DEFAULT NULL,
  `academic_year_semester_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  `combination` int NOT NULL,
  PRIMARY KEY (`cause_code`) USING BTREE,
  KEY `fk_module_academic_year_semester1_idx` (`academic_year_semester_id`),
  KEY `fk_module_lecturer1_idx` (`lecturer_id`),
  KEY `fk_module_combination_has_department1_idx` (`combination`),
  CONSTRAINT `fk_module_academic_year_semester1` FOREIGN KEY (`academic_year_semester_id`) REFERENCES `academic_year_semester` (`id`),
  CONSTRAINT `fk_module_combination_has_department1` FOREIGN KEY (`combination`) REFERENCES `combination_has_department` (`id`),
  CONSTRAINT `fk_module_lecturer1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES ('BECS11234','Analog Electronics',1,1,1),('BECS11342','C Programming',1,2,2),('BECS12345','Digital Electronic',2,1,1);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module_has_demonstrator`
--

DROP TABLE IF EXISTS `module_has_demonstrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module_has_demonstrator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `module_module_id` varchar(50) NOT NULL,
  `demonstrator_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_module_has_demonstrator_module1_idx` (`module_module_id`),
  KEY `fk_module_has_demonstrator_demonstrator1_idx` (`demonstrator_id`),
  CONSTRAINT `fk_module_has_demonstrator_demonstrator1` FOREIGN KEY (`demonstrator_id`) REFERENCES `demonstrator` (`id`),
  CONSTRAINT `fk_module_has_demonstrator_module1` FOREIGN KEY (`module_module_id`) REFERENCES `module` (`cause_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module_has_demonstrator`
--

LOCK TABLES `module_has_demonstrator` WRITE;
/*!40000 ALTER TABLE `module_has_demonstrator` DISABLE KEYS */;
/*!40000 ALTER TABLE `module_has_demonstrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentNumber` varchar(15) NOT NULL,
  `name` varchar(100) NOT NULL,
  `student_mail` varchar(50) DEFAULT NULL,
  `contact_no` varchar(10) DEFAULT NULL,
  `faculty_id` int NOT NULL,
  `academic_year_semester_id` int NOT NULL,
  `combination_id` int NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_faculty1_idx` (`faculty_id`),
  KEY `fk_student_academic_year_semester1_idx` (`academic_year_semester_id`),
  KEY `fk_student_combination1_idx` (`combination_id`),
  CONSTRAINT `fk_student_academic_year_semester1` FOREIGN KEY (`academic_year_semester_id`) REFERENCES `academic_year_semester` (`id`),
  CONSTRAINT `fk_student_combination1` FOREIGN KEY (`combination_id`) REFERENCES `combination` (`id`),
  CONSTRAINT `fk_student_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (11,'cc','cc',NULL,'cc',1,1,1,'12345'),(12,'45','janitha',NULL,'888',1,1,1,'12345'),(13,'47','vimukthi','vimukthi47@stu.kln.ac.lk','0789677660',1,1,1,'12345'),(14,'EC/2022/001','saman','samanEC/2022/001@stu.kln.ac.lk','075845521',1,1,1,'12345'),(15,'EC/2022/002','kamal','kamalEC/2022/002@stu.kln.ac.lk','075845522',1,1,1,'12345'),(16,'EC/2022/003','nimal','nimalEC/2022/003@stu.kln.ac.lk','075845523',1,1,1,'12345'),(17,'EC/2022/004','anil','anilEC/2022/004@stu.kln.ac.lk','075845524',1,1,1,'12345'),(18,'EC/2022/005','sunil','sunilEC/2022/005@stu.kln.ac.lk','075845525',1,1,1,'12345'),(19,'EC/2022/006','tharusha','tharushaEC/2022/006@stu.kln.ac.lk','075845526',1,1,1,'12345'),(20,'EC/2022/007','tharindu','tharinduEC/2022/007@stu.kln.ac.lk','075845527',1,1,1,'12345'),(21,'EC/2022/008','upul','upulEC/2022/008@stu.kln.ac.lk','075845528',1,1,1,'12345'),(22,'EC/2022/009','kamala','kamalaEC/2022/009@stu.kln.ac.lk','075845529',1,1,1,'12345'),(23,'EC/2022/010','amara','amaraEC/2022/010@stu.kln.ac.lk','0758455210',1,1,1,'12345'),(24,'EC/2022/011','nayana','nayanaEC/2022/011@stu.kln.ac.lk','0758455211',1,1,1,'12345'),(25,'EC/2022/012','chamara','chamaraEC/2022/012@stu.kln.ac.lk','0758455212',1,1,1,'12345'),(26,'EC/2022/013','bandara','bandaraEC/2022/013@stu.kln.ac.lk','0758455213',1,1,1,'12345'),(27,'EC/2022/014','janith','janithEC/2022/014@stu.kln.ac.lk','0758455214',1,1,1,'12345'),(28,'EC/2022/015','prabodha','prabodhaEC/2022/015@stu.kln.ac.lk','0758455215',1,1,1,'12345'),(29,'EC/2022/016','nadun','nadunEC/2022/016@stu.kln.ac.lk','0758455216',1,1,1,'12345'),(30,'EC/2022/017','roshel','\\roshelEC/2022/017@stu.kln.ac.lk','0758455217',1,1,1,'12345'),(31,'EC/2022/018','tharushi','tharushiEC/2022/018@stu.kln.ac.lk','0758455218',1,1,1,'12345'),(32,'EC/2022/019','naduni','naduniEC/2022/019@stu.kln.ac.lk','0758455219',1,1,1,'12345'),(33,'EC/2022/020','minoli','minoliEC/2022/020@stu.kln.ac.lk','0758455220',1,1,1,'12345'),(34,'EC/2022/021','sineth','sinethEC/2022/021@stu.kln.ac.lk','0758455221',1,1,1,'12345'),(35,'EC/2022/022','teshan','teshanEC/2022/022@stu.kln.ac.lk','0758455222',1,1,1,'12345'),(36,'EC/2022/023','kaveena','kaveenaEC/2022/023@stu.kln.ac.lk','0758455223',1,1,1,'12345'),(37,'EC/2022/024','pamithi','pamithiEC/2022/024@stu.kln.ac.lk','0758455224',1,1,1,'12345'),(38,'EC/2022/025','nadula','nadulaEC/2022/025@stu.kln.ac.lk','0758455225',1,1,1,'12345'),(39,'EC/2022/026','kavindu','kavinduEC/2022/026@stu.kln.ac.lk','0758455226',1,1,1,'12345'),(40,'EC/2022/027','malinga','malingaEC/2022/027@stu.kln.ac.lk','0758455227',1,1,1,'12345'),(41,'EC/2022/028','samantha','samanthaEC/2022/028@stu.kln.ac.lk','0758455228',1,1,1,'12345'),(42,'EC/2022/029','mahinda','mahindaEC/2022/029@stu.kln.ac.lk','0758455229',1,1,1,'12345'),(43,'EC/2022/030','dadli','dadliEC/2022/030@stu.kln.ac.lk','0758455230',1,1,1,'12345'),(44,'EC/2022/031','sirisena','sirisenaEC/2022/031@stu.kln.ac.lk','0758455231',1,1,1,'12345'),(45,'EC/2022/032','chanux','chanuxEC/2022/032@stu.kln.ac.lk','0758455232',1,1,1,'12345'),(46,'EC/2022/033','wasthi','wasthiEC/2022/033@stu.kln.ac.lk','0758455233',1,1,1,'12345'),(47,'EC/2022/034','kaali','kaaliEC/2022/034@stu.kln.ac.lk','0758455234',1,1,1,'12345'),(48,'EC/2022/035','kanchuka','kanchukaEC/2022/035@stu.kln.ac.lk','0758455235',1,1,1,'12345'),(49,'EC/2022/036','amarabandu','amarabanduEC/2022/036@stu.kln.ac.lk','0758455236',1,1,1,'12345'),(50,'EC/2022/037','aruna','arunaEC/2022/037@stu.kln.ac.lk','0758455237',1,1,1,'12345'),(51,'EC/2022/038','aruni','aruniEC/2022/038@stu.kln.ac.lk','0758455238',1,1,1,'12345'),(52,'EC/2022/039','thinuli','thinuliEC/2022/039@stu.kln.ac.lk','0758455239',1,1,1,'12345'),(53,'EC/2022/040','pasan','pasanEC/2022/040@stu.kln.ac.lk','0758455240',1,1,1,'12345'),(54,'EC/2022/041','bhanuka','\\bhanukaEC/2022/041@stu.kln.ac.lk','0758455241',1,1,1,'12345'),(55,'EC/2022/042','sasmitha','sasmithaEC/2022/042@stu.kln.ac.lk','0758455242',1,1,1,'12345'),(56,'EC/2022/043','thiviru','thiviruEC/2022/043@stu.kln.ac.lk','0758455243',1,1,1,'12345'),(57,'EC/2022/044','thimira','thimiraEC/2022/044@stu.kln.ac.lk','0758455244',1,1,1,'12345'),(58,'EC/2022/045','aloka','alokaEC/2022/045@stu.kln.ac.lk','0758455245',1,1,1,'12345'),(59,'EC/2022/046','dhilukshi','dhilukshiEC/2022/046@stu.kln.ac.lk','0758455246',1,1,1,'12345'),(60,'EC/2022/047','roshan','roshanEC/2022/047@stu.kln.ac.lk','0758455247',1,1,1,'12345'),(61,'EC/2022/048','ananda','anandaEC/2022/048@stu.kln.ac.lk','0758455248',1,1,1,'12345'),(62,'EC/2022/049','rathnapala','rathnapalaEC/2022/049@stu.kln.ac.lk','0758455249',1,1,1,'12345'),(63,'EC/2022/050','rupasinghe','rupasingheEC/2022/050@stu.kln.ac.lk','0758455250',1,1,1,'12345'),(64,'EN/2022/001','lochi','lochiEN/2022/001@stu.kln.ac.lk','075845521',1,1,3,'12345'),(65,'EN/2022/002','himashi','himashiEN/2022/002@stu.kln.ac.lk','075845522',1,1,3,'12345'),(66,'EN/2022/003','kalana','kalanaEN/2022/003@stu.kln.ac.lk','075845523',1,1,3,'12345'),(67,'EN/2022/004','chathurya','chathuryaEN/2022/004@stu.kln.ac.lk','075845524',1,1,3,'12345'),(68,'EN/2022/005','prabhani','prabhaniEN/2022/005@stu.kln.ac.lk','075845525',1,1,3,'12345'),(69,'EN/2022/006','mali','maliEN/2022/006@stu.kln.ac.lk','075845526',1,1,3,'12345'),(70,'EN/2022/007','sathsala','sathsalaEN/2022/007@stu.kln.ac.lk','075845527',1,1,3,'12345'),(71,'EN/2022/008','gayesha','gayeshaEN/2022/008@stu.kln.ac.lk','075845528',1,1,3,'12345'),(72,'EN/2022/009','anuththra','anuththraEN/2022/009@stu.kln.ac.lk','075845529',1,1,3,'12345'),(73,'EN/2022/010','lochana','lochanaEN/2022/010@stu.kln.ac.lk','0758455210',1,1,3,'12345'),(74,'EN/2022/011','sulochana','sulochanaEN/2022/011@stu.kln.ac.lk','0758455211',1,1,3,'12345'),(75,'EN/2022/012','prabhashi','prabhashiEN/2022/012@stu.kln.ac.lk','0758455212',1,1,3,'12345'),(76,'EN/2022/013','thilakshi','thilakshiEN/2022/013@stu.kln.ac.lk','0758455213',1,1,3,'12345'),(77,'EN/2022/014','raveesha','raveeshaEN/2022/014@stu.kln.ac.lk','0758455214',1,1,3,'12345'),(78,'EN/2022/015','rajeesha','rajeeshaEN/2022/015@stu.kln.ac.lk','0758455215',1,1,3,'12345'),(79,'EN/2022/016','anura','anuraEN/2022/016@stu.kln.ac.lk','0758455216',1,1,3,'12345'),(80,'EN/2022/017','wijemuni','wijemuniEN/2022/017@stu.kln.ac.lk','0758455217',1,1,3,'12345'),(81,'EN/2022/018','wimal','wimalEN/2022/018@stu.kln.ac.lk','0758455218',1,1,3,'12345'),(82,'EN/2022/019','devi','deviEN/2022/019@stu.kln.ac.lk','0758455219',1,1,3,'12345'),(83,'EN/2022/020','rumesh','rumeshEN/2022/020@stu.kln.ac.lk','0758455220',1,1,3,'12345'),(84,'EC/2022/099','Induranga','IndurangaEC/2022/099@stu.kln.ac.lk','0789677660',1,1,1,'12345');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_lecture`
--

DROP TABLE IF EXISTS `student_has_lecture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_has_lecture` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `lecture_id` int NOT NULL,
  `present` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_has_lecture_lecture1_idx` (`lecture_id`),
  KEY `fk_student_has_lecture_student1_idx` (`student_id`),
  CONSTRAINT `fk_student_has_lecture_lecture1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
  CONSTRAINT `fk_student_has_lecture_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_lecture`
--

LOCK TABLES `student_has_lecture` WRITE;
/*!40000 ALTER TABLE `student_has_lecture` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_has_lecture` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-25  9:39:33
