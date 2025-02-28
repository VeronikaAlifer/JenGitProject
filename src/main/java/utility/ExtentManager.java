package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static final ExtentReports extentReports = new ExtentReports();

    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
        reporter.config().setDocumentTitle("Test report");
        reporter.config().setReportName("Simple test");
        extentReports.attachReporter(reporter);
    }

    public static ExtentReports getInstance(){
        return extentReports;
    }
}
