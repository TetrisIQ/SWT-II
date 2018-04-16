package de.swt.inf.model;

import de.swt.inf.model.Termin;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class TestTermin {

    @Test
    @Parameters({"22-10-17, 22-10-17, 10:00, 11:00"})
    public void TestIsValid(final String sD, final String eD, final String sT, final String eT) {
        assertFalse(Termin.isValid(eD,sD,sT,eT));
        assertTrue(Termin.isValid(sD, eD, sT, eT));
    }

}
