package controlls;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    Connection connection;
    public Connection sqlConnector(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_attendance_system_kln", "root", "HNGvmi21297");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
