package com.own;

import java.net.MalformedURLException;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Közös tesztek. 
//https://www.youtube.com/watch?v=HpL6EX2kjq4
@Disabled
public class BaseTest {

    protected WebDriver driver;
    // 10 másodperces várakoztatás deklarálása.
    protected WebDriverWait wait;

    @BeforeEach
    void setUp() {

        // Kiolvassa a "runTarget" paramétert (ezt futtatáskor adjuk majd meg)
        String target = System.getProperty("runTarget", "local");

        // Elkéri a megfelelő drivert a gyárból
        try {
            driver = DriverFactory.createDriver(target);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // ChromeOptions options = new ChromeOptions();
        // headlessMode(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    ChromeOptions headlessMode(ChromeOptions options) {
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");

        // Itt inicializáljuk a LOKÁLIS drivert.
        // Ezzel nem nyílik meg a böngésző.
        driver = new ChromeDriver(options);
        return options;
    }

    /** Csak azt, hogy a böngésző szerint az oldal betöltődött. */
    void waitForPageLoad() {
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState==='complete'"));

        // Modern Selenium Best Practice, ha egy konkrét gombra állítasz be wait-t,
        // mindegyik egységtesztnél.
    }

    /** Sütik elfogadása. */
    void handleCookies() {

        try {
            // Süti elfogadása gombra kattintás max 10 másodperces várakozással.
            wait.until(ExpectedConditions
                    .elementToBeClickable(By.id(
                            "CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")))
                    .click();

        } catch (Exception e) {
            // Ha véletlenül nem ugrana fel, ne hasaljon el a teszt
            System.out.println("--- !!! --- Süti sáv nem jelent meg.");
        }
    }

    void openPage(String url) {
        driver.get(url);
        waitForPageLoad();
        handleCookies();
    }

    @AfterEach
    void tearDown() {
        // Bezárja az összes ablakot és teljesen leállítja a WebDriver-t.
        if (driver != null) {
            driver.quit();
        }
    }
}
