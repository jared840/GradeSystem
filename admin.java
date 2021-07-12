import java.io.Console;
import java.util.*;

public class admin {
     /*
        Admin class holds all the data that can be accessed by teachers and students.
        This includes:
    */

    
//member variables
private tree key;
private boolean sqlConnect;
public static String privilege = new String();     //could become static variable later...
private DBConnect connection;
public Scanner inScan;

/**
 * Default constructor for admin
 */
public admin() {

}

/**
 * Constructor that takes user inputted role (ie. Student/teacher) and configures admin system accordingly
 * @param userRole Either "student" or "teacher", which is then used to set system privilege configuration
 */
public admin(String userRole) {
    this.setPrivilege(userRole);

    //now create the tree

    System.out.println("***" + this.getPrivilege() + " LOGIN***");
    Date currentDate = new Date();
        System.out.println();
        System.out.println("\n" + "Current login: " + currentDate + "\n");

        //connection = new DBConnect()
}

public admin (String position, String URL, String Username, String Password) {
    this.setPrivilege(position);
    System.out.println("***" + this.getPrivilege() + " LOGIN***");
    Date currentDate = new Date();
        System.out.println();
        System.out.println("\n" + "Current login: " + currentDate + "\n");

    connection = new DBConnect(URL, Username, Password, privilege);
}






/**
 * Privilege member getter method
 * @return privilege String: should be "student" or "teacher"
 */
public String getPrivilege() {
    return this.privilege;
}

/**
 * Privilege setter method
 * @param userRole User inputted role - teacher or student - to set privilege data member as
 */
public void setPrivilege(String userRole) {
    //this.privilege = userRole;

    if(userRole.equals("Teacher")) {
        this.privilege = userRole;
    }
    else if(userRole.equals("teacher")) {
        String newRole = "T";

        for(int i = 1; i<userRole.length(); i++) {
            newRole += userRole.charAt(i);
        }
        this.privilege = newRole;
    }
    else if(userRole.equals("Student")) {
        this.privilege = userRole;
    }
    else if(userRole.equals("student")) {
        String newRole = "S";
        int i = 1;

        while(i<userRole.length()) {
            newRole += userRole.charAt(i);
            i++;
        }
        this.privilege = newRole;
    }
    else{
privilege = "Error";
    }

    
    

    

    
}

public String teacherActions() {

    //System.out.println();
    System.out.println("\n" + "Please select an action for teacher: " + "\n") ;
    System.out.println("(1): Register new class" + "\n" + "(2): Update grades in existing class" + "\n" + "(3): Print report card" + "\n" + "(4): Other (more later)" + "\n");

    try{
        boolean selected = false;
       // Scanner in = new Scanner(System.in);
       inScan = new Scanner(System.in);
        String selectedInput = "";
        while (!selected && inScan.hasNext()) {
           // Scanner in = new Scanner(System.in);
            selectedInput = inScan.nextLine();
            if(selectedInput.equals("1")||selectedInput.equals("2") || selectedInput.equals("3") || selectedInput.equals("4")) {
                //in.close();
                break;
            }
        }
        //in.close();
        System.out.println("\n" + "You selected : " + selectedInput);
        return selectedInput;
    }catch(Exception e) {
        e.printStackTrace();   //want better, descriptive error plus testing for errors here!
    }
    this.adminClose();
    return "error!";
}

public void teacherActions(int selection) {
    if(selection==1) {
        this.buildClass();
    }
    //else for other actions....
}

public boolean buildClass() {
    System.out.println("REACHED****");

    String className = new String();
    String assessmentNumber = "";
    System.out.println("REACHED****");
   // Scanner classInfo = new Scanner(System.in);
   //inScan = new Scanner(System.in);
   Console console = System.console();
   if(console==null) {
       
   }else{
       className = console.readLine("Enter classname: ");
       System.out.println(className);
       assessmentNumber = console.readLine("Enter assessment categories, seperated by commas (eg. quizzes, midterm, final):");
       System.out.println(assessmentNumber);            //now sanitize this by removing spaces
       this.sanitize(assessmentNumber);

   }
   /* System.out.println("\n" + "Enter name of class: ");
    //String className = "";
    try{
        while(inScan.hasNext()) {
            //System.out.println("\n" + "Enter name of class: ");
            className = inScan.nextLine();
        }
        //classInfo.close();
    }catch(Exception e) {
        e.printStackTrace();
    }*/



    return false;
}

public void adminClose() {
    inScan.close();
}

public String[] sanitize(String toSanitize) {
    int commaCount = 0;
    String assign = "";
    for(int i = 0; i<toSanitize.length(); i++) {
       // assign += toSanitize.charAt(i);
        if(toSanitize.charAt(i)==44){
            commaCount++;
        }
        else if(toSanitize.charAt(i)!= 32){
        assign += toSanitize.charAt(i);
        }
    }

    String[] assessments = new String[commaCount];          //change to linked list or stack, etc. so you can assign weight to each node... **************************

    System.out.println(commaCount + " " + assign);

    return assessments;
}
/*
*******************************************************************************************************************************
Ended here -> must seperate the String assign to be three different strings to put into each array cell
Setup linked list or stack instead of array to attach weights to each assessment
Prompt user to enter weights for each assessment (may keep intermediate array, then ask for weights which will easily transfer array to list)
Then build table in SQL with id, grade, <assessments>, attendance as columns
Then file i/o of classlist with ids to fill in ids from a txt file of ids in class
Update grade method -> requires the weights to update overall grade
File output report card for individual person






*/
}
