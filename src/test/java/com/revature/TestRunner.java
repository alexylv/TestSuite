package com.revature;

import com.revature.utility.Setup;
import com.revature.poms.HomePage;
import com.revature.poms.LoginPage;
import com.revature.poms.RegistrationPage;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {
        "classpath:features/Login.feature",
        "classpath:features/ViewOwnedResources.feature",
        "classpath:features/Registration.feature",
        "classpath:features/PlanetCreation.feature",
        "classpath:features/MoonCreation.feature",
        "classpath:features/PlanetDeletion.feature",
        "classpath:features/MoonDeletion.feature"
    },
    glue = "com.revature.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json"
    }
)
public class TestRunner {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static HomePage homePage;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;

    @BeforeClass
    public static void setup() {
        
        System.out.println("DEBUG: About to reset DB...");
        Setup.resetTestDatabase();
        System.out.println("DEBUG: Finished reset DB call!");


        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
