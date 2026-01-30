import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PositiveRegistrationTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://127.0.0.1:5500/registration.html");

        driver.findElement(By.id("firstName")).sendKeys("Eshwar");
        driver.findElement(By.id("lastName")).sendKeys("Teja");
        driver.findElement(By.id("email")).sendKeys("eshwar@gmail.com");
        driver.findElement(By.id("phone")).sendKeys("+919876543210");
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Test@123");
        driver.findElement(By.cssSelector("input[value='Male']")).click();
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("state")).sendKeys("Telangana");
        driver.findElement(By.id("city")).sendKeys("Hyderabad");
        driver.findElement(By.id("terms")).click();

        driver.findElement(By.id("submitBtn")).click();

        String successText = driver.findElement(By.className("alert-message")).getText();

        if (successText.contains("Registration Successful")) {
            System.out.println("Positive test PASSED");
            ScreenshotUtil.takeScreenshot(driver, "success-state");
        } else {
            System.out.println("Positive test FAILED");
        }

        driver.quit();
    }
}
