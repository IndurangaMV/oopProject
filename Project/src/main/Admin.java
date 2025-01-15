package main;
import java.sql.ResultSet;
import java.sql.Statement;
import controlls.DBConnect;
import java.sql.Connection;
import java.util.Scanner;

public class Admin extends UniversityPerson{
//    String username;
//    String password;
    private boolean loginState=false;
    Scanner scn=new Scanner(System.in);

    @Override
    public void login() {
        System.out.println("Administrator Login");
        System.out.println("-------------------");
        System.out.print("Enter Username:");
        String username= scn.next();
        System.out.print("Enter Password: ");
        String password=scn.next();

        Connection connection= sqlConnection.sqlConnector();
       try{
           Statement st=connection.createStatement();
           ResultSet rs=st.executeQuery("SELECT * FROM admin");
           while(rs.next()){
               if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
                   loginState=true;
               }
           }
           if(loginState){
               this.password=password;
               this.username=username;
               System.out.println("Login Successfull...");
               adminDashboard();
           }else{
               System.out.println("Access Denied..!");
               login();
           }
       } catch (Exception e) {
           System.out.println(e);
       }


    }
    private void adminDashboard(){
        if(loginState){
            System.out.println("-----------------------------");
            System.out.println("Admin Dashboard");
            System.out.println("-----------------------------");
            System.out.println("01.Add new Student.");
            System.out.println("02.Remove Student.");
            System.out.println("03.Sign Out.");
            System.out.print("\nPlease enter the number of task:");
            int task=scn.nextInt();
            switch (task){
                case 1:
                    studentRegister();
                    break;
                case 2:
                    studentRemove();
                    break;
                case 3:
                    signOut();
                    break;
                default:
                    adminDashboard();
            }
        }else{
            System.out.println("You have to login first.");
            login();
        }


    }
    private void studentRegister(){
        System.out.println("register");
        adminDashboard();
    }
    private void studentRemove(){
        System.out.println("remove");
        adminDashboard();
    }

    @Override
    public void signOut() {
        if(loginState){
            loginState=false;
            this.username=null;
            this.password=null;
            System.out.println("Signed Out.");
        }else{
            System.out.println("You are already signed out.");
        }
    }
}

class Test{
    public static void main(String[] args) {
        Admin ad=new Admin();
        ad.login();
    }
}
