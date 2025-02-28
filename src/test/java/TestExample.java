import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gson.GsonExtentTypeAdapterBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.ExtentManager;

import java.lang.reflect.Method;
import java.time.Duration;

public class TestExample {

    private WebDriver driver;
    private static ExtentReports reports;
    private static ExtentTest test;
    private static final String URL = "https://www.softwaretestinghelp.com";

    @BeforeClass
    public void setUpReports(){
        reports = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(Method method) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        test = reports.createTest(method.getName());
    }

    @Test
    public void openMainPage() {

        test.log(Status.INFO, "открывает основную страницу");
        driver.get(URL);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Software Testing Help";

        test.log(Status.INFO, "сравниваем оглавление страницы");
        Assert.assertTrue(actualTitle.contains(expectedTitle));

        test.pass("Test passed successfully.");
    }


    @Test
    public void testNavigateResourcePage(){
        test.log(Status.INFO, "Starting test: " + test.getStatus());
        driver.get("https://www.softwaretestinghelp.com/resources/");

        test.log(Status.INFO, "Verify resource page");
        String expectedTitle = "Testing Resources";
        String actualTitle = driver.getTitle();

        Assert.assertTrue(actualTitle.contains(expectedTitle), "The expected title does not match the actual title, but should be");
    }

    @AfterMethod
    public void tearsDown(ITestResult result) {
        if(result.getStatus() == ITestResult.SUCCESS){
            test.pass("Test passed successfully.");
        }
        if(result.getStatus() == ITestResult.FAILURE){
            test.fail(result.getThrowable().getMessage());
            test.log(Status.FAIL, result.getThrowable().getMessage());
        }
        if (driver != null) {
            driver.quit();
        }
        reports.flush();
    }
}
