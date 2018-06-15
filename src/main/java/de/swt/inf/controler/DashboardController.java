package de.swt.inf.controler;

import de.swt.inf.database.DaoFactory;
import de.swt.inf.database.TerminDao;
import de.swt.inf.model.Termin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;


import static java.time.DayOfWeek.FRIDAY;


@Controller
public class DashboardController {

    TerminDao terminDao = DaoFactory.getTerminDao();

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(HttpServletRequest request, Model model) throws ParseException {
        String calendar = request.getParameter("kalender");
        TerminDao terminDao = DaoFactory.getTerminDao();
        if (calendar != null) {
            //use this Calendar // not implemented in Prototype
        } else {  //use default calendar

            List<Termin> terminList = terminDao.getAllTermine();

            for(Termin t : terminList){

                //if(checkInCurrentWeek(t.getStart())){
                    int weekday = getWeekdayFromDate(t.getStart());

                    switch (weekday){
                        case 1 :
                            model.addAttribute("Montag",t);
                            break;

                        case 2 :
                            model.addAttribute("Dienstag",t);
                            break;

                        case 3 :
                            model.addAttribute("Mittwoch",t);
                            break;

                        case 4 :
                            model.addAttribute("Donnerstag",t);
                            break;

                        case 5 :
                            model.addAttribute("Freitag",t);
                            break;

                        case 6 :
                            model.addAttribute("Samstag",t);
                            break;

                        case 7 :
                            model.addAttribute("Sontag",t);
                            break;
                    }
               // }
            }
        }



        return "dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String viewTermin(HttpServletRequest request, Model model) {
        return "termin/ViewTermin";
    }


    private int getWeekdayFromDate(String inputDate) throws ParseException {
        String[]dateFormat = inputDate.split("-");
        String date = dateFormat[2] + "/" + dateFormat[1] + "/" + dateFormat[0];


        Date dt1=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        String finalDay=new SimpleDateFormat("EEEE").format(dt1);

        if(finalDay.equals("Montag")) return 1;
        if(finalDay.equals("Dienstag"))return 2;
        if(finalDay.equals("Mittwoch")) return 3;
        if(finalDay.equals("Donnerstag")) return 4;
        if(finalDay.equals("Freitag")) return 5;
        if(finalDay.equals("Samstag")) return 6;
        if(finalDay.equals("Sontag")) return 7;

        return 0;
    }

    /*private boolean checkInCurrentWeek(String inputDate) throws ParseException {
        LocalDate today = LocalDate.now();

        Date monday = new SimpleDateFormat("yyyy-MM-dd").parse(today.with(previousOrSame(MONDAY)).toString());
        Date sunday = new SimpleDateFormat("yyyy-MM-dd").parse(today.with(nextOrSame(SUNDAY)).toString());

        Date toCheckDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);

        return toCheckDate.compareTo(monday) >= 0 && toCheckDate.compareTo(sunday) <= 0;
    }*/

}
