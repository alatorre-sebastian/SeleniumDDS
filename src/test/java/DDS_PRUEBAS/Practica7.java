package DDS_PRUEBAS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
//PRACTICA 7
//Hacer un ScreenShoot al primer objeto
//Y guardarlo con la fecha actual


public class Practica7 {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void launchapp(){
        driver.navigate().to("https://www.mercadolibre.com.mx/");
        driver.manage().window().maximize();
    }

    @Test
    public void shouldTakeScreenShot() throws IOException, InterruptedException {
        String item = "Celular";
        WebElement searchBar = driver.findElement(By.className("nav-search-input"));
        searchBar.sendKeys(item);
        searchBar.submit();

        //Esperamos a que cargen los articulos
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Usamos el xpath especifico que apunta siempre al primer elemento
        WebElement firstResult = driver.findElement(By.xpath("//li[@class='ui-search-layout__item shops__layout-item']//div[@class='ui-search-item__group ui-search-item__group--title shops__items-group']//a"));
        firstResult.click();

        TimeUnit.SECONDS.sleep(3);
        //Obtenemos la fecha actual
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        String actualDate = dateFormat.format(new Date());
        //Tomamos el ScreenShoot
        File screenshot  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot , new File(actualDate + "_.png"));
    }

    @AfterTest
    public void endTest(){driver.close();}
}
