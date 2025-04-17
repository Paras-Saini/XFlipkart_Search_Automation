package demo;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */
    @Test
    public void testCase01() throws InterruptedException {
        Boolean status = false;
        System.out.println("Test case 01 started");
        driver.get("https://www.flipkart.com/");

        status = Wrappers.textInput(driver, By.xpath("//input[@class='Pke_EE']"), "Washing Machine");

        Thread.sleep(2000);
        status = Wrappers.clickOption(driver, By.xpath("//div[contains(text(),'Popularity')]"));
        status = Wrappers.starTitleRating(driver, By.xpath("//span/div[text()<='4']"));

        Assert.assertTrue(status, "Test case 01 failed");
        System.out.println("Test case 01 Ended");
    }

    @Test
    public void testCase02() throws InterruptedException {
        boolean status = false;
        System.out.println("Test case 02 started");
        Thread.sleep(2000);
        status = Wrappers.textInput(driver, By.xpath("//input[@class='zDPmFV']"), "iPhone");

        Thread.sleep(2000);
        status = Wrappers.discountTitle(driver, By.xpath("//div[contains(@class,'yKfJKb ')]"));
        Assert.assertTrue(status, "Test case 02 failed");
        System.out.println("Test case 02 Ended");

    }

    @Test
    public void testCase03() throws InterruptedException {
        boolean status = false;
        System.out.println("Test case 03 started");

        status = Wrappers.textInput(driver, By.xpath("//input[@class='zDPmFV']"), "Coffee Mug");
        Thread.sleep(2000);
        status = Wrappers.clickOption(driver, By.xpath("//div[contains(@title,'4')]"));
        Thread.sleep(2000);

        status = Wrappers.selectTitleImg(driver, By.xpath("//div[contains(@class,'AVV')]"));
        Assert.assertTrue(status, "Test case 03 failed");
        System.out.println("Test case 03 Ended");
    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}