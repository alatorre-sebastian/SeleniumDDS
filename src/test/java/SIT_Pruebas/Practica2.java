package SIT_Pruebas;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.locators.RelativeLocator;



public class Practica2 {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void launchapp() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.navigate().to("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().window().maximize();
    }

    @Test
    public void inicioSesion() throws InterruptedException {
        String usuario = "Admin";
        String contrasenia = "admin123";

        //Usamos un locaziador relativo para encontrar el campo de username
        By usuarioLocator = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[text()='Username']"));
        TimeUnit.SECONDS.sleep(2);
        WebElement campoUsuario = driver.findElement(usuarioLocator);
        //Mandamos el string Admin al campo
        campoUsuario.sendKeys(usuario);

        //Usamos un locaziador relativo para encontrar el campo de password
        By passwordLocator = RelativeLocator.with(By.tagName("input")).below(By.xpath("//label[text()='Password']"));
        TimeUnit.SECONDS.sleep(2);
        WebElement campoContrasenia = driver.findElement(passwordLocator);
        campoContrasenia.sendKeys(contrasenia);


        //Usamos un locaziador relativo para encontrar el boton de Login
        By btnLocator = RelativeLocator.with(By.tagName("button")).above(By.xpath("//p[text()='Forgot your password? ']"));
        TimeUnit.SECONDS.sleep(1);
        WebElement btnLogin = driver.findElement(btnLocator);
        btnLogin.click();
        TimeUnit.SECONDS.sleep(2);
    }

    @AfterTest
    public void terminaTest(){
        driver.close();
    }
}
