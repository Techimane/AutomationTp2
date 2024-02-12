package travailpratique.tests;

import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        // Initialisation du WebDriver et des pages
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().minimize();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        driver.get("https://www.saucedemo.com/");

        // Initialisation du WebDriver et des pages pour Firefox
//        WebDriverManager.firefoxdriver().setup();
//        FirefoxOptions options = new FirefoxOptions();
//        driver = new FirefoxDriver(options);
        //driver.manage().window().minimize();
//        loginPage = new LoginPage(driver);
//        productsPage = new ProductsPage(driver);
//        driver.get("https://www.saucedemo.com/");

        // Initialisation du WebDriver et des pages pour Edge
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
        //driver.manage().window().minimize();
//        loginPage = new LoginPage(driver);
//        productsPage = new ProductsPage(driver);
//        driver.get("https://www.saucedemo.com/");
    }


    @Test
    public void testValidLogin() throws InterruptedException {
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(4000);
        // Assertion pour vérifier la redirection
        Assert.assertTrue(productsPage.isProductPageDisplayed());
    }


    @Test
    public void testInvalidLogin() {
        loginPage.login("invalid_user", "invalid_password");
        // Assertion pour vérifier le message d'erreur
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }


    @Test
    public void testProductListOrder() {
        loginPage.login("standard_user", "secret_sauce");
        // Vérification de l'ordre croissant de la liste de produits
        Assert.assertTrue(productsPage.isProductListInAscendingOrder(), "La liste des produits n'est pas en ordre croissant.");
    }


    @AfterMethod
    public void tearDown() {
        // Fermeture du navigateur
        driver.quit();
    }

}
