package com.revature.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.revature.TestRunner; 

public class Hooks {
    // taking screenshot of failures
    @After
    public void takeScreenshotOnFailure(Scenario scenario) {
  
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) TestRunner.driver).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
