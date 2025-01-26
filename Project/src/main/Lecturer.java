package main;

import controlls.DBConnectlecturer;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Scanner;
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
            login();
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
            LectureDashboard();
        }
        else {
            System.out.println("You have to login first");
            login();
        }
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
                System.out.println(x[1]+"\t\t"+x[2]+"\t\t"+x[3]+"\t\t"+x[4]+"\t\t"+x[0]);
            }
            LectureDashboard();
        }else {
            System.out.println("You have to login first");
            login();
        }
    }
    private void SendMessage() {
        if(loginState) {
                int type;
                String user;
                boolean msgstt;
            System.out.println("Which one that you want to send a message?");
            System.out.println("\t01.Student\n\t02.Lecturer\n\t03.Demonstrator");
            System.out.print("Select the number: ");
            type= scn.nextInt();
            System.out.println("Enter demonstrator ID:");
            user = scn.next();
            switch (type){
                case 1:user="Student";
                    break;
                case 2:user="Lecturer";
                    break;
                case 3:user="Demonstrator";
            }
            System.out.print("Enter the "+user+"ID here:");
            String user_id=scn.next();
            msgstt= dbc.sendMessage(type,user_id,this.username);
            if(msgstt){
                System.out.println("Message ware sent successfully.");
                LectureDashboard();
            }else{
                SendMessage();
            }
        }
        else {
            System.out.println("You have to login first");
            login();
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
}
