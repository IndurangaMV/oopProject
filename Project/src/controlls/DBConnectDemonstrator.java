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
    public int selectDemoLecture(String username,Date from,Date to){
        Connection connection=sqlConnector();
        int lecID =0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM lecture WHERE lecture.module_module_id IN(SELECT module_module_id FROM module_has_demonstrator WHERE demonstrator_id IN (SELECT id FROM demonstrator WHERE demonstrator_id='"+username+"')) AND `date` BETWEEN '"+from+"' AND '"+to+"';");
            while(rs.next()){
                System.out.println("\t"+rs.getString("id")+": "+rs.getString("module_module_id")+"\t"+rs.getString("date"));
            }
            System.out.print("Enter the Lecture ID: ");
            lecID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lecID;
    }
    public void markStudentAttendance(int lecID) {
        Connection connection=sqlConnector();
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT student_has_lecture.id,student_has_lecture.lecture_id,student.studentNumber,student.`name` \n" +
                    "FROM student_has_lecture \n" +
                    "LEFT JOIN student ON student_has_lecture.student_id=student.id\n" +
                    "WHERE lecture_id='"+lecID+"' AND present='0'");
            System.out.print("---------------------------------------------------------------------------------\nStudent Attendance Sheet\t\t");
            System.out.println("Enter 1 when present and 2 when absent.\n");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            String[][] att=new String[n][2];
            int i=0;
            while(rs.next()){
                System.out.print(rs.getString("studentNumber")+"\t"+rs.getString("name")+"\t\t\tStatus: ");
                att[i][1]=scn.next();
                att[i][0]=rs.getString("id");
                i++;
            }
            int c=0;
            int cc=0;
            for(String[] x:att){
                try (PreparedStatement ps = connection.prepareStatement("UPDATE student_has_lecture SET present=? WHERE id=?")) {
                    ps.setString(1, x[1]);
                    ps.setString(2, x[0]);
                    int rowsInserted = ps.executeUpdate();
                    if (rowsInserted > 0) {
                        c++;
                    }else{
                        cc++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nAttendance Updated.\tDone: "+c+"\tFails: "+cc);
            System.out.println("If some have been failed, you can update again.");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {

    }

}
