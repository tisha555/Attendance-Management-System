import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public @WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                res.sendRedirect("dashboard.html");
            } else {
                res.getWriter().print("Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 {
    
}
