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
        //model = putAllIn(model);
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
        //Teste Passwörter auf gleichheit
        if (!password.equals(passwordConfirm)) {
            model = putAllIn(model);
            model = createViewErros(model, false,false,false,false,true);
            return "userRegistration";
        }
        //Teste Passwort nach /LFR01/Passwortregeln
        if (!(password.matches(User.passwordRegex))) {
            //password don't match with our password policy
            model = putAllIn(model);
            model = createViewErros(model, false, false, false,true,false);
            return "userRegistration";
        }

        //Teste Benutzername nach   /LFR02/Usernamenregel
        if (userName.contains("unerwünschterUsername") || !userName.matches(User.userNameRegex)) {
            model = putAllIn(model);
            model = createViewErros(model,false,true,false,false, false);
            return "userRegistration"; //TODO: Der​ ​Username​ ​darf​ ​keine​ ​rassistischen/sexistischen Ausdrücke​ ​beinhalten.​
        }
        //Teste ob Benutzername schon vorhanden
        if (dau.getUserByName(userName) != null) {
            model = putAllIn(model);
            model = createViewErros(model,false,false,true,false, false);
            return "userRegistration";

        }
        //Teste ob Email schon vorhanden
        if (dau.isEmailInUse(email)) {
            model = putAllIn(model);
            model = createViewErros(model,true,false,false,false, false);
            return "userRegistration";
        }
        //Schreibe Benutzer in Datenbank
        User user = new User(userName, password, email, vn, nn);
        dau.addUser(user);

        //TODO: Email bestätigung
        //Weiterleitung nach 10 sekunden nach   /LF020/Benutzerregistrierung
        //10 sekunden ist ein wenig lange steht aber im Lastenheft so drinn
        /*try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "redictDashboard";
    }


    /**
     * Zeigt Error nachrichten bei Falsch eingegebenen Parametern an.
     * @param model Arbeits Model
     * @param email {@link Boolean} ob die Email schon genutzt wird
     * @param userNameToShort {@link Boolean} ob der Benutzername zu Kurz ist
     * @param userNameInUse {@link Boolean} ob der Benutzername schon Vergeben ist
     * @param password {@link Boolean} ob das Passwort nicht den Richtlienen entspricht
     * @return Model mit den entsprechenen Fehlernachrichten
     */
    private Model createViewErros(Model model, boolean email, boolean userNameToShort, boolean userNameInUse, boolean password, boolean passwordNotMatch) {
        if (passwordNotMatch) {
            model.addAttribute("passwordW", "Deine Passwörter stimmen nicht überein");
        }

        if (password) {
            //password entspricht nicht den richtlinien
            model.addAttribute("passwordW", "Dein Passwort muss min 6 zeichen lang groß und kleinschreibung beinhalten und ein sonderzeichen");
        }
        if (email) {
            //email wird schon verwendet
            model.addAttribute("emailW", "Diese Email adresse wird schon genutzt");
        }
        if (userNameToShort) {
            //Benutzername ist zu kurz
            model.addAttribute("userW", "Dein Benutzername muss min. 6 zeichen lang");
        }

        if (userNameInUse) {
            //Benutzername ist schon vergeben
            model.addAttribute("userW", "Dieser Benutzername ist schon vergeben");
        }

        return model;
    }

    /**
     * Fügt alle Felder mit ausnahme des Passwortfeldes in das Model ein
     * @param model Model
     * @return Model mit gefüllten feldern
     */
    private Model putAllIn(Model model) {
        model.addAttribute("Vname", vn);
        model.addAttribute("Nname", nn);
        model.addAttribute("email", email);
        model.addAttribute("username", userName);
        return model;
    }
}
