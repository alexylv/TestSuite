package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MoonDeletionSteps {

    private int oldRowCount;

    // User deletes a valid moon 

    @When("user inputs valid moon deletion data")
    public void user_inputs_valid_moon_deletion_data() {
        // get curr row count from table
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();
        System.out.println("DEBUG: oldRowCount (before deletion) = " + oldRowCount);

        // enter valid moon name into input bar
        WebElement deleteField = TestRunner.driver.findElement(By.id("deleteInput"));
        deleteField.clear();
        deleteField.sendKeys("Luna");

        // click delete button
        WebElement deleteBtn = TestRunner.driver.findElement(By.id("deleteButton"));
        deleteBtn.click();
    }

    @Then("table with moon info refreshes")
    public void table_refreshes_moon() {
        
        TestRunner.driver.get("http://localhost:8080/planetarium");
        WebDriverWait wait = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("celestialTable")));
    }

    @And("updated table no longer contains the deleted moon")
    public void updated_table_no_longer_contains_the_deleted_moon() {

        // confirm that deleted moon is gone from table
        List<WebElement> rows = TestRunner.driver.findElements(By.tagName("tr"));
        boolean foundMoon = false;
        for (WebElement row : rows) {
            if (row.getText().contains("Luna")) {
                foundMoon = true;
                break;
            }
        }
        Assert.assertFalse("Moon 'Luna' should not appear after deletion", foundMoon);
    }


    // User tries to delete an invalid moon

    @When("user inputs invalid moon name to delete")
    public void user_inputs_invalid_moon_name_to_delete() {
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();
        System.out.println("DEBUG: oldRowCount (invalid scenario) = " + oldRowCount);

        WebElement deleteField = TestRunner.driver.findElement(By.id("deleteInput"));
        deleteField.clear();
        
        deleteField.sendKeys("Umbriel");

        WebElement deleteBtn = TestRunner.driver.findElement(By.id("deleteButton"));
        deleteBtn.click();
    }

    @Then("browser alert message \"Invalid moon name\" appears")
    public void browser_alert_message_Invalid_moon_name_appears() {
        
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();

        String actualText = alert.getText();
        System.out.println("DEBUG: Invalid alert text is: " + actualText);
        Assert.assertEquals("Invalid moon name", actualText);

        alert.accept(); 
    }

    @And("table with moon info remains the same")
    public void table_with_moon_info_remains_the_same() {
        
        int newRowCount = TestRunner.homePage.getNumberOfCelestialRows();
        System.out.println("DEBUG: newRowCount (invalid scenario) = " + newRowCount);

        Assert.assertEquals("Row count should remain unchanged", oldRowCount, newRowCount);
    }
}

