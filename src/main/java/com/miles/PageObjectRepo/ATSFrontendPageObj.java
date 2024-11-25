package com.miles.PageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.miles.BaseSettings.MilesBasePage;

public class ATSFrontendPageObj extends MilesBasePage
{


	
	//protected WebElement Duriation = driver.findElement(By.id("wd-duration-value"));
	//protected WebElement TrainingLoad = driver.findElement(By.id("wd-training-load-value"));
	//protected WebElement HRValue = driver.findElement(By.id("wd-heart-rate-value"));
	//protected WebElement BRValue = driver.findElement(By.id("wd-breathing-rate-value"));
	//protected WebElement HrStValue = driver.findElement(By.id("wd-heart-strain-value"));
	//protected WebElement HRVValue = driver.findElement(By.id("wd-hrv-value"));
	//protected WebElement BodyShock = driver.findElement(By.id("wd-body-shock-value"));
	//protected WebElement CadenceValue = driver.findElement(By.id("wd-step-cadence-value"));
	protected WebElement AvgTab = driver.findElement(By.id("wd-avg-tab"));
	protected WebElement MaxTab = driver.findElement(By.id("wd-max-tab"));
	protected WebElement MinTab = driver.findElement(By.id("wd-min-tab"));
	
	//protected WebElement StartTimeOfActivityOnGraph = driver.findElements(By.xpath("//*[contains(@class, 'dygraph-axis-label dygraph-axis-label-x')]")).get(10);
	
	protected WebElement TrainingTime = driver.findElement(By.className("training-time"));
	//	Constructor
	public ATSFrontendPageObj(WebDriver driver) 
	{
		//PageFactory.initElements(driver, this);
		super(driver);
	}	
}
