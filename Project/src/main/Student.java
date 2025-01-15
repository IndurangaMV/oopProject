package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student extends UniversityPerson {
    @Override
    public void login() {
        Connection conn=sqlConnection.sqlConnector();
        try {
            Statement st=conn.createStatement();
            ResultSet rt=st.executeQuery("SELECT * FROM academic_year_semester");
            while(rt.next()){
                System.out.println(rt.getString("academicYear_semester"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void signOut() {
        System.out.println("ss");
    }
    public static void main(String[] args) {
        Student st=new Student();
        st.login();
    }
}


