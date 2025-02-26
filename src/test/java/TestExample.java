import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestExample {

    private WebDriver driver;
    private static final String URL = "https://www.softwaretestinghelp.com";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @Test
    public void openMainPage() {
        driver.get(URL);
        System.out.println(driver.getTitle());
        String actualTitle = driver.getTitle();
        String expectedTitle = "Software Testing Help";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @AfterMethod
    public void tearsDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
