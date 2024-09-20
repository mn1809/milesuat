package com.Miles.SanityScripts;
import static org.testng.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.ForgotPasswordPageLib;
import com.miles.PageLibRepo.HeartHealthPageLib;
import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.ShareActivityPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesAPI;
import com.miles.Utilities.MilesUtilities;

public class FxSanity_User extends MilesSettings 
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 WorkoutDetailsPageLib wd ;
	
	 String ClassName = this.getClass().getSimpleName().toString();
	 @Parameters({ "enivironment" })
	 @BeforeMethod
    private void Initialize(String env)
    {
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 loginObj = new LoginPageLib(driver);
			 
			 
			 if(env == "serverless")
			 {
				 homeObj = loginObj.login("ravikiran@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Ravikiran : Serverless Production user");
			 }
			 
			 else
			 {   // Regular Prod User
				 homeObj = loginObj.login("quality@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality user : Regular Prod user");
			 }
		 }
		 catch (Exception e)
		 {
			 System.out.println("Failed to Initialize due to exception "+e.getMessage());
		 }
	 
    }
	 
	 /*
	  * After Every Method script will hit this section of code
	  * Where it'll decide to take screenshot and save on failure 
	  * or
	  * prints the name of passed test case as log (jenkin's console)
	  */

	 @AfterMethod
	 private void CloseDriverSession(ITestResult result) throws Exception
	 {
		 if (result.getStatus() == ITestResult.FAILURE) 
		 {
			 MilesUtilities.createWorkFlowFolder(ClassName);
			 MilesUtilities.CaptureEvidance(driver,result.getMethod().getMethodName(),result,ClassName);
			 System.out.println(result.getMethod().getMethodName()+" Test Failed Due to the reason\n"+result.getThrowable().getMessage());
		 }  
		 if(result.getStatus() == ITestResult.SUCCESS)
		 {
			 System.out.println("<-------------Passed Test case is -> " +result.getName()+"-------------->");
		 }
		 driver.quit();
	 }
	
	 
