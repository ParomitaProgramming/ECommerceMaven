package SeleniumGuru99;
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

public class ECommerceDay3 {

	ExtentReports report;
	ExtentTest test;
	WebDriver driver;

	@BeforeTest
	public void beforeTest()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtenrReportResults3.html");
		test = report.startTest("ECommerce3");
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
			String product = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li["+i+"]/div/h2/a")).getText();
			if(product.equalsIgnoreCase("Sony Xperia"))
			{
				driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/ul/li[1]/div/div[3]/button/span/span")).click();
				driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();
				driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).sendKeys("500");
				driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/button/span/span")).click();
				try{
					String msg = driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[2]/p")).getText();
					if(msg.trim().equalsIgnoreCase("The maximum quantity allowed for purchase is 500"))
					{
						test.log(LogStatus.FAIL, "The maximum quantity allowed for purchase is 500");
					}
				}
				catch(Exception e)
				{
					test.log(LogStatus.PASS, "Quantity has been updated");
				}
				
				driver.findElement(By.xpath("//*[@id='empty_cart_button']/span/span")).click();
				String emptyCartMsg = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div[2]/p[1]")).getText();
				if(emptyCartMsg.trim().equalsIgnoreCase("You have no items in your shopping cart."))
				{
					test.log(LogStatus.PASS, "You have no items in your shopping cart.");
				}
				break;
			}
		}
		
	}
	
	@AfterTest
	public void afterTest()
	{
		report.endTest(test);
		report.flush();
		driver.quit();
	}



}
