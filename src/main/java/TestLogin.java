import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestLogin {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = getDriver();
    }

    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    public static Collection<Object[]> reservation () {
        return Arrays.asList(new Object[][] {
                {"Kristina", "kristina@gmail.com", "077111222", "20002"},
                {"Marija", "kristina@gmail.com", "077232323", "20002"},
                {"Elena", "kristina@gmail.com", "077145242", "20002"}
        });
    }

    @ParameterizedTest
    @MethodSource("reservation")
    public void multipleReservations(String name, String email, String telephone, String time){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        try {
            assertTrue(loginPage.isLoaded());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            loginPage.reservation(name, email, telephone, time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/?successful=true");
    }
    @Test
    public void reservate() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        //assertTrue(loginPage.isLoaded());
        loginPage.registration("Viktorija", "viktorija@gmail.com", "077222333");
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/login");
    }

    //b=T, c=T, a=T
    @Test
    public void emailAndUsernameExist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Ivana", "ivana@gmail.com", "077222333");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/div/form/p")).getText(), "Веќе постои корисник со исто корисничко име или емаил.");
    }
    //b=F, c=T
    @Test
    public void emailExists() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Sara", "ivana@gmail.com", "077222333");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/div/form/p")).getText(), "Веќе постои корисник со исто корисничко име или емаил.");
    }
    //b=T,c=F
    @Test
    public void usernameExists() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Ivana", "ivana123@gmail.com", "077222333");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/div/form/p")).getText(), "Веќе постои корисник со исто корисничко име или емаил.");
    }
    //b=F,c=F
    @Test
    public void emailAndUsernameDoesNotExist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Nikola", "nikola@gmail.com", "filip123");
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/login");
    }

    //moni

    @Test
    public void passDoesNotExist() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        // tocnata registracija za Ana e pass:123mmm
        loginPage.registration("Ana", "ana@gmail.com", "123mmn");
        assertEquals(driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/div/form/p")).getText(), "Веќе постои корисник со исто корисничко име или емаил.");
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\seleniumProject\\src\\main\\resources\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }

    @Test
    public void registerUser() throws InterruptedException{
    }
    @AfterEach
    public void teardown() {
        driver.quit();
    }

}