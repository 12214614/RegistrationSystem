import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

public class NegativeRegistrationTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://127.0.0.1:5500/registration.html");

        // Fill form WITHOUT last name (negative test)
        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("email")).sendKeys("john@test.com");
        driver.findElement(By.id("phone")).sendKeys("+919876543210");
        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Test@123");
        driver.findElement(By.id("terms")).click();

        driver.findElement(By.id("submitBtn")).click();

        // Wait for error message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("lastNameError"))
        );

        if (error.isDisplayed()) {
            System.out.println("Negative test PASSED");
            takeScreenshot(driver, "missing-lastname-error.png");
        }

        driver.quit();
    }

    // Reusable screenshot utility
    static void takeScreenshot(WebDriver driver, String fileName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), new File(fileName).toPath());
    }
}
