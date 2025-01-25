package main;
import java.util.Scanner;
import controlls.DBConnectAdmin;
import controlls.DBConnect;
import controlls.DBConnectDemonstrator;

public class Demonstrator extends UniversityPerson {
    private boolean loginState = false;
    Scanner scn = new Scanner(System.in);
    DBConnectDemonstrator dbcd = new DBConnectDemonstrator(); // Using DBConnectAdmin for database actions

    @Override
    public void login() {
        System.out.println("Demonstrator Login");
        System.out.println("-------------------");
        System.out.print("Enter Username: ");
        String username = scn.next();
        System.out.print("Enter Password: ");
        String password = scn.next();
        loginState = dbcd.login(username, password); // Use the login method from DBConnectAdmin
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
            System.out.println("Demonstrator Dashboard");
            System.out.println("-----------------------------");
            System.out.println("01. Mark Attendance.");
            System.out.println("02. Send Message to Student.");
            System.out.println("03. Send Message to Lecturer.");
            System.out.println("04. Sign Out.");
            System.out.print("\nPlease enter the task number: ");
            int task = scn.nextInt();
            switch (task) {
                case 1:
                    markAttendance();
                    break;
                case 2:
                    sendMessageToStudent();
                    break;
                case 3:
                    sendMessageToLecturer();
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

            boolean attendanceMarked = dbcd.markStudentAttendance(studentId, courseId, date);
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
    private void sendMessageToStudent() {
        if (loginState) {
            System.out.println("\nSend Message to Student\n-------------------------");
            System.out.print("Enter Student ID: ");
            String studentId = scn.next();
            System.out.print("Enter Message: ");
            scn.nextLine();  // Consume newline left over from nextInt()
            String message = scn.nextLine();

            boolean messageSent = dbcd.sendMessageToStudent(studentId, message);
            if (messageSent) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Error sending message.");
            }
            demonstratorDashboard();
        } else {
            System.out.println("You must log in first.");
            login();
        }
    }

    // Method to send a message to a lecturer
    private void sendMessageToLecturer() {
        if (loginState) {
            System.out.println("\nSend Message to Lecturer\n---------------------------");
            System.out.print("Enter Lecturer ID: ");
            String lecturerId = scn.next();
            System.out.print("Enter Message: ");
            scn.nextLine();  // Consume newline left over from nextInt()
            String message = scn.nextLine();

            boolean messageSent = dbcd.sendMessageToLecturer(lecturerId, message);
            if (messageSent) {
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Error sending message.");
            }
            demonstratorDashboard();
        } else {
            System.out.println("You must log in first.");
            login();
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
    public static void main(String[] args) {
        Demonstrator demo=new Demonstrator();
        demo.login();
    }
}

