import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JFrame{
    private JPanel registrationPanel;
    private JButton btnRegister;
    private JButton btnCancel;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;


    public RegistrationForm() {
        setTitle("Rolana Hits App - Registration");
        setContentPane(registrationPanel);
        setLocation(300,300);
        setMinimumSize(new Dimension(600,700));
        setSize(600,700);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashBoard myForm = new DashBoard();
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fName = tfFirstName.getText();
        String lName = tfLastName.getText();
        String phoneNo = tfPhone.getText();
        String address = tfAddress.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

        if (fName.isEmpty() || lName.isEmpty() || phoneNo.isEmpty() || address.isEmpty() || email.isEmpty()|| password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields!",
                    "Error! Try again!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Confirm Password does not match!",
                    "Error! Try Again!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        user = addUserToDatabase(fName,lName,phoneNo,address,email,password);
        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "User Registration Successful!\nNow you can login using your email and password!",
                    "Congratulations!",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "User Registration Failed!",
                    "Error! Try Again!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String fName, String lName, String phoneNo, String address, String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/rolanahits";
        final String USERNAME = "java_user";
        final String PASSWORD = "Viorrel*1";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "insert into users (fname, lname, phone, address, email, password) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, fName);
            preparedStatement.setString(2, lName);
            preparedStatement.setString(3, phoneNo);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);

            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0) {
                user = new User();
                user.fName = fName;
                user.lName = lName;
                user.phoneNo = phoneNo;
                user.address = address;
                user.email = email;
                user.password = password;
            }

            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        DashBoard myForm = new DashBoard();
        return user;
    }
}
