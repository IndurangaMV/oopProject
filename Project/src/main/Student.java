package main;
import controlls.DBConnectStudent;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;


public class Student extends UniversityPerson {
    private boolean loginState = false;
    Scanner scn = new Scanner(System.in);
    DBConnectStudent dbc = new DBConnectStudent(); //

    @Override
    public void login() {
        System.out.println("Student Login");
        System.out.println("-------------------");
        System.out.print("Enter Username: ");
        String username = scn.next();
        System.out.print("Enter Password: ");
        String password = scn.next();
        loginState = dbc.login(username, password); // Use the login method from DBConnectAdmin
        if (loginState) {
            this.username = username;
            this.password = password;
            System.out.println("Login Success");
            studentDashboard();


        } else {
            System.out.println("Invalid login. Please try again.");
            login();
        }
    }
    private void studentDashboard() {
        if (loginState) {
            System.out.println("-----------------------------");
            System.out.println("Student Dashboard");
            System.out.println("-----------------------------");
            System.out.println("01. Send Message.");
            System.out.println("02. View Message.");
            System.out.println("03. Request Medical.");
            System.out.println("04. lecture schedule.");
            System.out.println("05. Sign Out.");
            System.out.print("\nPlease enter the task number: ");
            int task = scn.nextInt();
            switch (task) {
                case 1:
                    sendMessage();
                    break;
                case 2:
                    viewMessages();
                    break;
                case 3:
                    requestMedical();
                    break;
                case 4:
                    AboutLecture();
                    break;
                case 5:
                    signOut();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    studentDashboard();
            }
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }
    private void sendMessage() {
        int type;
        String user="";
        boolean msgStt;
        if (loginState) {
            System.out.println("\nSend Message\n-------------------------");
            System.out.println("Which one that you want to send a message?");
            System.out.println("\t01.Student\n\t02.Lecturer\n\t03.Demonstrator");
            System.out.print("Select the number: ");
            type= scn.nextInt();
            switch (type){
                case 1:user="Student";
                break;
                case 2:user="Lecturer";
                break;
                case 3:user="Demonstrator";
                break;
            }
            System.out.print("Enter the "+user+"ID here:");
            String user_id=scn.next();
            msgStt= dbc.sendMessage(type,user_id,this.username);
            if(msgStt){
                System.out.println("Message were sent successfully.");
                studentDashboard();
            }else{
                sendMessage();
            }
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }
    private void AboutLecture() {
        if(loginState){
            LocalDate currentDate = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            LocalDate dateFrom = currentDate.with(weekFields.dayOfWeek(), 1);
            LocalDate dateTo = currentDate.with(weekFields.dayOfWeek(), 5);
            Date sqlDateFrom = Date.valueOf(dateFrom);
            Date sqlDateTo = Date.valueOf(dateTo);
            String[][] lecList= dbc.seeLectureShedule(sqlDateFrom,sqlDateTo,username);
            for(String[] x:lecList){
                System.out.println(x[1]+"\t\t"+x[2]+"\t\t"+x[3]+"\t\t"+x[4]+"\t\t"+x[0]);
            }
            studentDashboard();
        }else{
            System.out.println("You must log in first.");
            login();
        }
    }
    private void viewMessages(){
        if(loginState){
            String[][] messages=dbc.getMessages(username);
            if(messages != null && messages.length > 0) {
                for (String[] x : messages) {
                    System.out.println("\nFrom: " + x[0] + "\t\tDate & Time: " + x[2]);
                    System.out.println(x[1]);
                }
            }else {
                System.out.println("No new messages");
            }
            studentDashboard();
        }else{
            System.out.println("You must log in first.");
            login();
        }

    }
    private void requestMedical(){
        if (loginState){
            System.out.println("You can Apply medical withing one week.");
           System.out.print("Enter date:");
           String date= scn.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate inputDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            LocalDate sevenDaysAgo = today.minusDays(7);
            boolean isWithinLastSevenDays = (inputDate.isEqual(today) || inputDate.isAfter(sevenDaysAgo)) && inputDate.isBefore(today.plusDays(1));
            if(isWithinLastSevenDays){
                Random random = new Random();
                int uniqueID = 10000 + random.nextInt(90000);
                dbc.saveMedical(uniqueID,inputDate,username);
            }else{
                System.out.println("Invalid date");
            }
            studentDashboard();
        }
    }



    @Override
    public void signOut() {
        if (loginState) {
            loginState = false;
            this.username = null;
            this.password = null;
            System.out.println("Signed Out.");
        } else {
            System.out.println("You are already signed out.");
        }

    }
}





