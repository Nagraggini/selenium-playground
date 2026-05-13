package com.own;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void ElsoTeszt() {
        // A böngésző indítása
        WebDriver driver = new ChromeDriver();

        try {
            // Navigáció egy oldalra
            driver.get("https://the-internet.herokuapp.com/abtest");

            // Kiírjuk az oldal címét ellenőrzésképpen
            System.out.println("Az oldal címe: " + driver.getTitle());

        } finally {
            // Mindig zárjuk be a böngészőt a végén!
            driver.quit();
        }
    }
}
