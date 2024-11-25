package com.miles.PageLibRepo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.miles.PageObjectRepo.AdminPageObj;
import com.miles.PageObjectRepo.OPTPageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;


public class ATSFrontendPageLib extends OPTPageObj
{
	
	public ATSFrontendPageLib(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
		
	/*
	 * Method to click on Add Coach button
	 */
//	
//	
//	public void ClickOnAddUserButton()
//	{
//		AddNewUserBtn.click();
//	}
//		
//	public void VerifyIfSearchedUserAppeared(String email)
//	{
//		Assert.assertEquals(UserRecords.get(1) .getText(), email,"User Found");
//	}
//	
//	public void VerifyEmailIdPopUpisDisplayed()
//	{
//		Assert.assertEquals(EmailIdPopUp.getText(),"Email Address","Email ID Pop Up is Displayed");
//	}
//	
//	public void WaitForAdminPageToLoad()
//	{
//		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "addNewTrainerBtn");
//	}
//	
//	public void SelectDisplyedUser()
//	{
//		UserRecords.get(0).click();
//	}
//	public void EnterUserInfo(String info) throws InterruptedException
//	{
//		UserSearchBox.click();
//		UserSearchBox.sendKeys(info);
//		Thread.sleep(1000);	
//		ButtonSearch.click();
//		Thread.sleep(3000);	
//	}
//	public void Enter_FHP_UserInfo(String info) throws InterruptedException
//	{
//		UserSearchBox.click();
//		UserSearchBox.sendKeys(info);
//		Thread.sleep(2000);	
//		
//	}
//	
//	
//	public void SelectDisplayedUser()
//	{
//		EmailFeild.click();
//	}
//	
//	public String getEmailFeildText()
//	{
//		return EmailFeild.getText();
//	}
//	
//	public void GenerateCombinedEDF()
//	{
//		GenerateCombinedEDF.getText();
//	}
//	
//	public void VerifyAdminPanelElement() throws InterruptedException
//	{ 
//		
//		
//	//  Assert.assertTrue(SearchBox.isDisplayed(),"Search box not displayed"); 
//	  Assert.assertTrue(AddNewUserBtn.isDisplayed(),"Add user");
//	  Thread.sleep(2000);
//	  Assert.assertTrue(Tableheader.isDisplayed(),"Tab header"); 
//	  
//	  driver.findElement((By.xpath("//*[contains(@class, 'sidebar-toggle')]"))).click();
//	  Thread.sleep(2000);
//	  
//	  
//	  String LUD = driver.findElement(By.id("liveUserDashboardBtn")).getText();
//	  System.out.println("The Button Text is "+LUD);
//	  Assert.assertEquals(driver.findElement(By.id("liveUserDashboardBtn")).getText(),"Live User Dashboard");
//	  
//	  String CP = driver.findElement(By.xpath("//div[contains(text(),'Cloud Platform')]")).getText(); 
//	  System.out.println ("Text is "+CP); 
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Cloud Platform')]")).getText(),"Cloud Platform");
//	  
//	  String SrlNo = driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText();
//	  System.out.println("Text is "+SrlNo);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText(),"S. No.");
//	  
//	  String Name = driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText();
//	  System.out.println("Text is "+Name);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText(),"Name");
//	  
//	  String Email = driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText();
//	  System.out.println("Text is "+Email);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText(),"Email");
//	  
//	  String UL = driver.findElement(By.xpath("//div[contains(text(),'User Level')]")).getText();
//	  System.out.println("Text is "+UL);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'User Level')]")).getText(),"User Level");
//	  
//	  String Report = driver.findElement(By.xpath("//div[contains(text(),'Report')]")).getText();
//	  System.out.println("Text is "+Report);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Report')]")).getText(),"Report");
//	  
//	  String LiveECG = driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText();
//	  System.out.println("Text is "+LiveECG);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText(),"Live ECG");
//	  
//	  String MD = driver.findElement(By.xpath("//div[contains(text(),'More Details')]")).getText();
//	  System.out.println("Text is "+MD);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'More Details')]")).getText(),"More Details");
//	  
//	 }	
//	
//	
//	public void Verify_FHP_AdminPanelElement() throws InterruptedException
//	{ 
//	 // Assert.assertTrue(SearchBox.isDisplayed(),"Search box not displayed"); 
//	  Assert.assertTrue(AddNewUserBtn.isDisplayed(),"Add user");
//	  Thread.sleep(2000);
//	 // Assert.assertTrue(LiveUserDashboardBtn.isDisplayed(),"Live user dash board button"); 
//	  
//	  Assert.assertTrue(Tableheader.isDisplayed(),"Tab header"); 
//	  
//	  String LUD = driver.findElement(By.id("liveUserDashboardBtn")).getText();
//	  System.out.println("The Button Text is "+LUD);
//	  Assert.assertEquals(driver.findElement(By.id("liveUserDashboardBtn")).getText(),"Live User Dashboard");
//	  
//	 
//	  
//	  String SrlNo = driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText();
//	  System.out.println("Text is "+SrlNo);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'S. No.')]")).getText(),"S. No.");
//	  
//	  String Name = driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText();
//	  System.out.println("Text is "+Name);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Name')]")).getText(),"Name");
//	  
//	  String Email = driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText();
//	  System.out.println("Text is "+Email);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Email')]")).getText(),"Email");
//	  
//	  
//	  String LiveECG = driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText();
//	  System.out.println("Text is "+LiveECG);
//	  Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'Live ECG')]")).getText(),"Live ECG");
//	  
//	 }	

	
}
