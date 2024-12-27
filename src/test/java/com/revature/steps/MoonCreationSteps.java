package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoonCreationSteps {

    private int oldRowCount;

   
    // User tries to add a valid new moon
   
    @When("user inputs valid moon creation data")
    public void user_inputs_valid_moon_creation_data() {
        
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();

        WebElement moonNameField = TestRunner.driver.findElement(By.id("moonNameInput"));
        moonNameField.clear();
        moonNameField.sendKeys("Luna_Orion-5");

        WebElement owningPlanetField = TestRunner.driver.findElement(By.id("orbitedPlanetInput"));
        owningPlanetField.clear();
        owningPlanetField.sendKeys("1"); 

        WebElement moonImageInput = TestRunner.driver.findElement(By.id("moonImageInput"));
        moonImageInput.sendKeys("/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/moon-5.jpg");

        WebElement submitMoonBtn = TestRunner.driver.findElement(By.cssSelector("button.submit-button"));
        submitMoonBtn.click();
    }

    @And("updated table contains new moon info in addition to old stuff")
    public void updated_table_contains_new_moon_info_in_addition_to_old_stuff() {
        
        TestRunner.driver.get("http://localhost:8080/planetarium");

        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(5));
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(
                By.id("celestialTable"),
                "Luna_Orion-5"
            )
        );

        int newRowCount = TestRunner.homePage.getNumberOfCelestialRows();

        Assert.assertEquals(oldRowCount + 1, newRowCount);
    }

    // User tries to add an invalid moon
    @When("user inputs moon name {string}")
    public void user_inputs_moon_name(String moonName) {
        
        WebElement moonNameField = TestRunner.driver.findElement(By.id("moonNameInput"));
        moonNameField.clear();
        moonNameField.sendKeys(moonName);
    }

    @And("owning planet {string}")
    public void owning_planet(String planetId) {
        WebElement owningPlanetField = TestRunner.driver.findElement(By.id("orbitedPlanetInput"));
        owningPlanetField.clear();
        owningPlanetField.sendKeys(planetId);
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();
    }

    @And("submits the moon info")
    public void submits_the_info() {
        
        WebElement moonImageInput = TestRunner.driver.findElement(By.id("moonImageInput"));
        moonImageInput.sendKeys("/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/moon-5.jpg");

        WebElement submitMoonBtn = TestRunner.driver.findElement(By.cssSelector("button.submit-button"));
        submitMoonBtn.click();
    }

    @And("table should stay the same")
    public void table_should_remain_unchanged() {
        int newRowCount = TestRunner.homePage.getNumberOfCelestialRows();
        Assert.assertEquals(oldRowCount, newRowCount);
    }
}
