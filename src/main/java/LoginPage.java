import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost:9091/");
    }

    public void openRegistration() { driver.get("http://localhost:9091/registration"); }

    public void openProfile() {driver.get("http://localhost:9091/profile");}

    public void logout() {driver.get("http://localhost:9091/logout");}
    public void admin() {driver.get("http://localhost:9091/admin");}

    public boolean isLoaded() throws InterruptedException {
        Thread.sleep(1000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"fh5co-page\"]/div[2]/div[2]/div/div/div/div[1]/div/div"))).isDisplayed();

    }

    public void profile(String name, String pass) throws InterruptedException{
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div[2]/div/div[1]/div/input")).sendKeys(name);
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div[2]/div/div[2]/div/input")).sendKeys(pass);

    }

    public void reservation(String name, String email, String telephone, String time) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"from-place\"]")).sendKeys(name);
       // Thread.sleep(2000);
        driver.findElement(By.id("to-place")).sendKeys(email);
       // Thread.sleep(2000);
        driver.findElement(By.id("telephone")).sendKeys(telephone);
       // Thread.sleep(2000);
        driver.findElement(By.id("date-start")).click();
       // Thread.sleep(2000);
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[4]/td[4]")).click();
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[4]/div/input")).click();
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[4]/div/input")).sendKeys(time);
       // Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[5]/section/div/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[5]/section/div/div/div/ul/li[1]")).click();
       // Thread.sleep(2000);
        //clenovi
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[6]/section/div/span")).click();
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[6]/section/div/div/ul/li[3]")).click();
        //Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"flights\"]/div/form/div[8]/input")).click();
        //Thread.sleep(2000);
    }

    public void registration(String username, String email, String password) throws InterruptedException {
        driver.get("http://localhost:9091/registration");
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div/div/div[1]/div/input")).sendKeys(username);
       // Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div/div/div[2]/div/input")).sendKeys(email);
       // Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div/div/div[3]/div/input")).sendKeys(password);
       // Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div/div/div[4]/div/input")).click();
       // Thread.sleep(2000);
    }
    public void login(String username, String password) throws InterruptedException {
        driver.get("http://localhost:9091/login");
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div[2]/div/div[1]/div/input")).sendKeys(username);
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div[2]/div/div[2]/div/input")).sendKeys(password);
        // Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"fh5co-contact\"]/div/form/div/div[2]/div/div[3]/div/input")).click();
        // Thread.sleep(2000);
    }


}
