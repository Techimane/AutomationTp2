package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    By usernameLocator = By.id("user-name");
    By passwordLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");
    By errorLocator = By.cssSelector("h3[data-test='error']");


    WebDriver driver;

    // Constructeur
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Initialisation des éléments locaux
    }

    // Méthode pour saisir les identifiants et se connecter
    public void login(String username, String password) {
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }

    // Méthode pour vérifier si un message d'erreur est affiché
    public boolean isErrorMessageDisplayed() {
        WebElement errorElement = driver.findElement(errorLocator);
        return errorElement.isDisplayed();
    }
}
