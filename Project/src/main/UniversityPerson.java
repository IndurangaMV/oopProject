package main;

import controlls.DBConnect;

import java.util.Scanner;

public abstract class UniversityPerson implements Action{
    public static final String university ="University of Kelaniya";
    protected String username;
    protected String password;
    Scanner scn = new Scanner(System.in);

}