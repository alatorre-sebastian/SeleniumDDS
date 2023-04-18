package SIT_Pruebas;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;

public class Practica3 {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void launchapp() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("https://www.demoblaze.com/index.html");
        driver.manage().window().maximize();
    }

    @Test
    public void agregarCarrito () throws InterruptedException {
        String samsung = "//a[normalize-space()='Samsung galaxy s6']"; //xpath
        String nokia = "//a[normalize-space()='Nokia lumia 1520']";    //xpath
        String btnCarrito = "//a[@class='btn btn-success btn-lg']";    //xpath
        String cartPage = "cartur";                                    //id
        String homePage = "(//a[@href='index.html'])[2]";              //xpath


        //Agregamos samsung al carrito
        WebElement selectSamsung = driver.findElement(By.xpath(samsung));
        selectSamsung.click();
        driver.findElement(By.xpath(btnCarrito)).click();
        TimeUnit.SECONDS.sleep(2);
        driver.switchTo().alert().accept();

        //Regresamos a HomePage
        driver.findElement(By.xpath(homePage)).click();

        //Agregamos nokia al carrito
        WebElement selectNokia   = driver.findElement(By.xpath(nokia));
        selectNokia.click();
        driver.findElement(By.xpath(btnCarrito)).click();
        TimeUnit.SECONDS.sleep(2);
        driver.switchTo().alert().accept();

        //Nos dirijimos a la pestaña del carrito
        driver.findElement(By.id(cartPage)).click();
        List<WebElement> precios = driver.findElements(By.xpath("//table[@class='table table-bordered table-hover table-striped']//tbody//tr//td[3]"));
        double precioTotal = 0.0;

        //Iteramos sobre cada elemento de la lista "precios"
        //for each precioIndividual in precios:
        for (WebElement precioIndividual : precios) {
            //dividimos con un espacio en blanco cada valor
            String precioTexto = precioIndividual.getText().split(" ")[0];
            //Lo transformamos a numero(double)
            double precioValor = Double.parseDouble(precioTexto);
            precioTotal += precioValor;
        }

        //Obtenemos el valor esperado
        WebElement precioEsperado = driver.findElement(By.id("totalp"));
        precioEsperado.getText();
        //Lo transofrmamos a double para poder comparar "precioTotal" con "precioEsperadoDouble"
        double  precioEsperadoDouble = Double.parseDouble(precioEsperado.getText());

        //Agregamos un try-catch para imprimir un mensaje dependiendo si coinciden o no
        try {
            Assert.assertEquals(precioTotal,precioEsperadoDouble);
        } catch (AssertionError e) {
            System.out.println("NO coinciden los precios");
            throw e;
        }
        System.out.println("Los precios coinciden :D!");


    }
}
