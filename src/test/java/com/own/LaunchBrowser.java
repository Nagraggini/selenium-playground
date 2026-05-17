package com.own;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

//https://www.youtube.com/watch?v=HpL6EX2kjq4
public class LaunchBrowser {

    // private WebDriver driver;

    // Felhős módszer
    private RemoteWebDriver driver;

    @BeforeEach
    void setUp() {
        // https://www.testmuai.com/capabilities-generator/-ről kimásolva.
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("148.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        // testmuai.com profil adatok és bal alul Credentials
        ltOptions.put("username", "ecsediandrea");
        ltOptions.put("accessKey", "LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq");
        ltOptions.put("project", "LambdaTest");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);
        try {
            // username and accessKey
            driver = new RemoteWebDriver(new URL(
                    "https://ecsediandrea:LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq@hub.lambdatest.com/wd/hub"),
                    browserOptions);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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

        String title = driver.getTitle();
        System.out.println("--!!--" + title);
        WebElement aInput = driver.findElement(By.id("sum1"));
        aInput.sendKeys("10");
        driver.findElement(By.id("sum2")).sendKeys("15");

    }

    @AfterEach
    void tearDown() {
        // Bezárja az összes ablakot és teljesen leállítja a WebDriver-t.
        driver.quit();
    }
}
