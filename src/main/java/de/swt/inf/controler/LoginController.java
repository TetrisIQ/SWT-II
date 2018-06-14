package de.swt.inf.controler;

import de.swt.inf.database.DaoFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;


@Controller
public class LoginController {

    public static final String MSG_USERNAME_OR_PASSWORD_WRONG = "Benutzername oder Passwort falsch!";
    public boolean login;
    private String username = "";
    private String password = "";

    public boolean loggedIn(){
        Connection con = DaoFactory.getConnection();
        String query = "SELECT * FROM user WHERE USERNAME = '"+this.username+"';";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String passwordDB = (rs.getString("PASSWORD"));
                if (passwordDB.equals(this.password)) {
                    System.out.println("Successfully LoggedIn!");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(MSG_USERNAME_OR_PASSWORD_WRONG);
        return false;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String getLoginData(HttpServletRequest request) throws ParseException {
        String logName = request.getParameter("LoginName");
        String logPassword = request.getParameter("LoginPassword");
        System.out.println("Username: " + logName);
        System.out.println("Password: " + logPassword);
        this.username = logName;
        this.password = logPassword;
        boolean success = loggedIn();;
        return success? "/dashboard" : "/login";
    }
}