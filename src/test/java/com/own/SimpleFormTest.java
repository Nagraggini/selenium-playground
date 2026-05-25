package com.own;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SimpleFormTest extends BaseTest {

    @Test
    @DisplayName("Single Input Field")
    void singleInputField() {

        openPage("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        WebElement userMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated((By.id("user-message"))));

        userMessage.sendKeys("You are the winner!");

        driver.findElement(By.id("showInput")).click();

        String browserMessage = driver.findElement(By.id("message")).getText();

        assertEquals("You are the winner!", browserMessage);
    }

    @DisplayName("Two Input Fields use two number")
    @ParameterizedTest
    // Formátum: "szam1, szam2, vartOsszeg",
    @CsvSource({
            "1,  2,  3",
            "5,  5,  10",
            "-3, 5,  2"
    })
    void twoInputFieldsUseTwoNumber(int number1, int number2, int expectedSum) {
        openPage("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        // Megvárjuk, amíg az első beviteli mező valóban megjelenik a felhőben futó
        // böngészőben
        WebElement aInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sum1")));

        assertEquals("Please enter first value", aInput.getAttribute("placeholder"),
                "A placeholder értékének üzenete nem egyezik!");

        aInput.sendKeys(String.valueOf(number1));

        // A második mezőnél és a gombnál is érdemes megvárni, vagy ellenőrizni őket:
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sum2"))).sendKeys(String.valueOf(number2));

        // F12 chromedev toolban -> //button[text()='Get Sum']
        driver.findElement(By.xpath("//button[contains(.,'Get Sum')]")).click();

        String actualResult = driver.findElement(By.id("addmessage")).getText();

        assertEquals(String.valueOf(expectedSum), actualResult, "A két szám összege nem egyezik.");
    }

    @Test
    @DisplayName("Two Input Fields use string instead of number on the first input field.")
    void twoInputFieldsUseOneStringInsteadOfNumberOnTheFirstInput() {
        openPage("https://www.testmuai.com/selenium-playground/simple-form-demo/");

        WebElement aInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sum1")));

        String a = "fifteen";
        int b = 15;
        aInput.sendKeys(a);
        driver.findElement(By.id("sum2")).sendKeys(String.valueOf(b));

        // F12 chromedev toolban -> //button[text()='Get Sum']
        driver.findElement(By.xpath("//button[text()='Get Sum']")).click();
        String expectedSum = "Entered value is not a number";
        String actualResult = driver.findElement(By.id("addmessage")).getText();

        assertEquals(expectedSum, actualResult,
                "Nem jelent meg a várt hibaüzenet.");
    }
    // TODO: A b változó legyen szöveg, egyik se legyen szöveg, és azt is csekkold,
    // hogy mi van, ha nem írsz egyikbe se semmit.
    // Ezekkel más eredményt kapsz: "0, 0, 0",
    // "-2, -2, -4"

}