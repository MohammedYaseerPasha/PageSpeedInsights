package genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

	public WebDriver driver;
	public static WebDriver sDriver;
	
	@BeforeMethod(alwaysRun = true)
	public void startBrowser() throws IOException {
		
		Properties pro= new Properties();
		FileInputStream fis= new FileInputStream("C://Users//Mohammed.pasha//Downloads//cmi (1)//com.cmi//Global.properties");
		pro.load(fis);
		String browserName= pro.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		driver = new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver= new EdgeDriver();
		}
		else
		{
			System.out.println("Please provide a valid browser name");
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://pagespeed.web.dev/");
		sDriver = driver;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}
}
