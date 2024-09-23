package com.miles.BaseSettings;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class MilesBasePage 

{
	
	protected WebDriver driver ;

	public MilesBasePage(WebDriver driver)
	{
		this.driver = driver ;
		System.out.println("Trying to Initialize :"+this.toString());
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("Elements Initialized  for "+this.toString()+" Successfully");
		
	}
	
	
	public boolean verifyPageTitle(String title)
	{
		//WebDriverWait wait = new WebDriverWait(driver, 20);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.titleIs(title));
	}


@AfterTest
public void EndAutomation()
{
	System.out.print("After Suite : Quitting Driver");
	driver.quit();
}


}
//TESTING//