@BeforeClass
private void SetEvidenceDir()
{
	MilesUtilities.createDateBasedDirectory();
	// For Jenkins Logs
	System.out.println("******User Level Sanity Test cases will be executed now..******");
	// Read JSON File from Backend Automation here TO-DO
}
	
	@Test (description = "Purpose of this test is to Verify that User can Sync his activity and it is being displayed")
	public void VerifyActivitySync() throws Exception
	{			
				
					//homeObj.VerifyTodaysActivityIsDisplayed();
					
	}
	   
	@Test(description = "Purpose of this test is to Verify Training load on home page and Workout details page are same")
	public void VerifyTrainingLoad() throws Exception
	{	
		 
		int TLHomePage = homeObj.GetTrainingLoad_ActivityLevel();
		homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();
		Thread.sleep(2500);
		Assert.assertEquals(TLHomePage, Integer.parseInt(wd.getTrainingValue()));
	}

	
	@Test (description = "Purpose of this test is to Verify that ECG has been synced and Green Tick mark is visble ")
	public void VerifyECGSyncIsSuccessfull() throws Exception
	{		
		//homeObj.VerifyTodaysECGIsSynced();
		homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		wd=new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();
		wd.VerifyECGGraphIsDisplayed();
	 }
	    
	@Test (description = "Purpose of this test is to Verify that user can Download and verify Current Day's FIT File and verifies if its valid or not based on length of file")
	public void VerifyUserCanDownloadLatestFITFiles() throws Exception
	{
	    String ext = ".fit";
	
				MilesUtilities.deleteFiles(ext);
				justSleepFor(6);
				
					homeObj.clickOnActivityButton();
					homeObj.clickOnDownloadFIT();
					System.out.println("FIT File option was clicked for user");
					justSleepFor(4);
					Assert.assertTrue(MilesUtilities.isCurrentDaysFileDownloaded(ext),"Fit File downloaded");
				driver.close();	
	}
	
	@Test (description = "Purpose of this test is to Verify that User can Edit the Activity and save it")
	public void VerifyEditedActivity() throws Exception 
	{
			
			homeObj.clickOnActivityButton();
			justSleepFor(3);
			homeObj.editActivity();
			justSleepFor(3);
			homeObj.EnterText_EditActivity();
			justSleepFor(3);
			homeObj.VerifyEditedTextDisplayed();
			justSleepFor(3);
			homeObj.logout();
	}
	
	
	@Test (description = "Purpose of this test is to Verify if Insights are displayed or not ")
	public void VerifyInsightInfoAreDisplayed() throws Exception
	{		
		//homeObj.VerifyTodaysActivityIsDisplayed();
		homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		wd = new WorkoutDetailsPageLib(driver);
		wd.WaitForWorkoutDetailsPageToLoad();
		wd.AssertInsightInfo();
	}
	
	
	@Test (description = "Purpose of this test to verify Add Report button and Section is working as expected")
	public void VerifyAddReportSection() throws Exception
	{		
		Thread.sleep(3000);
		homeObj.ClickAddReportBtn();
		Thread.sleep(3000);
		homeObj.VerifyAddReportScreen();
	}
	

	@Test (description = "Purpose of this test is to Verify that Health Treand Web Page opens and displays related Charts ")
	public void VerifyHealthTreandPageDisplays() throws Exception
	{		
		HeartHealthPageLib heartHealthObj = new HeartHealthPageLib(driver);
					homeObj.ClickHealthTrendBtn();
					heartHealthObj.VerifyChartsAreDisplayed();
	}
	
	@Test (description = "Purpose of this test is to Verify if ECG PDF Pop Up is displayed or not ")
	public void VerifyECGPDFPopUpDisplayed() throws Exception
	{	
		homeObj.clickOnActivityButton();
		Thread.sleep(2000);
		homeObj.ClickgenerateECGPDF();
		Thread.sleep(2000);
		homeObj.isECGPDFPopUpDisplayed();
			
	}
	
	@Test (description = "Purpose of this test is to Verify the Table Header for Regular user ")
	public void VerifyTableHeader_RegularUsers() throws Exception

		{	
			justSleepFor(2);
			homeObj.VerifyTableHeaders_RegularUser();
		}
	
	@Test (description = "Purpose of this test is to Verify the Calander Date picker is displayed on clicking calander icon")
	public void VerifyCalanderDatePickerIsDisplayed() throws Exception

		{	
			justSleepFor(2);
			homeObj.VerifyCalanderPickerisDisplayed();
		}
	
	@Test (description = "Purpose of this test is to Verify that user can logout from application and Login screen is displayed")
	public void VerifyUserCanLogout() throws Exception
		{	
			justSleepFor(2);
			MilesUtilities.WaitForVisibilityOfElement(driver, ClassName, "fa-power-off");
			homeObj.logout();
			LoginPageLib lp = new LoginPageLib(driver);
			justSleepFor(5);
			lp.VerifyLoginPageElements();
		}
	
	@Test (description = "Purpose of this test is to Verify labels of Last 30 Days Stats section")
	public void VerifyLast30DaysSection() throws Exception
		{	
			justSleepFor(2);
			homeObj.VerifyLast30DaysSection();
		}


	@Test (description = "Purpose of this test is to Verify Toot tip text is as same as Title after editing.")
	public void VerifyToolTipText() throws Exception
		{	
			justSleepFor(2);
			homeObj.VerifyToolTip();	
		}
	
	@Test (description = "Purpose of this test is to Verify Status Info pop ups")
	public void VerifyStatusInfoPopUps() throws Exception
		{	
			justSleepFor(2);
			homeObj.VerifyStatusInfoPopUpTitles();		
		}
	
	@Test (description = "Purpose of this test to verify that Upon tapping Refresh button , refresh icon is displayed")
	public void VerifyRefreshIconDisplayed() throws Exception
	{	
		homeObj.ClickRefresh();
		Thread.sleep(500);
		homeObj.VerifyRefreshFunctionality();
	}
	
	@Test (description = "Purpose of this test to verify that Upon clicking Search button , refresh icon is displayed")
	public void VerifyRefreshIconDisplayedOnActivitySearch() throws Exception
	{	
		homeObj.clickOnSearchWorkout();
		Thread.sleep(500);
		homeObj.VerifyRefreshFunctionality();
	}
	
	@Test (description = "Purpose of this test to verify that user can see Weekly , daily and Monthly tabs are visible")
	public void VerifyTrainingLoadTimeLineTabs() throws Exception
	{	
		homeObj.VerifyTrainingLoadTimeLineTabs();
	}
	
	@Test (description = "Purpose of this test to verify that Email ID filed is required field in fogot password screen")
	public void VerifyEmailFieldIsRequiredFiled_ForgotPasswordScreen() throws Exception
	{
		homeObj.logout();
		Thread.sleep(3000);
		// Locating this element here as for some strange reason its not identifiable in regular way 
		driver.findElement(By.id("email")).sendKeys("quality@fourthfrontier.com");
		//driver.findElement(By.xpath("btn btn-login"));
		//login.clickOnLogin();
		ClickOnNextButton();
		Thread.sleep(3000);
		ClicKOnForgotPasswordLink();
		Thread.sleep(1500);
		ForgotPasswordPageLib fp = new ForgotPasswordPageLib(driver);
		Thread.sleep(3000);
		fp.VerifyInstructionMsg();
		
	}
	
	@Test (description = "Purpose of this test to verify that user can see Weekly , daily and Monthly tabs are visible")
	public void VerifyEmailSentSuccessfullMsg_ForgotPasswordScreen() throws Exception
	{
		homeObj.logout();
		Thread.sleep(3000);
		driver.findElement(By.id("email")).sendKeys("quality@fourthfrontier.com");
		//driver.findElement(By.xpath("btn btn-login"));
		ClickOnNextButton();
		// Locating this element here as for some strange reason its not identifiable in regular way 
		ClicKOnForgotPasswordLink();
		ForgotPasswordPageLib fp = new ForgotPasswordPageLib(driver);
		Thread.sleep(3000);
		fp.VerifyEmailSent("ravikiran@fourthfrontier.com");
		
	}
	
	@Test (description = "Purpose of this test to verify that user expand training load arrow icon and activity time line is displayed")
	public void VerifyTrainingLoadArrowIsExpandable() throws Exception
	{		
		homeObj.clickOnTrainingLoadDownArrow();
		MilesUtilities.WaitForVisibilityOfElement(driver, "xPath", "//i[@class='fa fa-chevron-up']");
		Assert.assertTrue(driver.findElement(By.id("days-timeline")).isDisplayed());
	}
	
	
	@Test (description = "Purpose of this test to verify that user Navigate to Right Page and Left Page on clicking thru Pagination option on the top of the page")
	public void VerifyLeftAndRightPagination() throws Exception
	{	
	
		homeObj.ClickOnRightPagination();
		homeObj.WaiTillLoaderToDisappear();
		homeObj.VerifySerialNumber("21");
		
		Thread.sleep(1000);
		homeObj.ClickOnLefttPagination();
		homeObj.WaiTillLoaderToDisappear();
		homeObj.VerifySerialNumber("1");
	}
	
	@Test (description = "Purpose of this test to verify that user Navigate to Previous Month by clicking < icon in calander")
	public void VerifyPreviousMonthNavigationInCalander() throws Exception
	{	
		LocalDate now = LocalDate.now(); 
		LocalDate PreviousMonth = now.minusMonths(1);

		homeObj.ClickOnCalanderPicker();
		Thread.sleep(1000);
		// Below elements will be loaded only up on clicking calander icon so , adding this elements to Obj class will throw errors
		driver.findElement(By.className("mx-icon-left")).click();
		String ReadPreviousMonth = driver.findElement(By.xpath("//*[contains(@class, 'mx-btn mx-btn-text mx-btn-current-month')]")).getText();
		
		MilesUtilities.GetShortFormOfMonth(ReadPreviousMonth);
		
		Assert.assertEquals(ReadPreviousMonth, MilesUtilities.GetShortFormOfMonth(PreviousMonth.getMonth().toString()));
	}


