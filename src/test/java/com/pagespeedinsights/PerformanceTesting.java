package com.pagespeedinsights;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import genericUtility.BaseTest;
import genericUtility.ExcelUtility;
import genericUtility.ListenerImplementation;
import genericUtility.WebDriverUtility;
@Listeners(genericUtility.ListenerImplementation.class)
public class PerformanceTesting extends BaseTest {

	int i;
	@Test(dataProvider = "mobileTestLink")
	public void mobileLinkPerformanceTest(String link) throws InterruptedException, Throwable {
		driver.findElement(By.xpath("//input[@id='i4']")).sendKeys(link, Keys.ENTER);
		ListenerImplementation.test.info("Link Entered Successfully", MediaEntityBuilder.createScreenCaptureFromBase64String(WebDriverUtility.getScreenshotAsBase64(driver)).build());
		WebElement scroll = driver.findElement(By.xpath("(//a[@class='eNGozb'])[4]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",scroll);
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(40));
		wt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@jsname='mxdR1e'])[1]"))));
		Thread.sleep(2000);
		String mobilePerformanceScore = driver.findElement(By.xpath("(//div[@class='lh-gauge__percentage'])[5]")).getText();
		ListenerImplementation.test.info("Link ="+link+" || Mobile Score = "+mobilePerformanceScore, MediaEntityBuilder.createScreenCaptureFromBase64String(WebDriverUtility.getScreenshotAsBase64(driver)).build());
		System.out.println(mobilePerformanceScore);
		
	}
			
		
	
	@Test(dataProvider = "desktopTestLink")
	public void desktopLinkPerformanceTest(String link) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='i4']")).sendKeys(link, Keys.ENTER);
		ListenerImplementation.test.info("Link Entered Successfully", MediaEntityBuilder.createScreenCaptureFromBase64String(WebDriverUtility.getScreenshotAsBase64(driver)).build());
		driver.findElement(By.xpath("//button[@id='desktop_tab']")).click();
		WebElement scroll = driver.findElement(By.xpath("(//div[@class='ihA92 ucyZQe'])[2]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)",scroll);
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(40));
		wt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@jsname='mxdR1e'])[2]"))));
		Thread.sleep(2000);
		String desktopPerformanceScore = driver.findElement(By.xpath("(//div[@class='lh-gauge__percentage'])[16]")).getText();
		ListenerImplementation.test.info("Link ="+link+" || Desktop Score = "+desktopPerformanceScore, MediaEntityBuilder.createScreenCaptureFromBase64String(WebDriverUtility.getScreenshotAsBase64(driver)).build());
	}
	
	@DataProvider
	public Object[][] mobileTestLink() throws Throwable {
		return ExcelUtility.getMultipleDataFromExcel("Sheet7");
	}
	
	@DataProvider
	public Object[][] desktopTestLink() throws Throwable {
		return ExcelUtility.getMultipleDataFromExcel("Sheet7");
	}
}
