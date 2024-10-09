package com.miles.PageLibRepo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.miles.PageObjectRepo.atspageObj;
import com.miles.PageObjectRepo.AdminPageObj;
import com.miles.PageObjectRepo.OPTPageObj;
import com.miles.PageObjectRepo.atspageObj;
import com.miles.Utilities.MilesUtilities;
import com.miles.Utilities.MilesUtilities;


public class ATSPageLib extends atspageObj

{
	
	public ATSPageLib(WebDriver driver) 
	{
		super(driver);
	//	PageFactory.initElements(driver, this); 
		// TODO Auto-generated constructor stub
	}
		
	
	public void U8bucket() throws InterruptedException
	{
		driver.findElement(By.className("o_searchview_input")).click();
	//	UserName.sendKeys("AUTOMAYUON987");
		driver.findElement(By.className("o_searchview_input")).sendKeys("AUTOMAYUON987");
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(@class, 'o_menu_item dropdown-item focus')]")).click();
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[contains(@class, 'o_kanban_record_subtitle  kanban_tiles_subtitle')]")).click();
	}
	
	
	
	
	
	
	
	
//	public void VerifyAdminDropdownoptions()
//	{
//		
//		driver.findElement(By.className("dropdown-toggle")).click();
//		List <WebElement> Options = driver.findElements((By.xpath("//*[contains(@class, 'dropdown-item o_app')]")));
//		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class, 'dropdown-item o_app')]")).isDisplayed());
//		
//		Assert.assertEquals(Options.get(0).getText(), "USP Eligibility");
//		Assert.assertEquals(Options.get(1).getText(), "Miles ATS");
//		Assert.assertEquals(Options.get(2).getText(), "Miles Recruitments");
//		Assert.assertEquals(Options.get(3).getText(), "Helpdesk");
//		Assert.assertEquals(Options.get(4).getText(), "Discuss");
//		Assert.assertEquals(Options.get(5).getText(), "Calendar");
//		Assert.assertEquals(Options.get(6).getText(), "My Dashboard");
//		Assert.assertEquals(Options.get(7).getText(), "Job Queue");
//		Assert.assertEquals(Options.get(8).getText(), "Contacts");
//		Assert.assertEquals(Options.get(9).getText(), "Dashboards");
//		Assert.assertEquals(Options.get(10).getText(), "Surveys");
//		Assert.assertEquals(Options.get(11).getText(), "Employees");
//		Assert.assertEquals(Options.get(12).getText(), "Apps");
//		Assert.assertEquals(Options.get(13).getText(), "Settings");
//	}
	/*
	 * Method to click on Add Coach button
	 */
	
//	 public void VerifyUserLevelOptions() throws InterruptedException
//		{
//			Select UserLevelDropDown = new Select(driver.findElement(By.xpath("//*[contains(@title, 'Configuration')]")));
//			
//		
//			List<String> ConvertedList = new ArrayList<String>();
//			
//			
//			List<WebElement> ActualLevelsList=UserLevelDropDown.getOptions();
//			Thread.sleep(2000);
//				
//			for(int i = 0 ;i<ActualLevelsList.size();i++)
//			{
//				// Just print this for Debug purpose
//				System.out.println("Debug : -> Item "+i+" : "+ActualLevelsList.get(i).getText());
//			//	System.out.println("Debug : -> Expected List Item "+i+" : "+ExpectedUserList.get(i));
//				ConvertedList.add(ActualLevelsList.get(i).getText());
//				
//			//Assert.assertTrue(ConvertedList.get(i).equals(ExpectedUserList.get(i)),"Expected is "+ExpectedUserList.get(i)+" Actual is "+ConvertedList.get(i));
//
//			}
//			
//			//Assert.assertTrue(ConvertedList.equals(ExpectedUserList),"Not Equal");
//		
//			for(int j = 0 ;j<ActualLevelsList.size();j++)
//			{
//				Assert.assertTrue(ConvertedList.get(j).equals(ExpectedUserList.get(j)),"Expected is "+ExpectedUserList.get(j)+" Actual is "+ConvertedList.get(j));
//			}	
//		}
//
//	private List<String> ExpectedUserList = Arrays.asList("Allocation Configurations",
//            "Enrollment Batches",
//            "Enrollment University",
//            "Buckets",
//            "Enrollment Course",
//            "ATS Journey",
//            "LOR Question",
//            "Graduation Division",
//            "Pathway College",
//            "Sessions",
//            "Document Type",
//            "MSA Document Type",
//            "DS-160 Step",
//            "Ineligible Reason");
	
//	public void ClickOnAddUserButton()
//	{
//		AddNewUserBtn.click();
//	}
//		
//	
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
