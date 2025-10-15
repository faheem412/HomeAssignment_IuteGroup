package com.booking;

import com.booking.util.ReadConfig;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Selenide.open;

/**
 * BaseTest provides the common test setup for UI tests.
 * <p>
 * It:
 * - Configures the Allure Selenide listener for enhanced reporting with screenshots.
 * - Opens the application URL loaded from configuration before each test.
 * - Maximizes the browser window for consistent UI layout.
 * <p>
 * This class should be extended by all test classes to inherit configuration.
 */

public class BaseTest {

    private final String URL = ReadConfig.geturl();

    @BeforeTest
    public void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
        open(URL);
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
