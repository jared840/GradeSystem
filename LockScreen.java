import java.util.Scanner;

public class LockScreen {
    private String position;
    private boolean adminPrivilege;
    private String DBUrl;
    private String DBUsername;
    private String DBPassword;
    public static String[] validInputs = {"Student", "student", "teacher", "Teacher"};              //replace maybe with enum!
    public static Scanner identifyPosition;


    
    

    public LockScreen() {
        boolean tracker = false;
        int timeTrack = 5;

       /* do{                                                                                       //need to fix this for continous loops if incorrect input 
            System.out.println("You have " + timeTrack + " attempts left");
            tracker = this.setPosition();
            timeTrack--;

        } while (tracker!=false || timeTrack==0); */



        
        tracker = this.setPosition();
        if(!tracker) {
            System.out.println("Please Enter either 'Student' or 'Teacher'. " + "System will terminate");       //temporary termination
            System.exit(1);
        }
       
        
        System.out.println("\n" + "Your input: " +this.getPosition());              //add any form of 'student' ie. stUdENt should be read as Student and then used as such
        this.setAdminPrivilege();
        

        
        
        /*try{
        Scanner identifyPosition = new Scanner(System.in);
        System.out.println("Enter position (Student/Teacher): ");
        String inputPosition = identifyPosition.nextLine();
        //System.out.println(inputPosition);
        } catch( Exception e) {
            System.out.println("Error - program will now terminate!");
            e.printStackTrace();
            //System.exit(1);
        } */


    }



    public boolean setPosition() {
        String inputPosition = new String();
        try{
             identifyPosition = new Scanner(System.in);
            System.out.println("Enter position (Student/Teacher): ");
          //while (hasNext()????)
            inputPosition = identifyPosition.nextLine();                //use multiple nextLine to get various things and put into private vars (eg. also: jdbc url, user, pswd, name, id, etc.) -> avoids weird error message
            
            
        System.out.println("\n" + "Enter mySQL database URL (enter nothing for default URL): ");
        
        String URLIn = identifyPosition.nextLine(); 

        
           // String DBUrl;
        if(URLIn.equals("\n")||URLIn.equals("")){
            this.setDBUrl("jdbc:mysql://localhost:3306/");
           // DBUrl = "jdbc:mysql://localhost:3306/";

        }
        else {
            this.setDBUrl(URLIn);
            //DBUrl = URLIn;
        }

        System.out.println("THE URL IS: " + DBUrl);


        //retrieving DB Username from user

        System.out.println("\n" + "Enter mySQL username (entering nothing will default to 'root') : ");
        String userIn = identifyPosition.nextLine();

        if(userIn.equals("")||userIn.equals("\n")) {
            this.setUser("root");
        }
        else {
            this.setUser(userIn);
        }


        //retrieve password from user

        System.out.println("\n" + "Enter mySQL password: ") ;
        String passIn = identifyPosition.nextLine();
        this.setPassword(passIn);

        

           // identifyPosition.close();
        } catch (Exception e) {
            System.out.println("Error - program will now terminate!");          //identify errors specifially later on!
            e.printStackTrace();
            //System.exit(1);
        }
        
        boolean validInp = false;

        for(int i = 0; i<validInputs.length; i++) {
            if(inputPosition.equals(validInputs[i])) {
                validInp = true;
                break;
            }
        }

        if(validInp==true) {

           this.position =  this.firstCapital(inputPosition);

            return true;
        }
        else{
            System.out.println("Incorrect position entry. ");
            return false;
        }

    }

    public String getPosition() {
        try{
            return this.position;
        } catch (Exception e) {
            //nothing set!
        }
        return "";
    }

    public void setAdminPrivilege() {
        String pos = this.getPosition();

        if(pos.equals("Teacher")) {
            this.adminPrivilege = true;
            System.out.println("\n" + "***NOTE: YOU HAVE ADMIN ACCESS TO THIS SYSTEM***" + "\n");
            
            /*  //for now, commented out -> put back in later!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!********************


            System.out.println("Would you like to enter instead with student priviledges? (ENTER Y or N)");
            try{
                identifyPosition = new Scanner(System.in);
                while(identifyPosition.hasNext()){
                String inputted = identifyPosition.nextLine();
                

                if(inputted.equalsIgnoreCase("Y")) {
                    this.adminPrivilege = false;
                    this.resetPosition("Student");
                }
            }
            
           // identifyPosition.close();

            } catch (Exception e) {
                System.out.println("Error switching roles. Please restart system (Stack trace below)");
                e.printStackTrace();
            } */


        }
        else {
            this.adminPrivilege = false;
        }
    }

    public boolean getAdminPrivilege(){

     return this.adminPrivilege;
    }


    public void resetPosition(String role) {
        this.position = "Student";
    }


    public String firstCapital(String toCapital) {
        
        if(toCapital.charAt(0)>=97 && toCapital.charAt(0)<=122) {
            //according to ASCII placement, it is a lowercase letter
            char firstLetter = toCapital.charAt(0);
            String firstLetterString = new String();
            String letterString = firstLetterString.valueOf(firstLetter);
            letterString = letterString.toUpperCase();

            String noFirst = "";

            for(int i = 1; i<toCapital.length(); i++) {
                char letterOf = toCapital.charAt(i);

                String intermediate = new String();
                intermediate = intermediate.valueOf(letterOf);

                noFirst += intermediate;
            }

            String toReturn = letterString + noFirst;
           // firstLetter = firstLetter.toUpperCase();
           return toReturn;
        }

        return toCapital;
    }

    public void setDBUrl(String in) {
        this.DBUrl = in;
    }

    public String getURL() {
        return this.DBUrl;
    }

    public void setUser(String in) {
        this.DBUsername = in;
    }

    public String getUser() {
        return this.DBUsername;
    }

    public void setPassword(String in) {
        this.DBPassword = in;
    }

    public String getPassword () {
        return this.DBPassword;
    }

    public void closeLockScreen() {
        identifyPosition.close();
    }

}
