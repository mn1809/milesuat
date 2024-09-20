package com.Miles.SanityScripts;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
public class Fx24hrsActivity extends MilesSettings 
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
		String ExpectedActivityName = "Rest on "+ weekAbbreviation+", "+ CurrentMonth+" "+currentDate1+" at 11:44 AM";
		String ClassName = this.getClass().getSimpleName().toString();
		String WorkoutDuration = "23:31:42";
		String HomeTrainingLoad = "3055";
		String HomeMaxStrain = "0.06";
		//String NormalRythemValue = "Noise: 0.09%";
		String NormalRythemValue = "Normal: 99.9%";
		
	//---------------------------------BELOW DETAILS ARE INDIVIUAL WORKOUT DETAILS-------------------//	
		String ExpectedWorkoutTrainingload = "3055";
		String ExpectedWorkoutKCAL = "11927";
		
		String ActivityNotes = "Activity Notes added From Automation Script by QATeam On- "+weekAbbreviation+", "+ CurrentMonth+", "+currentDate1;
		
		String Zone1_Duration = "4h 42m";
		String Zone2_Duration = "16h 7m";
		String Zone3_Duration = "1h 19m";
		String Zone4_Duration = "20 sec";
		String Zone5_Duration = "1h 21m";
		
		String Zone1_Percentage = "20%";
		String Zone2_Percentage = "69%";
		String Zone3_Percentage = "6%";
		String Zone4_Percentage = "<1%";
		String Zone5_Percentage = "6%";
	
		String EneEnv;
		//String EditedActivityName = "Cycle on "+
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
			 {									//PROD//
				 homeObj = loginObj.login("quality+24Prod@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality : Serverless Production user");
			 }
			 
			 else
			 {   // Regular Prod User		//STAGE//
				 homeObj = loginObj.login("quality+24@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
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
	System.out.println("******24 hours Activity verification Test cases will be executed now..******");
	// Read JSON File from Backend Automation here TO-DO
}
	
	@Test (description = "Purpose of this test is to Verify that User can Sync his activity and it is being displayed")
	public void VerifyActivitySync_24Hr() throws Exception
	{			
			
		homeObj.VerifyTodaysActivityIsDisplayed(ExpectedActivityName);		
	}
	
	@Test(description = "Purpose of this to check Rest Icon")
	public void VerifyIcon_24Hrs()
	{	
		RestIcon();
	}
		
	@Test(description = "Purpose of this to check ECG Has Green Tick")
	public void VerifyECGTick_24Hrs()
	{
		homeObj.VerifyTodaysECGIsSynced(ExpectedActivityName);
	}
	
	@Test(description = "Purpose of this to check 1st Activity Duration")
	public void VerifyActivityDuration_24Hrs()
	{
		homeObj.VerifyTodaysActivityDuration(WorkoutDuration,ExpectedActivityName);
	}
	@Test(description = "Purpose of this to check 1st Activity Training Load")
	public void VerifyActivityTrainingLoad_24Hrs()
	{
		homeObj.VerifyTodaysTrainingLoad(ExpectedActivityName,HomeTrainingLoad);
	}
	
	@Test (description = "purpose of this to check 1st Activity MaxStrain")
	public void VerifyActivityMaxStrain_24Hrs()
	{
		homeObj.VerifyTodaysMaxStrain(HomeMaxStrain,ExpectedActivityName);
	}
	
	@Test (description = "Purpose of this to check the Normal Rhythm of Pie Chart")
	public void VerifyRhythmNormalValue_24Hrs()
	{
		homeObj.VerifyTodaysRhythmValues(ExpectedActivityName,NormalRythemValue);
	}
	
	
	@Test (description = "Open 1st Activity")
	public void VerifyOpen1stActivity_24Hrs()
	{
		homeObj.VerifyOpen1stActivity();	
	}
	
	@Test (description = "Purpose to test Workout TrainingLoad and KiloCalories Data")
	public void VerifyingTLandKCALData_24Hrs() throws InterruptedException
	{
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
	//	workoutObj = new WorkoutDetailsPageLib(driver);
		workoutObj.VerifyTLandKCALData(ExpectedWorkoutTrainingload,ExpectedWorkoutKCAL);
	}
	
	@Test (description = "Purpose to Test Workout Average Data")
	public void VerifyingAverageValue_24Hrs() throws InterruptedException
	{
		Average_Data = new HashMap<String, String>();
        // Mapping string values to int keys
		Average_Data.put("HR", "117");
		Average_Data.put("BR", "26");
		Average_Data.put("HeartStrain", "0.01");
		Average_Data.put("HRV", "1");
		Average_Data.put("Stress","100");
		
        homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		
		justSleepFor(6);
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String Stress = getStressValue();
		
        Assert.assertEquals(HR, Average_Data.get("HR"),"Actual HR is "+HR+" Expected HR is "+Average_Data.get("HR"));
		Assert.assertEquals(BR, Average_Data.get("BR"),"Actual BR is "+BR+" Expected BR is "+Average_Data.get("BR"));
		Assert.assertEquals(HeartStrain, Average_Data.get("HeartStrain"),"Actual HeartStrain is "+HeartStrain+" Expected HeartStrain is "+Average_Data.get("HeartStrain"));
		Assert.assertEquals(HRV, Average_Data.get("HRV"),"Actual HRV is "+HRV+" Expected HRV is "+Average_Data.get("HRV"));
		Assert.assertEquals(Stress, Average_Data.get("Stress"),"Actual Stress is "+Stress+"Expected Stress is"+Average_Data.get("Stress"));
		
	}
	
	@Test (description = "Purpose to Test Workout Maximum Data")
	public void VerifyingMaximumValue_24Hrs()
	{
		Maximum_Data = new HashMap<String, String>();
        // Mapping string values to int keys
		Maximum_Data.put("HR", "181");
		Maximum_Data.put("BR", "70");
//		if (EneEnv.contains("prod"))
//		{
//			Maximum_Data.put("HeartStrain", "0.06");
//		}
//		else
//		{
//			Maximum_Data.put("HeartStrain", "0.03");
//		}
		Maximum_Data.put("HeartStrain", "0.06");
		Maximum_Data.put("HRV", "31");
		Maximum_Data.put("Stress","100");
		
        homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj.ClickOnMaxTab();
		justSleepFor(6);
		
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String Stress = getStressValue();
        
		
        Assert.assertEquals(HR, Maximum_Data.get("HR"),"Actual HR is "+HR+" Expected HR is "+Maximum_Data.get("HR"));
		Assert.assertEquals(BR, Maximum_Data.get("BR"),"Actual BR is "+BR+" Expected BR is "+Maximum_Data.get("BR"));
		Assert.assertEquals(HeartStrain, Maximum_Data.get("HeartStrain"),"Actual HeartStrain is "+HeartStrain+" Expected HeartStrain is "+Maximum_Data.get("HeartStrain"));
		Assert.assertEquals(HRV, Maximum_Data.get("HRV"),"Actual HRV is "+HRV+" Expected HRV is "+Maximum_Data.get("HRV"));
		Assert.assertEquals(Stress, Maximum_Data.get("Stress"),"Actual Stress is "+Stress+"Expected Stress is"+Maximum_Data.get("Stress"));
	}
	
	@Test (description = "Purpose to Test Workout Maximum Data")
	public void VerifyingMinimumValue_24Hrs()
	{
		Minimum_Data = new HashMap<String, String>();
        // Mapping string values to int keys
		Minimum_Data.put("HR", "80");
		Minimum_Data.put("BR", "0");
		Minimum_Data.put("HeartStrain", "0");
	//	if (EneEnv.contains("prod"))
//			{
//				Maximum_Data.put("HeartStrain", "0");
//			}
//			else
//			{
//				Maximum_Data.put("HeartStrain", "-0.06");
//			}
		Minimum_Data.put("HRV", "0");
		Minimum_Data.put("Stress","47");
		
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj.ClickOnMinTab();
		justSleepFor(6);
		
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String Stress = getStressValue();
		
		Assert.assertEquals(HR, Minimum_Data.get("HR"),"Actual HR is"+HR+" Expected HR is "+Minimum_Data.get("HR"));
		Assert.assertEquals(BR, Minimum_Data.get("BR"),"Actual BR is"+BR+" Expected BR is "+Minimum_Data.get("BR"));
		Assert.assertEquals(HeartStrain, Minimum_Data.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Minimum_Data.get("HeartStrain"));
		Assert.assertEquals(HRV, Minimum_Data.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Minimum_Data.get("HRV"));
		Assert.assertEquals(Stress, Minimum_Data.get("Stress"),"Actual Stress is"+Stress+"Expected Stress is"+Minimum_Data.get("Stress"));
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone1 in Workout page")
	public void Verify_HeartRateZone1_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone1 Duration Is:"+workoutObj.getHRZoneDuration(1));
	System.out.println("Expected Zone1 Duration Is:"+Zone1_Duration);
	Assert.assertEquals(workoutObj.getHRZoneDuration(1),Zone1_Duration);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone2 in Workout page")
	public void Verify_HeartRateZone2_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone2 Duration Is:"+workoutObj.getHRZoneDuration(2));
	System.out.println("Expected Zone2 Duration Is:"+Zone2_Duration);
	Assert.assertEquals(workoutObj.getHRZoneDuration(2),Zone2_Duration);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone3 in Workout page")
	public void Verify_HeartRateZone3_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone3 Duration Is:"+workoutObj.getHRZoneDuration(3));
	System.out.println("Expected Zone3 Duration Is:"+Zone3_Duration);
	Assert.assertEquals(workoutObj.getHRZoneDuration(3),Zone3_Duration);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone4 in Workout page")
	public void Verify_HeartRateZone4_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone4 Duration Is:"+workoutObj.getHRZoneDuration(4));
	System.out.println("Expected Zone4 Duration Is:"+Zone4_Duration);
	Assert.assertEquals(workoutObj.getHRZoneDuration(4),Zone4_Duration);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone5 in Workout page")
	public void Verify_HeartRateZone5_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone5 Duration Is:"+workoutObj.getHRZoneDuration(5));
	System.out.println("Expected Zone5 Duration Is:"+Zone5_Duration);
	Assert.assertEquals(workoutObj.getHRZoneDuration(5),Zone5_Duration);
	}
	
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone1 Percentage in Workout page")
	public void Verify_HeartRateZonePercentage1_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone1 Percentage Is:"+workoutObj.getHRZonePercentage(1));
	System.out.println("Expected Zone1 Percentage Is:"+Zone1_Percentage);
	Assert.assertEquals(workoutObj.getHRZonePercentage(1),Zone1_Percentage);
	}

	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone2 Percentage in Workout page")
	public void Verify_HeartRateZonePercentage2_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone2 Percentage Is:"+workoutObj.getHRZonePercentage(2));
	System.out.println("Expected Zone2 Percentage Is:"+Zone2_Percentage);
	Assert.assertEquals(workoutObj.getHRZonePercentage(2),Zone2_Percentage);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone3 Percentage in Workout page")
	public void Verify_HeartRateZonePercentage3_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone3 Percentage Is:"+workoutObj.getHRZonePercentage(3));
	System.out.println("Expected Zone3 Percentage Is:"+Zone3_Percentage);
	Assert.assertEquals(workoutObj.getHRZonePercentage(3),Zone3_Percentage);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone4 Percentage in Workout page")
	public void Verify_HeartRateZonePercentage4_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone4 Percentage Is:"+workoutObj.getHRZonePercentage(4));
	System.out.println("Expected Zone4 Percentage Is:"+Zone4_Percentage);
	Assert.assertEquals(workoutObj.getHRZonePercentage(4),Zone4_Percentage);
	}
	
	@Test (description = "Purpose of this test is to Verfiy Heart Rate Zone5 Percentage in Workout page")
	public void Verify_HeartRateZonePercentage5_() throws InterruptedException
	{
	driver.findElement(By.className("w-title")).click();
	MilesUtilities.SwitchTab(1,driver);
	workoutObj = new WorkoutDetailsPageLib(driver);
	workoutObj.WaitForWorkoutDetailsPageToLoad();
	ScrollToHeartRateZones();
	
	System.out.println("Actual Zone5 Percentage Is:"+workoutObj.getHRZonePercentage(5));
	System.out.println("Expected Zone5 Percentage Is:"+Zone5_Percentage);
	Assert.assertEquals(workoutObj.getHRZonePercentage(5),Zone5_Percentage);
	}
	
