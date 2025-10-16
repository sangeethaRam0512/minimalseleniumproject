package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
import org.testng.annotations.Listeners;
@Listeners(utils.TestListener.class)
public class AddToCart {
public WebDriver driver;
private ExtentReports extent;
private ExtentTest test;
@BeforeTest
public void setUpReport() {
    extent = ExtentReportManager.getInstance();
}

@BeforeClass
 public void setup()
 
 {
	WebDriverManager.chromedriver().setup();
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 
 }

	
@Test(priority = 1)
public void login()

{
	test = extent.createTest("login account");
	
	driver.get("https://www.saucedemo.com/");
	test.log(Status.INFO, "Opened SauceDemo");
	driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	driver.findElement(By.name("login-button")).click();
	test.log(Status.PASS, "Logged in successfully");
	
	}
@Test(priority = 2, dependsOnMethods = "login")
public void verifyTitle() throws Exception {
	Thread.sleep(2000);
	test = extent.createTest("Verify Title");
	Assert.assertEquals(driver.getTitle(), "Swag Labs", "Page title is not as expected.");
	System.out.println("✅ Page title is correct: 'Swag Labs'");
	 test.log(Status.PASS, "Page title is correct");
	 
}
@Test(priority = 3, dependsOnMethods = "login")
public void addTocart() throws Exception {
	ExtentTest test = extent.createTest("Add to Cart Test");
	Thread.sleep(2000); 
	  List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
      boolean itemFound = false;
      for (WebElement item : items) {
          if (item.getText().equalsIgnoreCase("Sauce Labs Backpack")) {
              itemFound = true;
              test.log(Status.INFO, "'Sauce Labs Backpack' is present");
              break;
          } Assert.assertTrue(itemFound, "'Sauce Labs Backpack' was not found.");
          test.log(Status.PASS, "✅ 'Sauce Labs Backpack' is listed after login.");
      }
	
}
@AfterTest
public void flushReport() {
    extent.flush();
}


@AfterClass
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }

}}

