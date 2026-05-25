package com.own;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DropdownFromTest extends BaseTest {
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

        WebElement multiDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("multi-select")));
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
}
