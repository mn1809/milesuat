package com.miles.PageLibRepo;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.miles.PageObjectRepo.ForgotPasswordPageObj;
import com.miles.PageObjectRepo.HeartHealthPageObj;
import com.miles.PageObjectRepo.LiveUsersPageObj;
import com.miles.PageObjectRepo.ShareActivityPageObj;
import com.miles.Utilities.MilesUtilities;

public class ShareActivityPageLib extends ShareActivityPageObj
{
	
	
	//	Constructor
	public ShareActivityPageLib(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public String U17Testing()
	{
		return 	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-light o_switch_view o_list oi oi-view-list')]")).getText();
	}
	
	
	public String getDurationValue()
	{
		//return Duriation.getText();
		return driver.findElement(By.id("wd-duration-value")).getText();
	}
	
	public String getHeartRateValue() 
	{
		
		//return HRValue.getText();
		return driver.findElement(By.id("wd-heart-rate-value")).getText();
	}
	
	public String getBRValue() 
	{
//		driver.navigate().refresh();
//		//return BRValue.getText();
//		
	
		return driver.findElement(By.id("wd-breathing-rate-value")).getText();
	}

	public String getStrainValue()
	{
		return driver.findElement(By.id("wd-heart-strain-value")).getText();
	}
	public String getTrainingValue()
	{
		return driver.findElement(By.id("wd-training-load-value")).getText();
	}
	public String getHRVValue()
	{
		return driver.findElement(By.id("wd-hrv-value")).getText();
	}


	public String getBodyShock()
	{
		return driver.findElement(By.id("wd-body-shock-value")).getText();
	}
	
	public String getCadenceValue()
	{
		return driver.findElement(By.id("wd-step-cadence-value")).getText();
	}
	
	public void ClickOnAvgTab()
	{
		AvgTab.click();
	}
	
	public void ClickOnMaxTab()
	{
		MaxTab.click();
	}
	public void ClickOnMinTab()
	{
		MinTab.click();
	}
	
	public String getTrainingTime()
	{
		return TrainingTime.getText();
	}
	
	//public String getStartTimeOnGraph()
	{
		//return StartTimeOfActivityOnGraph.getText();
	}
	
	
	public void WaitForShareActivityPageToLoad()
	{
		MilesUtilities.WaitForVisibilityOfElement(driver, "xPath", "//*[contains(@class, 'dygraph-axis-label dygraph-axis-label-x')]");
	}
	
}
