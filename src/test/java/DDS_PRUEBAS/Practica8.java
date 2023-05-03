package DDS_PRUEBAS;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import org.testng.Assert;
import DDS_PRUEBAS.Pages.SearchItemPage;

public class Practica8 {
    WebDriver driver;
    //Creamos objeto
    SearchItemPage objSearchItemPage;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\SebasOx\\OneDrive\\Documentos\\DDS_SELENIUM\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mercadolibre.com.mx/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void shouldSearchItemAndFilterByPrice() throws InterruptedException{
        objSearchItemPage = new SearchItemPage(driver);
        objSearchItemPage.enterItem("Laptop");
        objSearchItemPage.min_maxFilter("500","8000");
    }

    @Test(dependsOnMethods = "shouldSearchItemAndFilterByPrice")
    public void shouldSelect10Items()throws InterruptedException{
        objSearchItemPage = new SearchItemPage(driver);
        objSearchItemPage.sortByPrice10Items();
    }

    @AfterSuite
    public void endTest(){driver.close();}
}