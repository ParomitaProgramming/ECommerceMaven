package SeleniumGuru99;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ECommerceDay4 {

	ExtentReports reports;
	ExtentTest test;
	WebDriver driver;

	@BeforeTest
	public void beforeTest()
	{
		reports  = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults4.html");
		test = reports.startTest("ECommerceDay4");
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://live.demoguru99.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(driver.getTitle().equalsIgnoreCase("Home page"))
		{
			test.log(LogStatus.PASS, "Home Page title verified successfully");
		}
		else
		{
			test.log(LogStatus.FAIL, "Home Page title verified unsuccessfully");
		}
	}

	@Test
	public void test()
	{
		driver.findElement(By.xpath("//*[@id='nav']/ol/li[1]/a")).click();

		for(int i=1;i<=3;i++)
		{
			if((driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li["+i+"]/div/h2/a")).getText()).equalsIgnoreCase("SONY XPERIA") || (driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li["+i+"]/div/h2/a")).getText()).equalsIgnoreCase("SAMSUNG GALAXY")){
				driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li["+i+"]/div/div[3]/ul/li[2]/a")).click();
			}
		}

		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[3]/div[1]/div[2]/div/button")).click();

		String parentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for(String i : windows)
		{
			if(!(i.equalsIgnoreCase(parentWindow)))
			{
				driver.switchTo().window(i);
				if(driver.getTitle().equalsIgnoreCase("Products Comparison List - Magento Commerce"))
				{
					test.log(LogStatus.PASS, "Window Handling validation successfully");
					
					boolean mobile1 = driver.findElement(By.xpath("//*[@id='product_comparison']/tbody[1]/tr[1]/td[1]/h2/a")).getText().equalsIgnoreCase("SONY XPERIA") || driver.findElement(By.xpath("//*[@id='product_comparison']/tbody[1]/tr[1]/td[1]/h2/a")).getText().equalsIgnoreCase("SAMSUNG GALAXY");
					boolean mobile2 = driver.findElement(By.xpath("//*[@id='product_comparison']/tbody[1]/tr[1]/td[2]/h2/a")).getText().equalsIgnoreCase("SONY XPERIA") || driver.findElement(By.xpath("//*[@id='product_comparison']/tbody[1]/tr[1]/td[2]/h2/a")).getText().equalsIgnoreCase("SAMSUNG GALAXY");
					
					if(mobile1 && mobile2)
					{
						test.log(LogStatus.PASS, "Mobiles are successfully added in compare list");
					}
					else
					{
						test.log(LogStatus.FAIL, "Mobiles are successfully added in compare list");
					}
						
				}
				else
				{
					test.log(LogStatus.FAIL, "Window Handling validation successfully");
				}
				
				
				break;
			}
		}
		driver.close();
	}

	@AfterTest
	public void afterTest()
	{
		reports.endTest(test);
		reports.flush();
		driver.quit();
	}


}
