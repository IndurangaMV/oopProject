package controlls;
import java.sql.*;

public class DBConnectAdmin extends DBConnect {
      public boolean login(String username,String password){
        Connection connection= sqlConnector();
        boolean rtnStt=false;
        try{
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM admin");
            while(rs.next()){
                if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                    rtnStt= true;
                }else{
                    rtnStt= false;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return rtnStt;
    }
    public void addNewStudent(String stuNum,String stuName,String mail,String stuCN,int facID,int semID,int combID){
        Connection connection=sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO student (studentNumber,name,student_mail,contact_no,faculty_id,academic_year_semester_id,combination_id) VALUES(?,?,?,?,?,?,?)")) {
            ps.setString(1, stuNum);
            ps.setString(2, stuName);
            ps.setString(3,mail);
            ps.setString(4, stuCN);
            ps.setInt(5,facID);
            ps.setInt(6,semID);
            ps.setInt(7,combID);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New Student has been saved successfully");
            }else{
                System.out.println("Student can not be saved. Please recheck the information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addNewDemonstrator(String demNum,String demName,String mail,String title,String demCN,int facID,int deptID){
        Connection connection=sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO demonstrator (demonstrator_id,demonstrator_name,demonstrator_mail,title,contact_no,faculty_id,department_id) VALUES(?,?,?,?,?,?,?)")) {
            ps.setString(1, demNum);
            ps.setString(2, demName);
            ps.setString(3,mail);
            ps.setString(4, title);
            ps.setString(5,demCN);
            ps.setInt(6,facID);
            ps.setInt(7,deptID);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New Demonstrator has been saved successfully");
            }else{
                System.out.println("Demonstrator can not be saved. Please recheck the information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addNewLecturer(String lecNum,String lecName,int lpID,String title,String mail,String lecCN,int facID,int deptID){
        Connection connection=sqlConnector();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO lecturer (lecturer_id,lecturer_name,lecture_position_post_id,lecturer_title,lecturer_mail,contact_no,faculty_id,department_id) VALUES(?,?,?,?,?,?,?,?)")) {
            ps.setString(1, lecNum);
            ps.setString(2, lecName);
            ps.setInt(3,lpID);
            ps.setString(4, title);
            ps.setString(5,mail);
            ps.setString(6,lecCN);
            ps.setInt(7,facID);
            ps.setInt(8,deptID);

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New Demonstrator has been saved successfully");
            }else{
                System.out.println("Demonstrator can not be saved. Please recheck the information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getMedicalAction(){
        Connection connection=sqlConnector();
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT medical.id AS mid,medical.date,student.id,student.studentNumber FROM medical LEFT JOIN student ON medical.student_id=student.id WHERE status='0' AND admin_id='1'");
            while (rs.next()){
                System.out.println("Medical No:"+rs.getInt("mid")+"\tStudent:"+rs.getString("studentNumber")+"\t\tDate:"+rs.getString("date"));
           }
            System.out.print("Enter the Medical number:");
            String mNum= scn.next();
            System.out.println("Enter Action (Approve-1\tDecline-2):");
           String action=scn.next();
            try (PreparedStatement ps = connection.prepareStatement("UPDATE medical SET status=? WHERE id=?")) {
                ps.setString(1, action);
                ps.setString(2, mNum);
                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Medical Status Updated.");
                }else{
                    System.out.println("Process could not be done. Try again");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public int medicalNotification(){
        Connection connection=sqlConnector();
        int not =0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM medical WHERE status='0' AND admin_id='1'");
            rs.last();
            not=rs.getRow();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return not;
    }
}

