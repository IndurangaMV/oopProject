package main;
import controlls.DBConnectDemonstrator;


public class Demonstrator extends UniversityPerson {
    private boolean loginState = false;
    DBConnectDemonstrator dbc = new DBConnectDemonstrator(); // Using DBConnectAdmin for database actions

    @Override
    public void login() {
        System.out.println("Demonstrator Login");
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
            demonstratorDashboard();
        } else {
            System.out.println("Invalid login. Please try again.");
            login();
        }
    }

    private void demonstratorDashboard() {
        if (loginState) {
            System.out.println("-----------------------------");
            System.out.println("Demonstrator Dashboard\t\tYou have "+dbc.getMsgNotifications(username)+" Messages");
            System.out.println("-----------------------------");
            System.out.println("01. Mark Attendance.");
            System.out.println("02. Send Message.");
            System.out.println("03. View Messages.");
            System.out.println("04. Sign Out.");
            System.out.print("\nPlease enter the task number: ");
            int task = scn.nextInt();
            switch (task) {
                case 1:
                    markAttendance();
                    break;
                case 2:
                    sendMessage();
                    break;
                case 3:
                    viewMessages();
                    break;
                case 4:
                    signOut();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    demonstratorDashboard();
            }
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }

    // Method to mark attendance for students
    private void markAttendance() {
        if (loginState) {
            System.out.println("\nMarking Attendance\n-------------------");
            System.out.print("Enter Student ID: ");
            String studentId = scn.next();
            System.out.print("Enter Course ID: ");
            String courseId = scn.next();
            System.out.print("Enter Date (yyyy-mm-dd): ");
            String date = scn.next();

            boolean attendanceMarked = dbc.markStudentAttendance(studentId, courseId, date);
            if (attendanceMarked) {
                System.out.println("Attendance marked successfully.");
            } else {
                System.out.println("Error marking attendance.");
            }
            demonstratorDashboard();
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }

    // Method to send a message to a student


    // Method to send a message to a lecturer
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
                demonstratorDashboard();
            }else{
                sendMessage();
            }
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }
    private void viewMessages(){
        String[][] messages=dbc.getMessages(username);
        for(String[] x: messages){
                System.out.println("\nFrom: "+x[0]+"\t\tDate & Time: "+x[2]);
                System.out.println(x[1]);
        }
        markAsRead();
        demonstratorDashboard();
    }
    private void markAsRead(){
dbc.setViewStt(username);
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
    public static void main(String[] args) {
        Demonstrator demo=new Demonstrator();
        demo.login();
    }
}

