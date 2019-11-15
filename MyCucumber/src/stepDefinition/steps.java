package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class steps {
	WebDriver driver;
	@Given("^I am in demo login page$")
	public void i_am_in_demo_login_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/login.html");
		driver.manage().window().maximize();
	}

	@When("^I enter \"([^\"]*)\" for login$")
	public void i_enter_for_login(String arg1) throws Throwable {
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(arg1);
		driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("12345678");
		driver.findElement(By.xpath("//*[@id='SubmitLogin']")).click();
	}


	@Then("^I verify the \"([^\"]*)\" after login$")
	public void i_verify_the_after_login(String arg1){
		try{
			String msg = driver.findElement(By.xpath("/html/body/div[2]/div/div/h3")).getText();
			Assert.assertEquals(msg, arg1);
			System.out.println("Title Verfication Successful");
		}
		catch(Exception e)
		{
			System.out.println("Title Verfication Unsuccessful");
		}
	}
	
	@Then("^Logout from demo site$")
	public void logout_from_demo_site() throws Throwable {
	    driver.quit();
	}



}
