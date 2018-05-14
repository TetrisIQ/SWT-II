package de.swt.inf.model;


import de.swt.inf.database.DaoFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

public class SeleniumTest {

    @BeforeClass
    public static void beforeClass() {
        DaoFactory.test = true;
    }

    @AfterClass
    public static void afterClass() {
        DaoFactory.test = false;
    }

    @Test
    public void SeleniumTest() {

        WebDriver driver = new HtmlUnitDriver();

        driver.navigate().to("http://localhost:8080/termin");

        /* //Pfad zum Chrome Driver
        System.setProperty("webdriver.chrome.driver", "C:/Users/Nina/Downloads/chromedriver_win32/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
       */
        
        //Browserfenster maximieren
        driver.manage().window().maximize();
        //Termin automatisch erstellen
        driver.findElement(By.name("name")).sendKeys("Vorlesung");
        driver.findElement(By.name("startT")).sendKeys("13:00");
        driver.findElement(By.name("start")).sendKeys("20.05.2018");
        driver.findElement(By.name("ort")).sendKeys("Lübeck");
        driver.findElement(By.name("endT")).sendKeys("20:00");
        driver.findElement(By.name("end")).sendKeys("20.05.2018");
        WebElement element = driver.findElement(By.name("speichern"));
        element.click();

        //jUnit Tests
        assertEquals("Dashboard", driver.getTitle());
        assertEquals("http://localhost:8080/dashboard", driver.getCurrentUrl());
    }
}
