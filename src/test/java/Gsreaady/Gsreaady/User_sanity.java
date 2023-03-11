package Gsreaady.Gsreaady;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class User_sanity {
	WebDriver driver;

	@BeforeMethod
	public void launcBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Lenovo\\Desktop\\JAVA\\chromedriver_win32\\ChromeDriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

	@Test(enabled = false)
	public void verifyLaunchApp() throws InterruptedException {
		driver.get("http://gcreddy.com/project");
		Thread.sleep(3000);
		String pagetitle = driver.getTitle();
		Assert.assertEquals("GCR Shop", pagetitle);
	}

	@Test(enabled = false)
	public void verifyElements() throws InterruptedException {
		driver.get("http://gcreddy.com/project/");
		Thread.sleep(3000);
		boolean element1 = driver.findElement(By.linkText("create an account")).isDisplayed();
		Assert.assertEquals(element1, true);
		boolean element2 = driver.findElement(By.linkText("login")).isDisplayed();
		Assert.assertEquals(element2, true);
	}
	@Test(enabled = false)
	public void verifyCustomerReg() throws InterruptedException {
		driver.get("https://gcreddy.com/project/create_account.php");
		driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/form/div/div[2]/table/tbody/tr[1]/td[2]/input[2]")).click();
		driver.findElement(By.name("firstname")).sendKeys("ABCD");
		driver.findElement(By.name("lastname")).sendKeys("Asmita");
		driver.findElement(By.id("dob")).sendKeys("10/10/1990");

		driver.findElement(By.name("email_address")).sendKeys("asmiaro2020@gmail.com");
		driver.findElement(By.name("street_address")).sendKeys("abcd sdf");
		driver.findElement(By.name("postcode")).sendKeys("500072");
		driver.findElement(By.name("city")).sendKeys("Hyderabad");
		driver.findElement(By.name("state")).sendKeys("Telangana");
		Select dropDown=new Select(driver.findElement(By.name("country")));
		dropDown.selectByVisibleText("India");
		driver.findElement(By.name("telephone")).sendKeys("9209348689");
		driver.findElement(By.name("password")).sendKeys("Abcd@123");
		driver.findElement(By.name("confirmation")).sendKeys("Abcd@123");
		driver.findElement(By.id("tdb4")).click();
		driver.findElement(By.name("password")).sendKeys("abcd321");
		driver.findElement(By.name("confirmation")).sendKeys("abcd321");
		driver.findElement(By.id("tdb4")).click();
		Thread.sleep(3000);
		String message = driver.findElement(By.tagName("h1")).getText();
		//System.out.println(message);

		Assert.assertEquals(message, "Your Account Has Been Created!");
		}
	@Test(enabled = false)
	public void verifyLogin() {
		driver.get("https://gcreddy.com/project/login.php");
		driver.findElement(By.name("email_address")).sendKeys("asmiaro2020@gmail.com");
		driver.findElement(By.name("password")).sendKeys("abcd321");
		driver.findElement(By.id("tdb1")).click();
		boolean elementExits=driver.findElement(By.linkText("Log Off")).isDisplayed();
		Assert.assertEquals(elementExits,true);
		}
	@Test(enabled = false)
	public void verifyShopingcart() {
		driver.get("https://gcreddy.com/project/index.php");
		driver.findElement(By.xpath("//*[@id=\"columnRight\"]/div/div[1]/a")).click();
				String message = driver.findElement(By.xpath("//*[@id=\"bodyContent\"]/div/div")).getText();
				//System.out.println(message);
				Assert.assertTrue(message.contains("Your Shopping Cart is empty!"));
	}
	@Test(priority = 6)
public void checkOut() throws InterruptedException {
	driver.get("https://gcreddy.com/project/index.php");
	driver.findElement(By.linkText("login")).click();
	driver.findElement(By.name("email_address")).sendKeys("asmiaro2020@gmail.com");
	driver.findElement(By.name("password")).sendKeys("abcd321");
	driver.findElement(By.id("tdb1")).click();
	driver.findElement(By.xpath("//*[contains(text(),'Shopping Cart')]")).click();
	driver.findElement(By.id("tdb5")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("tdb6")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("tdb6")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("tdb6")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("tdb5")).click();
	
}
}
