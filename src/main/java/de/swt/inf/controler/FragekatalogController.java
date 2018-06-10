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

    private UserPreferences userPreference = Main.user.getUserPreferences();

    @RequestMapping(value = "/fragekatalog", method = RequestMethod.GET)
    public String fragekatalog(Model model){

        // Falls der Fragekatalog schon einmal verwendet wurde, werden die alten Preferenzen vorausgewählt
        if(userPreference.getMusicPreferencesEnum() != null){
            MusicPreferencesEnum musicSelected = userPreference.getMusicPreferencesEnum();
            model.addAttribute("musicSelected", musicSelected);
        }
        if(userPreference.getProvince() != null){
            ProvinceEnum provinceSelected = userPreference.getProvince();
            model.addAttribute("provinceSelected", provinceSelected);
        }
        if(userPreference.getCourseEnum() != null){
            CourseEnum courseSelected = userPreference.getCourseEnum();
            model.addAttribute("courseSelected", courseSelected);
        }
        if(userPreference.getGenderEnum() != null){
            GenderEnum genderSelected = userPreference.getGenderEnum();
            model.addAttribute("genderSelected", genderSelected);
        }
        if(userPreference.getUniversityEnum() != null){
            UniversityEnum universitySelected = userPreference.getUniversityEnum();
            model.addAttribute("universitySelected", universitySelected);
        }


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
        List<String> kategorien = new LinkedList<String>();

        String musicPref = request.getParameter("music");
        String genderPref = request.getParameter("gender");
        String coursePref = request.getParameter("course");
        String provincePref = request.getParameter("province");
        String universityPref = request.getParameter("university");


        kategorien.add(musicPref);
        kategorien.add(genderPref);
        kategorien.add(coursePref);
        kategorien.add(provincePref);
        kategorien.add(universityPref);

        List<Termin> termine = initTermine(kategorien);
        for(Termin t : termine){
            terminDao.addTermin(t);
        }

        return "redictDashboard";
    }


    private List<Termin> initTermine(List<String> kategorien){
        List<Termin> tempTermine = new LinkedList<Termin>();
        Termin temp;


        for(String kategorie : kategorien) {
            if(kategorie.length() > 0) {
                if ("Rock".equals(kategorie)) {
                    if(userPreference.getMusicPreferencesEnum() != MusicPreferencesEnum.Rock){
                        temp = new Termin("Rock am Ring", "2018-06-01", "2018-06-03", true,
                                "10:00", "22:00");
                        tempTermine.add(temp);
                        userPreference.setMusic(MusicPreferencesEnum.Rock);
                    }
                } else if ("RnB".equals(kategorie)) {
                    if(userPreference.getMusicPreferencesEnum() != MusicPreferencesEnum.RnB){
                        temp = new Termin("Splash! Festival", "2018-07-06", "2018-07-08", true,
                                "10:00", "22:00");
                        tempTermine.add(temp);
                        userPreference.setMusic(MusicPreferencesEnum.RnB);
                    }
                } else if ("EDM".equals(kategorie)) {
                    if(userPreference.getMusicPreferencesEnum() != MusicPreferencesEnum.EDM){
                        temp = new Termin("Deichbrand Festival", "2018-07-19", "2018-07-22", true,
                                "10:00", "22:00");
                        tempTermine.add(temp);
                        userPreference.setMusic(MusicPreferencesEnum.EDM);
                    }
                } else if ("Pop".equals(kategorie)) {
                    if(userPreference.getMusicPreferencesEnum() != MusicPreferencesEnum.Pop){
                        temp = new Termin("Haldern Pop", "2018-08-09", "2018-08-11", true,
                                "13:00", "21:30");
                        tempTermine.add(temp);
                        userPreference.setMusic(MusicPreferencesEnum.Pop);
                    }
                } else if ("Informatik_I".equals(kategorie)) {
                    if(userPreference.getCourseEnum() != CourseEnum.Informatik_I){
                        temp = new Termin("Informatik_I", "2018-06-11", "2018-06-11", false,
                                "08:15", "09:45");
                        tempTermine.add(temp);
                        userPreference.setCourse(CourseEnum.Informatik_I);
                    }
                } else if ("Informatik_II".equals(kategorie)) {
                    if(userPreference.getCourseEnum() != CourseEnum.Informatik_II){
                        temp = new Termin("Informatik_II", "2018-06-11", "2018-06-11", false,
                                "10:00", "11:30");
                        tempTermine.add(temp);
                        userPreference.setCourse(CourseEnum.Informatik_II);
                    }
                } else if ("Softwaretechnik_I".equals(kategorie)) {
                    if(userPreference.getCourseEnum() != CourseEnum.Softwaretechnik_I){
                        temp = new Termin("Softwaretechnik_I", "2018-06-11", "2018-06-11", false,
                                "12:00", "13:30");
                        tempTermine.add(temp);
                        userPreference.setCourse(CourseEnum.Softwaretechnik_I);
                    }
                } else if ("Softwaretechnik_II".equals(kategorie)) {
                    if(userPreference.getCourseEnum() != CourseEnum.Softwaretechnik_II){
                        temp = new Termin("Softwaretechnik_II", "2018-06-11", "2018-06-11", false,
                                "14:30", "16:00");
                        tempTermine.add(temp);
                        userPreference.setCourse(CourseEnum.Softwaretechnik_II);
                    }
                } else if ("Female".equals(kategorie)) {
                    if(userPreference.getGenderEnum() != GenderEnum.Female){
                        temp = new Termin("Weltfrauentag", "2019-03-08", "2019-03-08", true,
                                "01:00", "23:59");
                        tempTermine.add(temp);
                        userPreference.setGender(GenderEnum.Female);
                    }
                } else if ("Male".equals(kategorie)) {
                    if(userPreference.getGenderEnum() != GenderEnum.Male){
                        temp = new Termin("Herrentag", "2019-05-30", "2019-05-30", true,
                                "01:00", "23:59");
                        tempTermine.add(temp);
                        userPreference.setGender(GenderEnum.Male);
                    }
                } else if ("Intersexuell".equals(kategorie)) {
                    if(userPreference.getGenderEnum() != GenderEnum.Intersexuell){
                        temp = new Termin("Welttag der Intersexualität", "2018-08-26", "2018-08-26", true,
                                "01:00", "23:59");
                        tempTermine.add(temp);
                        userPreference.setGender(GenderEnum.Intersexuell);
                    }
                } else if ("Schleswig_Holstein".equals(kategorie)) {
                    if(userPreference.getProvince() != ProvinceEnum.Schleswig_Holstein){
                        temp = new Termin("Schleswig Holstein", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setProvince(ProvinceEnum.Schleswig_Holstein);
                    }
                } else if ("Hansestadt_Hamburg".equals(kategorie)) {
                    if(userPreference.getProvince() != ProvinceEnum.Hansestadt_Hamburg){
                        temp = new Termin("Hansestadt Hamburg", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setProvince(ProvinceEnum.Hansestadt_Hamburg);
                    }
                } else if ("Mecklenburg_Vorpommern".equals(kategorie)) {
                    if(userPreference.getProvince() != ProvinceEnum.Mecklenburg_Vorpommern){
                        temp = new Termin("Mecklenburg Vorpommern", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setProvince(ProvinceEnum.Mecklenburg_Vorpommern);
                    }
                } else if ("FH_Luebeck".equals(kategorie)) {
                    if(userPreference.getUniversityEnum() != UniversityEnum.FH_Luebeck){
                        temp = new Termin("FH Luebeck", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setUniversity(UniversityEnum.FH_Luebeck);
                    }
                } else if ("Uni_Luebeck".equals(kategorie)) {
                    if(userPreference.getUniversityEnum() != UniversityEnum.Uni_Luebeck){
                        temp = new Termin("Uni Luebeck", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setUniversity(UniversityEnum.Uni_Luebeck);
                    }
                } else if ("Uni_Hamburg".equals(kategorie)) {
                    if(userPreference.getUniversityEnum() != UniversityEnum.Uni_Hamburg){
                        temp = new Termin("Uni Hamburg", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setUniversity(UniversityEnum.Uni_Hamburg);
                    }
                } else if ("FH_Hamburg".equals(kategorie)) {
                    if(userPreference.getUniversityEnum() != UniversityEnum.FH_Hamburg){
                        temp = new Termin("FH Hamburg", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setUniversity(UniversityEnum.FH_Hamburg);
                    }
                } else if ("FH_Wismar".equals(kategorie)) {
                    if(userPreference.getUniversityEnum() != UniversityEnum.FH_Wismar){
                        temp = new Termin("FH Wismar", "2018-08-02", "2018-08-02", false,
                                "07:00", "08:00");
                        tempTermine.add(temp);
                        userPreference.setUniversity(UniversityEnum.FH_Wismar);
                    }
                }
            }
        }

        return tempTermine;
    }



}
