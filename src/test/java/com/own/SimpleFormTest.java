package com.own;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SimpleFormTest extends BaseTest {

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
}