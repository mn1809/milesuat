package com.miles.PageLibRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.miles.PageObjectRepo.AdminPageObj;
import com.miles.PageObjectRepo.DoctorsPageObj;
import com.miles.Utilities.MilesUtilities;


public class DoctorsPageLib extends DoctorsPageObj
{
	
	public DoctorsPageLib(WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
		
	/*
	 * Method to click on Add Coach button
	 */
	public void ClickOnAddUserButton()
	{
		AddNewUserBtn.click();
	}
	public void VerifyIfSearchedUserAppeared(String email)
	{
		Assert.assertEquals(UserRecords.get(1) .getText(), email,"User Found");
	}
	
	public void VerifyEmailIdPopUpisDisplayed()
	{
		Assert.assertEquals(EmailIdPopUp.getText(),"Email Address","Email ID Pop Up is Displayed");
	}
	
	public void WaitForDocPageToLoad()
	{
		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "addNewPatientBtn");
	}
	
	public void SelectDisplyedUser()
	{
		UserRecords.get(0).click();
	}
	
	public void VerifyDocPanelElements()
	{
	Assert.assertTrue(SearchBox.isDisplayed());	
	Assert.assertTrue(AddNewUserBtn.isDisplayed());
	Assert.assertTrue(LiveUserDashboardBtn.isDisplayed());
	Assert.assertTrue(Indexing.isDisplayed());
	Assert.assertTrue(Tableheader.isDisplayed());
	}
}
