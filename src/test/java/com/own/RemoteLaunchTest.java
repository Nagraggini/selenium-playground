package com.own;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteLaunchTest extends BaseFormTest {

    @BeforeEach
    void setUp() {
        // https://www.testmuai.com/capabilities-generator/-ről kimásolva.
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("148.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        // testmuai.com profil adatok és bal alul Credentials
        ltOptions.put("username", "ecsediandrea");
        ltOptions.put("accessKey", "LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq");
        ltOptions.put("project", "LambdaTest");
        ltOptions.put("build", "First run");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        try {
            // Itt inicializáljuk a TÁVOLI drivert
            // username and accessKey
            driver = new RemoteWebDriver(new URL(
                    "https://ecsediandrea:LT_qWiCXthkGax20mTB3wvZHai8FGvAhZsbZxdiQfQ4mb2bQNq@hub.lambdatest.com/wd/hub"),
                    browserOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
