package Gsreaady.Gsreaady;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class guru99 {
	WebDriver driver;
	@BeforeMethod
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\Lenovo\\\\Desktop\\\\JAVA\\\\chromedriver_win32\\\\ChromeDriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
@Test(priority = 1)
public void validateTitle() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(3000);
	String Pagetitle=driver.getTitle();
	Assert.assertEquals("Guru99 Payment Gateway", Pagetitle);
}
@Test(priority = 2)
public void validateLink() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(3000);
	String url=driver.getCurrentUrl();
	Assert.assertEquals("https://demo.guru99.com/payment-gateway/purchasetoy.php", url);
}
@Test(priority = 3)
public void clikAndValidateBuyOptions() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(2000);
	driver.findElement(By.className("actions")).click();
	String link=driver.getCurrentUrl();
	Assert.assertEquals("https://demo.guru99.com/payment-gateway/process_purchasetoy.php", link);
	}
@Test(priority = 4)
public void logoIsDisplay() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(3000);
	boolean logo=driver.findElement(By.xpath("//p[@style='text-align:center;']")).isDisplayed();
	Assert.assertEquals(logo,true);
	}
@Test(priority = 5)
public void placeOrder() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(2000);
	driver.findElement(By.className("actions")).click();
	driver.findElement(By.id("card_nmuber")).sendKeys("1234567897643569");
	Select select=new Select(driver.findElement(By.id("month")));
	select.selectByVisibleText("04");
	Select year=new Select(driver.findElement(By.id("year")));
	year.selectByVisibleText("2025");
	driver.findElement(By.id("cvv_code")).sendKeys("055");
	driver.findElement(By.name("submit")).click();
	WebElement msg=driver.findElement(By.xpath("//*[@class='table-wrapper']//h2"));
	String e=msg.getText();
	System.out.println(e);
	Assert.assertEquals("Payment successfull!", e);
}@Test(priority = 6)
public void genrateCreditCard() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='nav']//a[2]")).click();
	Thread.sleep(3000);
	String parent=driver.getWindowHandle();
	Set<String>s=driver.getWindowHandles();
	Iterator<String>I1=s.iterator();
	while(I1.hasNext()) {
		String child_window=I1.next();
		if(!parent.equals(child_window))
		{
			driver.switchTo().window(child_window);
			System.out.println(driver.switchTo().window(child_window).getTitle());
			System.out.println(driver.getCurrentUrl());
		}
		
	}
	WebElement msg = driver.findElement(By.xpath("//*[@id='three']//h2"));
	String e=msg.getText();
	Assert.assertEquals("Here is your New Card", e);
	
}@Test(priority = 7)
public void toCheckTheCvvNumber() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@id='nav']//a[2]")).click();
	Thread.sleep(2000);
	String main=driver.getWindowHandle();
	Set<String>T1=driver.getWindowHandles();
	Iterator<String>S1=T1.iterator();
	while(S1.hasNext()) {
		String Child_window=S1.next();
		if(!main.equals(Child_window)) {
			driver.switchTo().window(Child_window);
		}
	}
	WebElement number = driver.findElement(By.xpath("//*[@id='three']//h4[4]"));
	String n1=number.getText();
	Assert.assertEquals("Credit Limit $100.00", n1);
	}
@Test(priority = 8)
public void toCheckCreditCardLimit() {
	driver.get("https://demo.guru99.com/payment-gateway/check_credit_balance.php");
	driver.findElement(By.xpath("//*[@id='nav']//a[3]")).click();
	driver.findElement(By.id("card_nmuber")).sendKeys("6522625567889345");
	driver.findElement(By.name("submit")).click();
WebElement msg = driver.findElement(By.xpath("//*[@class='inner']//font[2]"));
	String name=msg.getText();
	Assert.assertEquals("This Card Not Any Transactions",name);
	
}@Test(priority = 9)
public void verifyCartOptions() throws InterruptedException {
	driver.get("https://demo.guru99.com/payment-gateway/check_credit_balance.php");
	driver.findElement(By.id("card_nmuber")).sendKeys("6522625567889345");
	driver.findElement(By.name("submit")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//*[@class='actions']//a")).click();
	WebElement name = driver.findElement(By.xpath("//*[@id='three']//h2"));
	String print=name.getText();
	Assert.assertEquals("Mother Elephant With Babies Soft Toy",print);
	
	}
@Test(priority = 10)
public void checkCreditCardBalance() {
	driver.get("https://demo.guru99.com/payment-gateway/check_credit_balance.php");
	driver.findElement(By.name("card_nmuber")).sendKeys("1234567898765432");
	driver.findElement(By.name("submit")).click();
	WebElement num = driver.findElement(By.xpath("//*[@id=\"three\"]/div/div/table/tbody[1]/tr/td[1]/b/font"));
	String number=num.getText();
	Assert.assertEquals("1234567898765432", number);
	
	
}
}
	