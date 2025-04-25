import javax.swing.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public LoginFrame() {
        // Add components, layout, listeners
        loginButton.addActionListener(e -> authenticate());
    }

    private void authenticate() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            pst.setString(1, usernameField.getText());
            pst.setString(2, new String(passwordField.getPassword()));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                new Dashboard().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
