package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ProductsPage {
    By productLocator = By.className("inventory_item");


    WebDriver driver;

    // Constructeur
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        // Initialisation des éléments locaux
    }

    // Méthode pour vérifier si la page des produits est affichée
    public boolean isProductPageDisplayed() {
        WebElement productElement = driver.findElement(productLocator);
        return productElement.isDisplayed();
    }

    // Méthode pour vérifier si la liste de produits est en ordre croissant
    public boolean isProductListInAscendingOrder() {
        // Récupérer la liste des éléments de produits
        List<WebElement> productList = driver.findElements(productLocator);

        // Vérifier si la liste est en ordre croissant
        return isListInAscendingOrder(productList);
    }

    // Méthode utilitaire pour vérifier si une liste de produits est en ordre croissant
    private boolean isListInAscendingOrder(List<WebElement> productList) {
        // Vérifier si la liste est vide ou contient un seul élément
        if (productList.isEmpty() || productList.size() == 1) {
            return true;
        }

        // Parcourir la liste pour comparer chaque élément avec l'élément suivant
        for (int i = 0; i < productList.size() - 1; i++) {
            String currentProductName = productList.get(i).getText();
            String nextProductName = productList.get(i + 1).getText();

            // Comparer les noms des produits en ordre alphabétique
            if (currentProductName.compareToIgnoreCase(nextProductName) > 0) {
                return false; // La liste n'est pas en ordre croissant
            }
        }

        // Si aucune inversion n'est détectée, la liste est en ordre croissant
        return true;
    }
}

