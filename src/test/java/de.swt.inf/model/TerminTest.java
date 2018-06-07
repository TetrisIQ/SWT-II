package de.swt.inf.model;

import de.swt.inf.database.DaoFactory;
import de.swt.inf.database.TerminDao;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TerminTest {

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
        if (!sT.equals(eT)  && sD.equals(eD))  {
            assertFalse(Termin.isValid(sD, eD, eT, sT));
        }
    }


    @Test
    public void testDeleteWithDatabase(){
        TerminDao terminDao = new DaoFactory().getTerminDao();
        Termin termin = new Termin("Test", "22-10-17", "05-10-18", false, "10:00", "11:00");
        terminDao.addTermin(termin);

        List<Termin> terminList = new LinkedList<Termin>();
        terminList = terminDao.getAllTermine();

        Termin terminOutDatabase = new Termin();

        for(Termin t: terminList){
            if(t.getName().equals("Test")){
                terminOutDatabase = t;
                break;
            }
        }

        System.out.println(terminOutDatabase.getName());
        assertTrue(terminOutDatabase.getName().equals("Test"));

        int terminId = terminOutDatabase.getId();

        terminDao.deleteTermin(terminId);

        terminOutDatabase = terminDao.getTermin(terminId);

        assertEquals(null,terminOutDatabase);
    }

}
