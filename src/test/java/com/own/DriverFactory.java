package com.own;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static WebDriver createDriver(String target) throws MalformedURLException {
        // Ha a futtatáskor nem adtak meg paramétert, biztonsági okokból helyileg
        // indítjuk el.
        if (target == null) {
            target = "local";
        }

        switch (target.toLowerCase()) {
            case "remote":
            case "cloud":
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("148.0");

                HashMap<String, Object> ltOptions = new HashMap<>();
                ltOptions.put("username", "ecsediandrea");
                ltOptions.put("accessKey", "LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq");
                ltOptions.put("project", "LambdaTest");
                ltOptions.put("build", "Modern execution");
                ltOptions.put("selenium_version", "4.0.0");
                ltOptions.put("w3c", true);
                browserOptions.setCapability("LT:Options", ltOptions);

                // Kapcsolódás a távoli LambdaTest Selenium Hub-hoz
                return new RemoteWebDriver(

                        // Itt inicializáljuk a TÁVOLI drivert
                        // username and accessKey
                        new URL("https://ecsediandrea:LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq@hub.lambdatest.com/wd/hub"),
                        browserOptions);

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
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return options;
    }

}