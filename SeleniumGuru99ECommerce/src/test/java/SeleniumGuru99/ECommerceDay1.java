package SeleniumGuru99;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ECommerceDay1 {
	static ExtentTest test;
	static ExtentReports report;
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults1.html");
		test = report.startTest("MySelenium1");
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.getTitle().equals("This is demo site"))
		{
			test.log(LogStatus.PASS, "Home Page title verified successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Home Page title verified unsuccessfully");
		}
	}
	
	@Test
	public void Test()
	{
		driver.findElement(By.xpath("//*[@id='nav']/ol/li[1]/a")).click();
		if(driver.getTitle().equals("Mobile"))
		{
			test.log(LogStatus.PASS, "Mobile title verified successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Mobile title verified unsuccessfully");
		}

		Select sel = new Select(driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")));
		sel.selectByVisibleText("Name");
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/h2/a")).getText());
		arr.add(driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[2]/div/h2/a")).getText());
		arr.add(driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[3]/div/h2/a")).getText());
		
		ArrayList<String> arr1 = arr;
		Collections.sort(arr);
		if(arr==arr1)
			test.log(LogStatus.PASS, "Sorted Successfully");
		else
			test.log(LogStatus.FAIL, "Sorted Unsuccessfully");
	}
	
	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}

}
