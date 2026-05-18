package com.own;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//https://www.youtube.com/watch?v=HpL6EX2kjq4
@Disabled
public class BaseFormTest {

    protected WebDriver driver;

    @Disabled
    @Test
    void localTest() {
        // Ezzel nem jelenik meg a GUI, mert github actions-ben nincs ilyen, ha hagyod
        // megjelenni, akkor a workflows el fog hasalni.
        // Helyi futtatáshoz.
        /*
         * ChromeOptions options = new ChromeOptions();
         * 
         * options.addArguments("--headless=new");
         * options.addArguments("--no-sandbox");
         * options.addArguments("--disable-dev-shm-usage");
         * options.addArguments("--remote-allow-origins=*");
         * options.addArguments("--disable-gpu");
         * 
         * // Újabb Selenium verziókban már sokszor nem kell ez.
         * WebDriverManager.chromedriver().setup();
         * 
         * // Add át az opciókat a driver példányosításakor
         * driver = new ChromeDriver(options);
         */
        // Így meg is fog jelenni a GUI.
        // driver = new ChromeDriver();
        driver.get("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        String title = driver.getTitle();
        System.out.println("--!!--" + title);

        driver.findElement(By.id("sum1"));

        // driver.manage().window().maximize();
    }

    @Test
    void myTest() {

        // driver = new ChromeDriver();
        driver.get("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            wait.until(ExpectedConditions
                    .elementToBeClickable(By.id(
                            "CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")))
                    .click();

        } catch (Exception e) {
            // Ha véletlenül nem ugrana fel, ne hasaljon el a teszt
            System.out.println("--- !!! --- Süti sáv nem jelent meg.");
        }

        assertEquals("Selenium Grid Online | Run Selenium Test On Cloud", driver.getTitle());

        WebElement aInput = driver.findElement(By.id("sum1"));

        assertEquals("Please enter first value", aInput.getAttribute("placeholder"));

        aInput.sendKeys("10");
        driver.findElement(By.id("sum2")).sendKeys("15");

        // F12 chromedev toolban -> //button[text()='Get Sum']
        driver.findElement(By.xpath("//button[text()='Get Sum']")).click();

        String browserResult = driver.findElement(By.id("addmessage")).getText();

        assertEquals("25", browserResult);
    }

    @AfterEach
    void tearDown() {
        // Bezárja az összes ablakot és teljesen leállítja a WebDriver-t.
        if (driver != null) {
            driver.quit();
        }
    }
}
