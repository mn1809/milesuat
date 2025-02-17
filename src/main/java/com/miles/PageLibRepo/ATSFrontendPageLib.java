package com.miles.PageLibRepo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.miles.PageObjectRepo.ATSFrontendPageObj;
import com.miles.PageObjectRepo.AdminPageObj;
import com.miles.PageObjectRepo.OPTPageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;


public class ATSFrontendPageLib extends ATSFrontendPageObj
{
	
	public ATSFrontendPageLib (WebDriver driver) 
	{
		super(driver);
		// TODO Auto-generated constructor stub
	}
		
	/*
	 * Method to click on Add Coach button
	 */
	
	
	public void EnteruserNumber() throws InterruptedException
	{
		WebElement PhoneNumber = driver.findElements(By.xpath("//*[contains(@placeholder, 'Enter your number')]")).get(0);
		
		PhoneNumber.click();
		PhoneNumber.sendKeys("8970792998");
		
		WebElement Continune = driver.findElements(By.xpath("//*[contains(@class, 'overflow-hidden relative w-full py-2 px-4 rounded-xl font-bold space-x-2 bg-miles text-white text-md')]")).get(0);
		Continune.click();
		
		Thread.sleep(2000);
		WebElement OKCLICK =  driver.findElement(By.xpath("//span[normalize-space()='Okay']"));
		OKCLICK.click();
	}
	
	
	
	
	public void OTP() throws InterruptedException
	{
		
//		WebElement OTP1 = driver.findElement(By.id("1"));
//		OTP1.click();
//		OTP1.sendKeys("1");
//		Thread.sleep(1000);
//		WebElement OTP2 = driver.findElement(By.id("2"));
//		OTP2.sendKeys("8");
//		Thread.sleep(1000);
//		WebElement OTP3 = driver.findElement(By.id("3"));
//		OTP3.sendKeys("8");
//		Thread.sleep(1000);
//		WebElement OTP4 = driver.findElement(By.id("4"));
//		OTP4.sendKeys("1");
//		Thread.sleep(1000);
//		WebElement OTP5 = driver.findElement(By.id("5"));
//		OTP5.sendKeys("1");
//		Thread.sleep(1000);
//		WebElement OTP6 = driver.findElement(By.id("6"));
//		OTP6.sendKeys("8");
		
		String otp = "188118";

		for (int i = 0; i < otp.length(); i++)
		{
		    WebElement otpField = driver.findElement(By.id(String.valueOf(i + 1))); // IDs are "1" to "6"
		    otpField.click();
		    otpField.sendKeys(String.valueOf(otp.charAt(i))); // Extract each digit
		    Thread.sleep(1000);
		}

		
		WebElement Confirm = driver.findElements(By.xpath("//*[contains(@class, 'overflow-hidden relative w-full py-2 px-4 rounded-xl font-bold space-x-2 bg-miles text-white text-md')]")).get(0);
		Confirm.click();
		
		Thread.sleep(3000);
		WebElement OK = driver.findElements(By.xpath("//*[contains(@class, 'overflow-hidden relative w-full py-2 px-4 rounded-xl font-bold space-x-2 bg-miles text-white text-md')]")).get(0);
		OK.click();
	}
	
	
	
