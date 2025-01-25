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
            String from="2025-01-01"; //add start date of current month
            String to="2025-01-31";//add end date of current month
            String[][] lecList= dbc.seeLectureShedule(from,to,username);
            for(int x=0;x<lecList.length;x++){
                System.out.println("Index:"+x+"\t"+lecList[x][1]+"\t"+lecList[x][2]+"\t"+lecList[x][3]+"\t"+lecList[x][4]+"\t"+lecList[x][0]);
            }
            System.out.println("Enter Index:");
            int i=scn.nextInt();
            String[][] studentList= dbc.seeStudentAtt(Integer.parseInt(lecList[i][5]));
            for(int n=0;n<studentList.length;n++){
                System.out.println(studentList[n][0]+"\t"+studentList[n][1]+"\t"+studentList[n][2]);
            }

        }
        else {
            System.out.println("You have to login first");
        }
        LectureDashboard();
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
            String from="2025-01-15";
            String to="2025-04-10";
            String[][] lecList= dbc.seeLectureShedule(from,to,username);
            for(String[] x:lecList){
                System.out.println(x[0]+"\t"+x[1]+"\t"+x[2]+"\t"+x[3]+"\t"+x[4]);
            }
    }
        else {
            System.out.println("You have to login first");
        }
        LectureDashboard();
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
