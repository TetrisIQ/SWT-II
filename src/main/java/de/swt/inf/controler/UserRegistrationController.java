package de.swt.inf.controler;

import de.swt.inf.database.DaoFactory;
import de.swt.inf.database.UserDao;
import de.swt.inf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
public class UserRegistrationController {
    String vn = "";
    String nn = "";
    String email = "";
    String userName = "";

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String UserRegistration(Model model) {
        model = putAllIn(model);
        return "userRegistration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String sendUserRegistration(HttpServletRequest request, Model model) throws ParseException {
        UserDao dau = DaoFactory.getUserDao();
        vn = request.getParameter("Vname");
        nn = request.getParameter("Nname");
        userName = request.getParameter("username");
        email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordconfirm");
        if (userName.contains("unerwünschterUsername") || !userName.matches(User.userNameRegex)) {
            model = putAllIn(model);
            model = createViewErros(model,false,true,false,false);
            return "userRegistration"; //TODO: Der​ ​Username​ ​darf​ ​keine​ ​rassistischen/sexistischen Ausdrücke​ ​beinhalten.​
        }
        if (dau.getUserByName(userName) != null) {
            model = putAllIn(model);
            model = createViewErros(model,false,false,true,false);
            return "userRegistration";

        }

        if (dau.isEmailInUse(email)) {
            model = putAllIn(model);
            model = createViewErros(model,true,false,false,false);
            return "userRegistration";
        }

        if (!password.equals(passwordConfirm)) {
            model = putAllIn(model);
            model = createViewErros(model, false,false,false,true);
            return "userRegistration";
        }
        if (!password.matches(User.passwordRegex)) {
            //password don't match with our password policy
            model = putAllIn(model);
            model = createViewErros(model, false, false, false,true);
            return "userRegistration";
        }
        User user = new User(userName, password, email, vn, nn);
        dau.addUser(user);

        //TODO: Email bestätigung
        //TODO: user nach 10 sec weiterleiten ??
        return "redictDashboard";
    }


    private Model createViewErros(Model model, boolean email, boolean userNameToShort, boolean userNameInUse, boolean password) {
        if (password) {
            //password is not in our policy
            model.addAttribute("passwordW", "Dein Passwort muss min 6 zeichen lang groß und kleinschreibung beinhalten und ein sonderzeichen");
        }
        if (email) {
            //email is in use or wrong
            model.addAttribute("emailW", "Diese Email adresse wird schon genutzt");
        }
        if (userNameToShort) {
            model.addAttribute("userW", "Dieser Benutzername muss min. 6 zeichen lang");
        }

        if (userNameInUse) {
            model.addAttribute("userW", "Dieser Benutzername ist schon vergeben oder ist nicht min. 6 zeichen lang");

        }

        return model;
    }

    private Model putAllIn(Model model) {
        model.addAttribute("Vname", vn);
        model.addAttribute("Nname", nn);
        model.addAttribute("email", email);
        model.addAttribute("username", userName);
        return model;
    }
}