@Test (description = "Purpose of this test to verify that user Navigate to Next Month by clicking > icon in calander")
public void VerifyNextMonthNavigationInCalander() throws Exception
{	
	LocalDate now = LocalDate.now(); 
	LocalDate nextMonth =now.plusMonths(1);

	homeObj.ClickOnCalanderPicker();
	Thread.sleep(1000);
	// Below elements will be loaded only up on clicking calander icon so , adding this elements to Obj class will throw errors
	driver.findElement(By.className("mx-icon-right")).click();
	String ReadPreviousMonth = driver.findElement(By.xpath("//*[contains(@class, 'mx-btn mx-btn-text mx-btn-current-month')]")).getText();
	MilesUtilities.GetShortFormOfMonth(ReadPreviousMonth);
	Assert.assertEquals(ReadPreviousMonth, MilesUtilities.GetShortFormOfMonth(nextMonth.getMonth().toString()));
}
	
@Test (description = "Purpose of this test to verify that user Navigate to Previous year by clicking << icon in calander")
public void VerifyPreviousYearNavigationInCalander() throws Exception
{	
	LocalDate now = LocalDate.now(); 
	LocalDate PreviousYear =now.minusYears(1);

	homeObj.ClickOnCalanderPicker();
	Thread.sleep(1000);
	// Below elements will be loaded only up on clicking calander icon so , adding this elements to Obj class will throw errors
	driver.findElement(By.className("mx-icon-double-left")).click();
	String ReadPreviousYear = driver.findElement(By.xpath("//*[contains(@class, 'mx-btn mx-btn-text mx-btn-current-year')]")).getText();
	//MilesUtilities.GetShortFormOfMonth(ReadPreviousMonth);
	Assert.assertEquals(Integer.parseInt(ReadPreviousYear), PreviousYear.getYear());
}

