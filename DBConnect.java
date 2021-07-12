import java.io.BufferedReader;
import java.sql.*;
import java.util.Scanner;
import java.io.*;


public class DBConnect {
    private boolean SQLLogIn;
    private String DBUrl = "";
    private String DBUser;
    private String DBPassword;
    private Scanner x;
    private Connection DBConnection;
    public static boolean DBExists;
    private static String DBPrivilege;

    public DBConnect() {
        //SQLLogIn = readSQLAccess();

    }

    public DBConnect(String URL, String user, String password, String privilege){
        System.out.println("HERE!");

        try{
               DBConnection = DriverManager.getConnection(URL, user, password);
               
            this.setDBUrl(URL);
            this.setDBPassword(password);
            this.setDBUser(user);
            this.setDBPrivilege(privilege);
                this.setSQLLogin(true);
                
                System.out.println("\n" + "Successfully connected to the MYSQL Database" + "\n");
        } catch (SQLException e) {
            this.setSQLLogin(false);
            System.out.println("\n" + "Unable to connect to the MYSQL Database" + "\n");
            //e.printStackTrace();
            System.exit(1);
        }

        this.createDatabase();

        //add user input to create new class list

        this.createClass("History",1234);

    }


    public boolean readSQLAccess() {

        //System.out.println("kokokokokokok");

      /*   x = new Scanner (System.in);
        System.out.println("Enter mySQL database URL (enter nothing for default URL): ");
        
        String xIn = x.nextLine(); */
       
//WILL USE!!!!!!!!!!!!!!!
       /* if(xIn.equals("\n")){
            DBUrl = "jdbc:mysql://localhost:3306/";

        }
        else {
            DBUrl = xIn;
        }*/

        System.out.println("THE URL IS: " + this.getDBUrl());

        x.close();

        return true;

    }

    public String getDBUrl() {
        return this.DBUrl;
    }

    public void setSQLLogin(boolean bool) {
        this.SQLLogIn = bool;
    }

    public boolean getSQLLogin() {
        return this.SQLLogIn;
    }


    public void createDatabase() {                                  //maybe better way to check if the database exists!!!!

        try {
            if(DBExists==false){
                Statement state = DBConnection.createStatement();
                String toExecute = "CREATE DATABASE GRADESYSTEM";
                state.executeUpdate(toExecute);
                System.out.println("Successfully created database");
                DBExists = true;
                state.close();
                
            }
        }catch (SQLException e) {
            if(DBExists = true) {
                System.out.println("Already exists");
            }
            else {
                System.out.println("Somethings wrong");
                DBExists = false;
                System.exit(1);
            }
        }

            //MOVE THIS TO TEACHER -> make it where they enter the fields that will be created (eg. exams, projects, etc.)
            //Teacher has database that has all students and their grades broken down, while student has slightly different database structure -> therefore requires intermediate saving  (thus update in teacher updates student -> teacher enters ID to change, must save this to after access this ID's student DB)
            //eg. TEACHER : GRADESYSTEM (DB) -> ID30087886 (TABLES) -> table of all grades  .....   STUDENT : (IN JAVA) Enter ID -> GRADESYSTEM (DB) -> Exams, Projects, Quizzes (Tables)

            //no database selected because url doesnt include /gradesystem yet! -> attempted to fix with resetURL(), but caused further issues!
            //maybe code USE DATABASE first
        //this.resetDBURL();

            try {
                this.resetDBURL();
                   //UNCOMMENT THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11

                
                String tbl = "CREATE TABLE IF NOT EXISTS REGISTRATIONINFO " + 
                "(id INTEGER not NULL, " + 
                "firstname VARCHAR(255), " + 
                "lastname VARCHAR(255), " + 
                "age INTEGER, " + 
                "average INTEGER, " + 
                " PRIMARY KEY (id))";

                Statement s1 = DBConnection.createStatement();
                s1.executeUpdate(tbl);
                
                //added this but unsure.....left off here

                /*
                Statement state = DBConnection.createStatement();
                String toExecute = "USE DATABASE GRADESYSTEM";
                state.executeUpdate(toExecute);
                state.close();
                System.out.println("SYDYUDSFGIAUFBVGAIGV"); */
                System.out.println(getDBPrivilege());
            }catch (SQLException e2) {
                System.out.println("Unable to create Tables");  
                e2.printStackTrace();                //currently an issue here!   
            } 
            

        
    }

    public void resetDBURL() {
        this.setDBUrl(this.getDBUrl() + "GRADESYSTEM");
        
        try{
            DBConnection.close();   //close previous connection
           // System.out.println(this.getDBUrl() + this.getDBUser() +this.getDBPassword());
            DBConnection = DriverManager.getConnection(this.getDBUrl(), this.getDBUser(), this.getDBPassword());
            System.out.println("Second connect succeed");
        } catch (SQLException e3) {
            System.out.println("HMMMMM");
            e3.printStackTrace();
        }
    }

    public void createClass(String className, int ID) {
        try{
            String tbl = "CREATE TABLE IF NOT EXISTS " + className + " " + 
            "(id INTEGER not NULL, " + 
            "firstname VARCHAR(255), " + 
            "lastname VARCHAR(255), " + 
            "age INTEGER, " + 
            "average INTEGER, " + 
            " PRIMARY KEY (id))";

            Statement s1 = DBConnection.createStatement();
            s1.executeUpdate(tbl);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDBUrl(String URL) {
        this.DBUrl = URL;
    }

    public void setDBUser(String user) {
        this.DBUser = user;
    }

    public String getDBUser(){
        return this.DBUser;
    }

    public void setDBPassword(String pass) {
        this.DBPassword = pass;
    }

    public String getDBPassword() {
        return this.DBPassword;
    }

    public void setDBPrivilege(String privilegeRequested) {
        DBPrivilege = privilegeRequested;
    }
    public String getDBPrivilege() {
        return DBPrivilege;
    }
    
    
}
