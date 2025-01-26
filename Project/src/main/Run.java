package main;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        System.out.println("Select you Type:");
        System.out.println("\t1. Student");
        System.out.println("\t2. Demonstrator");
        System.out.println("\t3. Lecturer");
        System.out.println("\t4. Admin");
        System.out.println("Enter index:");
        int type= scn.nextInt();
        switch (type){
            case 1:Student stu = new Student();
                    stu.login();
                    break;
            case 2: Demonstrator demo=new Demonstrator();
                demo.login();
                break;
            case 3:Lecturer lect= new Lecturer();
                lect.login();
                break;
            case 4:  Admin ad=new Admin();
                ad.login();
                break;
            default:
                System.out.println("Invalid Index. Please Try again.");

        }
    }
}
