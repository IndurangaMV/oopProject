package controlls;
import java.sql.*;

public class DBConnectDemonstrator extends DBConnect{

    public boolean login(String username, String password) {
        Connection connection = sqlConnector();
        boolean loginStatus = false;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM demonstrator");

            while (rs.next()) {
                if (rs.getString("demonstrator_id").equals(username) && rs.getString("password").equals(password)) {
                    loginStatus = true;  // If login successful
                    break;  // Exit the loop as soon as match is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginStatus;
    }
    public boolean sendMessageToLecturer(String lecturerId, String message) {
        Connection connection = sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO messages (recipient_id, message, sender_role) VALUES (?, ?, 'Demonstrator')")) {
            ps.setString(1, lecturerId);
            ps.setString(2, message);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Message sent to Lecturer ID: " + lecturerId);
                return true;
            } else {
                System.out.println("Failed to send message to lecturer.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean sendMessageToStudent(String studentId, String message) {
        Connection connection = sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO messages (recipient_id, message, sender_role) VALUES (?, ?, 'Demonstrator')")) {
            ps.setString(1, studentId);
            ps.setString(2, message);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Message sent to Student ID: " + studentId);
                return true;
            } else {
                System.out.println("Failed to send message to student.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean markStudentAttendance(String studentId, String courseId, String date) {
        Connection connection = sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO attendance (student_id, course_id, date) VALUES (?, ?, ?)")) {
            ps.setString(1, studentId);
            ps.setString(2, courseId);
            ps.setString(3, date);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Attendance marked successfully for Student ID: " + studentId);
                return true;
            } else {
                System.out.println("Failed to mark attendance.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {

    }

}
