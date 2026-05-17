package com.own;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationExcerciseTest {
    // https://automationexercise.com/test_cases

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
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @AfterEach
    void lebontas() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("TC01-Főoldal ellenőrzése.")
    void fooldalSikeresBetoltese() {
        String elvartErtek = "Automation Exercise";

        // 2. Navigate to url 'http://automationexercise.com'
        driver.get("https://automationexercise.com/");

        // 3. Verify that home page is visible successfully
        // assertEquals(elvartErtek, driver.getTitle());

        // Modernebb AssertJ megoldás.
        assertThat(driver.getTitle()).isEqualTo(elvartErtek);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            // Megvárjuk, amíg az Elfogad gomb kattintható lesz, ha már elfogadtuk a
            // sütiket, akkor is tovább megyünk, ha lejárt a duration időnk.
            // xPath
            wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//button[contains(., 'Beleegyezés') or contains(., 'Accept')]")))
                    .click();

            System.out.println("--!!--Süti elfogadva!");
        } catch (Exception e) {
            // Ha nem ugrott fel az ablak (mert pl. már el van mentve), a teszt megy tovább
            System.out.println("--!!--Nem jelent meg a süti ablak, vagy nem sikerült rákattintani.");
        }

        // 4. Click on 'Signup / Login' button
        WebElement loginBtn = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[contains(.,'Signup / Login')]")));
        loginBtn.click();

        // 5. Verify 'New User Signup!' is visible

        /*
         * 
         * 6. Enter name and email address
         * 7. Click 'Signup' button
         * 8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
         * 9. Fill details: Title, Name, Email, Password, Date of birth
         * 10. Select checkbox 'Sign up for our newsletter!'
         * 11. Select checkbox 'Receive special offers from our partners!'
         * 12. Fill details: First name, Last name, Company, Address, Address2, Country,
         * State, City, Zipcode, Mobile Number
         * 13. Click 'Create Account button'
         * 14. Verify that 'ACCOUNT CREATED!' is visible
         * 15. Click 'Continue' button
         * 16. Verify that 'Logged in as username' is visible
         * 17. Click 'Delete Account' button
         * 18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
         */
    }
}
