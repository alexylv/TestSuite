package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationSteps {

    //Background

    @And("the user clicks the register link")
    public void the_user_clicks_the_register_link() {
        TestRunner.loginPage.clickRegisterLink();
    }

    // User registers w valid credentials

    @When("the user provides a valid username")
    public void the_user_provides_a_valid_username() {
        TestRunner.registrationPage.enterUsername("Super_man-2001");
    }

    @And("the user provides a valid password")
    public void the_user_provides_a_valid_password() {
        TestRunner.registrationPage.enterPassword("Krypton-was_2000");
    }

    @When("the user submits the credentials")
    public void the_user_submits_the_credentials() {
        TestRunner.registrationPage.submitRegistration();
    }

    @Then("the user should get a browser alert saying {string}")
    public void the_user_should_get_a_browser_alert_saying(String expectedAlertText) {
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = TestRunner.driver.switchTo().alert();
        String actualText = alert.getText();
        Assert.assertEquals(expectedAlertText, actualText);
        alert.accept();  // Closes the alert
    }

    @Then("the user should be redirected to the login page")
    public void the_user_should_be_redirected_to_the_login_page() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Login"));
        Assert.assertEquals("Login", TestRunner.driver.getTitle());
    }

    // User tries to register w invalid credentials

    @When("the user provides username {string}")
    public void the_user_provides_username(String username) {
        TestRunner.registrationPage.enterUsername(username);
    }

    @And("the user provides password {string}")
    public void the_user_provides_password(String password) {
        TestRunner.registrationPage.enterPassword(password);
    }

    @Then("the user should stay on the registration page")
    public void the_user_should_stay_on_the_registration_page() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Account Creation"));
        Assert.assertEquals("Account Creation", TestRunner.driver.getTitle());
    }
}


