package de.swt.inf.controler;

import de.swt.inf.database.*;
import de.swt.inf.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Controller
public class FragekatalogController {

    public static UserPreferences userPreference;

    @RequestMapping(value = "/fragekatalog", method = RequestMethod.GET)
    public String fragekatalog(Model model){
        //Testeingabe UserPreferences
        /*UserPreferences userPreferences = user.getUserPreferences();
        userPreferences.setMusic(MusicPreferencesEnum.RnB);

        MusicPreferencesEnum musicPref = MusicPreferencesEnum.valueOf(user.getUserPreferences().getMusicPreferencesEnum());
        model.addAttribute("musicPref", musicPref);*/

        //Enums einlesen
        List<MusicPreferencesEnum> musicEnums = Arrays.asList(MusicPreferencesEnum.values());
        List<GenderEnum> genderEnums = Arrays.asList(GenderEnum.values());
        List<CourseEnum> courseEnums = Arrays.asList(CourseEnum.values());
        List<ProvinceEnum> provinceEnums = Arrays.asList(ProvinceEnum.values());
        List<UniversityEnum> universityEnums = Arrays.asList(UniversityEnum.values());


        //Enums dem Model hinzufügen
        model.addAttribute("music", musicEnums);
        model.addAttribute("gender", genderEnums);
        model.addAttribute("course", courseEnums);
        model.addAttribute("province", provinceEnums);
        model.addAttribute("university", universityEnums);
        return "fragekatalog";
    }

    @RequestMapping(value = "/fragekatalog", method = RequestMethod.POST)
    public String addFragekatalog(HttpServletRequest request, Model model){
        TerminDao terminDao = DaoFactory.getTerminDao();

        String musicPref = request.getParameter("music");
        String genderPref = request.getParameter("gender");
        String coursePref = request.getParameter("course");
        String provincePref = request.getParameter("province");
        String universityPref = request.getParameter("university");

        List<String> kategorien = new LinkedList<String>();
        kategorien.add(musicPref);
        kategorien.add(genderPref);
        kategorien.add(coursePref);
        kategorien.add(provincePref);
        kategorien.add(universityPref);

        for(String s : kategorien){
            List<Termin> temp = initTermine(s);
            for(Termin t : temp){
                terminDao.addTermin(t);
            }
        }

        return "redictDashboard";
    }


    private List<Termin> initTermine(String kategorie){
        List<Termin> tempTermine = new LinkedList<Termin>();
        Termin temp;


        if("Rock".equals(kategorie)){
            temp = new Termin("Rock am Ring", "2018-06-01", "2018-06-03", true,
                    "10:00", "22:00");
            tempTermine.add(temp);
        } else if("RnB".equals(kategorie)){
            temp = new Termin("Splash! Festival", "2018-07-06", "2018-07-08", true,
                    "10:00", "22:00");
            tempTermine.add(temp);
        } else if("EDM".equals(kategorie) || "Pop".equals(kategorie)){
            temp = new Termin( "Deichbrand Festival", "2018-07-19", "2018-07-22", true,
                    "10:00", "22:00");
            tempTermine.add(temp);
        }

        return tempTermine;
    }

    private void initEnums(){

    }
}
