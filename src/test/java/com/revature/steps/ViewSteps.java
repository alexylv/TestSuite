package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ViewSteps {

    @Given("the user is logged in on the home page")
    public void the_user_is_logged_in_on_the_home_page() {
        TestRunner.loginPage.setUpLoggedInUser();
    }

    @Given("the user is not logged in")
    public void the_user_is_not_logged_in() {
        TestRunner.loginPage.openLoginPage();
    }

    @When("the user tries to directly access the home page")
    public void the_user_tries_to_directly_access_the_home_page() {
        TestRunner.homePage.tryToAccessHomePageDirectly();
    }

    @Then("they should see their planets and moons")
    public void they_should_see_their_planets_and_moons() {
        // We wrap everything in try/finally because the code logs out at the end
        try {
            // 1) Wait until the title is "Home"
            TestRunner.wait.until(ExpectedConditions.titleIs("Home"));

            // 2) Wait for the table to have at least 5 <tr> total
            //    (1 header row + 4 data rows, for example)
            TestRunner.homePage.waitForTableRows(5);

            // 3) Check that the greeting is correct
            Assert.assertEquals(
                "Welcome to the Home Page Batman",
                TestRunner.homePage.getHomePageGreeting()
            );

            // 4) Check the number of data rows is 5
            //    (assuming 1 header row, so total <tr> is 6)
            Assert.assertEquals(4, TestRunner.homePage.getNumberOfCelestialRows());

        } finally {
            // Logout so subsequent tests don’t start from a logged-in state
            TestRunner.homePage.logout();
        }
    }

    @Then("the user should be denied access")
    public void the_user_should_be_denied_access() {
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }
}