@Test (description = "Purpose of this test to verify that user Navigate to Next year by clicking >> icon in calander")
public void VerifyNextYearNavigationInCalander() throws Exception
{	
	LocalDate now = LocalDate.now(); 
	LocalDate nextYear =now.plusYears(1);

	homeObj.ClickOnCalanderPicker();
	Thread.sleep(1000);
	// Below elements will be loaded only up on clicking calander icon so , adding this elements to Obj class will throw errors
	driver.findElement(By.className("mx-icon-double-right")).click();
	String ReadNextYear = driver.findElement(By.xpath("//*[contains(@class, 'mx-btn mx-btn-text mx-btn-current-year')]")).getText();
	//MilesUtilities.GetShortFormOfMonth(ReadPreviousMonth);
	Assert.assertEquals(Integer.parseInt(ReadNextYear), nextYear.getYear());
}
	
@Test (description = "Purpose of this test to verify that at the bottom of the web app Version info is displayed in specific formate i.e vX.yz (V3.19)")
public void VerifyVersionInfoIsDisplayedInSpecificFormat() throws Exception
{	
	String Var = homeObj.getVersionInfo();
	String RegX = "v\\d\\.\\d\\d";
	Assert.assertTrue(Pattern.matches(RegX, Var));
}

@Test (description = "Purpose of this test to verify that Pagination Text is displayed in specific formate ex :Showing 1 - 40 of 162 records in Page 1 and Showing 21 - 40 of 162 records in page 2 ")
public void VerifyPaginationInfoIsDisplayedInSpecificFormat() throws Exception
{	
	
	String PaginationRegX = "Showing \\d+ \\-\\s\\d+\\sof\\s\\d+\\srecords";
	String Page1 = homeObj.getPaginationText();
	Assert.assertTrue(Pattern.matches(PaginationRegX, Page1));
	homeObj.ClickOnRightPagination();
	homeObj.WaiTillLoaderToDisappear();
	String Page2 = homeObj.getPaginationText();
	Assert.assertTrue(Pattern.matches(PaginationRegX, Page2));
}


@Test (description = "Purpose of this test to verify that when user click on disclaimer link is working as expected")
public void VerifyDisclaimerLink() throws Exception
{	
	homeObj.clickOnGreenTick();
	MilesUtilities.SwitchTab(1, driver);
	WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	wd.ClickOnDisclaimerLink();
	MilesUtilities.SwitchTab(2, driver);
	MilesUtilities.WaitForVisibilityOfElement(driver, "id", "MainContent");
	Assert.assertTrue(driver.getPageSource().contains("Disclaimers"));
}


