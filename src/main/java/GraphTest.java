import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    private WebDriver driver;
    @BeforeEach
    public void setup() {
        driver = getDriver();
    }
//1 2 3 12 13
    @Test
    public void shouldOpen() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openProfile();
        String[] url = driver.getCurrentUrl().split(";",2);
        assertEquals(url[0], "http://localhost:9091/login");
    }
//1 2 3 4 5 6 7 9 4 5 12 13
    @Test
    public void registerLoginReservationLogout() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Viktorija", "viktorija@gmail.com", "077222333");
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/login");
        loginPage.login("Viktorija", "077222333");
        loginPage.reservation("Viktorija", "viktorija@gmail.com", "0771263456", "20:30");
        loginPage.openProfile();
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/profile");
        loginPage.logout();
        String url[] =  driver.getCurrentUrl().split("=",2);
        assertEquals(url[1], "true");
    }
//1 2 3 4 5 6 7 10 4 5 12 13
    @Test
    public void confirmedReservation() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Viktorija", "viktorija@gmail.com", "077222333");
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/login");
        loginPage.login("Viktorija", "077222333");
        loginPage.reservation("Viktorija", "viktorija@gmail.com", "0771263456", "20:30");
        loginPage.openProfile();
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/profile");
        loginPage.logout();
        loginPage.registration("admin", "admin@gmail.com", "11111111");
        loginPage.login("admin", "11111111");
        loginPage.admin();
        driver.findElement(By.xpath("//*[@id=\"four\"]/section/div/table/tbody/tr/td[6]/form[1]/input[3]")).click();
        loginPage.logout();
        String url[] =  driver.getCurrentUrl().split("=",2);
        assertEquals(url[1], "true");
    }
//1 2 3 4 5 6 8 4 5 12 13
    @Test
    public void reservationWithDifferentSessionInfo() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openRegistration();
        loginPage.registration("Viktorija", "viktorija@gmail.com", "077222333");
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/login");
        loginPage.login("Viktorija", "077222333");
        loginPage.reservation("Marija", "marija@gmail.com", "0771263456", "20:30");
        loginPage.openProfile();
        assertEquals(driver.getCurrentUrl(), "http://localhost:9091/profile");
        loginPage.logout();
        String url[] =  driver.getCurrentUrl().split("=",2);
        assertEquals(url[1], "true");
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