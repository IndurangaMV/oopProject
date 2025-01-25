package controlls;

import java.sql.*;

public class DBConnectlecturer extends DBConnect{
    public boolean login(String username,String password){
        Connection connection= sqlConnector();
        boolean rtnStt=false;
        try{
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM lecturer");
            while(rs.next()){
                if(rs.getString("lecturer_id").equals(username) && rs.getString("password").equals(password)){
                    rtnStt= true;
                    break;
                }else{
                    rtnStt= false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rtnStt;
    }
    public String getModuleID(String username){
        Connection connection=sqlConnector();
        String modID=null;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM module WHERE `lecturer_id` IN (SELECT `id` FROM `lecturer` WHERE `lecturer_id`='"+username+"')");
            System.out.println("Select The Module Here (Don't use spaces.):");
            while(rs.next()){
                System.out.println("\t"+rs.getString("cause_code")+":\t"+rs.getString("module_name"));
            }
            System.out.println("Enter the Cause code: ");
            modID =scn.nextLine();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modID;
    }
    public int addLecture(String causeCode,String title,String date,String from,String to,int hall){
        int lecID=0;
        Connection connection=sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO lecture (title,module_module_id,date,time_from,time_to) VALUES(?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, title);
            ps.setString(2, causeCode);
            ps.setString(3,date);
            ps.setString(4, from);
            ps.setString(5,to);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lecID = generatedKeys.getInt(1);
                        try (PreparedStatement ps1 = connection.prepareStatement("INSERT INTO lecture_has_lecture_hall (lecture_id,lecture_hall_id,time_from,time_to,date) VALUES(?,?,?,?,?)")) {
                            ps1.setInt(1, lecID);
                            ps1.setInt(2, hall);
                            ps1.setString(3,from);
                            ps1.setString(4, to);
                            ps1.setString(5,date);

                            int rowsInserted1 = ps1.executeUpdate();
                            if(rowsInserted1>0){
                                System.out.println("New Lecture has been saved successfully.");
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Lecture can not be saved. Please recheck the information and try again.");
                    }
                }  catch (SQLException e) {
                e.printStackTrace();
            }
            }else{
                System.out.println("Lecture can not be saved. Please recheck the information and try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecID;
    }
    public void attendanceSheet(int lecID){
        Connection connection=sqlConnector();
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM student WHERE student.combination_id \n" +
                    "IN (SELECT combination FROM module WHERE cause_code \n" +
                    "IN (SELECT module_module_id FROM lecture WHERE id='"+lecID+"'))");
            while(rs.next()){
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO student_has_lecture(student_id,lecture_id,present) VALUES(?,?,?)")) {
                    ps.setInt(1, rs.getInt("id"));
                    ps.setInt(2, lecID);
                    ps.setInt(3,0);
                    int rowsInserted = ps.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}


