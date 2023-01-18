import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel loginPanel;

    public LoginForm() {
        setTitle("Rolana Hits App - Login");
        setContentPane(loginPanel);
        setLocation(300,300);
        setMinimumSize(new Dimension(600,400));
        setSize(600,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashBoard dashBoard = new DashBoard();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        setVisible(true);
    }

    private void loginUser() {
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields!",
                    "Error! Please try again!",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = getAuthenticatedUser(email,password);
        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "You are now logged in!",
                    "Welcome",
                    JOptionPane.OK_OPTION);
            dispose();
            UserDashboard userDashboard = new UserDashboard(user);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid email or password!",
                    "Error! Try again!",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public User user;

    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/rolanahits";
        final String USERNAME = "java_user";
        final String PASSWORD = "Viorrel*1";

        try {
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "select * from users where email=? and password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.fName = resultSet.getString("fname");
                user.lName = resultSet.getString("lname");
                user.phoneNo = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");
            }

            stmt.close();
            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
