package com.own;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckBoxTest extends BaseTest {
    @Test
    @DisplayName("Check h1 tag.")
    void checkH1Tag() {

        openPage("https://www.testmuai.com/selenium-playground/checkbox-demo/");

        WebElement h1 = driver.findElement(By.xpath("//h1[contains(.,'Checkbox Demo')]"));

        String expectedH1 = "Checkbox Demo";

        assertTrue(h1.getText().equals(expectedH1), "A h1-es tagben lévő szöveg nem ez: " + expectedH1);
    }
}
