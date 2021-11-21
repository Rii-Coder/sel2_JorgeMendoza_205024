/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author R2
 */
public class PruebasCheckoutTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        //Cambiar la ubicación del driver con la ubicación en su computadora
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\R2\\Desktop\\SELENIUM\\browser\\chromedriver.exe");
        //Se crea la instancia para poder utilizar el buscador Chrome
        driver = new ChromeDriver();
        //Maximiza el tamaño de la ventana
        driver.manage().window().maximize();
        //Regla otorgada para esperar 20 segundos (máximo) en lo que carga la página
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownClass() {
        //Cierra la ventana del buscador
        driver.quit();
    }

    @Before
    public void setUp() {
        driver.get("file:///D:/ProyectosNetbeans/sel2_JorgeMendoza_205024/paginaPrueba/ejemploCheckout.html");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void pruebaPagina() {
        System.out.println("Prueba el título de la página web");

        String titulo = driver.getTitle();

        System.out.println("El título es : " + titulo);
        assertEquals("Checkout example for Bootstrap", titulo);
    }

    @Test
    public void pruebaVerificarVacios() {
        System.out.println("Verifica que ningún campo pueda quedar vacío");

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        //Obtiene los elementos con los mensajes de Error
        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));
        //Lista para agregar los mensajes de errores
        List<String> msjErrores = new ArrayList<>();
        //Obtiene cada uno de los errores y los agrega a la lista
        for (WebElement elementoError : listaErrores) {
            String msj = elementoError.getText();
            msjErrores.add(msj);
        }

        //Verifica todos los mensajes de error
        assertEquals("Valid first name is required.", msjErrores.get(0));
        assertEquals("Valid last name is required.", msjErrores.get(1));
        assertEquals("Your username is required.", msjErrores.get(2));
        assertEquals("Please enter your shipping address.", msjErrores.get(4));
        assertEquals("Please select a valid country.", msjErrores.get(5));
        assertEquals("Please provide a valid state.", msjErrores.get(6));
        assertEquals("Zip code required.", msjErrores.get(7));
        assertEquals("Name on card is required", msjErrores.get(8));
        assertEquals("Credit card number is required", msjErrores.get(9));
        assertEquals("Expiration date required", msjErrores.get(10));
        assertEquals("Security code required", msjErrores.get(11));
    }

    @Test
    public void pruebaVerificarLlenos() {
        System.out.println("Verifica campos llenados correctamente");

        driver.findElement(By.id("firstName")).sendKeys("Javier");
        driver.findElement(By.id("lastName")).sendKeys("Hernandez");
        driver.findElement(By.id("username")).sendKeys("R2D2cx");
        driver.findElement(By.id("email")).sendKeys("mendoza1305@gmail.com");
        driver.findElement(By.id("address")).sendKeys("De la cañada, 548, monte carlos");
        driver.findElement(By.id("address2")).sendKeys("De la cañada, 545, monte carlos");
        driver.findElement(By.id("country")).sendKeys("United States");
        driver.findElement(By.id("state")).sendKeys("Chihuahua");
        driver.findElement(By.id("zip")).sendKeys("85132");
        driver.findElement(By.id("cc-name")).sendKeys("Jorge Arturo Mendoza Valdez");
        driver.findElement(By.id("cc-number")).sendKeys("1234 5678 9101 1121");
        driver.findElement(By.id("cc-expiration")).sendKeys("03/23");
        driver.findElement(By.id("cc-cvv")).sendKeys("123");
        
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        //Obtiene los elementos con los mensajes de Error
        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));
        //Lista para agregar los mensajes de errores
        List<String> msjErrores = new ArrayList<>();
        //Obtiene cada uno de los errores y los agrega a la lista
        for (WebElement elementoError : listaErrores) {
            String msj = elementoError.getText();
            msjErrores.add(msj);
        }

        //Verifica todos los mensajes de error
        assertNotEquals("Valid first name is required.", msjErrores.get(0));
        assertNotEquals("Valid last name is required.", msjErrores.get(1));
        assertNotEquals("Your username is required.", msjErrores.get(2));
        assertNotEquals("Please enter a valid email address for shipping updates.", msjErrores.get(3));
        assertNotEquals("Please enter your shipping address.", msjErrores.get(4));
        assertNotEquals("Please select a valid country.", msjErrores.get(5));
        assertNotEquals("Please provide a valid state.", msjErrores.get(6));
        assertNotEquals("Zip code required.", msjErrores.get(7));
        assertNotEquals("Name on card is required", msjErrores.get(8));
        assertNotEquals("Credit card number is required", msjErrores.get(9));
        assertNotEquals("Expiration date required", msjErrores.get(10));
        assertNotEquals("Security code required", msjErrores.get(11));
    }

    @Test
    public void verificarPrimerNombreIncorrecto() {
        System.out.println("Verifica que el campo de texto de primer nombre solo tenga letras y no más de 50 letras");

        String nombreIncorrecto = "12345678910asbdsadaksdlajsdkljaksdjasldjalksd"
                + "jaksldjkalsdjkalsjdkalsjdklasjdklasjdklj123123123123123123lkaj"
                + "sdkasjldkjaskdlajsldkasjdlkajsdlkjaksld123123123123awsdasdasda"
                + "123123123123121212121212121212121212121212121212312312312321312";

        driver.findElement(By.id("firstName")).sendKeys(nombreIncorrecto);

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("col-md-6"));

        String msj = "";

        for (WebElement elementoError : listaErrores) {
            msj = elementoError.findElement(By.className("invalid-feedback")).getText();
            if (msj.equalsIgnoreCase("Valid first name is required.")) {
                break;
            }
        }
        assertEquals("Valid first name is required.", msj);
    }

    @Test
    public void verificarPrimerNombreCorrecto() {
        System.out.println("Verifica que el campo de texto de primer nombre acepte nombres correctos");

        String nombreCorrecto = "Javier";

        driver.findElement(By.id("firstName")).sendKeys(nombreCorrecto);

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("col-md-6"));

        String msj = "";

        for (WebElement elementoError : listaErrores) {
            msj = elementoError.findElement(By.className("invalid-feedback")).getText();
            if (msj.equalsIgnoreCase("Valid first name is required.")) {
                break;
            }
        }
        assertNotEquals("Valid first name is required.", msj);
    }

    @Test
    public void verificarSegundoNombreIncorrecto() {
        System.out.println("Verifica que el campo de texto de segundo nombre solo tenga letras y no más de 50 letras");

        String nombreIncorrecto = "12345678910asbdsadaksdlajsdkljaksdjasldjalksd"
                + "jaksldjkalsdjkalsjdkalsjdklasjdklasjdklj123123123123123123lkaj"
                + "sdkasjldkjaskdlajsldkasjdlkajsdlkjaksld123123123123awsdasdasda"
                + "123123123123121212121212121212121212121212121212312312312321312";

        driver.findElement(By.id("lastName")).sendKeys(nombreIncorrecto);

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("col-md-6"));

        String msj = "";

        for (WebElement elementoError : listaErrores) {
            msj = elementoError.findElement(By.className("invalid-feedback")).getText();
            if (msj.equalsIgnoreCase("Valid last name is required.")) {
                break;
            }
        }
        assertEquals("Valid last name is required.", msj);

    }

    @Test
    public void verificarSegundoNombreCorrecto() {
        System.out.println("Verifica que el campo de texto de segundo nombre acepte nombres correctos");

        String nombreCorrecto = "Felipe";

        driver.findElement(By.id("lastName")).sendKeys(nombreCorrecto);

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("col-md-6"));

        String msj = "";

        for (WebElement elementoError : listaErrores) {
            msj = elementoError.findElement(By.className("invalid-feedback")).getText();
            if (msj.equalsIgnoreCase("Valid last name is required.")) {
                break;
            }
        }
        assertNotEquals("Valid last name is required.", msj);
    }

    @Test
    public void verificarEmailIncorrecto() {
        System.out.println("Verifica que el campo de texto de email no acepte emails incorrectos");

        String nombreIncorrecto = "aasdasd123";

        driver.findElement(By.id("email")).sendKeys(nombreIncorrecto);

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Please enter a valid email address for shipping updates.")) {
                break;
            }
        }
        assertEquals("Please enter a valid email address for shipping updates.", msj);
    }

    @Test
    public void verificarEmailCorrecto() {
        System.out.println("Verifica que el campo de texto de email acepte emails correctos");

        String nombreCorrecto = "jamv1305@gmail.com";

        driver.findElement(By.id("email")).sendKeys(nombreCorrecto);

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";

        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Please enter a valid email address for shipping updates.")) {
                break;
            }
        }
        assertNotEquals("Please enter a valid email address for shipping updates.", msj);
    }

    @Test
    public void verificarEspaciosBlancos() {
        System.out.println("Verifica que ningún campo pueda quedar con un espacio o salto de linea");

        driver.findElement(By.id("firstName")).sendKeys("                      ");
        driver.findElement(By.id("lastName")).sendKeys("                       ");
        driver.findElement(By.id("username")).sendKeys("                       ");
        driver.findElement(By.id("email")).sendKeys("                          ");
        driver.findElement(By.id("address")).sendKeys("                        ");
        driver.findElement(By.id("country")).sendKeys("                        ");
        driver.findElement(By.id("state")).sendKeys("                          ");
        driver.findElement(By.id("zip")).sendKeys("                            ");
        driver.findElement(By.id("cc-name")).sendKeys("                        ");
        driver.findElement(By.id("cc-number")).sendKeys("                      ");
        driver.findElement(By.id("cc-expiration")).sendKeys("                  ");
        driver.findElement(By.id("cc-cvv")).sendKeys("                         ");

        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        //Obtiene los elementos con los mensajes de Error
        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));
        //Lista para agregar los mensajes de errores
        List<String> msjErrores = new ArrayList<>();
        //Obtiene cada uno de los errores y los agrega a la lista
        for (WebElement elementoError : listaErrores) {
            String msj = elementoError.getText();
            msjErrores.add(msj);
        }

        //Verifica todos los mensajes de error
        assertEquals("Valid first name is required.", msjErrores.get(0));
        assertEquals("Valid last name is required.", msjErrores.get(1));
        assertEquals("Your username is required.", msjErrores.get(2));
        assertEquals("Please enter a valid email address for shipping updates.", msjErrores.get(3));
        assertEquals("Please enter your shipping address.", msjErrores.get(4));
        assertEquals("Please select a valid country.", msjErrores.get(5));
        assertEquals("Please provide a valid state.", msjErrores.get(6));
        assertEquals("Zip code required.", msjErrores.get(7));
        assertEquals("Name on card is required", msjErrores.get(8));
        assertEquals("Credit card number is required", msjErrores.get(9));
        assertEquals("Expiration date required", msjErrores.get(10));
        assertEquals("Security code required", msjErrores.get(11));
    }

    @Test
    public void verificarZipIncorrecto() {
        System.out.println("Verifica que el campo de texto de zip no acepte letras");

        String campoIncorrecto = "asdasd";

        driver.findElement(By.id("zip")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Zip code required.")) {
                break;
            }
        }
        assertEquals("Zip code required.", msj);
    }

    @Test
    public void verificarZipCorrecto() {
        System.out.println("Verifica que el campo de texto de zip no acepte letras");

        String campoIncorrecto = "85134";

        driver.findElement(By.id("zip")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Zip code required.")) {
                break;
            }
        }
        assertNotEquals("Zip code required.", msj);
    }

    @Test
    public void verificarNombreTarjetaIncorrecto() {
        System.out.println("Verificar que el campo de texto de nombre de tarjeta no acepte nombres incorrectos");

        String campoIncorrecto = "1234512334123123123";

        driver.findElement(By.id("cc-name")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Name on card is required")) {
                break;
            }
        }
        assertEquals("Name on card is required", msj);
    }

    @Test
    public void verificarNombreTarjetaCorrecto() {
        System.out.println("Verificar que el campo de texto de nombre de tarjeta acepte nombres correctos");

        String campoIncorrecto = "Jorge Arturo Mendoza Valdez";

        driver.findElement(By.id("cc-name")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Name on card is required")) {
                break;
            }
        }
        assertNotEquals("Name on card is required", msj);

    }

    @Test
    public void verificarNumeroTarjetaIncorrecto() {
        System.out.println("Verificar que el campo de texto de numero de tarjeta no acepte campos incorrectos");

        String campoIncorrecto = "asdasdasdasdasdasdasd";

        driver.findElement(By.id("cc-number")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Credit card number is required")) {
                break;
            }
        }
        assertEquals("Credit card number is required", msj);
    }

    @Test
    public void verificarNumeroTarjetaCorrecto() {
        System.out.println("Verificar que el campo de texto de numero de tarjeta acepte campos correctos");

        String campoIncorrecto = "5528 2910 2290 3019";

        driver.findElement(By.id("cc-number")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Credit card number is required")) {
                break;
            }
        }
        assertNotEquals("Credit card number is required", msj);

    }

    @Test
    public void verificarExpiracionTarjetaIncorrecto() {
        System.out.println("Verificar que el campo de texto de expiracion de tarjeta no acepte campos incorrectos");

        String campoIncorrecto = "asdasdasdasdasdasdasd";

        driver.findElement(By.id("cc-expiration")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Expiration date required")) {
                break;
            }
        }
        assertEquals("Expiration date required", msj);
    }

    @Test
    public void verificarExpiracionTarjetaCorrecto() {
        System.out.println("Verificar que el campo de texto de expiracion de tarjeta acepte campos correctos");

        String campoIncorrecto = "08/23";

        driver.findElement(By.id("cc-expiration")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Expiration date required")) {
                break;
            }
        }
        assertNotEquals("Expiration date required", msj);
    }

    @Test
    public void verificarCVVTarjetaIncorrecto() {
        System.out.println("Verificar que el campo de texto de cvv de tarjeta no acepte campos incorrectos");

        String campoIncorrecto = "asdasdasdasdasdasdasd";

        driver.findElement(By.id("cc-cvv")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Security code required")) {
                break;
            }
        }
        assertEquals("Security code required", msj);
    }

    @Test
    public void verificarCVVTarjetaCorrecto() {
        System.out.println("Verificar que el campo de texto de cvv de tarjeta acepte campos correctos");

        String campoIncorrecto = "123";

        driver.findElement(By.id("cc-cvv")).sendKeys(campoIncorrecto);
        driver.findElement(By.xpath("//button[text()='Continue to checkout']")).click();

        List<WebElement> listaErrores = driver.findElements(By.className("invalid-feedback"));

        String msj = "";
        for (WebElement elementoError : listaErrores) {
            msj = elementoError.getText();
            if (msj.equalsIgnoreCase("Security code required")) {
                break;
            }
        }
        assertNotEquals("Security code required", msj);
    }
}