//	////@Test (description = "Purpose to Test verifiy Activity Notes is Visible")
//	public void VerifyActivityNotes_24Hrs()
//	{
//		homeObj.OpenFirstActivity();
//		WaitForWorkoutDetailsPagetoLoad();
//		ScrollToActitivyNotes();
//		Assert.assertTrue(driver.findElements((By.xpath("//*[contains(@class, 'activity-notes')]"))).get(0).isDisplayed(),"Activity text area");
//		
//	}
//	
//	////@Test (description = "Purpose to Test verifiy Activity Notes Screen")
//	public void VerifyActivityNotesScreen_24Hrs()
//	{
//		homeObj.OpenFirstActivity();
//		WaitForWorkoutDetailsPagetoLoad();
//		ScrollToActitivyNotes();
//		driver.findElement(By.className("add-notes")).click();
//		Assert.assertTrue(driver.findElements(By.className("form-modal")).get(0).isDisplayed(),"Activity section displayed");
//		Assert.assertFalse(driver.findElements((By.xpath("//*[contains(@class, 'btn save-button')]"))).get(0).isEnabled(),"Save button is disabled");
//	}
//	
//	////@Test (description  = "Purpose to Test Add Activity Notes")
//	public void VerifyAddActivityNotes_24Hrs() throws InterruptedException
//	{
//		homeObj.OpenFirstActivity();
//		WaitForWorkoutDetailsPagetoLoad();
//		Thread.sleep(2000);
//		workoutObj.EnterNote(ActivityNotes);
//		System.out.println("Added notes is "+driver.findElement(By.className("activity-notes")).getText());
//	}
//	
// (description = "Purpose to Verify Added Activity Note in Home Page")
//	public void VerifyAddedActivityNote_24Hrs()
//	{
//		Assert.assertEquals(driver.findElement(By.className("workout_notes")).getText(), ActivityNotes);	
//	}
	
	
	/*
	 * Helper Methods
	 */

	public String getHRZonePercentage(int zone) throws InterruptedException
	{
		Thread.sleep(2000);
		return driver.findElements(By.xpath("//*[contains(@class,'hr-zone-item-right percent')]")).get(zone-1).getText();
		
	}
	
	//protected List <WebElement>HRZonePercentage = driver.findElements(By.xpath("//*[contains(@class,'hr-zone-item-right percent')]"));
	
	public void ScrollToHeartRateZones()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'hr-zone-distribution')]")))).perform();
		//driver.findElement(By.className("fa fa-plus")).click();
	}
	
	public void ScrollToActitivyNotes()
	{
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement((By.xpath("//*[contains(@class, 'activity-notes')]")))).perform();
		
	}
	protected void WaitForWorkoutDetailsPagetoLoad()
	{
		MilesUtilities.SwitchTab(1, driver);
		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
		workoutObj = new WorkoutDetailsPageLib(driver);
	}
	
	
	protected String getStressValue()
	{
		return  driver.findElement(By.id("wd-stress-value")).getText();
	}
	
	protected void RestIcon()
	{		
		List <WebElement> ActivityContainer = driver.findElements((By.xpath("//*[contains(@class, 'row w-list')]")));	
		WebElement IconContainer = ActivityContainer.get(0).findElement((By.xpath("//*[contains(@class, 'col-10 w-name-info col-lg-3')]")));
		
		try
		{
			WebElement IconRest = IconContainer.findElement(By.className("icon-rest"));
			Assert.assertTrue(IconRest.isDisplayed());
		}
		catch (Exception e)
		{
			Assert.fail("Rest icon is not diaplyed for the activity ->" +e.getMessage());
		}
	}

}
	
//Adding comment to check the source treess///


