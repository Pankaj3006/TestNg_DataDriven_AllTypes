package First_DataDriver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Login {
   
	public WebDriver driver = null;

	@Test(priority=1)
	public void EnterData() throws IOException
	{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("E:\\DRIVER\\TestNG_DataDriver_Project\\DataDriver.Properties");
	prop.load(fis);
	driver.get(prop.getProperty("url"));
	driver.findElement(By.xpath("//input[@id='username']")).sendKeys(prop.getProperty("username"));
	driver.findElement(By.xpath("//input[@id='password']")).sendKeys(prop.getProperty("password"));
	driver.findElement(By.xpath("//input[@id='Login']")).click();
	String text= driver.findElement(By.xpath("//div[@id='error']")).getText();
	System.out.println(text);
	FileOutputStream fos = new FileOutputStream("E:\\DRIVER\\TestNG_DataDriver_Project\\DataDriver.Properties");
	String text1 = (String) prop.setProperty("Errormessage", text);
	prop.store(fos, text1);
	//return text;
	}

	@Test(priority=0)
	public void AccessData() throws IOException 
	{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream("E:\\DRIVER\\TestNG_DataDriver_Project\\DataDriver.Properties");
	prop.load(fis);
	System.out.println(prop.getProperty("username"));
	if (prop.getProperty("Browser").equalsIgnoreCase("chrome")) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
		Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		System.setProperty("webdriver.chrome.driver", "E:\\DRIVER\\Drivers\\Chrome Driver\\chromedriver.exe");
	    ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("useAutomationExtension", false);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}
	}
	
	@AfterTest
	public void closewindow() throws InterruptedException, Exception
	{
		//String text1= EnterData();
		//System.out.println("AfterTest Successfully Return  " + text1);
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("E:\\DRIVER\\TestNG_DataDriver_Project\\DataDriver.Properties");
		prop.load(fis);
		System.out.println("AfterTest Successfully Return  " + prop.getProperty("Errormessage"));
		Thread.sleep(3000);
		driver.close();
		System.out.println("All methods have been Executed");
	}
}
