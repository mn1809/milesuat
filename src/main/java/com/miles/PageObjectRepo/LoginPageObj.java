package com.miles.PageObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;

public class LoginPageObj extends MilesBasePage
{

	//	Creating WebElements
	@FindBy(id = "login")
	protected WebElement email ;
	
	@FindBy(id = "password")
	protected WebElement password ;
	
	@FindBy(xpath = "//*[contains(@class, 'btn btn-primary btn-block')]")
	protected WebElement loginButton ;
	

	
	//driver.findElement(By.xpath("//*[contains(@class, 'btn btn-primary btn-block')]")).click();

	@FindBy (className = "mini-toastr-notification__message")
	protected WebElement NotificationInfoPopUp;
	
	protected List<WebElement> WarningMsg = driver.findElements(By.className("text-danger"));	
	
	//	Constructor
	public LoginPageObj(WebDriver driver) 
	{
		//PageFactory.initElements(driver, this);
		super(driver);
	}
	
	//	Creating Action Methods
	public HomePageObj login(String emailValue, String passwordValue) throws InterruptedException {
		email.click();
		email.sendKeys(emailValue);
		Thread.sleep(2);
		password.click();
		password.sendKeys(passwordValue);
		loginButton.click();
		return new HomePageObj(driver);
	}
	
	
	
}
