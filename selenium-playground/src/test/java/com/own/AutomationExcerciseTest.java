package com.own;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AutomationExcerciseTest {
    // https://automationexercise.com/test_cases

    @Test
    public void ElsoTeszt() {
        // A böngésző indítása
        WebDriver driver = new ChromeDriver();

        try {
            // Navigáció egy oldalra
            driver.get("https://automationexercise.com/");

            // Kiírjuk az oldal címét ellenőrzésképpen
            System.out.println("Az oldal címe: " + driver.getTitle());

        } finally {
            // Mindig zárjuk be a böngészőt a végén!
            driver.quit();
        }
    }
}