	public void UGeducationdetails() throws InterruptedException
	{
		
		WebElement PlusIcon = driver.findElements(By.xpath("//*[contains(@class, 'svg-inline--fa fa-plus')]")).get(0);
		
		PlusIcon.click();
		Thread.sleep(1000);

		WebElement degree = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(0);
		degree.click();	
	
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		    By.xpath("//*[contains(@class,'mat-mdc-option mdc-list-item ng-tns-c1154042729-3 ng-star-inserted')]")));

		// Ensure the list is large enough and perform the action on the 6th element (index 5)
		if (options.size() > 5) {
		    Actions act = new Actions(driver);
		    act.moveToElement(options.get(5)).perform();  // Perform action on the 6th element (index 5)
		} else {
		    System.out.println("Element not found!");
		}
		
		
		
		
		driver.findElement(By.xpath("//span[@class='mdc-list-item__primary-text' and contains(text(), 'Bachelor of Engineering')]")).click();  //Ading Degree//
		Thread.sleep(2000);

		
		WebElement university = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(1);
		university.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(0).click();  //Unversity College //
		Thread.sleep(2000);
		
		WebElement NAAC = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(2);
		NAAC.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(0).click();  //Unversity College //
		Thread.sleep(2000);

		
		WebElement GraduationRank = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(3);
		GraduationRank.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(0).click();  //Unversity College //
		Thread.sleep(2000);
		
		
		WebElement GraduationStatus = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(4);
		GraduationStatus.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(1).click();  //Unversity College //
		Thread.sleep(5000);

		WebElement GraduactionPercentage = driver.findElement(By.xpath("//mat-form-field[contains(@class, 'mat-mdc-form-field')]//input[@type='number']"));
		GraduactionPercentage.click();
		GraduactionPercentage.sendKeys("85");
		
		WebElement Calendar = driver.findElement(By.className("mat-mdc-button-touch-target"));
		Calendar.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='button' and @aria-label='2020']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='button' and @aria-label='2020 July']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='button' and @aria-label='Saturday, July 18, 2020 12:00 AM']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
		
	}
	
	public void PGeducationdetails() throws InterruptedException
	{
		WebElement PlusIcon = driver.findElements(By.xpath("//*[contains(@class, 'svg-inline--fa fa-plus')]")).get(0);
		
		PlusIcon.click();
		Thread.sleep(1500);
		
		WebElement PG =	driver.findElement(By.xpath("//*[contains(@value, 'pg')]"));
		PG.click();
		
		WebElement degree = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(0);
		degree.click();	
		
		driver.findElement(By.xpath("//span[@class='mdc-list-item__primary-text' and contains(text(), 'Master of Engineering')]")).click();  //Ading Degree//
		Thread.sleep(2000);

		//span[@class='mdc-list-item__primary-text' and contains(text(), 'Master of Technology')]

		WebElement university = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(1);
		university.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(0).click();  //Unversity College //
		Thread.sleep(2000);
		
		WebElement NAAC = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(2);
		NAAC.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(0).click();  //Unversity College //
		Thread.sleep(2000);

		
		WebElement GraduationRank = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(3);
		GraduationRank.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(0).click();  //Unversity College //
		Thread.sleep(2000);
		
		
		WebElement GraduationStatus = driver.findElements(By.xpath("//*[contains(@class, 'mat-mdc-select-arrow-wrapper')]")).get(4);
		GraduationStatus.click();	
		driver.findElements(By.xpath("//*[contains(@class, 'mdc-list-item__primary-text')]")).get(1).click();  //Unversity College //
		Thread.sleep(5000);

		WebElement GraduactionPercentage = driver.findElement(By.xpath("//mat-form-field[contains(@class, 'mat-mdc-form-field')]//input[@type='number']"));
		GraduactionPercentage.click();
		GraduactionPercentage.sendKeys("85");
		
		WebElement Calendar = driver.findElement(By.className("mat-mdc-button-touch-target"));
		Calendar.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type='button' and @aria-label='2022']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='button' and @aria-label='2022 September']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@type='button' and @aria-label='Sunday, September 18, 2022 12:00 AM']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
		
	}
	
	
	public void ExperienceFeilds() throws InterruptedException
	{
		WebElement EXP = driver.findElement(By.id("has_experience"));
		EXP.click();
		ScrollToSubmitTab();
		
		WebElement TotalExpYear = driver.findElements(By.xpath("//input[@type='number']")).get(0);
		TotalExpYear.click();
		TotalExpYear.sendKeys("2");
		Thread.sleep(1500);
		
				
		WebElement TotalExpMonth = driver.findElements(By.xpath("//input[@type='number']")).get(1);
		TotalExpMonth.click();
		TotalExpMonth.sendKeys("2");
		Thread.sleep(1500);
		
		WebElement ActExpYear = driver.findElements(By.xpath("//input[@type='number']")).get(2);
		ActExpYear.click();
		ActExpYear.sendKeys("2");
		Thread.sleep(1500);
		
		
		WebElement ActExpMonth = driver.findElements(By.xpath("//input[@type='number']")).get(3);
		ActExpMonth.click();
		ActExpMonth.sendKeys("2");
		Thread.sleep(1500);
		
		WebElement CurrentCompany = driver.findElements(By.xpath("//input[@type='text']")).get(0);
		CurrentCompany.click();
		CurrentCompany.sendKeys("Miles Automation Company");
		Thread.sleep(1500);
		
		WebElement Submit = driver.findElement(By.xpath("//span[@class='relative']"));
		Submit.click();
		
//		Thread.sleep(1500);
//		WebElement okay = driver.findElement(By.xpath("//span[@class='relative']"));
//		okay.click();
		
	}
	
	
	
	public void ScrollToSubmitTab() throws InterruptedException 
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//button[@class='overflow-hidden relative w-full py-2 px-4 rounded-xl font-bold space-x-2 bg-miles border-miles text-white border-2']"))).perform();
	}
	
	public void ScrollToEngineeringTab() throws InterruptedException 
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[contains(@class='ng-trigger ng-trigger-transformPanel ng-tns-c1154042729-8 mat-mdc-select-panel mdc-menu-surface mdc-menu-surface--open mat-primary ng-star-inserted')]"))).perform();
		
	}
	
	public void RecommenedUniveristy()
	{
		
	}
	
	
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
