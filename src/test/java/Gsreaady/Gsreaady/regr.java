package Gsreaady.Gsreaady;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.ErrorManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class regr {
	WebDriver driver;
	@BeforeMethod
public void launchBrowser() {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Desktop\\JAVA\\chromedriver_win32\\ChromeDriver.exe");
	driver=new ChromeDriver();
	}
	@AfterMethod
	public void CloseBrowser() {
		driver.close();
	}
	@Test(priority=1)
	public void verifyLaunchApp() throws InterruptedException {
		driver.get("https://gcreddy.com/project/admin/");
		Thread.sleep(2000);
		String PageTitle=driver.getTitle();
		Assert.assertEquals(PageTitle,"osCommerce Online Merchant Administration Tool");
        }
	@Test (priority=2)
	public void verifyElements() throws InterruptedException {
	driver.get("https://gcreddy.com/project/admin/");
	Thread.sleep(2000);

	boolean ele1 = driver.findElement(By.name("username")).isDisplayed();
	Assert.assertEquals(ele1, true);

	boolean ele2 = driver.findElement(By.name("password")).isDisplayed();
	Assert.assertEquals(ele2, true);

	boolean ele3 = driver.findElement(By.id("tdb1")).isDisplayed();
	Assert.assertEquals(ele3, true);
}@Test(priority = 3)
	public void verifyPageReditriction() throws InterruptedException {
		driver.get("https://gcreddy.com/project/admin/");
		Thread.sleep(2000);
		driver.findElement(By.linkText("Online Catalog")).click();
		String url=driver.getCurrentUrl();
		Assert.assertEquals(url,"http://gcreddy.com/project/");
	}
@Test(priority = 4)
public void verifyAdminLogin() throws InterruptedException {
	driver.get("https://gcreddy.com/project/admin/");
	driver.findElement(By.name("username")).sendKeys("gcreddy");
	driver.findElement(By.name("password")).sendKeys("Temp@2020");
	driver.findElement(By.id("tdb1")).click();
	Thread.sleep(2000);
	String url=driver.getCurrentUrl();
	Assert.assertEquals(url,"http://gcreddy.com/project/admin/index.php");
}
@Test(priority = 5)
public void verifyLoggOff() throws InterruptedException {
	driver.get("https://gcreddy.com/project/admin/");
	driver.findElement(By.name("username")).sendKeys("gcreddy");
	driver.findElement(By.name("password")).sendKeys("Temp@2020");
	driver.findElement(By.id("tdb1")).click();
	Assert.assertEquals(driver.getCurrentUrl(),"http://gcreddy.com/project/admin/index.php");
	Thread.sleep(3000);
	driver.findElement(By.linkText("Logoff")).click();
	Thread.sleep(3000);
	Assert.assertEquals(driver.getCurrentUrl(), "http://gcreddy.com/project/admin/login.php");
	
}
@Test(priority = 6)
public void verifyErrorMsg() {
	driver.get("https://gcreddy.com/project/admin/");
	driver.findElement(By.name("username")).sendKeys("abcdef");
	driver.findElement(By.name("password")).sendKeys("Temp$2020");
	driver.findElement(By.id("tdb1")).click();
	String errorMessage=driver.findElement(By.className("messageStackError")).getText();
	Assert.assertTrue(errorMessage.contains("Error: Invalid administrator login attempt."));
	}
@Test(priority = 7)
public void adminLoginLocking() throws IOException, InterruptedException  {
	FileReader file=new FileReader("C:\\Users\\Lenovo\\Desktop\\selinum\\input.txt");
    BufferedReader br= new BufferedReader(file);
    int count=0;
    int iteration=0;
    String line;
    while((line=br.readLine())!=null){
    	count=count+1;
    	if(count>1) {
    		String inputData[] =line.split(",", 2);
    		iteration=iteration+1;
    		driver.get("https://gcreddy.com/project/admin/");
    		driver.findElement(By.name("username")).sendKeys(inputData[0]);
    		driver.findElement(By.name("password")).sendKeys(inputData[1]);
    		driver.findElement(By.id("tdb1")).click();
    		String errormessage=driver.findElement(By.xpath("//td[@class='messageStackError']")).getText();
    	    Thread.sleep(3000);
    	if(iteration<=3) {
    		Assert.assertTrue(errormessage.contains("Error: Invalid administrator login attempt."));
    	}
    	else if(iteration>=3) {
    		Assert.assertTrue(errormessage.contains("Error: The maximum number of login attempts has been reached. Please try again in 5 minutes."));
    		
    	}
    	br.close();
    	file.close();
    	}
    }
    		
    		
    		
    		
    		
    		
    		
    		

}
}

			

			
			
