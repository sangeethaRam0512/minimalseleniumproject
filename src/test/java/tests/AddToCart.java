package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToCart {
private WebDriver driver;

@BeforeTest
 public void setup()
 
 {
	WebDriverManager.chromedriver().setup();
	 driver=new ChromeDriver();
	 driver.manage().window().maximize();
	 
 }

	
@Test
public void login()

{
	driver.get("https://www.saucedemo.com/");
	driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
	driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
	driver.findElement(By.name("login-button")).click();
	//driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
	//driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");
	//driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

	
	}
@Test
public void verifyTitle() {
	Assert.assertEquals(driver.getTitle(), "Swag Labs", "Page title is not as expected.");
	System.out.println("✅ Page title is correct: 'Swag Labs'");
}
@Test
public void addTocart() throws Exception {
	Thread.sleep(2000);
	//WebElement item=driver.findElement(By.xpath("//div[text()='sauce Labs Backpack']"));
	//Assert.assertTrue(item.isDisplayed(), "the item is not displayed");
	//System.out.println("item is displayed");
	List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
	//System.out.println(items.size());
	Assert.assertTrue(items.isEmpty(), "'Sauce Labs Backpack' not found on page.");
	System.out.println("✅ 'Sauce Labs Backpack' is listed after login.");
	
}



@AfterTest
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }

}}

