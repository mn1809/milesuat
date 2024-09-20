package com.Miles.SanityScripts;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.miles.BaseSettings.MilesSettings;
import com.miles.PageLibRepo.HomePageLib;
import com.miles.PageLibRepo.LoginPageLib;
import com.miles.PageLibRepo.WorkoutDetailsPageLib;
import com.miles.Utilities.MilesUtilities;
public class FxSanity extends MilesSettings 
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 WorkoutDetailsPageLib workoutObj ;
	 HashMap<String, String> Average_Data;
	 HashMap<String, String> Maximum_Data;
	 HashMap<String, String> Minimum_Data;
	 LocalDate currentDate = LocalDate.now();
		Locale locale = Locale.getDefault();
		String currentMonthAsString = currentDate.getMonth().getDisplayName(
             TextStyle.FULL, 
             Locale.getDefault()
     );		
		
		String weekAbbreviation = currentDate.format(DateTimeFormatter.ofPattern("Eee", locale)).substring(0, 3);
   		String CurrentMonth = MilesUtilities.GetShortFormOfMonth(currentMonthAsString.toUpperCase());
		int currentDate1 = currentDate.getDayOfMonth();
		String ExpectedActivityName = "Run on "+ weekAbbreviation+", "+ CurrentMonth+" "+currentDate1+" at 05:59 AM";
		String ClassName = this.getClass().getSimpleName().toString();
		String WorkoutDuration = "01:29:08";
		String HomeTrainingLoad = "554";
		String HomeMaxStrain = "0.16";
		String NormalRythemValue = "Noise: 0.38%";
		String ExpectedEditedActivityName = "Editied Run on "+ weekAbbreviation+", "+ CurrentMonth+" "+currentDate1+" at 05:59 AM";
	
		//String dateStamp = weekAbbreviation +" |"+ CurrentMonth +" |"+currentDate1;
		//---------------------------------BELOW DETAILS ARE INDIVIUAL WORKOUT DETAILS-------------------//	
		String ExpectedWorkoutTrainingload = "554";
		String ExpectedWorkoutKCAL = "1424";
		String SearchedActivityTitle = "Searchable Activity";
		String EneEnv;
	 @Parameters({ "enivironment" })
	 @BeforeMethod
	 
    private void Initialize(String env)
    {
		 
		 
		 try
		 {
			 this.driver = DecideEnvironment(env);
			 
			 loginObj = new LoginPageLib(driver);
			
			 
			 
			 EneEnv = env;
			 if(env.contains("prod")) 
			 {							//PROD//
				 homeObj = loginObj.login("quality+automationProd@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality : Serverless Production user");
			 }
			 
			 else
			 {   // Regular Prod User	//STAGE//
				 homeObj = loginObj.login("quality+automation@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality user : Regular Stage user");
			 }
			 

		 }
		 catch (Exception e)
		 {
			 System.out.println("Failed to Initialize due to exception "+e.getMessage());
			 System.out.println("\n Full trace : "+e.getCause());
			 
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
		 System.out.println("driver.quit being called");
		 
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
			
		homeObj.VerifyTodaysActivityIsDisplayed(ExpectedActivityName);		
	}
	
	@Test(description = "Purpose of this to check Rest Icon")
	public void VerifyIconRun()
	{	
		RunIcon();
	}
	
	@Test(description = "Purpose of this to check ECG Has Green Tick")
	public void VerifyECGTick()
	{
		homeObj.VerifyTodaysECGIsSynced(ExpectedActivityName);
	}
	
	@Test(description = "Purpose of this to check 1st Activity Duration")
	public void VerifyActivityDuration()
	{
		homeObj.VerifyTodaysActivityDuration(WorkoutDuration,ExpectedActivityName);
	}
	
	@Test(description = "Purpose of this to check 1st Activity Training Load")
	public void VerifyActivityTrainingLoad()
	{
		homeObj.VerifyTodaysTrainingLoad(ExpectedActivityName,HomeTrainingLoad);
	}
	
	
	@Test (description = "Purpose of this to check the Daily Training Load")
	public void VerifyDailyTrainingLoad() throws InterruptedException
	{
		homeObj.VerifyTrainingLoadTimeLineTabs();
	}
	
	@Test (description = "purpose of this to check 1st Activity MaxStrain")
	public void VerifyActivityMaxStrain()
	{
		homeObj.VerifyTodaysMaxStrain(HomeMaxStrain,ExpectedActivityName);
	}
	
	@Test (description = "Purpose of this to check the Normal Rhythm of Pie Chart")
	public void VerifyRhythmNormalValue()
	{
		homeObj.VerifyTodaysRhythmValue(ExpectedActivityName,NormalRythemValue);
	}
	
	@Test (priority = 2,description = "purpose of this to Edit the Activity")
	
	public void PerformEditActivity() throws InterruptedException
	{
		homeObj.PerformEditActivity(ExpectedEditedActivityName);
		justSleepFor(4);
		
		RefreshButton();
	}
	
	
	@Test (priority = 3, description = "Purpose to Read The Edited Activity")
	public void readeditedactivity()
	{
	homeObj.ReadEditedActivity(ExpectedEditedActivityName);
	}
	
	@Test (description = "Open 1st Activity")
	public void VerifyOpen1stActivity()
	{
		homeObj.VerifyOpen1stActivity();	
	}
	
	@Test (description = "Purpose to test Workout TrainingLoad and KiloCalories Data")
	public void VerifyingTLandKCALData() throws InterruptedException
	{
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj = new WorkoutDetailsPageLib(driver);
		workoutObj.VerifyTLandKCALData(ExpectedWorkoutTrainingload,ExpectedWorkoutKCAL);
	}
	
	@Test (description = "Purpose to Test Workout Average Data")
	public void VerifyingAverageData() throws InterruptedException
	{
		Average_Data = new HashMap<String, String>();
        // Mapping string values to int keys
		Average_Data.put("HR", "159");
		Average_Data.put("BR", "43");
		Average_Data.put("HeartStrain", "0.07");
		Average_Data.put("HRV", "4");
		Average_Data.put("BodyShock", "108");
		Average_Data.put("StepCadence","147");
 	
        homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		Thread.sleep(6000);
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String BodyShock = workoutObj.getBodyShock();
		String StepCadence = workoutObj.getCadenceValue();
        
        Assert.assertEquals(HR, Average_Data.get("HR"),"Actual HR is"+HR+" Expected HR is "+Average_Data.get("HR"));
		Assert.assertEquals(BR, Average_Data.get("BR"),"Actual BR is"+BR+" Expected BR is "+Average_Data.get("BR"));
		Assert.assertEquals(HeartStrain, Average_Data.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Average_Data.get("HeartStrain"));
		Assert.assertEquals(HRV, Average_Data.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Average_Data.get("HRV"));
		Assert.assertEquals(BodyShock, Average_Data.get("BodyShock"),"Actual BodyShock is"+BodyShock+" Expected BodyShock is "+Average_Data.get("BodyShock"));
		Assert.assertEquals(StepCadence, Average_Data.get("StepCadence"),"Actual StepCadence is"+StepCadence+" Expected StepCadence is "+Average_Data.get("StepCadence"));
		
	}
	
	@Test (description = "Purpose to Test Workout Maximum Data")
	public void VerifyingMaximumData() throws InterruptedException
	{
		Maximum_Data = new HashMap<String, String>();
        // Mapping string values to int keys
		Maximum_Data.put("HR", "184");
		Maximum_Data.put("BR", "56");
		Maximum_Data.put("HeartStrain", "0.16");
		Maximum_Data.put("HRV", "27");
		Maximum_Data.put("BodyShock", "178");
		Maximum_Data.put("StepCadence","174");
 	
        homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj.ClickOnMaxTab();
		Thread.sleep(6000);
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String BodyShock = workoutObj.getBodyShock();
		String StepCadence = workoutObj.getCadenceValue();
        
        Assert.assertEquals(HR, Maximum_Data.get("HR"),"Actual HR is"+HR+" Expected HR is "+Maximum_Data.get("HR"));
		Assert.assertEquals(BR, Maximum_Data.get("BR"),"Actual BR is"+BR+" Expected BR is "+Maximum_Data.get("BR"));
		Assert.assertEquals(HeartStrain, Maximum_Data.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Maximum_Data.get("HeartStrain"));
		Assert.assertEquals(HRV, Maximum_Data.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Maximum_Data.get("HRV"));
		Assert.assertEquals(BodyShock, Maximum_Data.get("BodyShock"),"Actual BodyShock is"+BodyShock+" Expected BodyShock is "+Maximum_Data.get("BodyShock"));
		Assert.assertEquals(StepCadence, Maximum_Data.get("StepCadence"),"Actual StepCadence is"+StepCadence+" Expected StepCadence is "+Maximum_Data.get("StepCadence"));
		
	}
	
	@Test (description = "Purpose to Test Workout Maximum Data")
	public void VerifyingMinimumData() throws InterruptedException
	{
		Minimum_Data = new HashMap<String, String>();
        // Mapping string values to int keys
		Minimum_Data.put("HR", "78");
		Minimum_Data.put("BR", "14");
		Minimum_Data.put("HeartStrain", "0");
		Minimum_Data.put("HRV", "2");
		Minimum_Data.put("BodyShock", "0");
		Minimum_Data.put("StepCadence","0");
		
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj.ClickOnMinTab();
		Thread.sleep(6000);
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String BodyShock = workoutObj.getBodyShock();
		String StepCadence = workoutObj.getCadenceValue();
		
		Assert.assertEquals(HR, Minimum_Data.get("HR"),"Actual HR is"+HR+" Expected HR is "+Minimum_Data.get("HR"));
		Assert.assertEquals(BR, Minimum_Data.get("BR"),"Actual BR is"+BR+" Expected BR is "+Minimum_Data.get("BR"));
		Assert.assertEquals(HeartStrain, Minimum_Data.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Minimum_Data.get("HeartStrain"));
		Assert.assertEquals(HRV, Minimum_Data.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Minimum_Data.get("HRV"));
		Assert.assertEquals(BodyShock, Minimum_Data.get("BodyShock"),"Actual BodyShock is"+BodyShock+" Expected BodyShock is "+Minimum_Data.get("BodyShock"));
		Assert.assertEquals(StepCadence, Minimum_Data.get("StepCadence"),"Actual StepCadence is"+StepCadence+" Expected StepCadence is "+Minimum_Data.get("StepCadence"));
		
	}
	
	@Test (description = "Purpose of this test to verify that at the bottom of the web app Version info is displayed in specific formate i.e vX.yz (V3.19)")
	public void VerifyVersionInfo_IsDisplayedInSpecificFormat() throws Exception
	{	
		Calendar cal = Calendar.getInstance();
		MilesUtilities.deleteTxtFiles();
		String Var = homeObj.getVersionInfo();
		String filePath = "./resources/App_"+Var+".txt"; 
		System.out.println("file created with web application version");

	    FileWriter writer = new FileWriter(filePath, false); // Open in append mode
	    writer.write("App Version :"+Var+" \n Version file updated on "+MilesUtilities.getCurrentDateInSpecificFormat(cal));
	    writer.flush();
	    writer.close();
	    System.out.println("WebApp Version - "+Var+" is written to file in path"+filePath);
	  //String RegX = "^v\\d+\\.\\d+\\.\\d+$";
	    String RegX = "^v\\d+\\.\\d+\\.\\d+\\.\\d+$";
		Assert.assertTrue(Pattern.matches(RegX, Var));
	}
	
	@Test (description = "Purpose of this test to verify that when user click on disclaimer link is working as expected")
	public void VerifyDisclaimerLinkIsNotBroken() throws Exception
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
	public void VerifyCaliperButton_Function() throws Exception
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

	@Test (description = "Purpose of this test to verify that Pagination Text is displayed in specific formate ex :Showing 1 - 40 of 162 records in Page 1 and Showing 21 - 40 of 162 records in page 2 ")
	public void VerifyPaginationInfo_IsDisplayedInSpecificFormat() throws Exception
	
	{	
		String PaginationRegX = "Showing \\d+ \\-\\s\\d+\\sof\\s\\d+\\srecords";
		String Page1 = homeObj.getPaginationText();
		Assert.assertTrue(Pattern.matches(PaginationRegX, Page1));
		homeObj.ClickOnRightPagination();
		homeObj.WaiTillLoaderToDisappear();
		String Page2 = homeObj.getPaginationText();
		Assert.assertTrue(Pattern.matches(PaginationRegX, Page2));
	}


	@Test (description = "Purpose of this test to verify that user Navigate to Previous Month by clicking < icon in calander")
	public void VerifyPreviousMonthNavigation_InCalander() throws Exception
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


	@Test (description = "Purpose of this test to verify that user Navigate to Right Page and Left Page on clicking thru Pagination option on the top of the page")
	public void VerifyLeftAndRight_Pagination() throws Exception
	{	

		homeObj.ClickOnRightPagination();
		homeObj.WaiTillLoaderToDisappear();
		homeObj.VerifySerialNumber("21");
		
		Thread.sleep(1000);
		homeObj.ClickOnLefttPagination();
		homeObj.WaiTillLoaderToDisappear();
		homeObj.VerifySerialNumber("1");
	}


	@Test (description = "Purpose of this test to verify that user expand training load arrow icon and activity time line is displayed")
	public void VerifyTrainingLoadArrow_IsExpandable() throws Exception
	{		
		homeObj.clickOnTrainingLoadDownArrow();
		MilesUtilities.WaitForVisibilityOfElement(driver, "xPath", "//i[@class='fa fa-chevron-up']");
		Assert.assertTrue(driver.findElement(By.id("days-timeline")).isDisplayed());
	}

	@Test (description = "Purpose of this test is to Verify if Insights are displayed or not ")
	public void Verify_InsightInfoAreDisplayed() throws Exception
	{					
		homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		wd.AssertInsightInfo();
	}
		
	@Test (description = "Purpose of this test to Verify User Can Search Activity In Dashboard")
	public void VerifyUserSearch_Activity() throws InterruptedException
	{
		String Activity = "Searchable Activity";
		homeObj.searchWorkout(Activity);
		homeObj.ReadSearchedActivity(SearchedActivityTitle);		
	}
	
	@Test (description = "Purpose of this test to Verify User Can Search Activity In Dashboard")
	public void VerifyUserSearch_ClearButton() throws InterruptedException
	{
		String Activity = "Searchable Activity";
		homeObj.searchWorkout(Activity);
		driver.findElement((By.xpath("//*[contains(@class, 'btn btn-clear')]"))).isDisplayed();
		searchbyquery();
	}
	
	@Test (description = "Purpose of this test to Verify User Can Search Activity In Dashboard")
	public void VerifyUserSearch_IsCleared() throws InterruptedException
	{
		String Activity = "Searchable Activity";
		homeObj.searchWorkout(Activity);
		driver.findElement((By.xpath("//*[contains(@class, 'btn btn-clear')]"))).click();
		Thread.sleep(7000);
		
		try 
		{
			if ((driver.findElement((By.xpath("//*[contains(@class, 'btn btn-clear')]"))).isDisplayed() 
					&& driver.findElement(By.className("my-2")).isDisplayed()))
			{
				Assert.fail("Search result did not disapeard");
			}
		}
		catch(Exception e)
		{
			System.out.println("As Expected search result is not displayed");
		}
		
	}
	
	@Test (description = "Purpose of this test to Verfiy Feedback Section is Displayed.")
	public void VerifyFeedback_Insights() throws InterruptedException
	{
		homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		ScrollToFeedback_Insights();
		driver.findElement(By.className("insight-review-box")).getText();
		System.out.println("Title of the Feedback Section:- "+driver.findElement(By.className("insight-review-box")).getText());

	}
	
	@Test (description = "Purpose of this to test to Verify Feedback Section Elements and Pop Up")
	public void VerifyFeedback_ElementsPopup() throws InterruptedException
	{
		homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		ScrollToFeedback_Insights();
		driver.findElement(By.id("insight-like-btn")).isDisplayed();
		driver.findElement(By.id("insight-dislike-btn")).isDisplayed();
		driver.findElement(By.id("insight-like-btn")).click();
		Thread.sleep(2000);
		driver.findElement(By.className("popup-container")).isDisplayed();
		
	}
	
	@Test (description = "Purpose of this to test to Verify Feedback Section Submiting")
	public void VerifyFeedback_Submit() throws InterruptedException
	{
	 	homeObj.clickOnGreenTick();
		MilesUtilities.SwitchTab(1,driver);
		WorkoutDetailsPageLib wd = new WorkoutDetailsPageLib(driver);
		ScrollToFeedback_Insights();
		driver.findElement(By.id("insight-like-btn")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("feedback-content")).click();
		driver.findElement(By.className("feedback-content")).sendKeys("Feedback From Automation Script-QATeam.");
		Thread.sleep(2000);
		driver.findElement(By.id("review-submit-button")).click();
	
		String expectedText= "Thankyou for your feedback";
		VerifyFeedbackSubmitInfoPopUp(expectedText);
	}
	
//Need to add Zones //	
	/*
	 * Helper Methods
	 */
	
 public void VerifyFeedbackSubmitInfoPopUp(String expectedInfoTxt)
 {
 	Assert.assertTrue(driver.findElement(By.className("mini-toastr-notification__message")).getText().contains(expectedInfoTxt));
 }
	
	public void ScrollToFeedback_Insights()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'insight-review-box')]")))).perform();
		//driver.findElement(By.className("fa fa-plus")).click();
		
	}	

	protected void searchbyquery()
	{
		driver.findElement(By.className("my-2")).getText();
		System.out.println("Title of Searched Activity:"+driver.findElement(By.className("my-2")).getText()+"\n");
	}
	
	protected void WaitForWorkoutDetailsPagetoLoad()
	{
		MilesUtilities.SwitchTab(1, driver);
		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
		workoutObj = new WorkoutDetailsPageLib(driver);
	}
	

	protected void RefreshButton()
	{
		
		driver.findElement(By.id("refresh-btn")).click();
		justSleepFor(5);
	}
	
	protected void RunIcon()
	{		
		List <WebElement> ActivityContainer = driver.findElements((By.xpath("//*[contains(@class, 'row w-list')]")));	
		WebElement IconContainer = ActivityContainer.get(0).findElement((By.xpath("//*[contains(@class, 'col-10 w-name-info col-lg-3')]")));
		
		try
		{
			WebElement IconRun = IconContainer.findElement(By.className("icon-ic_running"));
			Assert.assertTrue(IconRun.isDisplayed());
		}
		catch (Exception e)
		{
			Assert.fail("Run icon is not diaplyed for the activity ->" +e.getMessage());
		}
	}
	
}
	

//CHECK//

