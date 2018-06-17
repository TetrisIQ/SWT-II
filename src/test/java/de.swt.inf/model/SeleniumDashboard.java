package de.swt.inf.model;

import de.swt.inf.database.DaoFactory;
<<<<<<< Updated upstream
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
=======
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
>>>>>>> Stashed changes

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class SeleniumDashboard {

    @BeforeClass
    public static void beforeClass(){
        DaoFactory.test = true;
        BeforSelenium.beforSeleniumTests();
    }

    @AfterClass
    public static void afterClass(){
        DaoFactory.test = false;
    }

    @Test
<<<<<<< Updated upstream
    public void wochenAnsicht(){
        /*WebDriver driver = new HtmlUnitDriver();
        Calendar calendar = calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        WebElement element;
        driver.navigate().to("http://localhost:8080/dashboard/woche");
        WebElement NeuerTerminElement = driver.findElement(By.linkText("Neuer Termin"));
        NeuerTerminElement.click();
        //Neuen Termin erstellen
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test wochenAnsicht");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("19:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys(df.format(calendar.getTime()));
=======
    public void dashboardWocheSeleniumTest(){
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        WebDriver driver = new HtmlUnitDriver();
        driver.navigate().to("http://localhost:8080/dashboard");

        WebElement elementKlick = driver.findElement(By.linkText("Neuer Termin"));
        elementKlick.click();

        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Vorlesung");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("13:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys(df.format(c.getTime()));
>>>>>>> Stashed changes
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
<<<<<<< Updated upstream
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        element.sendKeys(df.format(calendar.getTime()));
        //Termin speichern
        element = driver.findElement(By.name("speichern"));
        element.click();

        String strg = driver.findElement(By.linkText("Test wochenAnsicht")).getText();
        assertEquals("Test wochenAnsicht", strg);

        element = driver.findElement(By.name("Näschste Woche"));
        element.click();*/
    }
=======
        element.sendKeys(df.format(c.getTime()));

        element = driver.findElement(By.name("speichern"));
        element.click();

        assertEquals("http://localhost:8080/dashboard/woche", driver.getCurrentUrl());
        assertEquals("Vorlesung",driver.findElement(By.linkText("Vorlesung")).getText());

        WebElement testElement = driver.findElement(By.linkText("Nächste Woche"));
        testElement.click();

        boolean present;
        try {
            driver.findElement(By.linkText("Vorlesung"));
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        assertEquals(false, present);

        testElement = driver.findElement(By.linkText("letzte Woche"));
        testElement.click();

        assertEquals("Vorlesung", driver.findElement(By.linkText("Vorlesung")).getText());

        /*testElement = driver.findElement(By.linkText("letzte Woche"));
        testElement.click();

        try {
            driver.findElement(By.linkText("Vorlesung"));
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }

        assertEquals(false, present);

        testElement = driver.findElement(By.linkText("Nächste Woche"));
        testElement.click();

        assertEquals("Vorlesung", driver.findElement(By.linkText("Vorlesung")).getText());*/
    }

>>>>>>> Stashed changes
}
