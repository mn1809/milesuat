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
public class FxSleepActivity extends MilesSettings 
{	
	 WebDriver driver = null ;
	 LoginPageLib loginObj ;
	 HomePageLib homeObj ;
	 WorkoutDetailsPageLib workoutObj ;
	 HashMap<String, String> Average_Value;
	 HashMap<String, String> Maximum_Value;
	 HashMap<String, String> Minimum_Value;
	 LocalDate currentDate = LocalDate.now();
	 Locale locale = Locale.getDefault();
	 String currentMonthAsString = currentDate.getMonth().getDisplayName(
             TextStyle.FULL, 
             Locale.getDefault()
     );		
				
		String weekAbbreviation = currentDate.format(DateTimeFormatter.ofPattern("Eee", locale)).substring(0, 3);
   		String CurrentMonth = MilesUtilities.GetShortFormOfMonth(currentMonthAsString.toUpperCase());
		int currentDate1 = currentDate.getDayOfMonth();
		String ExpectedActivityName = "Sleep on "+ weekAbbreviation+", "+ CurrentMonth+" "+currentDate1+" at 02:35 AM";
		String ClassName = this.getClass().getSimpleName().toString();
		String WorkoutDuration = "08:08:46";
		String HomeTrainingLoad = "0";
		String HomeMaxStrain = "0.03";
		//String NormalRythemValue = "Noise: 1.46%";
		String NormalRythemValue = "Normal: 99.24%";
		String ExpectedEditedActivityName = "Cycle on "+ weekAbbreviation+", "+ CurrentMonth+" "+currentDate1+" at 06:05 AM";
	//---------------------------------BELOW DETAILS ARE INDIVIUAL WORKOUT DETAILS-------------------//	
		String ExpectedWorkoutTrainingload = "0";
		String ExpectedWorkoutKCAL = "503";
		
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
			 {								//PROD//
				 homeObj = loginObj.login("quality+sleepProd@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
				 System.out.println("Logging in as Quality : Serverless Production user");
			 }
			 
			 else
			 {   					//STAGE//
				 homeObj = loginObj.login("quality+sleep@fourthfrontier.com",MilesUtilities.DecryptPass("MTIzNDEyMzQ="));
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
	public void VerifyActivitySync_Sleep() throws Exception
	{			
			
		homeObj.VerifyTodaysActivityIsDisplayed(ExpectedActivityName);		
	}
	
	@Test(description = "Purpose of this to check Sleep Icon")
	public void VerifyIcon_Sleep()
	{	
		SleepIcon();
	}
	
	@Test(description = "Purpose of this to check ECG Has Green Tick")
	public void VerifyECGTick_Sleep()
	{
		homeObj.VerifyTodaysECGIsSynced(ExpectedActivityName);
	}
	
	@Test(description = "Purpose of this to check 1st Activity Duration")
	public void VerifyActivityDuration_Sleep()
	{
		homeObj.VerifyTodaysActivityDuration(WorkoutDuration,ExpectedActivityName);
	}
	@Test(description = "Purpose of this to check 1st Activity Training Load")
	public void VerifyTrainingLoad_Sleep()
	{
		homeObj.VerifyTodaysTrainingLoad(ExpectedActivityName,HomeTrainingLoad);
	}
	
	@Test (description = "purpose of this to check 1st Activity MaxStrain")
	public void VerifyStrain_Sleep()
	{
		homeObj.VerifyTodaysMaxStrain(HomeMaxStrain,ExpectedActivityName);
	}

	
	@Test (description = "Open 1st Activity")
	public void VerifyOpen1stActivity_Sleep()
	{
		homeObj.VerifyOpen1stActivity();	
	}
	
	
	
	@Test (description = "Purpose to Test Workout Average Value")
	public void VerifyingAverageValue_Sleep() throws InterruptedException
	{
		Average_Value = new HashMap<String, String>();
        // Mapping string values to int keys
		Average_Value.put("HR", "65");
		Average_Value.put("BR", "13");
		Average_Value.put("HeartStrain", "0.01");
		Average_Value.put("HRV", "18");		
		Average_Value.put("Stress","40");
 	
        homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		justSleepFor(6);
		
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String Stress = workoutObj.getStressValue();
		        
        Assert.assertEquals(HR, Average_Value.get("HR"),"Actual HR is"+HR+" Expected HR is "+Average_Value.get("HR"));
		Assert.assertEquals(BR, Average_Value.get("BR"),"Actual BR is"+BR+" Expected BR is "+Average_Value.get("BR"));
		Assert.assertEquals(HeartStrain, Average_Value.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Average_Value.get("HeartStrain"));
		Assert.assertEquals(HRV, Average_Value.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Average_Value.get("HRV"));
		Assert.assertEquals(Stress, Average_Value.get("Stress"),"Actual Stress is "+Stress+" Expected Stress is "+Average_Value.get("Stress"));
		
	}
	
	@Test (description = "Purpose to Test Workout Maximum Data")
	public void VerifyingMaximumValue_Sleep()
	{
		Maximum_Value = new HashMap<String, String>();
        // Mapping string values to int keys
		Maximum_Value.put("HR", "82");
		Maximum_Value.put("BR", "21");
		Maximum_Value.put("HeartStrain", "0.03");
		Maximum_Value.put("HRV", "89");
		Maximum_Value.put("Stress","76");
 	
        homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj.ClickOnMaxTab();
		justSleepFor(6);
		
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String Stress = workoutObj.getStressValue();
		
        Assert.assertEquals(HR, Maximum_Value.get("HR"),"Actual HR is"+HR+" Expected HR is "+Maximum_Value.get("HR"));
		Assert.assertEquals(BR, Maximum_Value.get("BR"),"Actual BR is"+BR+" Expected BR is "+Maximum_Value.get("BR"));
		Assert.assertEquals(HeartStrain, Maximum_Value.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Maximum_Value.get("HeartStrain"));
		Assert.assertEquals(HRV, Maximum_Value.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Maximum_Value.get("HRV"));
		Assert.assertEquals(Stress, Maximum_Value.get("Stress"),"Actual Stress is"+Stress+" Expected Stress is "+Maximum_Value.get("Stress"));
			
	}
	
	@Test (description = "Purpose to Test Workout Maximum Data")
	public void VerifyingMinimumValue_Sleep() throws InterruptedException
	{
		Minimum_Value = new HashMap<String, String>();
        // Mapping string values to int keys
		Minimum_Value.put("HR", "52");
		Minimum_Value.put("BR", "11");
		Minimum_Value.put("HeartStrain", "0");
		Minimum_Value.put("HRV", "4");
		Minimum_Value.put("Stress","8");
		
		homeObj.OpenFirstActivity();
		WaitForWorkoutDetailsPagetoLoad();
		workoutObj.ClickOnMinTab();
		justSleepFor(6);
		
		String HR = workoutObj.getHeartRateValue();
		String BR = workoutObj.getBRValue();
		String HeartStrain = workoutObj.getStrainValue();
		String HRV = workoutObj.getHRVValue();
		String Stress = workoutObj.getStressValue();
				
		Assert.assertEquals(HR, Minimum_Value.get("HR"),"Actual HR is"+HR+" Expected HR is "+Minimum_Value.get("HR"));
		Assert.assertEquals(BR, Minimum_Value.get("BR"),"Actual BR is"+BR+" Expected BR is "+Minimum_Value.get("BR"));
		Assert.assertEquals(HeartStrain, Minimum_Value.get("HeartStrain"),"Actual HeartStrain is"+HeartStrain+" Expected HeartStrain is "+Minimum_Value.get("HeartStrain"));
		Assert.assertEquals(HRV, Minimum_Value.get("HRV"),"Actual HRV is"+HRV+" Expected HRV is "+Minimum_Value.get("HRV"));
		Assert.assertEquals(Stress, Minimum_Value.get("Stress"),"Actual Stress is"+Stress+" Expected Stress is "+Minimum_Value.get("Stress"));
		
	}
	
	/*
	 * Helper Methods 
	 */

	protected void WaitForWorkoutDetailsPagetoLoad()
	{
		MilesUtilities.SwitchTab(1, driver);
		MilesUtilities.WaitForVisibilityOfElement(driver, "id", "wd-heart-rate-value");
		 workoutObj = new WorkoutDetailsPageLib(driver);
	}
		
	protected void SleepIcon()
	{		
		List <WebElement> ActivityContainer = driver.findElements((By.xpath("//*[contains(@class, 'row w-list')]")));	
		WebElement IconContainer = ActivityContainer.get(0).findElement((By.xpath("//*[contains(@class, 'col-10 w-name-info col-lg-3')]")));
		
		try
		{
			WebElement IconSleep = IconContainer.findElement(By.className("icon-sleep"));
			Assert.assertTrue(IconSleep.isDisplayed());
		}
		catch (Exception e)
		{
			Assert.fail("Sleep icon is not diaplyed for the activity ->" +e.getMessage());
		}
	}

}
	



