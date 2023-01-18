import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard extends JFrame{
    private JPanel userDashboardPanel;
    private JLabel welcome;
    private JButton btnLogout;
    private JLabel lFirstName;
    private JLabel lLastName;
    private JLabel lPhone;
    private JLabel lAddress;
    private JLabel lEmail;
    private JLabel lPassword;

    public UserDashboard(User user){
        setTitle("Rolana Hits App - My DashBoard");
        setContentPane(userDashboardPanel);
        setLocation(300,300);
        setMinimumSize(new Dimension(600,400));
        setSize(600,400);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        welcome.setText("Welcome, " + user.fName);
        lFirstName.setText(user.fName);
        lLastName.setText(user.lName);
        lPhone.setText(user.phoneNo);
        lAddress.setText(user.address);
        lEmail.setText(user.email);
        lPassword.setText(user.password);
        setVisible(true);

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashBoard dashBoard = new DashBoard();
            }
        });

    }
}
