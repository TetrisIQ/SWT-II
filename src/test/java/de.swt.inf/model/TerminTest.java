package de.swt.inf.model;

import de.swt.inf.database.DaoFactory;
import de.swt.inf.database.TerminDao;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TerminTest {

    @BeforeClass
    public static void beforeClass(){
        BeforSelenium.beforSeleniumTests();
        DaoFactory.test = true;
    }

    @AfterClass
    public static void afterClass(){
        DaoFactory.test = false;
    }

    @Test
    @Parameters({"22-10-17, 23-10-17, 10:00, 11:00", "22-10-17, 22-10-17, 10:00, 11:00"})
    public void TestIsValid(final String sD, final String eD, final String sT, final String eT) {
        //Test Day
        assertTrue(Termin.isValid(sD, eD, sT, eT));
        if (!sD.equals(eD)) {
            assertFalse(Termin.isValid(eD, sD, sT, eT));
        }
        //assertFalse(true);

        //Test Time
        assertTrue(Termin.isValid(sD, eD, sT, eT));
        if (!sT.equals(eT) && sD.equals(eD)) {
            assertFalse(Termin.isValid(sD, eD, eT, sT));
        }
    }

    /**
     * Die Idee, <br>
     * wir gucken uns die Links auf dem Dashboard an, von denen einer ein valider link zu einem Termin ist <br>
     * Dann holen wir uns aus diesem Link die ID des Termins um ihn dann über das delete statment zu löschen <br>
     * Anschließend sollte dieser termin nicht mehr im Dashboard vorhanden sein <br>
     * Dazu durchlaufen wir alle links und überprüfen nach dieser ID
     */
    @Test
    public void seleniumTestDelete() {
        DaoFactory.getTerminDao().addTermin(new Termin("J-Unit Test Termin","22-10-17","22-10-17",false,"13:00","14:00"));
        WebDriver driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:8080/dashboard");
        List<WebElement> ls = driver.findElements(By.tagName("a"));
        String id = "";
        int linksBefor = ls.size();
        //find a Id from a Termin in Dashboard
        for (WebElement e : ls) {
            if (e.toString().contains("/edit")) {
                //link for editing a Termin
                String s = e.toString().substring(9, e.toString().length() - 2);
                id = s.substring(9);
                String uri = "http://localhost:8080/delete?id=" + id;
                driver.navigate().to(uri);
                break;
            }
        }
        driver.navigate().to("http://localhost:8080/dashboard");
        ls = driver.findElements(By.tagName("a"));
        System.out.println(ls);
        for (WebElement e : ls) {
            if (e.toString().contains("/edit") && e.toString().contains(id)) {
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    public void testDeleteWithDatabase() {
        TerminDao terminDao = new DaoFactory().getTerminDao();
        Termin termin = new Termin("Test", "22-10-17", "05-10-18", false, "10:00", "11:00");
        terminDao.addTermin(termin);

        List<Termin> terminList = new LinkedList<Termin>();
        terminList = terminDao.getAllTermine();

        Termin terminOutDatabase = new Termin();

        for (Termin t : terminList) {
            if (t.getName().equals("Test")) {
                terminOutDatabase = t;
                break;
            }
        }

        System.out.println(terminOutDatabase.getName());
        assertTrue(terminOutDatabase.getName().equals("Test"));

        int terminId = terminOutDatabase.getId();

        terminDao.deleteTermin(terminId);

        terminOutDatabase = terminDao.getTermin(terminId);

        assertEquals(null, terminOutDatabase);
    }

    @Test
    public void testRepeatTerminStuendlich(){
        int count = 0;
        int hour = 10;
        boolean wrongTermin;
        TerminDao terminDao = new DaoFactory().getTerminDao();

        Termin termin = new Termin("Repeat Test", "2018-06-09", "2018-06-09",
                false, "10:00", "11:30");
        termin.setRepeat(true);
        termin.setRepeatTime("stündlich");
        terminDao.addTermin(termin);
        System.out.println(termin.getStartTime());
        for(Termin t : terminDao.getAllTermine()){
            System.out.println("Test");
            if(t.getName() == "Repeat Test"){
                String h = hour + ":00";
                System.out.println("Stunde: " + h);
                System.out.println("Termin: " + t.getStartTime());
                if(t.getStartTime().equals(h)){
                    count++;
                    hour++;
                }else{
                    wrongTermin = true;
                }
            }
        }
        wrongTermin = false;
        assertFalse(wrongTermin);
    }
}
