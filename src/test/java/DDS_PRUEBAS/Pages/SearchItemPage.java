package DDS_PRUEBAS.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class SearchItemPage {

    WebDriver driver;
    By seachBarBy = By.id("cb1-edit");
    By minimumBy = By.xpath("//input[@data-testid='Minimum-price']");
    By maximumBy = By.xpath("//input[@data-testid='Maximum-price']");
    By btnFilterPriceBy = By.xpath("//div[contains(@class, 'price')][.//button[@data-testid='submit-price']]");
    By acceptBtnCookiesBy = By.xpath("//button[text()='Entendido']");

    //Xpath contenedor de todoo el articulo
    By resultsContainerBy = By.xpath("//div[@class='ui-search-result__wrapper shops__result-wrapper']");
    //Xpath hijo donde encontramos el titulo
    By titleResultsBy = By.xpath("child::div[contains(@class,\"ui\")]//a//h2");
    //Xpath hijo donde encontramos el precio
    By priceResultsBy = By.xpath("child::div[contains(@class,\"ui\")]//div/div/div/div/div/span[1]/span[2]/span[2]");
    //Xpath hijo donde encontramos el score
    By scoreResutlsBy =  By.xpath("child::div[contains(@class,\"ui\")]//div[2]/div[2]/div[2]/div/span");



    public SearchItemPage(WebDriver driver) {
        this.driver = driver;

    }

    public void enterItem(String item) {
        WebElement cookiesBtn = driver.findElement(acceptBtnCookiesBy);
        cookiesBtn.click();
        WebElement searchBar = driver.findElement(seachBarBy);
        searchBar.sendKeys(item);
        searchBar.submit();

    }

    public void min_maxFilter(String min, String max) throws InterruptedException {

        WebElement minimum = driver.findElement(minimumBy);
        WebElement maximum = driver.findElement(maximumBy);
        WebElement btnFilter = driver.findElement(btnFilterPriceBy);

        minimum.sendKeys(min);
        TimeUnit.SECONDS.sleep(2);
        maximum.sendKeys(max);
        //Verificamos que el boton pueda ser precionado
        if (btnFilter.isEnabled()){
            btnFilter.click();
        }
    }

    //Clase que utilizaremos para poder imprimier por precio
    class Item {
        private String description;
        private String price;
        private double score;

        public Item(String description, String price, double score) {
            this.description = description;
            this.price = price;
            this.score = score;
        }

        public String getDescription() {
            return description;
        }

        public String getPrice() {
            return price;
        }

        public double getScore() {
            return score;
        }
    }

    public void sortByPrice10Items() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        //Lista secundaria donde guardaremos results para despues acomodarlos por precio
        List<Item> items = new ArrayList<>();

        //Lista de todos los articulos <li>
        List<WebElement> results = driver.findElements(resultsContainerBy);

        //Lista con solo 10 articulos
        List<WebElement> firstTenResults = results.subList(0, Math.min(results.size(), 10));

        double maxScore = -1;
        WebElement recommendedResult = null;
        for (WebElement result : firstTenResults) {
            String description = result.findElement(titleResultsBy).getText();

            //Reemplazamos ,  con .
            String price = result.findElement(priceResultsBy).getText().replace(",",".");
            double priceFromText = Double.parseDouble(price);

            //FIXME quitar // de aqui si queremos imprimir sin hacer sort
            //System.out.println("Descripción: " + description);
            //System.out.println("Precio: " + price);

            double score = 0.0;

            //Hacemos un try-catch por si no encontramos el elemnto del score (las estrellitas)
            try {
                String scoreText = result.findElement(scoreResutlsBy).getText();

                //Dividimos el texto al encontrar "de", para asi quedarnos solo con la informacion que queremos.
                //EJEMPLO DE LO QUE CONTIENE scoreResultsBy : Calificación 4.5 de 5. 6 opiniones.
                String[] scoreParts = scoreText.split(" de ");

                String scoreValue = scoreParts[0].substring(scoreParts[0].lastIndexOf(" ") + 1);
                score = Double.parseDouble(scoreValue);

                //Fixme
                //System.out.println("Score : "+ scoreValue);

            // Si no encontramos el score, entonces score = 0
            }catch (NoSuchElementException e){
                //Fixme
                //System.out.println("Score : 0.0");
            }
            Item item = new Item(description, price, score);
            items.add(item);

            //Calculamos el puntaje
            double scorePerPrice = score / priceFromText;

            //Si el puntaje es el más alto encontrado hasta ahora, actualizamos la recomendación
            if (scorePerPrice > maxScore) {
                maxScore = scorePerPrice;
                recommendedResult = result;
            }
        }
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return Double.compare(Double.parseDouble(item1.getPrice()), Double.parseDouble(item2.getPrice()));
            }
        });

        for (Item item : items) {
            System.out.println("Descripción: " + item.getDescription());
            System.out.println("Precio: $" + item.getPrice());
            System.out.println("Score : "+ item.getScore());
            System.out.println("=========================");
        }
        if(recommendedResult!=null){
            String description = recommendedResult.findElement(titleResultsBy).getText();
            System.out.println("El artículo recomendado es: " + description);
            System.out.println("**El artiuclo se recomienda calculando el score/precio **");
        } else {
            System.out.println("No se encontró ningún artículo para recomendar.");
        }
    }
}