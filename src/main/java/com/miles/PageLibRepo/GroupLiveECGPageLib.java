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
import com.miles.PageObjectRepo.GroupLiveECGPageObj;
import com.miles.PageObjectRepo.HeartHealthPageObj;
import com.miles.PageObjectRepo.LiveUsersPageObj;
import com.miles.Utilities.MilesUtilities;

public class GroupLiveECGPageLib extends GroupLiveECGPageObj
{
	
	
	//	Constructor
	public GroupLiveECGPageLib(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
	

public void clickOnUserCard(int cardIndex)
{
	UserCards.get(cardIndex).click();
}

public void VerifyGroupLiveECGPageElements()
{
	Assert.assertTrue(ECGGraph.isDisplayed(), "ECG Graph is Displayed");
	Assert.assertTrue(UserName.isDisplayed(), "User Name is Displayed");
	Assert.assertTrue(HRLabl.isDisplayed(), "HR Label is Displayed");
	Assert.assertTrue(HRVal.isDisplayed(), "HR Value is Displayed");
	Assert.assertTrue(HRUnit.isDisplayed(), "HR Unit is Displayed");
	//Assert.assertTrue(UserClose.isDisplayed(), "Close User Button is Displayed");
	Assert.assertTrue(UserCards.size() > 0);
}
	
public void DismissUser()
{
	UserClose.click();
}



public int getUserCount()
{
	return UserCards.size();
}


	public void WaitForGroupLiveECGDashboard_isLoaded()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(UserClose));
	}
	
}
