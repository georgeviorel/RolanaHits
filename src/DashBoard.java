import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoard extends JFrame{
    private JButton btnLogin;
    private JButton btnRegistration;
    private JButton btnCloseApp;
    private JPanel dashboardPanel;

    public DashBoard() {
        setTitle("Rolana Hits App");
        setContentPane(dashboardPanel);
        setLocation(300,300);
        setMinimumSize(new Dimension(600,400));
        setSize(600,400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnCloseApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);

        btnRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationForm registrationForm = new RegistrationForm();
                dispose();
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        DashBoard myForm = new DashBoard();
    }
}
