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
import com.miles.Utilities.MilesUtilities;

public class LiveUserPageLib extends LiveUsersPageObj
{
	
	
	//	Constructor
	public LiveUserPageLib(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickHideMap()
	{
		HideMap.click();
	}
	
	public void clickShowMap()
	{
		ShowMap.click();
	}
	
public void VerifyLiveUserScreenElements()
{	
	Assert.assertTrue(Map.isDisplayed());
	Assert.assertTrue(searchBox.isDisplayed());
	Assert.assertTrue(GroupLiveECGBtn.isDisplayed());
	Assert.assertTrue(ShowMap.isDisplayed());
	Assert.assertTrue(HideMap.isDisplayed());
}

public void clickOnUserCard(int cardIndex)
{
	UserCards.get(cardIndex).click();
}

public void clickOnHideMap()
{
	HideMap.click();
}

	public void clickOnGroupLiveECG()
	{
		GroupLiveECGBtn.click();
	}
	
	public void VerifyMapIsHidden()
	{
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'map-main d-none')]")).isDisplayed(),"Map is Not Displayed"); 
	}
	public void WaitForMapToHide()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'map-main d-none')]"))); //(driver.findElement(By.xpath("//*[contains(@class, 'map-main d-none')]"))));
	}
	
	public void WaitForLiveUserDashboard_isLoaded()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(GroupLiveECGBtn));
	}
	
}
