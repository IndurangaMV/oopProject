package controlls;

import java.sql.*;
import java.time.LocalDate;

public class DBConnectStudent extends DBConnect {
    public boolean login(String username, String password) {
        Connection connection = sqlConnector();
        boolean rtnStt = false;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                if (rs.getString("studentNumber").equals(username) && rs.getString("password").equals(password)) {
                    rtnStt = true;
                    break;
                } else {
                    rtnStt = false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rtnStt;
    }

    public String[][] seeLectureShedule(Date from, Date to, String username){
        String[][] lecList=null;
        Connection connection=sqlConnector();
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs_id=st.executeQuery("SELECT id FROM student WHERE studentNumber='"+username+"'");
            rs_id.next();
            ResultSet rs=st.executeQuery("SELECT * FROM lecture WHERE module_module_id IN(SELECT cause_code FROM module WHERE module.`combination` IN (SELECT combination_id FROM student WHERE id='"+rs_id.getInt("id")+"')) AND `date` BETWEEN '"+from+"' AND '"+to+"'");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            lecList=new String[n][6];
            int i=0;
            while (rs.next()){
                lecList[i][0]=rs.getString("title");
                lecList[i][1]=rs.getString("module_module_id");
                lecList[i][2]=rs.getString("date");
                lecList[i][3]=rs.getString("time_from");
                lecList[i][4]=rs.getString("time_to");
                lecList[i][5]=rs.getString("id");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lecList;
    }

    public String[][] seeLectureShedule(String from, String to, String username) {
        String[][] lecList = null;
        Connection connection = sqlConnector();
        try {
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs_id = st.executeQuery("SELECT id FROM student WHERE studentNumber='" + username + "'");

            if (rs_id.next()) {  // Ensure there's a valid lecturer ID
                ResultSet rs = st.executeQuery("SELECT * FROM lecture WHERE module_module_id " +
                        "IN(SELECT cause_code FROM module WHERE lecturer_id='" + rs_id.getInt("id") + "') " +
                        "AND date BETWEEN '" + from + "' AND '" + to + "';");

                rs.last();
                int n = rs.getRow(); // Get the number of rows
                rs.beforeFirst(); // Move back to the start

                if (n > 0) {  // Check if any results were found
                    lecList = new String[n][6];
                    int i = 0;
                    while (rs.next()) {
                        lecList[i][0] = rs.getString("title");
                        lecList[i][1] = rs.getString("module_module_id");
                        lecList[i][2] = rs.getString("date");
                        lecList[i][3] = rs.getString("time_from");
                        lecList[i][4] = rs.getString("time_to");
                        lecList[i][5] = rs.getString("id");
                        i++;
                    }
                } else {
                    System.out.println("No lectures found for this username within the given date range.");
                }
            } else {
                System.out.println("Lecturer ID not found for username: " + username);
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return lecList;
    }
    public void saveMedical(int id,LocalDate date, String username){
        int stdId=0;
        Connection connection=sqlConnector();
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT id FROM student WHERE studentNumber='"+username+"'");
            rs.next();
            stdId=rs.getInt("id");
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO medical(id,date,status,student_id,admin_id) VALUES(?,?,?,?,?)")) {
            ps.setInt(1, id);
            ps.setString(2, String.valueOf(date));
            ps.setInt(3,0);
            ps.setInt(4, stdId);
            ps.setInt(5,1);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Medical Request is pending");
            }else{
                System.out.println("Medical Request failed. Please try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


