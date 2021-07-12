//import java.sql.*;
import java.util.*;

public class Student {

    private DBConnect connection;


    public Student() {
        System.out.println("***STUDENT LOGIN***");
        Date currentDate = new Date();
        System.out.println();
        System.out.println("\n" + "Current login: " + currentDate + "\n");
        

       // DBConnect studentConnect = new DBConnect();
    }

    public Student(String URL, String user, String password) {

        System.out.println("***STUDENT LOGIN***");
        Date currentDate = new Date();
        System.out.println();
        System.out.println("\n" + "Current login: " + currentDate + "\n");

        DBConnect studentConnectFields = new DBConnect(URL, user, password, "Student");
    }
    
}
