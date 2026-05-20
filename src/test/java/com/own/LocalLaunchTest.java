package com.own;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocalLaunchTest extends BaseFormTest {

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--headless=new");

        // Itt inicializáljuk a LOKÁLIS drivert.
        // Ezzel nem nyílik meg a böngésző.
        driver = new ChromeDriver(options);

        // Ezzel megnyílik a böngésző, viszont a GitHub Actions el fog hasalni!
        // driver = new ChromeDriver();

        // A GitHub Actions elhasal ezzel, mert nincsen böngésző, amit maximalizálni
        // lehetne.
        // driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}