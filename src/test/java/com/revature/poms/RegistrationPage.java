package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private WebDriver driver;
   
    @FindBy(id = "usernameInput")
    private WebElement regUsernameInput;
  
    @FindBy(id = "passwordInput")
    private WebElement regPasswordInput;
   
    @FindBy(css = "input[type='submit'][value='Create']")
    private WebElement createButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        regUsernameInput.clear();
        regUsernameInput.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        regPasswordInput.clear();
        regPasswordInput.sendKeys(password);
    }
   
    public void submitRegistration() {
        createButton.click(); 
        
    }
}



