package com.miles.PageLibRepo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.miles.PageObjectRepo.HeartHealthPageObj;

public class HeartHealthPageLib extends HeartHealthPageObj
{
	
	private String PageTitle = "Cardiac Health Details";
	
	
	//	Constructor
	public HeartHealthPageLib(WebDriver driver)
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}

	
	//	Creating Action Methods
	
	
public void VerifyChartsAreDisplayed() throws InterruptedException
{
	Thread.sleep(7);
	//get window handlers as list
	List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
	//switch to new tab
	driver.switchTo().window(browserTabs .get(1));
	Assert.assertEquals(driver.getTitle(), PageTitle);
	Assert.assertTrue(OtherRhythmChart.isDisplayed(),"Other Rhythm Chart is Displayed");
	Assert.assertTrue(AvgHRChart.isDisplayed(),"Avg HR Chart is Displayed");
	Assert.assertTrue(AvgSTChart.isDisplayed(),"Avg ST Chart is Displayed");
	Assert.assertTrue(AvgHRVChart.isDisplayed(),"Avg HRV Chart is Displayed");
	Assert.assertTrue(AvgBRChart.isDisplayed(),"Avg BR Chart is Displayed");
	driver.close();
	driver.switchTo().window(browserTabs.get(0));
}
	
	
}
