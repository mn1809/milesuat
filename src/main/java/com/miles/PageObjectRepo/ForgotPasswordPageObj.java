package com.miles.PageObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;

public class ForgotPasswordPageObj extends MilesBasePage
{
	
	protected WebElement userMsg = driver.findElement(By.xpath("//*[contains(@class, 'user-message')]"));
	
	
	protected WebElement emailBox = driver.findElement(By.name("email"));
	
	
	protected WebElement sendEmailBtn = driver.findElement(By.cssSelector("input[type='submit']"));
	
	
	//	Constructor
	public ForgotPasswordPageObj(WebDriver driver) 
	{
		//PageFactory.initElements(driver, this);
		super(driver);
	}	
}
