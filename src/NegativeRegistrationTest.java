import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;

public class NegativeRegistrationTest {

    public static void main(String[] args) throws Exception {

        // Selenium Manager will handle ChromeDriver automatically
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("http://127.0.0.1:5500/registration.html");

        Thread.sleep(2000);

        driver.findElement(By.id("firstName")).sendKeys("John");
        driver.findElement(By.id("email")).sendKeys("john@test.com");
        driver.findElement(By.id("phone")).sendKeys("+919876543210");
        driver.findElement(By.xpath("//input[@value='Male']")).click();
        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Test@123");
        driver.findElement(By.id("terms")).click();

        driver.findElement(By.id("submitBtn")).click();

        Thread.sleep(2000);

        WebElement error = driver.findElement(By.id("lastNameError"));
        if (error.isDisplayed()) {
            System.out.println("Negative test PASSED");
        }

        takeScreenshot(driver, "error-state.png");

        driver.quit();
    }

    static void takeScreenshot(WebDriver driver, String fileName) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(src.toPath(), new File(fileName).toPath());
    }
}
