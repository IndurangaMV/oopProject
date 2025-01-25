package main;

import controlls.DBConnectlecturer;
import java.util.Scanner;

public class Lecturer extends UniversityPerson {
    private boolean loginState=false;
    Scanner scn=new Scanner(System.in);
    DBConnectlecturer dbc = new DBConnectlecturer();

    @Override
    public void login() {
        System.out.println("---------Lecturer login-----------");
        System.out.println("Enter User name: ");
        String username= scn.nextLine();
        System.out.println("Enter the password:");
        String password=scn.nextLine();
        loginState=dbc.login(username,password);
        if (loginState){
            System.out.println("Login success");
            this.username=username;
            this.password=password;
            LectureDashboard();
        }
        else {
            System.out.println("Invalid login.");
            login();
        }
    }
    private void LectureDashboard(){
        if(loginState){
            System.out.println("----------------------------------------------------------");
            System.out.println("Lecturer Dashboard\t\tYou have "+dbc.getMsgNotifications(username)+" Messages");
            System.out.println("----------------------------------------------------------");
            System.out.println("1. Get students attendance");
            System.out.println("2. Lecture Schedule");
            System.out.println("3. Send message to demonstrator");
            System.out.println("4. Add new Lecture");
            System.out.println("5. Sign out");
            System.out.println("Enter the number of task:");
            int task=scn.nextInt();

            switch (task){
                case 1:
                    getStudentAttendance();
                    break;
                case 2:
                    AboutLecture();
                    break;
                case 3:
                    SendMessageToDemo();
                    break;
                case 4:
                    sheduleNewLecture();
                    break;
                case 5:
                    signOut();
                    break;
                default:
                    System.out.println("Enter valid task.");
                    break;
            }
        }
        else {
            System.out.println("You have to login first");
        }

    }
    private void getStudentAttendance(){
        if(loginState){
            System.out.println("1. Search by Student.");
            System.out.println("2. Search by course code.");
            System.out.println("3. search by date.");
            System.out.println("please enter the task:");
            int task2=scn.nextInt();
            switch (task2){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Please enter correct task.");
                    break;
            }

        }
        else {
            System.out.println("You have to login first");
        }

    }
    private void searchStudent() {
        if (loginState) {
            System.out.println("Enter Student Id:");
            String stID=scn.nextLine();
        }
        else {
            System.out.println("You have to login first.");
        }
    }
    private void searchSourceCode(){
        if(loginState){
            System.out.println("Enter the Source code:");
            String SourceCode=scn.nextLine();
        }
        else {
            System.out.println("You have to login first");
        }
    }
    private void searchDate(){
        if(loginState){
            System.out.println("Enter the date:");
            String date=scn.nextLine();
        }
        else {
            System.out.println("You have to login first.");
        }
    }
    private void AboutLecture(){
        if(loginState){

    }
        else {
            System.out.println("You have to login first");
        }

    }
    private void SendMessageToDemo() {
        if(loginState){

        }
        else {
            System.out.println("You have to login first");
        }
    }
    private void sheduleNewLecture(){
        String modID=dbc.getModuleID(username);
        int hall= dbc.getLectureHallId();
        System.out.println("Enter title for Lecture:");
        String title=scn.nextLine();
        System.out.println("Enter Date: (format: yyyy-mm-dd)");
        String date=scn.nextLine();
        System.out.println("Time from(hh:mm:ss): ");
        String from=scn.nextLine();
        System.out.println("Time to(hh:mm:ss): ");
        String to=scn.nextLine();
        int lecId=dbc.addLecture(modID,title,date,from,to,hall);
        dbc.attendanceSheet(lecId);
        LectureDashboard();
    }


    @Override
    public void signOut() {
        if(loginState){
            loginState=false;
            this.username=null;
            this.password=null;
            System.out.println("Signed out.");
        }
        else {
            System.out.println("You are already signed out.");
        }

    }

    public static void main(String[] args) {
        Lecturer lect= new Lecturer();
        lect.login();
    }

}
