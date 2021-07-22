import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;



public class loginGUI implements ActionListener {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel id;
    private static JLabel pswrd;
    private static JTextField usernameEntry;
    private static JPasswordField passwordEntry;
    private static JButton button;
    private static JLabel successfulLogin;

    private static Map myHashMap = new HashMap();       //HashMap<String, String>

    public loginGUI() {
        panel = new JPanel();
        frame = new JFrame("Login");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        

        id = new JLabel("ID: ");
        id.setBounds(10, 20, 80, 25);
        panel.add(id);

        usernameEntry = new JTextField();
        usernameEntry.setBounds(100, 20, 165, 25);
        panel.add(usernameEntry);

        pswrd = new JLabel("Password: ") ;
        pswrd.setBounds(10, 50, 80, 25);
        panel.add(pswrd);

        

        passwordEntry = new JPasswordField();
        passwordEntry.setBounds(100, 50, 165, 25);
        panel.add(passwordEntry);

        button  = new JButton("Login");
        button.setBounds(200, 150, 100, 100);
        button.addActionListener(this);
        panel.add(button);

        successfulLogin = new JLabel();
        successfulLogin.setBounds(30, 250, 175, 185);
        panel.add(successfulLogin);

        frame.setVisible(true);
    }


/*
    public static void main(String[] args) throws Exception {
        loginGUI firstGUI = new loginGUI();
    }*/



    @Override
    public void actionPerformed(ActionEvent e) {
      
        String usernameEntered = usernameEntry.getText();
        String passWordEntered = "";       //deprecated! change to getpassword()
        char[] passwordEntered = passwordEntry.getPassword();
        for(int i = 0; i<passwordEntered.length; i++) {
            passWordEntered += passwordEntered[i];
        }

        if(myHashMap.containsKey(usernameEntered)==false) {
            successfulLogin.setText("user ID not registered");
            
        }
        else {
            if(myHashMap.get(usernameEntered).equals(passWordEntered)){
                    //successful login
                    successfulLogin.setText("Login success");
            }
            else{
                //unsuccessful login
                successfulLogin.setText("Login unsuccessful");
            }
        }

        
    }
}
