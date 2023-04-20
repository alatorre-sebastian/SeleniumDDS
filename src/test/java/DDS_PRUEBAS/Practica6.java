package DDS_PRUEBAS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
//Imports para escribir
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// PRACTICA 6
// Guardaremos en un archivo txt,
// Todas las categorias encontradas en el menu SELECT
public class Practica6 {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void launchapp(){
        driver.navigate().to("https://www.amazon.com.mx/");
        driver.manage().window().maximize();
    }

    @Test
    public void shouldCopyCategorysFromSelectToTxt() throws IOException, InterruptedException {

        //Identificamos el elemnto "select", en este caso es el menu con las distintas categorias
        WebElement categoriesMenu = driver.findElement(By.id("searchDropdownBox"));
        TimeUnit.SECONDS.sleep(2);
        //Inicializamos un objeto de tipo "Select"
        Select select = new Select(categoriesMenu);

        //Obtenemos una lista de todas las opciones del elemento <select>(categoriesMenu)
        List<WebElement> categoriesList = select.getOptions();

        //Archivo categories.txt guardara las distintas categorias
        String TestFile = "categories.txt";

        //BufferedReader es una clase de Java para leer el texto de una secuencia de entrada (como un archivo)
        BufferedWriter BW = new BufferedWriter(new FileWriter(TestFile));

        //For each categorie in categoriesList
        for (WebElement categorie : categoriesList){
            //Guardamos cada categoria individualmente separandolas
            String individualCategory = categorie.getText();
            // Imprime el texto de la opción en la consola
            System.out.println(individualCategory);
            //Escribimos en el txt cada categoria
            BW.write(individualCategory);
            //Agrega una nueva línea después de cada categoria
            BW.newLine();
        }
        //Cerramos para guardar los datos en el archivo
        BW.close();
    }
    @AfterTest
    public void terminaTest(){driver.close();}
}
