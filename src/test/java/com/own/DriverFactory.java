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
        // Ha nem adtunk meg semmit, alapértelmezetten fusson local-ban
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
                ltOptions.put("w3c", true);
                browserOptions.setCapability("LT:Options", ltOptions);

                return new RemoteWebDriver(
                        new URL("https://ecsediandrea:LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq@hub.lambdatest.com/wd/hub"),
                        browserOptions);

            case "local":
            default:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--headless=new"); // CI/CD-hez (pl. GitHub Actions)
                return new ChromeDriver(options);
        }
    }
}