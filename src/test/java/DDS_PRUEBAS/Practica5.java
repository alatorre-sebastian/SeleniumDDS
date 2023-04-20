package DDS_PRUEBAS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

//Importes necesario para el manejo de archivos
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

//Practica 5, manejo de archivos
//Ingresar a Mercado Libre
//Y realizar un busqueda dado un archivo txt
public class Practica5 {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void launchapp() {
        driver.navigate().to("https://www.mercadolibre.com.mx/");
        driver.manage().window().maximize();
    }
    @Test
    public void shouldReadTxt() throws IOException, InterruptedException {
        //Archivo items.txt contiene lo que queremos buscar
        String TestFile = "items.txt";
        //BufferedReader es una clase de Java para leer el texto de una secuencia de entrada (como un archivo)
        BufferedReader BR = new BufferedReader(new FileReader(TestFile));
        //Guardamos en una variable lo que leimos del archivo
        String Item = BR.readLine();
        BR.close();

        TimeUnit.SECONDS.sleep(2);
        //Localizamos la barra de busqueda
        WebElement searchBar = driver.findElement(By.id("cb1-edit"));
        //Escribimos el texto guardado
        searchBar.sendKeys(Item);
        //Introducimos lo valores
        searchBar.submit();
        TimeUnit.SECONDS.sleep(2);

    }

    @AfterTest
    public void terminaTest(){
        driver.close();
    }
}

