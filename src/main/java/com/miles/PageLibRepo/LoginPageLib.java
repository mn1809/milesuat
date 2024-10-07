package com.miles.PageLibRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.miles.PageObjectRepo.LoginPageObj;

public class LoginPageLib extends LoginPageObj
{
	
	//	Constructor
	public LoginPageLib(WebDriver driver) 
	{
		super(driver);
		PageFactory.initElements(driver, this); 
		//
	}
	
	//	Creating Action Methods
	public HomePageLib login(String emailValue, String passwordValue) 
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		String URL = driver.getCurrentUrl();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		email.click();
		email.sendKeys(emailValue);
		switch (URL)
		{
		
		case "https://usp-uat.mileseducation.com/web/login" : // Serverless Stage
			System.out.println("Serverless Environment");
			password.click();
			password.sendKeys(passwordValue);
			loginButton.click();
			
			break;
		
		case "https://uspathway.mileseducation.com/web/login" : // Existing Prod 
			System.out.println("Regular Environment");
			//btn btn-login
			//driver.findElement(By.xpath("//*[contains(@class, 'btn btn-login')]")).click();
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
//			driver.findElement(By.id("password")).click();
//password.click();
			password.sendKeys(passwordValue);
			loginButton.click();
			break;
		}
		
		//username.sendKeys(usernameValue);
		
		long startTime = System.currentTimeMillis();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("o_view_nocontent_smiling_face")));
		long endTime = System.currentTimeMillis();
	System.out.println("Script waited for "+((endTime - startTime)/1000) % 60+" Seconds To Load Entire Page for User "+emailValue);
		return new HomePageLib(driver);
	}
	
	public void TryLogin(String usernameValue, String passwordValue) 
	{
		email.sendKeys(usernameValue);
		password.click();
		password.sendKeys(passwordValue);
		loginButton.click();
	}
	
	public void clickOnLogin()
	{
		loginButton.click();
	}
	
	public void VerifyRequiredFiledWarninigMsgs(List<String> Msgs)
	{
		
		
		Assert.assertEquals(getEmailWarningMsg(), Msgs.get(0));
		Assert.assertEquals(getPasswordWarningMsg(), Msgs.get(1));
		
	}
	
	public String getEmailWarningMsg()
	{
		try 
		{
		    return WarningMsg.get(0).getText();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return driver.findElements(By.className("text-danger")).get(0).getText();
		}
	}
	
	public String getPasswordWarningMsg()
	{
		try 
		{
		    return WarningMsg.get(1).getText();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return driver.findElements(By.className("text-danger")).get(1).getText();
		}
	}
	
	public void VerifyUserInfoPopUp(String expectedInfoTxt)
	{
		//Assert.assertEquals(NotificationInfoPopUp.getText(), expectedInfoTxt,"Found Expected text is "+expectedInfoTxt);
		Assert.assertTrue(NotificationInfoPopUp.getText().contains(expectedInfoTxt));
	}
	
	
	public void VerifyLoginPageElements()
	{
		
	//	Assert.assertTrue(loginButton.isDisplayed());
		//Assert.assertTrue(NextBtn.isDisplayed());
		Assert.assertTrue(email.isDisplayed());
		
	}
	
//	public void ClickNextBtn()
//	{
//		NextBtn.click();
//	}
	
}
