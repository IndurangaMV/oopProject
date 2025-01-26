package main;

import controlls.DBConnectlecturer;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.sql.Date;

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
            System.out.println("4. View messages");
            System.out.println("5. Add new Lecture");
            System.out.println("6. Sign out");
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
                    SendMessage();
                    break;
                case 4:
                    viewMessage();
                    break;
                case 5:
                    sheduleNewLecture();
                    break;
                case 6:
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
            LocalDate currentDate = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            LocalDate dateFrom = currentDate.with(weekFields.dayOfWeek(), 1);
            LocalDate dateTo = currentDate.with(weekFields.dayOfWeek(), 5);
            Date sqlDateFrom = Date.valueOf(dateFrom);
            Date sqlDateTo = Date.valueOf(dateTo);
            String[][] lecList= dbc.seeLectureShedule(sqlDateFrom,sqlDateTo,username);
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
    private void markAsRead(){
        dbc.setViewStt(username);
    }
    private void AboutLecture(){
        if(loginState){
            LocalDate currentDate = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            LocalDate dateFrom = currentDate.with(weekFields.dayOfWeek(), 1);
            LocalDate dateTo = currentDate.with(weekFields.dayOfWeek(), 5);
            Date sqlDateFrom = Date.valueOf(dateFrom);
            Date sqlDateTo = Date.valueOf(dateTo);
            String[][] lecList= dbc.seeLectureShedule(sqlDateFrom,sqlDateTo,username);
            for(String[] x:lecList){
                System.out.println(x[0]+"\t"+x[1]+"\t"+x[2]+"\t"+x[3]+"\t"+x[4]);
            }
    }
        else {
            System.out.println("You have to login first");
        }
        LectureDashboard();
    }
    private void SendMessage() {

        if(loginState) {
                int type;
                String demoId;
                boolean msgstt;
                System.out.println("If you want to send a message to a demonstrator Enter no 3:");
                type = scn.nextInt();
                System.out.println("Enter demonstrator ID:");
                demoId = scn.next();
                System.out.println("Enter the message:");
                String message=scn.nextLine();
                msgstt = dbc.sendMessage(type, demoId, this.username);
                if (msgstt) {
                    System.out.println("Message send success!");
                    LectureDashboard();
                }
                else {
                    System.out.println("Message send not success!");
                }
        }
        else {
            System.out.println("You have to login first");
        }
    }
    private void viewMessage(){
        String[][] messages=dbc.getMessages(this.username);
        for (String[] x:messages){
            System.out.println("\nFrom"+x[0]+"\t\t Date & time"+x[2]);
            System.out.println(x[1]);
        }
        markAsRead();
        LectureDashboard();
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
