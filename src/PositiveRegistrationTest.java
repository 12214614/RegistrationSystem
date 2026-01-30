import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PositiveRegistrationTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("http://127.0.0.1:5500/registration.html");
        Thread.sleep(2000); // page load

        driver.findElement(By.id("firstName")).sendKeys("Eshwar");
        Thread.sleep(1000);

        driver.findElement(By.id("lastName")).sendKeys("Teja");
        Thread.sleep(1000);

        driver.findElement(By.id("email")).sendKeys("eshwar@gmail.com");
        Thread.sleep(1000);

        driver.findElement(By.id("phone")).sendKeys("+919876543210");
        Thread.sleep(1000);

        driver.findElement(By.id("password")).sendKeys("Test@123");
        Thread.sleep(1000);

        driver.findElement(By.id("confirmPassword")).sendKeys("Test@123");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("input[value='Male']")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("country")).sendKeys("India");
        Thread.sleep(1000);

        driver.findElement(By.id("state")).sendKeys("Telangana");
        Thread.sleep(1000);

        driver.findElement(By.id("city")).sendKeys("Hyderabad");
        Thread.sleep(1000);

        driver.findElement(By.id("terms")).click();
        Thread.sleep(1500);

        driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(2500); // wait to show success message

        String successText = driver.findElement(By.className("alert-message")).getText();

        if (successText.contains("Registration Successful")) {
            System.out.println("Positive test PASSED");
            ScreenshotUtil.takeScreenshot(driver, "success-state");
        } else {
            System.out.println("Positive test FAILED");
        }

        Thread.sleep(3000); // pause before closing browser
        driver.quit();
    }
}
