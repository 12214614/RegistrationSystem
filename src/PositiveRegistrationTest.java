import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.nio.file.Files;
import java.time.Duration;

public class PositiveRegistrationTest {

    public static void main(String[] args) throws Exception {

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Open your registration page
        driver.get("http://127.0.0.1:5500/registration.html");

        // Fill valid data
        driver.findElement(By.id("firstName")).sendKeys("Eshwar");
        driver.findElement(By.id("lastName")).sendKeys("Teja");
        driver.findElement(By.id("email")).sendKeys("eshwar@gmail.com");
        driver.findElement(By.id("phone")).sendKeys("+919876543210");

        driver.findElement(By.id("password")).sendKeys("Test@123");
        driver.findElement(By.id("confirmPassword")).sendKeys("Test@123");

        driver.findElement(By.cssSelector("input[value='Male']")).click();

        driver.findElement(By.id("country")).sendKeys("India");
        Thread.sleep(500);
        driver.findElement(By.id("state")).sendKeys("Telangana");
        Thread.sleep(500);
        driver.findElement(By.id("city")).sendKeys("Hyderabad");

        driver.findElement(By.id("terms")).click();

        // Click Register
        driver.findElement(By.id("submitBtn")).click();
        Thread.sleep(2000);

        // Validate success message
        String successText = driver.findElement(By.className("alert-message")).getText();

        if (successText.contains("Registration Successful")) {
            System.out.println("Positive test PASSED");

            // Screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), new File("success-state.png").toPath());
        } else {
            System.out.println("Positive test FAILED");
        }

        driver.quit();
    }
}