@Test (description = "Purpose of this test to verify that user can enable and disable caliber tool")
public void VerifyCaliperButtonFunction() throws Exception
{	
	homeObj.clickOnGreenTick();
	MilesUtilities.SwitchTab(1, driver);
	WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
	wd.WaitForWorkoutDetailsPageToLoad();
	wd.EnableCaliper();
	Assert.assertEquals(driver.findElement(By.className("mb-4")).getText(),"Select 2 points on any ECG strip to calculate the Time Interval & Amplitude.","Caliber Tool is enabled ");
	wd.EnableCaliper();
	Assert.assertEquals(driver.findElement(By.className("mb-4")).getText(),"Activate the Caliper tool to calculate the Time Interval & Amplitude on the ECG strips.","Caliber Tool is disabled ");
}

@Test (description = "Purpose of this test to verify that User Can download ECG PDF and Verify the Response code of respetive API")
public void VerifyECGPDFGeneration() throws Exception
{	
	
	List<String> Response = MilesAPI.GetAPIResponse(driver);
	homeObj.clickOnActivityButton();
	homeObj.ClickgenerateECGPDF();
	homeObj.clickOnECGPDFGen();
	Thread.sleep(3000);
	
	// Checking  if Generate ECG PDF API responded with 200/OK status 
	
	String URL = driver.getCurrentUrl();
	
	if (URL.contains("frontierxapp"))
	{
		System.out.println("API URL Under Test ->" +MilesAPI.ECGGenrateAPI_SL);
		Assert.assertTrue(Response.get(0).contains(MilesAPI.ECGGenrateAPI_SL));

	}
	else
	{
		System.out.println("API URL Under Test ->" +MilesAPI.ECGGenerateAPI);
		Assert.assertEquals(Response.get(0), MilesAPI.ECGGenerateAPI);
	}
	
	Assert.assertEquals(Response.get(1), MilesAPI.StatusOK);
	System.out.println("Response Status Code Reveived ->" +Response.get(1));
	Thread.sleep(1000);
	
}


@Test (description = "Purpose of this test is to Verify List of Health Tags displayed")
public void VerifyHealthEntryList() throws Exception
{				
		 List<String> HealthTagList = Arrays.asList("Vitals", "Symptom", "Sleep","Medication","Beverage","Food","Supplement","Body Status","Mental Status","Wellness Activity","Environment");
		 homeObj.ClickOnAddHealtEntry();
		 Thread.sleep(1500);
		 homeObj.VerifyHealthTags(HealthTagList);
		 Thread.sleep(1000);
}


@Test (description = "Purpose of this test is to Verify ECG zip files gets downloaded ")
public void VerifyECGZIPFiledownloads() throws Exception
{	
	
	String Duration = homeObj.getWorkoutDuration();
	System.out.println("Duration of the workout is "+Duration);
	//"01:36:55"
	homeObj.clickOnActivityButton();
	homeObj.clickOnDownloadZIP();
	

	// This check is required to see if duration of activity is less than 1 hour 
	//zip file will gets downloaded locally else email will goes to user
	if (Integer.parseInt(Duration.substring(0, 2)) > 0) // Extracting Hour part from over all duration
	{
		//MilesUtilities.WaitForVisibilityOfElement(driver, "className", "vs-popup");
		System.out.println("ECG ZIP file will be sent thur email");
		
	}
	else // Verify locally downloaded zip file
	{
		MilesUtilities.deleteFiles(".zip");
		Thread.sleep(5000);
		Assert.assertTrue(MilesUtilities.isCurrentDaysFileDownloaded(".zip"),"ECG Zip file is downloaded");
	}
	
	
}


/*
 * Helper Methods
 */


private void ClicKOnForgotPasswordLink()
{
	driver.findElement(By.className("forget-link")).click();
}

private void ClickOnNextButton()
{
	driver.findElement(By.xpath("//*[contains(@class, 'btn btn-login')]")).click();
}

}
