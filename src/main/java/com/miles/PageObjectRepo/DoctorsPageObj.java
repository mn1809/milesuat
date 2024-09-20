package com.miles.PageObjectRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.miles.BaseSettings.MilesBasePage;




public class DoctorsPageObj extends MilesBasePage
{

	   public DoctorsPageObj(WebDriver driver) 
	   {
		super(driver);
		// TODO Auto-generated constructor stub
	   }

	   protected List <WebElement> UserRecords = driver.findElements(By.className("longContentControl"));
	   protected WebElement SearchBox = driver.findElement(By.className("form-search"));
	   protected WebElement AddNewUserBtn = driver.findElement(By.id("addNewPatientBtn"));
	   protected WebElement LiveUserDashboardBtn = driver.findElement(By.id("liveUserDashboardBtn"));
	   protected WebElement Indexing = driver.findElement(By.className("float-right"));
	   protected WebElement Tableheader = driver.findElement(By.id("myHeader"));
	   
	   protected WebElement RefreshIcon = driver.findElement(By.className("inner"));
	   
	   protected WebElement EmailIdPopUp = driver.findElement(By.className("edit-name"));

}
