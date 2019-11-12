package SeleniumGuru99;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ECommerceDay2 {

	ExtentReports report;
	ExtentTest test;
	WebDriver driver;

	@BeforeTest
	public void beforeTest()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults2.html");
		test = report.startTest("ECommerceDay2");
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://live.demoguru99.com/index.php/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.getTitle().equals("Home page"))
		{
			test.log(LogStatus.PASS, "Home page title is verified successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Home page title is verified unsuccessfully");
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void Test() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@id='nav']/ol/li[1]/a")).click();
		int totItems = Integer.parseInt((driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[2]/div/p/strong")).getText()).split(" ")[0]);
		
		
		for(int i=1;i<=totItems;i++)
		{
			String product = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li["+i+"]/div/h2/a")).getText();
			if(product.equalsIgnoreCase("Sony Xperia"))
			{
				String price = driver.findElement(By.xpath("//*[@id='product-price-"+i+"']/span")).getText();
				driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li["+i+"]/div/h2/a")).click();
				Thread.sleep(5000);
				String detailPrice = driver.findElement(By.xpath("//*[@id='product-price-"+i+"']/span")).getText();
				
				if(price.equalsIgnoreCase(detailPrice))
				{
					test.log(LogStatus.PASS, "Amount matched");
				}
				else
				{
					test.log(LogStatus.FAIL, "Amount unmatched");
				}
			}
			break;
		}
	}
	
	@AfterTest
	public void afterTest()
	{
		report.endTest(test);
		report.flush();
	}
}
