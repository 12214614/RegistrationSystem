import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NegativeRegistrationTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://127.0.0.1:5500/registration.html");
        Thread.sleep(2000); // page load pause

        driver.findElement(By.id("firstName")).sendKeys("John");
        Thread.sleep(1000);

        driver.findElement(By.id("email")).sendKeys("john@test.com");
        Thread.sleep(1000);

        driver.findElement(By.id("phone")).sendKeys("+919876543210");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//input[@value='Male']")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("password")).sendKeys("Test@123");
        Thread.sleep(1000);

        driver.findElement(By.id("confirmPassword")).sendKeys("Test@123");
        Thread.sleep(1000);

        driver.findElement(By.id("terms")).click();
        Thread.sleep(1500);

        driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(2000); // wait to see error

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("lastNameError"))
        );

        if (error.isDisplayed()) {
            System.out.println("Negative test PASSED");
            ScreenshotUtil.takeScreenshot(driver, "missing-lastname-error");
        }

        Thread.sleep(3000); // pause before closing browser
        driver.quit();
    }
}
