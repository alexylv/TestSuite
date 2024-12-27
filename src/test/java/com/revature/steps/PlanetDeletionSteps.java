package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PlanetDeletionSteps {
    private int oldRowCount;


    @When("user inputs valid planet deletion data")
    public void user_inputs_valid_planet_deletion_data() {

        WebElement deleteField = TestRunner.driver.findElement(By.id("deleteInput"));
        deleteField.clear();
        deleteField.sendKeys("Earth");

        WebElement deleteBtn = TestRunner.driver.findElement(By.id("deleteButton"));
        deleteBtn.click();
    }

    @Then("table with planet info refreshes")
    public void table_with_planet_info_refreshes() {
        TestRunner.driver.get("http://localhost:8080/planetarium");

        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celestialTable")));
    }

    @And("updated table no longer contains the deleted planet")
    public void updated_table_no_longer_contains_the_deleted_planet() {
        List<WebElement> rows = TestRunner.driver.findElements(By.tagName("tr"));
        boolean foundPlanet = false;

        for (int i = 1; i < rows.size(); i++) { 
            // start at 1 since row 0 is the header row
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().contains("Earth")) {
                    foundPlanet = true;
                    break;
                }
            }
            if (foundPlanet) break;
        }

        Assert.assertFalse(
            "Planet 'Earth' should not appear after deletion", 
            foundPlanet
        );
    }

    // Trying to delete an invalid planet

    @When("user inputs invalid planet name to delete")
    public void user_inputs_invalid_planet_name_to_delete() {
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();
 
        WebElement deleteField = TestRunner.driver.findElement(By.id("deleteInput"));
        deleteField.clear();
        deleteField.sendKeys("Orionis Delta");
 
        WebElement deleteBtn = TestRunner.driver.findElement(By.id("deleteButton"));
        deleteBtn.click();
    }
 
 
    @Then("browser alert message \"Invalid planet name\" appears")
    public void browser_alert_message_Invalid_planet_name_appears() {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        String actualText = alert.getText();
        Assert.assertEquals("Invalid planet name", actualText);
        alert.accept();
    }
 
 
    @And("table with planet info remains the same")
    public void table_remains_the_same() {
        int newRowCount = TestRunner.homePage.getNumberOfCelestialRows();
        Assert.assertEquals(oldRowCount, newRowCount);
    }
 }
 


