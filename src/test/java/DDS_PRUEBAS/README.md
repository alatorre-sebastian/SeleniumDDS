# Practica 8
## Se utilizo PAGE-OBJECT-MODEL para realizar esta practica.

Documentacion sobre [Page-Object-Model](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

### Visión general
Page Object es un patrón de diseño que se ha hecho popular en la automatización de pruebas para mejorar el mantenimiento de pruebas y reducir la duplicación de código. Un *Page object* es una ***clase orientada a objetos*** que sirve de interfaz. Las pruebas utilizan los métodos de esta page object class siempre que se necesite interactuar con la interfaz de usuario de esa página. La ventaja es que si la interfaz de usuario de la página cambia, las pruebas no tienen que cambiar, sólo el código del objeto de página. Posteriormente, todos los cambios para dar soporte a esa nueva interfaz de usuario se encuentran en un único lugar.

### Ventajas
- Existe una separación clara entre el código de prueba y el código específico de la página, como los localizadores y el diseño.
- Existe un único repositorio para los servicios u operaciones que ofrece la página, en lugar de tener estos servicios dispersos a lo largo de las pruebas.
En ambos casos, esto permite que cualquier modificación necesaria debido a cambios en la interfaz de usuario se realice en un único lugar.

## Ejemplo
Este es un ejemplo tipico donde NO se utiliza page-object-model
```java
/***
 * Tests login feature
 */
public class Login {

  public void testLogin() {
    // fill login data on sign-in page
    driver.findElement(By.name("user_name")).sendKeys("userName");
    driver.findElement(By.name("password")).sendKeys("my supersecret password");
    driver.findElement(By.name("sign-in")).click();
  }
}
}
``` 
### Con Page-object-model
#### loginPage:
```java
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object encapsulates the Sign-in page.
 */
public class SignInPage {
  protected WebDriver driver;

  // <input name="user_name" type="text" value="">
  private By usernameBy = By.name("user_name");
  // <input name="password" type="password" value="">
  private By passwordBy = By.name("password");
  // <input name="sign_in" type="submit" value="SignIn">
  private By signinBy = By.name("sign_in");

  public SignInPage(WebDriver driver){
    this.driver = driver;
     if (!driver.getTitle().equals("Sign In Page")) {
      throw new IllegalStateException("This is not Sign In Page," +
            " current page is: " + driver.getCurrentUrl());
    }
  }
  public HomePage loginValidUser(String userName, String password) {
    driver.findElement(usernameBy).sendKeys(userName);
    driver.findElement(passwordBy).sendKeys(password);
    driver.findElement(signinBy).click();
    return new HomePage(driver);
  }
}
``` 

