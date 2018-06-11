package de.swt.inf.controler;

import de.swt.inf.database.*;
import de.swt.inf.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Calendar;

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
        List<Termin> alteTermine = terminDao.getAllTermine();
        List<Termin> neueTermine = new LinkedList<Termin>();
        Map<Integer, String> allSelections = new HashMap<Integer, String>();

        //MusicPreferences
        allSelections.put(0, request.getParameter("music"));

        //GenderPreferences
        allSelections.put(1, request.getParameter("gender"));

        //CoursePreferences
        allSelections.put(2, request.getParameter("course"));

        //ProvincePreferences
        allSelections.put(3, request.getParameter("province"));

        //UniversityPreferences
        allSelections.put(4, request.getParameter("university"));

        int i = 0;
        for(Map.Entry e : allSelections.entrySet()){
            if(!e.getValue().equals("")){
                switch (i){
                    //MusicPreferences
                    case 0:
                        userPreference.setMusic(MusicPreferencesEnum.valueOf(e.getValue().toString()));
                        neueTermine.addAll(loadTermine(i));
                        break;

                    //GenderPreferences
                    case 1:
                        userPreference.setGender(GenderEnum.valueOf(e.getValue().toString()));
                        neueTermine.addAll(loadTermine(i));
                        break;

                    //CoursePreferences
                    case 2:
                        userPreference.setCourse(CourseEnum.valueOf(e.getValue().toString()));
                        neueTermine.addAll(loadTermine(i));
                        break;

                    //ProvincePreferences
                    case 3:
                        userPreference.setProvince(ProvinceEnum.valueOf(e.getValue().toString()));
                        neueTermine.addAll(loadTermine(i));
                        break;

                    //UniversityPreferences
                    case 4:
                        userPreference.setUniversity(UniversityEnum.valueOf(e.getValue().toString()));
                        neueTermine.addAll(loadTermine(i));
                        break;
                }
            }
            i++;
        }

        //Alle Termine, die noch nicht im Karl Ender vorhanden sind, werden nun hinzugefügt.
        boolean found = false;
        if(neueTermine != null){
            for(Termin tNeu : neueTermine){
                for(Termin tAlt : alteTermine){
                    if( (tNeu.getName().equals(tAlt.getName())) && (tNeu.getStart().equals(tAlt.getStart())) &&
                            (tNeu.getEnd().equals(tAlt.getEnd()))){
                        found = true;
                    }
                }
                if(!found){
                    terminDao.addTermin(tNeu);
                }else{
                    found = false;
                }
            }
        }




        return "redictDashboard";
    }


    private List<Termin> loadTermine(int i){
        Termin temp;
        List<Termin> neueTermine = new LinkedList<Termin>();
        Calendar calendar = new GregorianCalendar();
        int aktuellesJahr = calendar.get(Calendar.YEAR);


        switch (i) {
            //MusicPreferences
            case 0:
                MusicPreferencesEnum tempMusic = userPreference.getMusicPreferencesEnum();
                switch (tempMusic) {
                    case EDM:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Deichbrand Festival", "2018-07-19", "2018-07-22", true,
                                        "10:00", "22:00");
                                neueTermine.add(temp);

                            case 2019:
                                temp = new Termin("Deichbrand Festival", "2019-07-19", "2019-07-22", true,
                                        "11:00", "23:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case Pop:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Haldern Pop", "2018-08-09", "2018-08-11", true,
                                        "13:00", "21:30");
                                neueTermine.add(temp);

                            case 2019:
                                temp = new Termin("Haldern Pop", "2019-08-09", "2019-08-11", true,
                                        "14:00", "22:30");
                                neueTermine.add(temp);
                        }
                        break;

                    case RnB:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Splash! Festival", "2018-07-06", "2018-07-08", true,
                                        "10:00", "22:00");
                                neueTermine.add(temp);

                            case 2019:
                                temp = new Termin("Splash! Festival", "2019-07-06", "2019-07-08", true,
                                        "11:00", "23:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case Rock:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Rock am Ring", "2018-06-01", "2018-06-03", true,
                                        "10:00", "22:00");
                                neueTermine.add(temp);

                            case 2019:
                                temp = new Termin("Rock am Ring", "2019-06-01", "2019-06-03", true,
                                        "11:00", "23:00");
                                neueTermine.add(temp);
                        }
                        break;
                }
                break;

            //GenderPreferences
            case 1:
                GenderEnum tempGender = userPreference.getGenderEnum();
                switch (tempGender) {
                    case Female:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Weltfrauentag", "2018-03-08", "2018-03-08", true,
                                        "01:00", "22:59");
                                neueTermine.add(temp);
                            case 2019:
                                temp = new Termin("Weltfrauentag", "2019-03-08", "2019-03-08", true,
                                        "02:00", "23:59");
                                neueTermine.add(temp);
                        }
                        break;

                    case Male:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Herrentag", "2018-05-30", "2018-05-30", true,
                                        "01:00", "22:59");
                                neueTermine.add(temp);
                            case 2019:
                                temp = new Termin("Herrentag", "2019-05-30", "2019-05-30", true,
                                        "02:00", "23:59");
                                neueTermine.add(temp);
                        }
                        break;

                    case Intersexuell:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Welttag der Intersexualität", "2018-08-26", "2018-08-26", true,
                                        "01:00", "23:59");
                                neueTermine.add(temp);
                        }
                        break;
                }
                break;

            //CoursePreferences
            case 2:
                CourseEnum tempCourse = userPreference.getCourseEnum();
                switch (tempCourse) {
                    case Informatik_I:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Informatik_I", "2018-06-11", "2018-06-11", false,
                                        "08:15", "09:45");
                                neueTermine.add(temp);
                        }
                        break;

                    case Informatik_II:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Informatik_II", "2018-06-11", "2018-06-11", false,
                                        "10:00", "11:30");
                                neueTermine.add(temp);
                        }
                        break;

                    case Softwaretechnik_I:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Softwaretechnik_I", "2018-06-11", "2018-06-11", false,
                                        "12:00", "13:30");
                                neueTermine.add(temp);
                        }
                        break;

                    case Softwaretechnik_II:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Softwaretechnik_II", "2018-06-11", "2018-06-11", false,
                                        "14:30", "16:00");
                                neueTermine.add(temp);
                        }
                        break;
                }
                break;

            //ProvincePreferences
            case 3:
                ProvinceEnum tempProvince = userPreference.getProvince();
                switch (tempProvince) {
                    case Schleswig_Holstein:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Schleswig Holstein", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case Hansestadt_Hamburg:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Hansestadt Hamburg", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case Mecklenburg_Vorpommern:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Mecklenburg Vorpommern", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;
                }
                break;

            //UniversityPreferences
            case 4:
                UniversityEnum tempUniversity = userPreference.getUniversityEnum();
                switch (tempUniversity) {
                    case FH_Luebeck:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("FH Luebeck", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case Uni_Luebeck:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Uni Luebeck", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case FH_Hamburg:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("FH Hamburg", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case Uni_Hamburg:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("Uni Hamburg", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;

                    case FH_Wismar:
                        switch (aktuellesJahr) {
                            case 2018:
                                temp = new Termin("FH Wismar", "2018-08-02", "2018-08-02", false,
                                        "07:00", "08:00");
                                neueTermine.add(temp);
                        }
                        break;
                }
                break;
        }


        return neueTermine;
    }



}
