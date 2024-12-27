package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PlanetCreationSteps {

    private int oldRowCount;

    // User tries to add a valid new planet

    @Given("user is on home page")
    public void user_is_on_home_page() {
        // setting up a logged in user
        TestRunner.loginPage.setUpLoggedInUser();

        // Waiting until the page title is "Home"
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @When("user selects {string} from the dropdown")
    public void user_selects_from_the_dropdown(String optionValue) {
        TestRunner.wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("locationSelect"))
        );

        WebElement dropdown = TestRunner.driver.findElement(By.id("locationSelect"));
        dropdown.click();

        Select select = new Select(dropdown);
        select.selectByValue(optionValue.toLowerCase()); 

        // Wait for planetNameInput to appear
        TestRunner.wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("planetNameInput"))
        );
    }

    @And("user inputs valid planet creation data")
    public void user_inputs_valid_planet_creation_data() {
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();

        WebElement planetName = TestRunner.driver.findElement(By.id("planetNameInput"));
        planetName.clear();
        planetName.sendKeys("Nova_Terra 5-1");

        WebElement planetImageInput = TestRunner.driver.findElement(By.id("planetImageInput"));
        planetImageInput.sendKeys("/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/planet-3.jpg");

        WebElement submitBtn = TestRunner.driver.findElement(By.cssSelector("button.submit-button"));
        submitBtn.click();
      
    }

    @Then("table refreshes")
    public void table_refreshes() {
        // waits for the number of <tr> elements to exceed oldRowCount
        TestRunner.wait.until(
            ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"), oldRowCount)
        );
    }

    @And("updated table contains new planet info in addition to old stuff")
    public void updated_table_contains_new_planet_info_in_addition_to_old_stuff() {
        TestRunner.driver.get("http://localhost:8080/planetarium");

        TestRunner.wait.until(ExpectedConditions.textToBePresentInElementLocated(
            By.id("celestialTable"), "Nova_Terra 5-1"
        ));


        int newRowCount = TestRunner.homePage.getNumberOfCelestialRows();

        String currentPageSource = TestRunner.driver.getPageSource();

        Assert.assertEquals(oldRowCount + 1, newRowCount);
    }


    // User tries to add a new planet with invalid data
 
    @When("user inputs planet name {string}")
    public void user_inputs_planet_name(String name) {
        oldRowCount = TestRunner.homePage.getNumberOfCelestialRows();

        TestRunner.wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("planetNameInput"))
        );

        WebElement planetNameField = TestRunner.driver.findElement(By.id("planetNameInput"));
        planetNameField.clear();
        planetNameField.sendKeys(name);
    }

    @And("file type {string}")
    public void file_type(String type) {
        String filePath;
        switch (type.toUpperCase()) {
            case "JPEG":
                filePath = "/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/planet-3.jpg";
                break;
            case "GIF":
                filePath = "/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/planet.gif";
                break;
            default:
                filePath = "/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/planet-3.jpg";
        }

        WebElement planetImageInput = TestRunner.driver.findElement(By.id("planetImageInput"));
        planetImageInput.sendKeys(filePath);
    }

    @And("submits the planet info")
    public void submits_the_info() {
        
        WebElement planetImageInput = TestRunner.driver.findElement(By.id("planetImageInput"));
        planetImageInput.sendKeys("/Users/al/Downloads/TestSuite/src/test/resources/Celestial-Images/planet-5.jpg");

        WebElement submitMoonBtn = TestRunner.driver.findElement(By.cssSelector("button.submit-button"));
        submitMoonBtn.click();
    }


    @Then("browser alert message {string} should be displayed")
    public void browser_alert_message_should_be_displayed(String expectedAlertText) {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        String actualText = alert.getText();
        System.out.println("ALERT TEXT: " + actualText);

        Assert.assertEquals(expectedAlertText, actualText);
        alert.accept();
    }

    @And("table should remain unchanged")
    public void table_should_remain_unchanged() {
        int newRowCount = TestRunner.homePage.getNumberOfCelestialRows();
        Assert.assertEquals(oldRowCount, newRowCount);
    }
}



