package de.swt.inf.model;

import de.swt.inf.database.DaoFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class SeleniumWiederholung {

  @BeforeClass
    public static void beforeClass() {
        DaoFactory.test = true;
        BeforSelenium.beforSeleniumTests();
    }

    @AfterClass
    public static void afterClass() {
        DaoFactory.test = false;
    }

    @Test
    public void WdhTestTaeglich(){
        WebDriver driver = new HtmlUnitDriver();

        driver.navigate().to("http://localhost:8080/termin");
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test täglich");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("19:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys("2018-05-13");
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
        element.sendKeys("2018-05-13");
        element = driver.findElement(By.name("repeat"));
        element.click();
        Select dropdown = new Select(driver.findElement(By.name("repeatTime")));
        dropdown.selectByVisibleText("täglich");



        element = driver.findElement(By.name("speichern"));
        element.click();

        System.out.println("Name:" + driver.getCurrentUrl());
        System.out.println("Title is: " + driver.getTitle());

        assertEquals("http://localhost:8080/dashboard", driver.getCurrentUrl());
        assertEquals("Dashboard", driver.getTitle());
    }

    @Test
    public void WdhTestStuendlich() {

        WebDriver driver = new HtmlUnitDriver();

        driver.navigate().to("http://localhost:8080/termin");
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test stündlich");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("18:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys("2018-06-09");
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
        element.sendKeys("2018-06-09");
        element = driver.findElement(By.name("repeat"));
        element.click();
        Select dropdown = new Select(driver.findElement(By.name("repeatTime")));
        dropdown.selectByVisibleText("stündlich");



        element = driver.findElement(By.name("speichern"));
        element.click();

        System.out.println("Name:" + driver.getCurrentUrl());
        System.out.println("Title is: " + driver.getTitle());

        assertEquals("http://localhost:8080/dashboard", driver.getCurrentUrl());
        assertEquals("Dashboard", driver.getTitle());
    }

    @Test
    public void WdhTestWoechentlich() {

        WebDriver driver = new HtmlUnitDriver();

        driver.navigate().to("http://localhost:8080/termin");
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test wöchentlich");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("13:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys("2018-05-26");
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("14:00");
        element = driver.findElement(By.name("end"));
        element.sendKeys("2018-06-26");
        element = driver.findElement(By.name("repeat"));
        element.click();
        Select dropdown = new Select(driver.findElement(By.name("repeatTime")));
        dropdown.selectByVisibleText("wöchentlich");



        element = driver.findElement(By.name("speichern"));
        element.click();

        System.out.println("Name:" + driver.getCurrentUrl());
        System.out.println("Title is: " + driver.getTitle());

        assertEquals("http://localhost:8080/dashboard", driver.getCurrentUrl());
        assertEquals("Dashboard", driver.getTitle());
    }

    @Test
    public void WdhTestMonatlich() {

        WebDriver driver = new HtmlUnitDriver();

        driver.navigate().to("http://localhost:8080/termin");
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test monatlich");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("18:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys("2018-05-01");
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
        element.sendKeys("2018-06-09");
        element = driver.findElement(By.name("repeat"));
        element.click();
        Select dropdown = new Select(driver.findElement(By.name("repeatTime")));
        dropdown.selectByVisibleText("monatlich");



        element = driver.findElement(By.name("speichern"));
        element.click();

        System.out.println("Name:" + driver.getCurrentUrl());
        System.out.println("Title is: " + driver.getTitle());

        assertEquals("http://localhost:8080/dashboard", driver.getCurrentUrl());
        assertEquals("Dashboard", driver.getTitle());
    }

    @Test
    public void WdhTestjaerhlich() {

        WebDriver driver = new HtmlUnitDriver();

        driver.navigate().to("http://localhost:8080/termin");
        WebElement element = driver.findElement(By.name("name"));
        element.sendKeys("Test jährlich");
        element= driver.findElement(By.name("startT"));
        element.sendKeys("18:00");
        element= driver.findElement(By.name("start"));
        element.sendKeys("2018-05-01");
        element = driver.findElement(By.name("ort"));
        element.sendKeys("Lübeck");
        element = driver.findElement(By.name("endT"));
        element.sendKeys("20:00");
        element = driver.findElement(By.name("end"));
        element.sendKeys("2020-05-01");
        element = driver.findElement(By.name("repeat"));
        element.click();
        Select dropdown = new Select(driver.findElement(By.name("repeatTime")));
        dropdown.selectByVisibleText("jährlich");



        element = driver.findElement(By.name("speichern"));
        element.click();

        System.out.println("Name:" + driver.getCurrentUrl());
        System.out.println("Title is: " + driver.getTitle());

        assertEquals("http://localhost:8080/dashboard", driver.getCurrentUrl());
        assertEquals("Dashboard", driver.getTitle());
    }
}
