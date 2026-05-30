package com.own;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static WebDriver createDriver(String target) {
        // Ha a futtatáskor nem adtak meg paramétert, biztonsági okokból helyileg
        // indítjuk el.
        if (target == null) {
            target = "local";
        }

        switch (target.toLowerCase()) {
            case "remote":
            case "cloud":
                // Egyedi nevet adunk a környezeti változónak, hogy ne ütközzön a rendszer saját
                // USERNAME változójával
                String ltUser = System.getenv("LAMBDATEST_USERNAME");
                String ltKey = System.getenv("LAMBDATEST_KEY");

                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("148.0");

                HashMap<String, Object> ltOptions = new HashMap<>();
                ltOptions.put("username", ltUser);
                ltOptions.put("accessKey", ltKey);
                ltOptions.put("project", "LambdaTest");
                ltOptions.put("build", "Modern execution");
                ltOptions.put("selenium_version", "4.0.0");
                ltOptions.put("w3c", true);
                browserOptions.setCapability("LT:Options", ltOptions);

                // Kapcsolódás a távoli LambdaTest Selenium Hub-hoz
                try {
                    return new RemoteWebDriver(

                            // Itt inicializáljuk a TÁVOLI drivert
                            // username and accessKey
                            new URL("https://" + ltUser + ":"
                                    + ltKey
                                    + "@hub.lambdatest.com/wd/hub"),
                            browserOptions);
                } catch (MalformedURLException e) {

                    e.printStackTrace();
                }
                return null;
            case "local":
            default:
                ChromeOptions options = new ChromeOptions();

                headlessMode(options);

                return new ChromeDriver(options);
        }
    }

    /** Ezzel nem nyílik meg a böngésző. */
    static ChromeOptions headlessMode(ChromeOptions options) {
        options.addArguments("--headless=new"); // Újabb Selenium verzióknál a javasolt formátum
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080"); // Headless módban fontos a fix ablakméret
        options.addArguments("--no-sandbox"); // GitHub-os linux szerveren történő futtatáshoz kell.
        options.addArguments("--disable-dev-shm-usage");// GitHub-os linux szerveren történő futtatáshoz kell.
        options.addArguments("--incognito");

        // Teljesen elnémítja a CDP verziókereső hibaüzeneteit
        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder")
                .setLevel(java.util.logging.Level.OFF);

        // Az összes Selenium figyelmeztetés elnémítása
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.SEVERE);

        return options;
    }

}