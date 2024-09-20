package com.miles.PageObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.miles.BaseSettings.MilesBasePage;

public class LiveUsersPageObj extends MilesBasePage
{
	
	// driver.findElement(By.xpath("//*[contains(@class, 'user-message')]"));
	
	protected WebElement Map = driver.findElement(By.className("vue-map-container"));
	
	protected WebElement searchBox = driver.findElement(By.className("form-search"));
	
	protected WebElement GroupLiveECGBtn =  driver.findElement(By.xpath("//*[contains(@class, 'btn all-live-ecg-button')]"));
	
	
	
	protected WebElement ShowMap = driver.findElements(By.className("btn-map")).get(0);
	
	protected WebElement HideMap = driver.findElements(By.className("btn-map")).get(1);
	
	protected List <WebElement> UserCards = driver.findElements(By.className("user-card"));
	//search-button
	
	//	Constructor
	public LiveUsersPageObj(WebDriver driver) 
	{
		//PageFactory.initElements(driver, this);
		super(driver);
	}	
}
