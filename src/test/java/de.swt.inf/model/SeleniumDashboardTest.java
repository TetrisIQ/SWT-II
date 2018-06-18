package de.swt.inf.model;

import de.swt.inf.database.DaoFactory;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class SeleniumDashboardTest {
    private Calendar c;
    private DateFormat df;
    private WebDriver driver;
    private WebElement element;
    private String day;
    private String monat;

    @BeforeClass
    public static void beforeClass(){
        DaoFactory.test = true;
        BeforSelenium.beforSeleniumTests();
    }

    @AfterClass
    public static void afterClass(){
        DaoFactory.test = false;
    }

    @Before
    public void addTermin(){
        c = Calendar.getInstance();
        df = new SimpleDateFormat("yyyy-MM-dd");
        driver = new HtmlUnitDriver();

        day = df.format(c.getTime()).substring(8);
        monat = df.format(c.getTime()).substring(5,7);

        driver.navigate().to("http://localhost:8080/dashboard");

        WebElement elementKlick = driver.findElement(By.linkText("Neuer Termin"));
        elementKlick.click();

        element = driver.findElement(By.name("name"));
        element.sendKeys("Vorlesung");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("13:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys(df.format(c.getTime()));
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
        element.sendKeys(df.format(c.getTime()));
        //Termin speichern
        element = driver.findElement(By.name("speichern"));
        element.click();

        assertEquals("http://localhost:8080/dashboard/woche", driver.getCurrentUrl());
        assertEquals("Vorlesung",driver.findElement(By.linkText("Vorlesung")).getText());
    }

    @Test
    public void dashboardWocheSeleniumTest(){
        element = driver.findElement(By.linkText("Nächste Woche"));
        element.click();

        boolean present;
        try {
            driver.findElement(By.linkText("Vorlesung"));
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        assertEquals(false, present);

        element = driver.findElement(By.linkText("letzte Woche"));
        element.click();

        assertEquals("Vorlesung", driver.findElement(By.linkText("Vorlesung")).getText());

        element = driver.findElement(By.linkText("letzte Woche"));
        element.click();

        try {
            driver.findElement(By.linkText("Vorlesung"));
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        assertEquals(false, present);

        element = driver.findElement(By.linkText("Nächste Woche"));
        element.click();

        assertEquals("Vorlesung", driver.findElement(By.linkText("Vorlesung")).getText());
    }


    @Test
    public void dashboardMonatSeleniumTest(){
        driver.navigate().to("http://localhost:8080/dashboard/monat");
        assertEquals("http://localhost:8080/dashboard/monat", driver.getCurrentUrl());

        element = driver.findElement(By.linkText(day));
        element.click();

        assertEquals("http://localhost:8080/dashboard/tag?datum=" + day, driver.getCurrentUrl());
        assertEquals("Vorlesung",driver.findElement(By.linkText("Vorlesung")).getText());
    }

    @Test
    public void dashboardJahrSeleniumTest(){
        driver.navigate().to("http://localhost:8080/dashboard/jahr");

        assertEquals("http://localhost:8080/dashboard/jahr", driver.getCurrentUrl());

        DateFormat dateFormat = new SimpleDateFormat("MMMMMMMM");
        String month = dateFormat.format(c.getTime());

        element = driver.findElement(By.linkText(month));
        element.click();

        assertEquals("http://localhost:8080/dashboard/monat?monat="+ Integer.parseInt(monat), driver.getCurrentUrl());

        element = driver.findElement(By.linkText(day));
        element.click();

        assertEquals("http://localhost:8080/dashboard/tag?datum=" + day, driver.getCurrentUrl());
        assertEquals("Vorlesung",driver.findElement(By.linkText("Vorlesung")).getText());
    }

    @Test
    public void dashboardTagSeleniumTest(){
        driver.navigate().to("http://localhost:8080/dashboard/tag");

        assertEquals("http://localhost:8080/dashboard/tag", driver.getCurrentUrl());
        assertEquals("Vorlesung",driver.findElement(By.linkText("Vorlesung")).getText());
    }
}
