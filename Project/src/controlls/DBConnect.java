package controlls;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.SQLException;


public abstract class DBConnect {
    Connection connection;
    Scanner scn=new Scanner(System.in);

    Connection sqlConnector(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_attendance_system_kln", "root", "HNGvmi21297");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public abstract boolean login(String username, String password);
    public int getFacultyID(){
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
        return facID;
    }
    public int getDepartmentID(int facID){
        Connection connection=sqlConnector();
        int depID =0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM department WHERE faculty_id='"+facID+"'");
            System.out.println("Select User Faculty Here: (enter the number)");
            while(rs.next()){
                System.out.println("\t"+rs.getString("id")+": "+rs.getString("department_name"));
            }
            System.out.print("Enter the Department Number: ");
            depID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return depID;
    }
    public int getCombinationID(int facID){
        Connection connection=sqlConnector();
        int combID=0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM combination WHERE faculty_id='"+facID+"'");
            System.out.println("Select Student Combination Here: (enter the number)");
            while(rs.next()){
                System.out.println("\t"+rs.getString("id")+": "+rs.getString("name"));
            }
            System.out.print("Enter the Combination Number: ");
            combID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return combID;
    }
    public int getSemID(){
        Connection connection=sqlConnector();
        int semID=0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM academic_year_semester");
            System.out.println("Select The Semester Here: (enter the number)");
            while(rs.next()){
                System.out.println("\t"+rs.getString("id")+": "+rs.getString("academicYear_semester"));
            }
            System.out.print("Enter the Semester Number: ");
            semID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return semID;
    }
    public int getLecturerPosition(){
        Connection connection=sqlConnector();
        int lpID =0;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM lecture_position");
            System.out.println("Select The Position of Lecturer Here: (enter the number)");
            while(rs.next()){
                System.out.println("\t"+rs.getString("post_id")+": "+rs.getString("position"));
            }
            System.out.print("Enter Position Number: ");
            lpID =scn.nextInt();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lpID;
    }

    public String[][] getFacultyList(){
        Connection connection=sqlConnector();
        String[][] facultyList=null;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM faculty");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            facultyList=new String[n][2];
            int i=0;
            while (rs.next()){
                facultyList[i][0]=rs.getString("id");
                facultyList[i][1]=rs.getString("faculty_name");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return facultyList;
    }
    public String[][] getDepartmentList(int facID){
        Connection connection=sqlConnector();
        String[][] departmentList =null;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM department WHERE faculty_id='"+facID+"'");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            departmentList =new String[n][2];
            int i=0;
            while (rs.next()){
                departmentList[i][0]=rs.getString("id");
                departmentList[i][1]=rs.getString("department_name");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return departmentList;
    }
    public String[][] getCombinationList(int facID){
        Connection connection=sqlConnector();
        String[][] combList =null;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM combination WHERE faculty_id='"+facID+"'");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            combList =new String[n][2];
            int i=0;
            while (rs.next()){
                combList[i][0]=rs.getString("id");
                combList[i][1]=rs.getString("name");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return combList;
    }
    public String[][] getSemesterList(){
        Connection connection=sqlConnector();
        String[][] semList =null;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM academic_year_semester");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            semList =new String[n][2];
            int i=0;
            while (rs.next()){
                semList[i][0]=rs.getString("id");
                semList[i][1]=rs.getString("academicYear_semester");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return semList;
    }
    public String[][] getLecturePositionList(){
        Connection connection=sqlConnector();
        String[][] lpList =null;
        try{
            Statement st = connection.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ResultSet rs=st.executeQuery("SELECT * FROM lecture_position");
            rs.last();
            int n=rs.getRow();
            rs.beforeFirst();
            lpList =new String[n][2];
            int i=0;
            while (rs.next()){
                lpList[i][0]=rs.getString("post_id");
                lpList[i][1]=rs.getString("position");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lpList;
    }
}
