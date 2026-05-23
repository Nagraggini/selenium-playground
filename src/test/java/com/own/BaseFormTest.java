package com.own;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//Közös tesztek. 
//https://www.youtube.com/watch?v=HpL6EX2kjq4
@Disabled
public class BaseFormTest {

    protected WebDriver driver;
    // 10 másodperces várakoztatás deklarálása.
    protected WebDriverWait wait;

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

    @Test
    @DisplayName("Check the title")
    void checkTitle() {
        openPage("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        assertEquals("Selenium Grid Online | Run Selenium Test On Cloud", driver.getTitle(),
                "A weboldal címe nem egyezik.");
    }

    @Test
    @DisplayName("Simple Form Demo")
    void simpleFormDemo() {

        openPage("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        driver.findElement(By.id("user-message")).sendKeys("You are the winner!");

        driver.findElement(By.id("showInput")).click();

        String browserMessage = driver.findElement(By.id("message")).getText();

        assertEquals("You are the winner!", browserMessage);

        WebElement aInput = driver.findElement(By.id("sum1"));

        assertEquals("Please enter first value", aInput.getAttribute("placeholder"),
                "A placeholder értékének üzenete nem egyezik!");
        int a = 10;
        int b = 15;
        aInput.sendKeys(String.valueOf(a));
        driver.findElement(By.id("sum2")).sendKeys(String.valueOf(b));

        // F12 chromedev toolban -> //button[text()='Get Sum']
        driver.findElement(By.xpath("//button[text()='Get Sum']")).click();

        String browserResult = driver.findElement(By.id("addmessage")).getText();

        assertEquals(String.valueOf(a + b), browserResult, "A két szám összege nem egyezik.");
    }

    @Test
    @DisplayName("Dropdown Demo")
    void dropdownDemo() {
        openPage("https://www.testmuai.com/selenium-playground/select-dropdown-demo/");

        WebElement dayDropdown = driver.findElement(By.id("select-demo"));

        Select select = new Select(dayDropdown);
        select.selectByVisibleText("Friday");
        WebElement firstSelectedOption = select.getFirstSelectedOption();

        assertEquals("Friday", firstSelectedOption.getText(), "A legördülő menüben az elemre kattintás nem működik!");

        select.selectByIndex(6); // 0-tól kezdődik. A disabled is számít.
        assertEquals("Friday", select.getFirstSelectedOption().getText(),
                "A legördülő menüben az elem index-e nem működik!");

        select.selectByValue("Friday");
        assertEquals("Friday", select.getFirstSelectedOption().getText(),
                "A legördülő menüben a value hibás!");
    }

    @Test
    @DisplayName("Multiple dropdrown")
    void multipleDropdown() {
        openPage("https://www.testmuai.com/selenium-playground/select-dropdown-demo/");

        WebElement multiDropdown = driver.findElement(By.id("multi-select"));
        Select countries = new Select(multiDropdown);
        String firstCountry = "New Jersey";
        String secondCountry = "Texas";

        var expectedCountries = new ArrayList<String>();
        expectedCountries.add(firstCountry);
        expectedCountries.add(secondCountry);

        countries.selectByIndex(2); // New Jersey
        countries.selectByValue(secondCountry);

        assertTrue(countries.isMultiple(), "A legördülő menüben nem lehet több elemet kiválasztani.");

        List<String> selectedTexts = countries.getAllSelectedOptions().stream().map(WebElement::getText).toList();

        assertEquals(expectedCountries, selectedTexts,
                "A multi-select legördülő menü nem ad vissza jó eredményt.");

    }

    @AfterEach
    void tearDown() {
        // Bezárja az összes ablakot és teljesen leállítja a WebDriver-t.
        if (driver != null) {
            driver.quit();
        }
    }
}
