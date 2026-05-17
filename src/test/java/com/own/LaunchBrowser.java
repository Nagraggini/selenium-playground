package com.own;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {

    private WebDriver driver;

    @BeforeEach
    void inicializalas() {

        // Ezzel nem jelenik meg a GUI, mert github actions-ben nincs ilyen, ha hagyod
        // megjelenni, akkor a workflows el fog hasalni.
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");

        // Újabb Selenium verziókban már sokszor nem kell ez.
        WebDriverManager.chromedriver().setup();

        // 1. Launch browser
        // Add át az opciókat a driver példányosításakor
        driver = new ChromeDriver(options);

        // driver.manage().window().maximize();
    }
}
