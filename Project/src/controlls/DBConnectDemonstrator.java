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
    public boolean selectDemoLecture(String username,String from,String to){
        Connection connection=sqlConnector();
        int lecID =0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM lecture WHERE lecture.module_module_id IN(SELECT module_module_id FROM module_has_demonstrator WHERE demonstrator_id IN (SELECT id FROM demonstrator WHERE demonstrator_id='"+username+"')) AND `date` BETWEEN '"+from+"' AND '"+to+"';");
            while(rs.next()){
                System.out.println("\t"+rs.getString("id")+": "+rs.getString("faculty_name"));
            }
            System.out.print("Enter the Faculty Number: ");
            lecID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }
    public boolean markStudentAttendance(String studentId, String courseId, String date) {
        Connection connection=sqlConnector();
        int facID =0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM faculty");
            System.out.println("Select User Faculty Here: (enter the number)");
            while(rs.next()){
                System.out.println("\t"+rs.getString("id")+": "+rs.getString("faculty_name"));
            }
            System.out.print("Enter the Faculty Number: ");
            facID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return true;
    }


    public static void main(String[] args) {

    }

}
