package de.swt.inf.model;

import de.swt.inf.database.DaoFactory;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

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
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
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
}
