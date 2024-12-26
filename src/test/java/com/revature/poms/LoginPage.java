package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(tagName = "input")
    private WebElement loginButton;

    // Locate the <a> link by its text
    @FindBy(linkText = "Create an Account")
    private WebElement registerLink;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openLoginPage(){
        driver.get("http://localhost:8080/");
    }

    public void enterUsername(String username){
        this.usernameInput.clear();
        this.usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        this.passwordInput.clear();
        this.passwordInput.sendKeys(password);
    }

    public void submitLoginForm(){
        this.loginButton.submit();
    }

    public void setUpLoggedInUser(){
        openLoginPage();
        enterUsername("Batman");
        enterPassword("Iamthenight1939");
        submitLoginForm();
    }

    // New method to click "Create an Account"
    public void clickRegisterLink(){
        registerLink.click();
    }
}

