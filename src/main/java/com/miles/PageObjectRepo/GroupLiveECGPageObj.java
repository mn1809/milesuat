package com.miles.PageObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;

public class GroupLiveECGPageObj extends MilesBasePage
{
	
	// driver.findElement(By.xpath("//*[contains(@class, 'user-message')]"));
	
	
	protected List <WebElement> UserCards = driver.findElements(By.className("user-card"));
	
	protected WebElement ECGGraph = driver.findElement(By.className("user-stats"));
	protected WebElement UserName = driver.findElement(By.className("user-name"));
	protected WebElement HRLabl = driver.findElement(By.className("user-heartrate-label"));
	protected WebElement HRVal = driver.findElement(By.className("user-heartrate-value"));
	protected WebElement HRUnit = driver.findElement(By.className("user-heartrate-unit"));
	protected WebElement UserClose = driver.findElement(By.className("user-close-button"));//
	//search-button
	
	//	Constructor
	public GroupLiveECGPageObj(WebDriver driver) 
	{
		//PageFactory.initElements(driver, this);
		super(driver);
	}	
}
