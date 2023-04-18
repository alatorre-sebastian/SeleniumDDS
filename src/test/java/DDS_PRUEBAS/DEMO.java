package DDS_PRUEBAS;

import org.openqa.selenium.By;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;

public class DEMO {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\SebasOx\\OneDrive\\Documentos\\chromedriver_win32\\chromedriver.exe");

        // Crea una nueva instancia del controlador de Chrome
        WebDriver driver = new ChromeDriver();
        driver.get("http://148.231.9.130:8080/sit-web/security/login");

        driver.findElement(By.id("inputEmail")).sendKeys("sit.soporte@uabc.edu.mx");
        WebElement contra  = driver.findElement(By.id("inputPassword"));
        contra.sendKeys("soporte");
        contra.submit();

        //inicio sesion alumno 360277
        driver.findElement(By.id("identificador")).sendKeys("360277");
        driver.findElement(By.id("btnCambiarUsuario")).click();

        //mouse sobre el menu
        WebElement Tuto = driver.findElement(By.xpath("//span[normalize-space()='Tutorías']"));
        WebElement SolicitarTuto = driver.findElement(By.xpath("//a[@href='/sit-web/solicitarTutoria/']"));
        Actions menu = new Actions(driver);
        menu.moveToElement(Tuto);
        //assertEquals("Solicitar tutoría", driver.findElement(By.xpath("//a[@href='/sit-web/solicitarTutoria/']")).getText());
        Thread.sleep(1000);
        menu.click(SolicitarTuto);
        menu.build().perform();
        Thread.sleep(3000);

        //Solicitar tutoria

        WebElement tipoTutoria = driver.findElement(By.id("tipo_tutoria"));
        Select select = new Select(tipoTutoria);
        WebElement regular = driver.findElement(By.cssSelector("option[value=two]"));
        //tipoTutoria.click();
        select.selectByVisibleText("CONSULTA REGULAR");
        assertTrue(regular.isSelected());
        driver.quit();

        //https://www.youtube.com/watch?v=dbzc9UbFZt8&t=5s&ab_channel=Mukeshotwani

    }

}
