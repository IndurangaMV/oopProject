-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.40 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping data for table student_attendance_system_kln.academic_year_semester: ~1 rows (approximately)
INSERT INTO `academic_year_semester` (`id`, `academicYear_semester`) VALUES
	(1, 'First Year First Semester');

-- Dumping data for table student_attendance_system_kln.demonstrator: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.department: ~2 rows (approximately)
INSERT INTO `department` (`id`, `department_name`, `faculty_id`) VALUES
	(1, 'Physics and Electronics', 1),
	(2, 'Statistics and Computer Science', 1);

-- Dumping data for table student_attendance_system_kln.faculty: ~1 rows (approximately)
INSERT INTO `faculty` (`id`, `faculty_name`) VALUES
	(1, 'Faculty of Science');

-- Dumping data for table student_attendance_system_kln.lecture: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.lecturer: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.lecture_hall: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.lecture_has_lecture_hall: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.lecture_position: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.module: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.module_has_demonstrator: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.student: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.student_has_department: ~0 rows (approximately)

-- Dumping data for table student_attendance_system_kln.student_has_module: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
mysql -u root -p HNGvmi21297 test < "C:\Users\indur\OneDrive\Documents\Academic\projects\OOP_project\Databases"
