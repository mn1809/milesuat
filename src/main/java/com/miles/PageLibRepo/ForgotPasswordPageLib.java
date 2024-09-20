package com.miles.PageLibRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.miles.PageObjectRepo.ForgotPasswordPageObj;
import com.miles.PageObjectRepo.HeartHealthPageObj;

public class ForgotPasswordPageLib extends ForgotPasswordPageObj
{
	
	
	//	Constructor
	public ForgotPasswordPageLib(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	
public void VerifyForgotPasswordScreen()
{	
	Assert.assertTrue(userMsg.getText().contains("Please enter the email you are registered with"));
	Assert.assertTrue(emailBox.isDisplayed());
	Assert.assertTrue(sendEmailBtn.isDisplayed());
}
	
	public void sendForgotPassEmail(String emailId)
	{
		emailBox.click();
		emailBox.sendKeys(emailId);
		sendEmailBtn.click();
	}
	
	
	public void VerifyEmailSent(String emailId) throws InterruptedException
	{
		sendForgotPassEmail(emailId);
		Thread.sleep(1000);
		Assert.assertTrue(GetUserMsg().contains("An email is sent to your registered email to reset password"));
	}
	protected String GetUserMsg()
	{
		try {
		    return userMsg.getText();
		}
		catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
			return driver.findElement(By.xpath("//*[contains(@class, 'user-message')]")).getText();
		}
	}
	public void VerifyInstructionMsg()
	{
		sendEmailBtn.click();
		Assert.assertEquals(driver.findElement(By.className("text-danger")).getText(), "Email is a required field");
	}
	
}
