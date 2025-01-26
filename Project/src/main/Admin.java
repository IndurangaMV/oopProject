package main;
import java.util.Scanner;
import controlls.DBConnectAdmin;

public class Admin extends UniversityPerson{
    String username;
    String password;
    private boolean loginState=false;
    Scanner scn=new Scanner(System.in);
    DBConnectAdmin dbc=new DBConnectAdmin();

    @Override
    public void login() {
        System.out.println("Administrator Login");
        System.out.println("-------------------");
        System.out.print("Enter Username:");
        String username= scn.next();
        System.out.print("Enter Password: ");
        String password=scn.next();
        loginState=dbc.login(username,password);
        if(loginState){
            System.out.println("Login Success");
            this.username=username;
            this.password=password;
            adminDashboard();
        }else{
            login();
        }
    }
    private void adminDashboard(){
        if(loginState){
            System.out.println("----------------------------------------------------------");
            System.out.println("Admin Dashboard\t\tMedical Requests:"+dbc.medicalNotification());
            System.out.println("----------------------------------------------------------");
            System.out.println("01.Add new Student.");
            System.out.println("02.Remove Student.");
            System.out.println("03. Add new Demonstrator.");
            System.out.println("04. Add new Lecturer.");
            System.out.println("05. Send Message.");
            System.out.println("06. Medical Request.");
            System.out.println("07.Sign Out.");
            System.out.print("\nPlease enter the task number:");
            int task=scn.nextInt();
            switch (task){
                case 1:
                    studentRegister();
                    break;
                case 2:
                    studentRemove();
                    break;
                case 3:
                    demoRegister();
                    break;
                case 4:
                    lecturerRegister();
                    break;
                case 5:
                    sendMessage();
                    break;
                case 6:
                    medicalAction();
                    break;
                case 7:
                    signOut();
                    break;
                default:
                    studentRemove();
                    adminDashboard();
            }
        }else{
            System.out.println("You have to login first.");
            login();
        }
    }

    private void medicalAction(){
       dbc.getMedicalAction();
       adminDashboard();
    }

    private void sendMessage() {
        int type;
        String user="";
        boolean msgStt;
        if (loginState) {
            System.out.println("\nSend Message to User\n-------------------------");
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
            }
            System.out.print("Enter the "+user+"ID here:");
            String user_id=scn.next();
            msgStt= dbc.sendMessage(type,user_id,this.username);
            if(msgStt){
                System.out.println("Message ware sent successfully.");
                adminDashboard();
            }else{
                sendMessage();
            }
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }
    private void sendMessage(String user_id) {
        boolean msgStt;
        if (loginState) {
            msgStt= dbc.sendMessage(1,user_id,this.username);
            if(msgStt){
                System.out.println("Message ware sent successfully.");
                adminDashboard();
            }else{
                System.out.println("Message sending failed..! try to inform manually.");
            }
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }
    private void studentRegister(){
        if(loginState){
            System.out.println("\nNew Student Registration\n-------------------------------");
            System.out.print("Enter Student Number: ");
            String stuNum=scn.next();
            System.out.print("Enter Student Name: ");
            String stuName=scn.next();
            System.out.print("Enter Student Contact Number: ");
            String stuCN=scn.next();
            int facID= dbc.getFacultyID();
            int semID= dbc.getSemID();
            int combID= dbc.getCombinationID(facID);
            String mail=stuName+stuNum+"@stu.kln.ac.lk";
            dbc.addNewStudent(stuNum,stuName,mail,stuCN,facID,semID,combID);
            adminDashboard();
        }else{
            System.out.println("You have to login first..!");
            login();
        }

    }
    private void demoRegister(){
        if(loginState){
            System.out.println("\nNew Demonstrator Registration\n-------------------------------");
            System.out.print("Enter Demonstrator Number: ");
            String demNum=scn.next();
            System.out.print("Enter Demonstrator Name: ");
            String demName =scn.next();
            System.out.print("Enter Demonstrator Degree Title: ");
            String demTtl =scn.next();
            System.out.print("Enter Demonstrator Contact Number: ");
            String demCN =scn.next();
            int facID= dbc.getFacultyID();
            int deptID= dbc.getDepartmentID(facID);
            String mail= demName +demNum+"@dem.kln.ac.lk";
            dbc.addNewDemonstrator(demNum, demName,mail,demTtl, demCN,facID,deptID);
            adminDashboard();
        }else{
            System.out.println("You have to login first..!");
            login();
        }

    }
    private void lecturerRegister(){
        if(loginState){
            System.out.println("\nNew Lecturer Registration\n-------------------------------");
            System.out.print("Enter Lecturer Number: ");
            String lecNum=scn.next();
            System.out.print("Enter Lecturer Name: ");
            String lecName =scn.next();
            int lpID= dbc.getLecturerPosition();
            System.out.print("Enter Lecturer Degree Title: ");
            String lecTtl =scn.next();
            System.out.print("Enter Lecturer Contact Number: ");
            String lecCN =scn.next();
            int facID= dbc.getFacultyID();
            int deptID= dbc.getDepartmentID(facID);
            String mail= lecName +lecNum+"@dem.kln.ac.lk";
            dbc.addNewLecturer(lecNum,lecName,lpID,lecTtl,mail,lecCN,facID,deptID);
            adminDashboard();
        }else{
            System.out.println("You have to login first..!");
            login();
        }
    }
    private void studentRemove(){
        System.out.println("student Can't be removed by yourself.");
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

