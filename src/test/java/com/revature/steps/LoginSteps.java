package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginSteps {

    
    // Background Steps
   

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        
        TestRunner.loginPage.openLoginPage();
    }

    @And("the user has an account")
    public void the_user_has_an_account() {
       
    }

    //User tries to log in with valid credentials
   

    @When("the user enters a valid username")
    public void the_user_enters_a_valid_username() {
        
        TestRunner.loginPage.enterUsername("Batman");
    }

    @And("the user enters a valid password")
    public void the_user_enters_a_valid_password() {
       
        TestRunner.loginPage.enterPassword("Iamthenight1939");
    }

    @And("the user submits the login form")
    public void the_user_submits_the_login_form() {
       
        TestRunner.loginPage.submitLoginForm();
    }

    @Then("the user should be directed to the home page")
    public void the_user_should_be_directed_to_the_home_page() {
        
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    // User tries to login with invalid credentials

    @When("the user enters username {string}")
    public void the_user_enters_username(String username) {
        
        TestRunner.loginPage.enterUsername(username);
    }

    @And("the user enters password {string}")
    public void the_user_enters_password(String password) {
       
        TestRunner.loginPage.enterPassword(password);
    }

    @Then("the user should see {string}")
    public void the_user_should_see(String expectedAlertText) {
       
        TestRunner.wait.until(ExpectedConditions.alertIsPresent());

        org.openqa.selenium.Alert alert = TestRunner.driver.switchTo().alert();

        String actualText = alert.getText();
        System.out.println("Alert text is: " + actualText);

        org.junit.Assert.assertEquals(expectedAlertText, actualText);

        alert.accept();
    }



    @And("the user should remain on the login page")
    public void the_user_should_remain_on_the_login_page() {
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }


   
    // Logged in user logs out and returns to login page

    @Given("the user is on the home page")
    public void the_user_is_on_the_home_page() {
        // Setup an already logged-in user
        TestRunner.loginPage.setUpLoggedInUser();
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @When("the user clicks {string}")
    public void the_user_clicks(String buttonText) {
        TestRunner.homePage.logout();
    }

    @Then("the user is taken from home page to login")
    public void the_user_is_taken_from_home_page_to_login() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Planetarium Login"));
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }
}
