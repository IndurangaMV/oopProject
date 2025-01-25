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
INSERT INTO `demonstrator` VALUES (1,'DM/2022/004','Suresh','SureshDM/2022/004@dem.kln.ac.lk','BSc(Hons) Phisycal Science','0785411165',1,1),(2,'DM/2020/003','Anuradha','AnuradhaDM/2020/003@dem.kln.ac.lk','BSc.Electronics','0785263854',1,1),(3,'DM/2019/001','Tharindu','TharinduDM/2019/001@dem.kln.ac.lk','BSc.ComputerScience','0759877415',1,2),(4,'DM/2018/089','Sawindya','SawindyaDM/2018@dem.kln.ac.lk','BSc.Maths','0754563254',1,2),(5,'DM/2021/002','Devi','DeviDM/2021/002@dem.kln.ac.lk','BSc(hons)Mathematics','0728564521',1,3);
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
-- Table structure for table `lecture_hall`
--

DROP TABLE IF EXISTS `lecture_hall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lecture_hall` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hall_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_hall`
--

LOCK TABLES `lecture_hall` WRITE;
/*!40000 ALTER TABLE `lecture_hall` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `fk_lecture_has_lecture_hall_lecture_hall1_idx` (`lecture_hall_id`),
  KEY `fk_lecture_has_lecture_hall_lecture1_idx` (`lecture_id`),
  CONSTRAINT `fk_lecture_has_lecture_hall_lecture1` FOREIGN KEY (`lecture_id`) REFERENCES `lecture` (`id`),
  CONSTRAINT `fk_lecture_has_lecture_hall_lecture_hall1` FOREIGN KEY (`lecture_hall_id`) REFERENCES `lecture_hall` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_has_lecture_hall`
--

LOCK TABLES `lecture_has_lecture_hall` WRITE;
/*!40000 ALTER TABLE `lecture_has_lecture_hall` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecture_position`
--

LOCK TABLES `lecture_position` WRITE;
/*!40000 ALTER TABLE `lecture_position` DISABLE KEYS */;
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
  PRIMARY KEY (`id`),
  KEY `fk_lecturer_lecture_position_idx` (`lecture_position_post_id`),
  KEY `fk_lecturer_faculty1_idx` (`faculty_id`),
  KEY `fk_lecturer_department1_idx` (`department_id`),
  CONSTRAINT `fk_lecturer_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_lecturer_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`),
  CONSTRAINT `fk_lecturer_lecture_position` FOREIGN KEY (`lecture_position_post_id`) REFERENCES `lecture_position` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer`
--

LOCK TABLES `lecturer` WRITE;
/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `module` (
  `module_id` int NOT NULL AUTO_INCREMENT,
  `module_name` varchar(50) DEFAULT NULL,
  `academic_year_semester_id` int NOT NULL,
  `department_id` int NOT NULL,
  `lecturer_id` int NOT NULL,
  PRIMARY KEY (`module_id`),
  KEY `fk_module_academic_year_semester1_idx` (`academic_year_semester_id`),
  KEY `fk_module_department1_idx` (`department_id`),
  KEY `fk_module_lecturer1_idx` (`lecturer_id`),
  CONSTRAINT `fk_module_academic_year_semester1` FOREIGN KEY (`academic_year_semester_id`) REFERENCES `academic_year_semester` (`id`),
  CONSTRAINT `fk_module_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_module_lecturer1` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
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
  `module_module_id` int NOT NULL,
  `demonstrator_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_module_has_demonstrator_module1_idx` (`module_module_id`),
  KEY `fk_module_has_demonstrator_demonstrator1_idx` (`demonstrator_id`),
  CONSTRAINT `fk_module_has_demonstrator_demonstrator1` FOREIGN KEY (`demonstrator_id`) REFERENCES `demonstrator` (`id`),
  CONSTRAINT `fk_module_has_demonstrator_module1` FOREIGN KEY (`module_module_id`) REFERENCES `module` (`module_id`)
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
  PRIMARY KEY (`id`),
  KEY `fk_student_faculty1_idx` (`faculty_id`),
  KEY `fk_student_academic_year_semester1_idx` (`academic_year_semester_id`),
  CONSTRAINT `fk_student_academic_year_semester1` FOREIGN KEY (`academic_year_semester_id`) REFERENCES `academic_year_semester` (`id`),
  CONSTRAINT `fk_student_faculty1` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_department`
--

DROP TABLE IF EXISTS `student_has_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_has_department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `department_id` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_has_department_department1_idx` (`department_id`),
  KEY `fk_student_has_department_student1_idx` (`student_id`),
  CONSTRAINT `fk_student_has_department_department1` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `fk_student_has_department_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_department`
--

LOCK TABLES `student_has_department` WRITE;
/*!40000 ALTER TABLE `student_has_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_has_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_module`
--

DROP TABLE IF EXISTS `student_has_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_has_module` (
  `id` int NOT NULL AUTO_INCREMENT,
  `module_module_id` int NOT NULL,
  `student_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_student_has_module_module1_idx` (`module_module_id`),
  KEY `fk_student_has_module_student1_idx` (`student_id`),
  CONSTRAINT `fk_student_has_module_module1` FOREIGN KEY (`module_module_id`) REFERENCES `module` (`module_id`),
  CONSTRAINT `fk_student_has_module_student1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_module`
--

LOCK TABLES `student_has_module` WRITE;
/*!40000 ALTER TABLE `student_has_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_has_module` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-24 11:15:03
