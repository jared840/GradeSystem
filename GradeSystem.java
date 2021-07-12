public class GradeSystem {
    public static void main(String[] args) throws Exception {
        LockScreen firstLock = new LockScreen();
        Boolean teacherBool = false;

      try{
        if(firstLock.getPosition().equals("Student")){
            teacherBool = false;

        }
        else if (firstLock.getPosition().equals("Teacher")) {
            teacherBool = true;
        }
      } catch (Exception e) {                                                                                       // create unique exeption maybe????
          System.out.println("Error in fetching position. Check that only teacher or student was entered");
          e.printStackTrace();
      }

        //UNCOMMENT THIS OUT AFTER !!!!!!!!!
        /*

      Student s1;
      Teacher t1;

      if(teacherBool) {
         t1 = new Teacher(); 
      }
      else{
          s1 = new Student(firstLock.getURL(), firstLock.getUser(), firstLock.getPassword());
      } */

      admin newAdmin = new admin(firstLock.getPosition(), firstLock.getURL(), firstLock.getUser(), firstLock.getPassword());

      if(teacherBool) {
          String action = newAdmin.teacherActions();

          if(action.equals("1")) {
              newAdmin.teacherActions(1);
          }
      }


      //admin newAdmin = new admin(firstLock.getPosition());

     // admin newAdmin = new admin(firstLock.getPosition());
        
    }  
}
